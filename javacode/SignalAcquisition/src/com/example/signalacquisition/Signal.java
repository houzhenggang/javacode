package com.example.signalacquisition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import com.example.signalacquisition.Signal11.TimerTask;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;


public class Signal extends Activity
{
	private TextView text;
	private  static String callCID;
	private  static int Signal1;
	private static final String ACTIVITY_TAG="LogDemo";
	private  ConnectivityManager cm;
	private NetworkInfo mobNetInfo;
    TelephonyManager Tel;
    public static String convertDecimalToBinary ( int value){
		return Integer.toHexString(value);
		}
    private TelephonyManager mTelephonyManager;
    private MyPhoneStateListener MyListener;
    private PowerManager powerManager = null;    
    private WakeLock wakeLock = null; 
    private static String NetWorkeType;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        MyListener = new MyPhoneStateListener();
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE); 
        Tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        text = (TextView)findViewById(R.id.text);
        text.setTextColor(R.drawable.black);
        text.setTextSize(17);
        xjh();
        Log.v(Signal.ACTIVITY_TAG, "signal-------1");
    }
    
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        }
 
    @Override
    protected void onPause() {
        super.onPause();
        Tel.listen(MyListener, PhoneStateListener.LISTEN_NONE);
    }
 
    public void onReceive() {  
    	ConnectivityManager connectMgr = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        mobNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	  }  
    
    @Override
    protected void onResume() {
        super.onResume();
        Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
    }          

    private class MyPhoneStateListener extends PhoneStateListener 
    {
        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) 
        {
        	super.onSignalStrengthsChanged(signalStrength); 
        	GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();
        	if(location != null)
        	{
        		int cellId = location.getCid(); 
                callCID = convertDecimalToBinary(cellId);
                int timetakt = Integer.parseInt(String.valueOf(signalStrength.getGsmSignalStrength()), 10);
                cm =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                mobNetInfo= cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                Signal1 = (timetakt*2 -113);
                text.setText(String.valueOf( "当前信号值 = "+Signal1+"  小区CID："+callCID+"----网络类型----"+mobNetInfo.getSubtypeName()+"  \n"+"信号获取的数据会保存在SDcard根目录下Signal.txt"
                +"  \n"+"提示：程序不要放入后台，会导致Modem休眠，抓取信号结束后关闭程序即可。"));
                /*Log.v(Signal.ACTIVITY_TAG, "signal-------2");*/
        		
        	}
        	else
        	{
        		callCID = "unknown";
        		int timetakt = Integer.parseInt(String.valueOf(signalStrength.getGsmSignalStrength()), 10);
                Signal1 = (timetakt*2 -113);
                text.setText(String.valueOf( "当前信号值 = "+Signal1+"  小区CID："+callCID+"  ----网络类型------"+mobNetInfo+"  \n"+"信号获取的数据会保存在SDcard根目录下Signal.txt"
                +"  \n"+"提示：程序不要放入后台，会导致Modem休眠，抓取信号结束后关闭程序即可。"));
                Log.v(Signal.ACTIVITY_TAG, "signal-------3");
        	}
        }
    }

    @SuppressLint("SimpleDateFormat")
	public void xjh()
	{
    	Intent intent = getIntent();
		String factorOneStr = intent.getStringExtra("one");
		int intSecondsToChange = Integer.parseInt(factorOneStr);
		SimpleDateFormat date1 = new SimpleDateFormat("ss");
		int loop = Integer.parseInt(date1.format(new Date()), 10);
		int a = 60 - loop;
		SimpleDateFormat date3 = new SimpleDateFormat("HH:mm:ss");
		if(a<10){
			java.util.Timer timer = new java.util.Timer(true); 
			/*Timer timer = new Timer();*/
			timer.schedule(new TimerTask(), (a+60)*1000, intSecondsToChange*60000);
			Log.v(Signal.ACTIVITY_TAG, "signal-------4"+date3.format(new Date()));
			
		}
		else{
			java.util.Timer timer = new java.util.Timer(true); 
			timer.schedule(new TimerTask(), a*1000, intSecondsToChange*60000);
			Log.v(Signal.ACTIVITY_TAG, "signal-------5"+date3.format(new Date()));
		}
			
	}
		
	class TimerTask extends java.util.TimerTask 
	{
		public void run()
		{
			
			// TODO Auto-generated method stub
			File resultReport=new File("/sdcard/Signal.txt");
			try {
				SimpleDateFormat date2 = new SimpleDateFormat("HH:mm:ss");
				resultReport.createNewFile();
				FileOutputStream inputResult=new FileOutputStream(resultReport,true);
				String GPRS = "GPRS";
				String EDGE = "EDGE";
				if((mobNetInfo.getSubtypeName()).equals(EDGE)||(mobNetInfo.getSubtypeName()).equals(GPRS)){
					NetWorkeType = "2G";
				}else{
					NetWorkeType = "3G";
				}
				String signalWrite = (date2.format(new Date()) +"  " +Signal1 +"  "+ callCID + "  "+ NetWorkeType+"  \n");
				inputResult.write(signalWrite.getBytes());
				Log.v(Signal.ACTIVITY_TAG, ("signal--write-------6" + date2.format(new Date())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}    
}


