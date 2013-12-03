package Bsptest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class WifiTest extends UiAutomatorTestCase
{
	public int result;
	public void testWifi() throws Exception{
		while(result != -1){
			Process getLogcat1 = Runtime.getRuntime().exec(new String[]{"/system/bin/sh","-c","dumpsys wifi |grep -i curState=ConnectedState"});
			InputStream is = getLogcat1.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			String Log="";
			while ((line = reader.readLine()) != null){
				Log = Log+line+"\n";
			}
			result=Log.indexOf("curState=ConnectedState");
			sleep((long) 1000.0);
		}
		System.out.println("wifi is disconnect!!!!!!");
		UiDevice.getInstance().takeScreenshot(new File("/sdcard/"+getTime("HH_mm_ss")+".png"));
	}
	
	/*获取当前的时间*/
	public String getTime(String time){
		SimpleDateFormat date1 = new SimpleDateFormat(time);
		String Time = date1.format(new Date());
		return Time;
	}

}
