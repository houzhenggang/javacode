package com.example.bsptestactivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public abstract class MainActivity extends Service {

    private boolean threadDisable;
    private static final String ACTIVITY_TAG="LogDemo"; 
    private int count;

    /*private BroadcastReceiver receiver = new BroadcastReceiver() {  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            String action = intent.getAction(); 
            Uri uri = intent.getData();
            if (action.equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)) { 
            	Log.v(MainActivity.ACTIVITY_TAG,"BsptestActivity-------------------->"+action);
            	Log.v(MainActivity.ACTIVITY_TAG,"BsptestActivity-------------------->"+uri);
            } 
            else if (action.equals(Intent.ACTION_MEDIA_SCANNER_STARTED)) {   
            	Log.v(MainActivity.ACTIVITY_TAG,"BsptestActivity------------------->"+action);
            	Log.v(MainActivity.ACTIVITY_TAG,"BsptestActivity-------------------->"+uri);
            }  
        }
    };*/
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BroadcastReceiver receiver = new BroadcastReceiver() {  
            @Override  
            public void onReceive(Context context, Intent intent) {  
                String action = intent.getAction(); 
                Uri uri = intent.getData();
                if (action.equals(Intent.ACTION_MEDIA_SCANNER_FINISHED)) { 
                	Log.v(MainActivity.ACTIVITY_TAG,"BsptestActivity-------------------->"+action);
                	Log.v(MainActivity.ACTIVITY_TAG,"BsptestActivity-------------------->"+uri);
                } 
                else if (action.equals(Intent.ACTION_MEDIA_SCANNER_STARTED)) {   
                	Log.v(MainActivity.ACTIVITY_TAG,"BsptestActivity------------------->"+action);
                	Log.v(MainActivity.ACTIVITY_TAG,"BsptestActivity-------------------->"+uri);
                }  
            }
        };
        
        
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        this.threadDisable = true;
        Log.v("CountService", "on destroy");
    }*/

    public int getCount() {
        return count;
    }

}