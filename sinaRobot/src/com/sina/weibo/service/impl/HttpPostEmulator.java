package com.sina.weibo.service.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpPostEmulator {
	// 每个post参数之间的分隔。随意设定，只要不会和其他的字符串重复即可。
	private static final String BOUNDARY = "----------HV2ymHFg03ehbqgZCaKO6jyH";

	public String sendHttpPostRequest(String serverUrl,
			ArrayList<FormFieldKeyValuePair> generalFormFields,
			ArrayList<UploadFileItem> filesToBeUploaded) throws Exception {

		// 向服务器发送post请求

		URL url = new URL(serverUrl/* "http://127.0.0.1:8080/test/upload" */);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 发送POST请求必须设置如下两行

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("Charset", "UTF-8");
		connection.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + BOUNDARY);
		connection.setRequestProperty("Cookie", "SINAGLOBAL=9070502941031.01.1438790682709; _s_tentry=login.sina.com.cn; Apache=1462707421742.3796.1439193339134; ULV=1439193339172:8:8:7:1462707421742.3796.1439193339134:1439178337888; myuid=5645930573; login_sid_t=9f437141499ca4fcf02a04bbaab45854; UOR=,,login.sina.com.cn; SUS=SID-5645930573-1439194917-GZ-t28wd-b9669012674dd7b109d39abf1884a70b; SUE=es%3D881066adef039cce683c2074c6cbb0c2%26ev%3Dv1%26es2%3D637a50cc79a76646e710a6b3ffa9a22f%26rs0%3DT6MS2NTo6r3lziiIgVSu9Hxk1Y3zOFlZy4rS9xRVj0bdLk8lsmS%252F98vC%252Bzoa4CZumN5pM5ZB%252FLdjjsadDtbPt0MJpv7eM1G4EfbnGXIniMfgcEs8lrfEj%252FyzejWNfoPaUs7%252B%252BYtGJLxpR62KvSsqnrU1e2nU3YJp8qldZUS8mJQ%253D%26rv%3D0; SUP=cv%3D1%26bt%3D1439194917%26et%3D1439281317%26d%3Dc909%26i%3Da70b%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D0%26st%3D0%26uid%3D5645930573%26name%3Ddageboclub%2540sina.com%26nick%3D%25E7%2594%25A8%25E6%2588%25B75645930573%26fmp%3D%26lcp%3D; SUB=_2A254zC91DeTxGeNI71cY8y7JzD-IHXVbuAe9rDV8PUNbuNBeLWbRkW8fvqBjgqjklTiAii-gPnHTEvugHQ..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9W5VgPKUmyZQqL-aaF-uShg85JpX5K2t; SUHB=0102ypH-TVM2r3; ALF=1439799718; SSOLoginState=1439194917; un=dageboclub@sina.com");
		
 
		
		
		
		// 头

		String boundary = BOUNDARY;

		// 传输内容

		StringBuffer contentBody = new StringBuffer("--" + BOUNDARY);

		// 尾

		String endBoundary = "\r\n--" + boundary + "--\r\n";

		OutputStream out = connection.getOutputStream();

		// 1. 处理文字形式的POST请求

		for (FormFieldKeyValuePair ffkvp : generalFormFields)

		{

			contentBody.append("\r\n")

			.append("Content-Disposition: form-data; name=\"")

			.append(ffkvp.getKey() + "\"")

			.append("\r\n")

			.append("\r\n")

			.append(ffkvp.getValue())

			.append("\r\n")

			.append("--")

			.append(boundary);

		}

		String boundaryMessage1 = contentBody.toString();

		out.write(boundaryMessage1.getBytes("utf-8"));

		// 2. 处理文件上传

		for (UploadFileItem ufi : filesToBeUploaded)

		{

			contentBody = new StringBuffer();

			contentBody.append("\r\n")

			.append("Content-Disposition:form-data; name=\"")

			.append(ufi.getFormFieldName() + "\"; ") // form中field的名称

					.append("filename=\"")

					.append(ufi.getFileName() + "\"") // 上传文件的文件名，包括目录

					.append("\r\n")

					.append("Content-Type:application/octet-stream")

					.append("\r\n\r\n");

			String boundaryMessage2 = contentBody.toString();

			out.write(boundaryMessage2.getBytes("utf-8"));

			// 开始真正向服务器写文件

			File file = new File(ufi.getFileName());

			DataInputStream dis = new DataInputStream(new FileInputStream(file));

			int bytes = 0;

			byte[] bufferOut = new byte[(int) file.length()];

			bytes = dis.read(bufferOut);

			out.write(bufferOut, 0, bytes);

			dis.close();

			contentBody.append("------------HV2ymHFg03ehbqgZCaKO6jyH");

			String boundaryMessage = contentBody.toString();

			out.write(boundaryMessage.getBytes("utf-8"));

			// System.out.println(boundaryMessage);

		}

		out.write("------------HV2ymHFg03ehbqgZCaKO6jyH--\r\n"
				.getBytes("UTF-8"));

		// 3. 写结尾

		out.write(endBoundary.getBytes("utf-8"));

		out.flush();

		out.close();

		// 4. 从服务器获得回答的内容

		String strLine = "";

		String strResponse = "";

		InputStream in = connection.getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		while ((strLine = reader.readLine()) != null)

		{

			strResponse += strLine + "\n";

		}

		 System.out.print(strResponse);

		return strResponse;

	}

}