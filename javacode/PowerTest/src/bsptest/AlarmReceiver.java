package bsptest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends Activity { 
	
	
	BroadcastReceiver receiver = new BroadcastReceiver() {  
        @Override  
        public void onReceive(Context context, Intent intent) {  
        	Looper.prepare(); 
        	 Toast.makeText(context, "alarm", Toast.LENGTH_SHORT).show(); 
        	 Looper.loop(); 
    	      
        }
 
    };

    protected void onResume() {  
        super.onResume();   
        //创建IntentFilter，加入扫描开始和结束的动作  
        IntentFilter filter = new IntentFilter();  
        filter.getAction(PendingIntent.FLAG_UPDATE_CURRENT);  
        filter.addDataScheme("file"); 
        //注册BroadcastReceiver  
        registerReceiver(receiver, filter);  
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);  
	    Intent intent1 = new Intent(getApplicationContext(), AlarmReceiver.class);  
	    int requestCode = 0;  
	    PendingIntent pendIntent = PendingIntent.getBroadcast(getApplicationContext(),  
	            requestCode, intent1, PendingIntent.FLAG_UPDATE_CURRENT);  
	    // 5秒后发送广播，只发送一次  
	    long triggerAtTime = SystemClock.elapsedRealtime() + 20 * 1000;  
	    alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendIntent);
	    System.out.print("0000000001");
	  
    } 
   
    
} 