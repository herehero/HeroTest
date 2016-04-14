package com.example.herotest.utils;

import android.util.Log;

public class LogMgr {
	public static void d(String tag, String message) {
		Log.d(tag, message);
	}

	public static void d(String message) {
		StackTraceElement stackTraceElement=Thread.currentThread().getStackTrace()[3];
		Log.d(getDefaultTag(stackTraceElement),getLogInfo(stackTraceElement)+ message);
	}

	public static void e(String tag, String message) {
		Log.e(tag, message);
	}

	public static void e(String message) {
		StackTraceElement stackTraceElement=Thread.currentThread().getStackTrace()[3];
		Log.e(getDefaultTag(stackTraceElement),getLogInfo(stackTraceElement)+message);
	}
	
	
	
    public static String getDefaultTag(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        String stringArray[] = fileName.split("\\.");
        String tag = stringArray[0];
        return "hero-" + tag;
    }
    public static String getLogInfo(StackTraceElement stackTraceElement) {
        StringBuilder logInfoStringBuilder = new StringBuilder();
        String methodName = stackTraceElement.getMethodName();
        // code line
        int lineNumber = stackTraceElement.getLineNumber();

        logInfoStringBuilder.append("[ ");
        logInfoStringBuilder.append("lineNumber =" + lineNumber);
        logInfoStringBuilder.append("]");
        methodName = logInfoStringBuilder.toString();
        logInfoStringBuilder = null;
        return methodName;
    }
	
/*	private static String getClassName(StackTraceElement stackTraceElement){
		String FileName=stackTraceElement.getClassName();
		//Log.e("LogMgr", "getClassName>>>FileName::"+FileName);
		String arrayString[]=FileName.split("\\.");
		String className=arrayString[arrayString.length-1];
		//Log.e("LogMgr", "getClassName>>>className::"+className);
		return className;
	}
	private static String getLogInfo(StackTraceElement stackTraceElement){
		String FileName=stackTraceElement.getClassName();
		String arrayString[]=FileName.split("\\.");
		String pakgeName=arrayString[arrayString.length-2];
		//Log.e("LogMgr", "getLogInfo>>>pakgeName::"+pakgeName);
		return pakgeName;
	}
		private static String getDefaultTag(StackTraceElement stackTraceElement){
			String FileName=stackTraceElement.getClassName();
			String arrayString[]=FileName.split("\\.");
			String applicationName=arrayString[2];
			//Log.e("LogMgr", "getLogInfo>>>pakgeName::"+pakgeName);
			return applicationName;
			
	}*/
	
}
