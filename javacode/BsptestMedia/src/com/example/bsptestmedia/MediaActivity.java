package com.example.bsptestmedia;

import java.io.File;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MediaActivity extends Activity
{
	private static final String ACTIVITY_TAG="LogDemo"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media);
		
	}
	
   private BroadcastReceiver receiver = new BroadcastReceiver() {  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            String action = intent.getAction(); 
            Uri uri = intent.getData();
            if (action.equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)) { 
            	Log.v(MediaActivity.ACTIVITY_TAG,"BsptestActivity-------------------->"+action);
            	Log.v(MediaActivity.ACTIVITY_TAG,"BsptestActivity-------------------->"+uri);
            } 
            else if (action.equals(Intent.ACTION_MEDIA_SCANNER_STARTED)) {   
            	Log.v(MediaActivity.ACTIVITY_TAG,"BsptestActivity------------------->"+action);
            	Log.v(MediaActivity.ACTIVITY_TAG,"BsptestActivity-------------------->"+uri);
            }  
        }
 
    };
    protected void onResume() {  
        super.onResume();  
        //创建IntentFilter，加入扫描开始和结束的动作  
        IntentFilter filter = new IntentFilter();  
        filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);  
        filter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
        filter.addDataScheme("file"); 
        //注册BroadcastReceiver  
        registerReceiver(receiver, filter);  

    } 
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.media, menu);
		return true;
	}

}
