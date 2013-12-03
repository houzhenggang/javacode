package com.example.bsptestmedia;
import android.content.BroadcastReceiver;  
import android.content.Context;  
import android.content.Intent;  
import android.content.IntentFilter;
import android.net.Uri;
import android.util.Log;
public class BootBroadcastReceiver extends BroadcastReceiver {  
	  
	 static final String ACTION = "android.intent.action.BOOT_COMPLETED";  
	 private static final String ACTIVITY_TAG="LogDemo";
	 @Override  
	 public void onReceive(Context context, Intent intent) {  
		 String action = intent.getAction(); 
	     Uri uri = intent.getData(); 
	  if (intent.getAction().equals(ACTION)){  
	   Intent sayHelloIntent=new Intent(context,MediaActivity.class);  
	   sayHelloIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	  
	   context.startActivity(sayHelloIntent);  
	  }  
	   
	  } 
	 

	 }  
	  
	