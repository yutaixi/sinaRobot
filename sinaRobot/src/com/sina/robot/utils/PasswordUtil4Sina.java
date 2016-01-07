package com.sina.robot.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class PasswordUtil4Sina {
	public static Invocable invoke = null;
	
	public static Invocable regInvoke = null;
	static {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine jsEngine = manager.getEngineByName("javascript");
		// String jsFileName = "resources.qq/password.js"; // 指定md5加密文件
		String jsFileName = "password4Sina.js"; // 指定md5加密文件
		Reader reader;
		try {
			InputStream in = PasswordUtil4Sina.class.getClassLoader()
					.getResourceAsStream(jsFileName);
			reader = new InputStreamReader(in);
			jsEngine.eval(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		invoke = (Invocable) jsEngine;

	}
	
	static {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine jsEngine = manager.getEngineByName("javascript");
		// String jsFileName = "resources.qq/password.js"; // 指定md5加密文件
		String jsFileName = "registerSSO.js"; // 指定md5加密文件
		Reader reader;
		try {
			InputStream in = PasswordUtil4Sina.class.getClassLoader()
					.getResourceAsStream(jsFileName);
			reader = new InputStreamReader(in);
			jsEngine.eval(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		regInvoke = (Invocable) jsEngine;

	}

	public static String getPassEncoding(String pubKey, String serverTime,
			String nonce,String password) {
		String pass = null;
		try {
			pass = (String) invoke.invokeFunction("getPassEncoding", new Object[] {
					pubKey, serverTime,nonce,password});
		} catch (Exception e) {
			System.out.println("为登陆密码加密时，出现异常!");
			e.printStackTrace();
		}
		return pass;
	}
	
	public static String getRegPassEncoding(String pubKey, String serverTime,
			String nonce,String password) {
		String pass = null;
		try {
			pass = (String) regInvoke.invokeFunction("getPassEncoding", new Object[] {
					pubKey, serverTime,nonce,password});
		} catch (Exception e) {
			System.out.println("为登陆密码加密时，出现异常!");
			e.printStackTrace();
		}
		return pass;
	}
//
//	public static String getHexString(String hexCode) {
//		String pass = null;
//		try {
//			pass = (String) invoke.invokeFunction("hexchar2bin",
//					new Object[] { hexCode });
//		} catch (Exception e) {
//			System.out.println("将字符串转化为16进制时，出现异常!");
//			e.printStackTrace();
//		}
//		return pass;
//	}
	public static void main(String[] args) {
		
	String pass=PasswordUtil4Sina.getRegPassEncoding("EB2A38568661887FA180BDDB5CABD5F21C7BFD59C090CB2D245A87AC253062882729293E5506350508E7F9AA3BB77F4333231490F915F6D63C55FE2F08A49B353F444AD3993CACC02DB784ABBB8E42A9B1BBFFFB38BE18D78E87A0E41B9B8F73A928EE0CCEE1F6739884B9777E4FE9E88A1BBE495927AC4A799B3181D6442443", 
			"1438869592", "V0NLJP", "1937294381");
	System.out.println(pass);
	}

}
