package com.gerry.medialogtest;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

/**
Gerry
 */

public class MediaLogActivity extends Activity {
	
	String TAG="GerryMediaTest";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        
        
    }
	
	
    public class ScanSdReceiver extends BroadcastReceiver  
    {  
        @Override    
        public void onReceive(Context context, Intent intent)  
        {  
            String action = intent.getAction();  
            if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(action))  
            {  
                Log.d(TAG, "GerryMediaTestStarttttttttttttttt");
            }  
            else if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action))  
            {  
            	 Log.d(TAG, "GerryMediaTestFinishhhhhhhhhhhhhhhhhhhhhhhh");
            }  
        }  
    }  
    
    
    protected void onResume() {  
        super.onResume();  
//        IntentFilter intentfilter = new IntentFilter(Intent.ACTION_MEDIA_SCANNER_STARTED);  
//        intentfilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);    
//        intentfilter.addDataScheme("file");    
//        ScanSdReceiver scanSdReceiver = new ScanSdReceiver();  
//        registerReceiver(scanSdReceiver, intentfilter);    

    } 
    

    

}
