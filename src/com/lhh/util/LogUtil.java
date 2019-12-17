package com.lhh.util;

import android.util.Log;

public class LogUtil {
	
	private static String TAG = "lhh_log";
	private static boolean sIsDebug = true;
	
	public static void setTAG(String tag) {
		TAG = tag;
	}

	public static void setIsDebug(boolean isDebug) {
		sIsDebug = isDebug;
	}
	
	public static void i(String tag, String msg) {
		Log.i(TAG, tag + " : " + msg);
	}
	
	public static void e(String tag, String msg) {
		Log.e(TAG, tag + " : " + msg);
	}
	
	public static void e(String tag, String msg, Throwable tr) {
		Log.e(TAG, msg, tr);
	}
	
	public static void v(String tag, String msg) {
		Log.v(TAG, tag + " : " + msg);
	}
	
	public static void w(String tag, String msg) {
		Log.w(TAG, tag + " : " + msg);
	}
	
	public static void d(String tag, String msg) {
		if (sIsDebug) {
			Log.d(TAG, tag + " : " + msg);
		}
	}
	
}
