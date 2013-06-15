package com.example.nostalgiaqrcodereader;



import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class NostalgiaQRCode extends Activity {

	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_nostalgia_qrcode);
		
		 intent = new Intent("com.google.zxing.client.android.SCAN");
		 intent.setPackage("com.google.zxing.client.android");
		 intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
		intent.putExtra("SCAN_WIDTH", 1280);
		intent.putExtra("SCAN_HEIGHT", 768);
		intent.putExtra("SAVE_HISTORY",false);
		startActivityForResult(intent, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nostalgia_qrcode, menu);
		return true;
	}
	public void scanNow(View view)
	{
		Log.d("test", "button works!");
		
		List<PackageInfo> packagess = getPackageManager().getInstalledPackages(0);
		for(int i=0;i<packagess.size();i++) {
		    PackageInfo pack = packagess.get(i);
		    if ((pack.versionName == null)) {
		        continue ;
		    }

		  //this is the application name
		    Log.d("NAme: ",pack.applicationInfo.loadLabel(getPackageManager()).toString());

		  //this is the package name
		    Log.d("Package name",pack.packageName);
		    

		}
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) 
	{
		Log.d("Passing","Once");
	    if (requestCode == 0) 
	    {
	        if (resultCode == RESULT_OK)
	        {
	            String contents = intent.getStringExtra("SCAN_RESULT");
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            Log.i("xZing", "contents: "+contents+" format: "+format);
	           
                /* Insert ip of machine running the Openframeworks application */
	            new Thread(new Client(contents,"192.186.1.1")).start();// Handle successful scan
	          	            
	        	startActivityForResult(intent, 0);
	            
	        } 
	        else if (resultCode == RESULT_CANCELED)
	        {
	            // Handle cancel
	            Log.i("xZing", "Cancelled");
	        }
	    }
	}
	
}
