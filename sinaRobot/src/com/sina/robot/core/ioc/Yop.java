package com.sina.robot.core.ioc;

public final class Yop {

	private static YopApplictionContext yopApplictionContext;
	private static String LOCAL_ADDRESS;
	private static String HOSTNAME;
    private static boolean loadJob=false;
    
	public static boolean isLoadJob() {
		return loadJob;
	}
    

	public static YopApplictionContext getContext() {
		return Yop.yopApplictionContext;
	}

	public static void setContext(YopApplictionContext yopApplictionContext) {
		Yop.yopApplictionContext = yopApplictionContext;
	}

	public static String getLocalAddress() {
		return LOCAL_ADDRESS;
	}

	public static void setLocalAddress(String lOCAL_ADDRESS) {
		LOCAL_ADDRESS = lOCAL_ADDRESS;
	}

	public static String getHostName() {
		return HOSTNAME;
	}

	public static void setHostName(String hOSTNAME) {
		HOSTNAME = hOSTNAME;
	}
	
	
	
}
