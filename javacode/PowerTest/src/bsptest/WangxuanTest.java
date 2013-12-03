package bsptest;

import bsptest.Util;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.AlarmManager;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;



public class WangxuanTest extends UiAutomatorTestCase
{	
	@SuppressWarnings({ "unused", "deprecation" })
	public void test1PlayGame() throws IOException, UiObjectNotFoundException, RemoteException
	{	
		for(int i = 0; i < 500;i++)
		{
		String Callnum = "10086";
		Process startCall= Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel:"+Callnum);
		UiObject sendApp = new UiObject(new UiSelector().text("00:02"));
		sendApp.waitForExists((long) 20000.0);
		if(sendApp.exists()) 
	    {
			getUiDevice().sleep();
	    }
	    UiObject endButton = new UiObject(new UiSelector().
	    		   className("android.widget.Button").index(1));
	    if(endButton.exists() && endButton.isEnabled()) 
	    {
	    	endButton.click();
	    }
	    sleep((long) 20000.0);
		}
		Process getLog= Runtime.getRuntime().exec("bugreport > /sdcard/xjh.txt");
		
	}

	
	

	public static void main(String args[])
	{ 
		junit.textui.TestRunner.run(WangxuanTest.class);
	}
	

}

