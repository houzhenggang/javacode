package bsptest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Alarm extends UiAutomatorTestCase {  

	public void testAlarm() throws UiObjectNotFoundException, IOException, RemoteException{
		for(int i = 1 ; i < 350 ; i++)
		{
			Process runSmsMo = Runtime.getRuntime().exec("am start com.android.deskclock/.DeskClockTabActivity");
			sleep((long) 5000.0);
			UiObject AlarmButton = new UiObject(new UiSelector()
	         .text("添加闹钟"));
			AlarmButton.clickAndWaitForNewWindow();
			SimpleDateFormat date1 = new SimpleDateFormat("ss");
			SimpleDateFormat date2 = new SimpleDateFormat("mm");
			int ss = Integer.parseInt(date1.format(new Date()), 10);
			int mm = Integer.parseInt(date2.format(new Date()), 10);
			int num = 60-ss;
			UiObject Set1Button = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(2).instance(2));
			UiObject Set2Button = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(2).instance(1));
			if(mm <= 59 && mm >= 56){
				Set2Button.click();
				for(int j = 0 ; j < 3 ; j++){
					Set1Button.click();
					
			}
			}else{
				for(int j = 0 ; j < 3 ; j++){
					Set1Button.click();
					/*sleep((long) 1000.0);*/
				}
			}
			
			UiObject OkButton = new UiObject(new UiSelector()
	         .text("确定"));
			OkButton.click();
			UiDevice.getInstance().pressHome();
			UiObject alarmAlert = new UiObject(new UiSelector().packageName("com.android.deskclock").
		    		   className("android.view.View").index(0));
			WaitFor(alarmAlert,(150+num));
			/*sleep((long) 10000.0);
			sleep((long) ((240+num)*1000));
			UiDevice.getInstance().sleep();
			sleep((long) ((240+num)*1000.0));*/
			sleep((long) 2000.0);
			Process p2 = Runtime.getRuntime().exec("screencap -p /sdcard/" + i + ".png");
			sleep((long) 3000.0);
			UiDevice.getInstance().swipe(215, 1717, 910, 1717, 10);
			sleep((long) 10000.0);
			Process closeGmae = Runtime.getRuntime().exec("am force-stop com.android.deskclock");
			sleep((long) 5000.0);
		
		}
	}

	
	public void WaitFor(UiObject uiObject,int tiemout) throws UiObjectNotFoundException, IOException{
		int i=0;
		while(!uiObject.exists() && (i < tiemout)){
			
			
				sleep((long) 1000.0);
				i++;
				Log.v("xjhao", "signal-------"+i);
		
		}
		Log.v("xjhao", "signalxxxxxxxx-------1");
	}

}
