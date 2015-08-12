package com.eteng.geolocation.baidu;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;

import android.util.Log;

public class CDVLocationListener implements BDLocationListener {
	
	private CallbackContext callback;
	private String TAG = "CDVLocationListener"; 
	private LocationClient client;
	
	CDVLocationListener(LocationClient client, CallbackContext callback) {
		this.client = client;
		this.callback = callback;
	}

	@Override
	public void onReceiveLocation(BDLocation position) {
		client.unRegisterLocationListener(this);
		client.stop();
		
		Log.i(TAG, "地理位置更新");
		JSONArray reply = new MessageBuilder(position).build();
		Log.i(TAG, "reply: " + reply);
		callback.success(reply);
	}

}
