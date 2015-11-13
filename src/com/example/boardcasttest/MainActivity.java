package com.example.boardcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private IntentFilter intentFilter;
	private NetworkChangeReceiver networkChangeReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		intentFilter=new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		networkChangeReceiver=new NetworkChangeReceiver();
		registerReceiver(networkChangeReceiver, intentFilter);
		
		Button btn=(Button) findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent("com.example.boardcasttest.My_BROADCAST");
				sendBroadcast(intent);
			}
		});
	}
	
	//ÃÌº”¡À◊¢ Õ
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(networkChangeReceiver);
	}

	class NetworkChangeReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			ConnectivityManager connectionManager=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo=connectionManager.getActiveNetworkInfo();
			if(networkInfo!=null && networkInfo.isAvailable()){
				Toast.makeText(context, "network WIFI is available", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(context, "network WIFI is unavailable", Toast.LENGTH_LONG).show();
			}
		}
	}
}
