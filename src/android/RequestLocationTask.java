package com.eteng.mobile.plugin.geolocation.baidu;

import java.util.Stack;

import org.apache.cordova.CallbackContext;

import android.util.Log;

import com.baidu.location.LocationClient;

public class RequestLocationTask implements Runnable {
	
	private static final String TAG = "RequestLocationTask";
	
	public static final int RESULT_SUCCESS = 0;
	public static final int RESULT_SERVICE_STOPED = 1;
	public static final int RESULT_NO_LISTENER = 2;
	public static final int RESULT_TOO_FAST = 6;

	CallbackContext callback;
	LocationClient client;
	Stack<CallbackContext> stack;
	
	RequestLocationTask(LocationClient client, Stack<CallbackContext> stack, CallbackContext callback) {
		this.client = client;
		this.stack = stack;
		this.callback = callback;
	}

	@Override
	public void run() {
		int result = client.requestLocation();
		Log.i(TAG, "result: " + result);
		if (RESULT_SUCCESS == result) {
			stack.push(callback);
			return;
		}
		
		callback.error(result);
	}

}