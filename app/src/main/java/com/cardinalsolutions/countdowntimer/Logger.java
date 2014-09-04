package com.cardinalsolutions.countdowntimer;

import android.util.Log;

/**
 * Countdown Timer
 *
 * Logger is a utility class that allows the developer to turn off logging by setting the LOGGING
 * flag to false prior to generating an APK.
 *
 * @author Shane King
 *         28 Aug 2014
 *         10:00
 */
public class Logger {

	private static final boolean LOGGING = true;
	private static final String TAG = "Countdown Timer: ";

	public static void i(String className, String message) {
		if (LOGGING) {
			Log.i(TAG, className + " - " + message);
		}
	}

	public static void d(String className, String message) {
		if (LOGGING) {
			Log.d(TAG, className + " - " + message);
		}
	}

	public static void v(String className, String message) {
		if (LOGGING) {
			Log.v(TAG, className + " - " + message);
		}
	}

	public static void e(String className, String message, Exception e) {
		if (LOGGING) {
			Log.e(TAG, className + " - " + message, e);
		}
	}

}
