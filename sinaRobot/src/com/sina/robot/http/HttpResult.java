package com.sina.robot.http;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
 









import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 对HttpClient返回的结果进一步封装
 * @author bangis.wangdf
 *
 */
public class HttpResult {
     
    private static Logger LOG = LoggerFactory.getLogger(HttpResult.class);
     
    private static Pattern headerCharsetPattern = Pattern.compile(
            "charset=((gb2312)|(gbk)|(utf-8))", 2);
    private static Pattern pattern = Pattern
            .compile(
                    "<meta[^>]*content=(['\"])?[^>]*charset=((gb2312)|(gbk)|(utf-8))\\1[^>]*>",
                    2);
    private String headerCharset;
    private String headerContentType;
    private String headerContentEncoding;
    private List<Header> headers;
    private String metaCharset;
    private byte[] response;
    private String responseUrl;
    private int statuCode = -1;
    private static final int BUFFER_SIZE = 4096;
 
    public static HttpResult empty() {
        return new HttpResult();
    }
 
    public String getHeaderCharset() {
        return this.headerCharset;
    }
 
    public String getHeaderContentType() {
        return this.headerContentType;
    }
 
    public final List<Header> getHeaders() {
        return this.headers;
    }
 
    public String getHtml() {
        try {
            return getText();
        } catch (UnsupportedEncodingException e) {
            LOG.error("[AGDS-SPIDER]" + e.getMessage(), e);
        }
        return "";
    }
     
    public String getHtml(String encoding) {
        try {
            return getText(encoding);
        } catch (UnsupportedEncodingException e) {
            LOG.error("[AGDS-SPIDER]" + e.getMessage(), e);
        }
        return "";
    }
 
    public String getMetaCharset() {
        return this.metaCharset;
    }
 
    public byte[] getResponse() {
        return Arrays.copyOf(this.response, this.response.length);
    }
 
    public String getResponseUrl() {
        return this.responseUrl;
    }
 
    public int getStatuCode() {
        return this.statuCode;
    }
 
    public String getText() throws UnsupportedEncodingException {
        return getText("");
    }
 
    public String getText(String encoding) throws UnsupportedEncodingException {
        if (this.response == null){
            return "";
        }
        String encodingStr = encoding;
        if (StringUtils.isBlank(encoding)){
            encodingStr = this.metaCharset;
        }
 
        if (StringUtils.isBlank(encoding)){
            encodingStr = this.headerCharset;
        }
 
        if (StringUtils.isBlank(encoding)){
            encodingStr = "UTF-8";
        }
 
        return new String(this.response, encodingStr);
    }
 
    private String getCharsetFromMeta() {
        StringBuilder builder = new StringBuilder();
        String charset = "";
        for (int i = 0; (i < this.response.length) && ("".equals(charset)); ++i) {
            char c = (char) this.response[i];
            switch (c) {
            case '<':
                builder.delete(0, builder.length());
                builder.append(c);
                break;
            case '>':
                if (builder.length() > 0){
                    builder.append(c);
                }
                String meta = builder.toString();
 
                if (meta.toLowerCase().startsWith("<meta")){
                    charset = getCharsetFromMeta(meta);
                }
                break;
            case '=':
            default:
                if (builder.length() > 0){
                    builder.append(c);
                }
            }
 
        }
 
        return charset;
    }
 
    private String getCharsetFromMeta(String meta) {
        if (StringUtils.isBlank(meta)){
            return "";
        }
        Matcher m = pattern.matcher(meta);
        if (m.find()){
            return m.group(2);
        }
        return "";
    }
 
    private void getHttpHeaders(HttpResponse httpResponse) {
        String headerName = "";
        String headerValue = "";
        int index = -1;
 
        Header[] rspHeaders = httpResponse.getAllHeaders();
        for (int i = 0; i < rspHeaders.length; ++i) {
            Header header = rspHeaders[i];
            this.headers.add(header);
 
            headerName = header.getName();
            if ("Content-Type".equalsIgnoreCase(headerName)) {
                headerValue = header.getValue();
                index = headerValue.indexOf(';');
                if (index > 0){
                    this.headerContentType = headerValue.substring(0, index);
                }
                Matcher m = headerCharsetPattern.matcher(headerValue);
                if (m.find()){
                    this.headerCharset = m.group(1);
                }
            }
 
            if ("Content-Encoding".equalsIgnoreCase(headerName)){
                this.headerContentEncoding = header.getValue();
            }
        }
    }
 
    private void getResponseUrl(HttpContext httpContext) {
        HttpHost target = (HttpHost) httpContext
                .getAttribute("http.target_host");
 
        HttpUriRequest req = (HttpUriRequest) httpContext
                .getAttribute("http.request");
 
        this.responseUrl = target.toString() + req.getURI().toString();
    }
 
    public HttpResult(HttpContext httpContext, HttpResponse httpResponse) {
        this.headers = new ArrayList<Header>();
 
        this.statuCode = httpResponse.getStatusLine().getStatusCode();
 
        if (httpContext != null) {
            getResponseUrl(httpContext);
        }
 
        if (httpResponse != null) {
            getHttpHeaders(httpResponse);
            try {
                if (("gzip".equalsIgnoreCase(this.headerContentEncoding))
                        || ("deflate".equalsIgnoreCase(this.headerContentEncoding))) {
                    GZIPInputStream is = new GZIPInputStream(httpResponse.getEntity().getContent());
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int count = 0;
                    while ((count = is.read(buffer)) > 0){
                        os.write(buffer, 0, count);
                    }
                    this.response = os.toByteArray();
                    os.close();
                    is.close();
                }else{
                    this.response = EntityUtils.toByteArray(httpResponse.getEntity());
                }
            } catch (Exception e) {
                LOG.error("[AGDS-SPIDER]" + e.getMessage(), e);
            }
            if (this.response != null){
                this.metaCharset = getCharsetFromMeta();
            }
        }
    }
 
    private HttpResult() {
    }
}
