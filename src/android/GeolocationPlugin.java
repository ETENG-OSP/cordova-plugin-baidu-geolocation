package com.eteng.geolocation.baidu;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class GeolocationPlugin extends CordovaPlugin {

	private static final String TAG = "BaiduGeoLocationPlugin";

	private static final String ACTION_GET_CURRENT_POSITION = "getCurrentPosition";
	private static final String ACTION_WATCH_POSITION = "watchPosition";
	private static final String ACTION_CLEAR_WATCH = "clearWatch";

	public static final String COORD_BD09LL = "bd09ll";
	public static final String COORD_BD09 = "bd09";
	public static final String COORD_GCJ02 = "gcj02";

//	private static final int SCAN_INTERVAL = 2000;

//	LocationClient client;
//	Stack<CallbackContext> callbackStack;
//	Stack<Pair<LocationClient, BDLocationListener>> watchClientStack;
//	LocationClientOption getOption;
//	LocationClientOption watchOption;

//	void initOptions() {
//		getOption = new LocationClientOption();
//		getOption.setLocationMode(LocationMode.Hight_Accuracy);
//		getOption.setCoorType(COORD_BD09LL);
//
//		watchOption = new LocationClientOption();
//		watchOption.setLocationMode(LocationMode.Hight_Accuracy);
//		watchOption.setCoorType(COORD_BD09LL);
//		watchOption.setScanSpan(SCAN_INTERVAL);
//	}

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);

//		Log.i(TAG, "插件初始化");
//		callbackStack = new Stack<CallbackContext>();
//		watchClientStack = new Stack<Pair<LocationClient, BDLocationListener>>();
//		initOptions();
//
//		Log.i(TAG, "启动客户端");
//		client = new LocationClient(cordova.getActivity().getApplicationContext());
//		client.setLocOption(getOption);
//		client.registerLocationListener(this);
//		client.start();
	}

//	@Override
//	public void onDestroy() {
//		Log.i(TAG, "插件销毁");
//		if (client == null || !client.isStarted()) {
//			Log.w(TAG, "客户端未初始化");
//			super.onDestroy();
//			return;
//		}
//		Log.i(TAG, "客户端停止");
//		client.stop();
//		client.unRegisterLocationListener(this);
//		client = null;
//		super.onDestroy();
//	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (ACTION_GET_CURRENT_POSITION.equals(action)) {
			return getCurrentPosition(callbackContext);
		} else if (ACTION_WATCH_POSITION.equals(action)) {
			return watchPosition(callbackContext);
		} else if (ACTION_CLEAR_WATCH.equals(action)) {
			return clearWatch();
		}
		return false;
	}

	private boolean clearWatch() {
		return false;
//		Log.i(TAG, "停止监视");
//		if (watchClientStack.isEmpty()) {
//			return false;
//		}
//
//		Pair<LocationClient, BDLocationListener> pair = watchClientStack.pop();
//		pair.first.stop();
//		pair.first.unRegisterLocationListener(pair.second);
//		return true;
	}

	private boolean watchPosition(CallbackContext callback) {
		return false;
//		Log.i(TAG, "持续监视位置改变");
//		LocationClient watchClient = new LocationClient(cordova.getActivity().getApplicationContext());
//		BDLocationListener watchListener = new WatchListener(callback);
//
//		watchClient.setLocOption(watchOption);
//		watchClient.registerLocationListener(watchListener);
//		watchClient.start();
//		watchClient.requestLocation();
//
//		Pair<LocationClient, BDLocationListener> pair = new Pair<LocationClient, BDLocationListener>(watchClient, watchListener);
//		watchClientStack.push(pair);
//		return true;
	}

	private boolean getCurrentPosition(CallbackContext callback) {
		Log.i(TAG, "请求当前地理位置");
		// cordova.getThreadPool().execute(new RequestLocationTask(client, callbackStack, callback));
		// public static final int RESULT_SUCCESS = 0;
		// public static final int RESULT_SERVICE_STOPED = 1;
		// public static final int RESULT_NO_LISTENER = 2;
		// public static final int RESULT_TOO_FAST = 6;

        
		CDVLocationListener listener = new CDVLocationListener(cordova.getActivity().getApplicationContext(), callback);
		listener.getCurrentPosition();
		return true;
	}

}
