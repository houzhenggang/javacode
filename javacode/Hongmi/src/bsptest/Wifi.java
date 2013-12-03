package bsptest;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Wifi extends UiAutomatorTestCase {   	

		@SuppressWarnings({ "unused", "deprecation" })
		public void test() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
			/*Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/Wifi");
			File newFile1=new File("/sdcard/Wifi/open_fail_list.text");
			newFile1.createNewFile();
			FileOutputStream fo=new FileOutputStream(newFile1);
			File newFile2=new File("/sdcard/Wifi/close_fail_list.text");
			newFile2.createNewFile();
			FileOutputStream fo1=new FileOutputStream(newFile2);*/
			Process px1 = Runtime.getRuntime().exec("dumpsys wifi");
		    DataInputStream px2 = new DataInputStream(px1.getInputStream());
		    String strx1 = px2.readLine();
		    String strx2 = "Wi-Fi is enabled";
		    //判断wifi是否是出于开启状态
			if(strx1.equals(strx2))
			{
				Process pc1 = Runtime.getRuntime().exec("am start -n com.android.settings/.Settings");
				sleep((long) 5000.0);
				UiScrollable appViews = new UiScrollable(new UiSelector()
		         .scrollable(true));
		      UiObject wifiApp = appViews.getChildByText(new UiSelector()
		         .className(android.widget.TextView.class.getName()), 
		         "WLAN");
		    	  wifiApp.click();

		      sleep((long) 5000.0);
		      UiObject wifiOpenApp = appViews.getChildByText(new UiSelector()
		         .className(android.widget.TextView.class.getName()), 
		         "开启WLAN");
		      wifiOpenApp.click();

				System.out.println("Start run test!");
				getUiDevice().pressHome();
			}
			else
			{
				System.out.println("Start run test!");	
				getUiDevice().pressHome();
			}
			Process pc1 = Runtime.getRuntime().exec("am start -n com.android.settings/.Settings");
			sleep((long) 5000.0);
			UiScrollable appViews = new UiScrollable(new UiSelector()
	         .scrollable(true));
	      UiObject wifiApp = appViews.getChildByText(new UiSelector()
	         .className(android.widget.TextView.class.getName()), 
	         "WLAN");
	      sleep((long) 2000.0);
	    	  wifiApp.click();

	      for(int i = 1 ; i < 400 ; i++){
	    	  sleep((long) 5000.0);
	    	  UiObject wifiOpenApp = new UiObject(new UiSelector()
		         .text("开启WLAN"));
		      wifiOpenApp.click();
	    	  sleep((long) 10000.0);
	      if(i%2 != 0){
	    	  Process p1 = Runtime.getRuntime().exec("dumpsys wifi");
	    	  sleep((long) 2000.0);
	    	  DataInputStream p2 = new DataInputStream(p1.getInputStream());
			    String str1 = p2.readLine();
			    String str2 = "Wi-Fi is enabled";
			    if(str1.equals(str2)){
			    	System.out.println("Open wifi successfully------->"+(i+1)/2);				    	
			    }
			    else
			    {
			    	System.out.println("Open wifi fail------->"+(i+1)/2);
			    	/*String failnote = "Open wifi fail_"+i;
      				SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
      				fo.write((date1.format(new Date())+"  \n"+failnote+"  \n").getBytes());*/
			    }
	    	  
	      }
	      else
	      {
	    	  Process p1 = Runtime.getRuntime().exec("dumpsys wifi");
	    	  DataInputStream p2 = new DataInputStream(p1.getInputStream());
			    String str1 = p2.readLine();
			    String str2 = "Wi-Fi is disabled";
			    if(str1.equals(str2)){
			    	System.out.println("Close wifi successfully------->"+i/2);				    	
			    }
			    else
			    {
			    	System.out.println("Close wifi fail------->"+i/2);
			    	/*String failnote = "Close wifi fail_"+i;
      				SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
      				fo1.write((date1.format(new Date())+"  \n"+failnote+"  \n").getBytes());	*/			    	
			    }
	    	  
	      }
	      }
		}
	}