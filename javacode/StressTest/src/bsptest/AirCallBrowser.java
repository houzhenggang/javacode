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

public class AirCallBrowser extends UiAutomatorTestCase {   
	private static final String ACTIVITY_TAG="bsptest";
	@SuppressWarnings({ "unused", "deprecation" })
	public void test() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/Airplane");
		File newFile1=new File("/sdcard/Airplane/open_fail_list.text");
		newFile1.createNewFile();
		FileOutputStream fo=new FileOutputStream(newFile1);
		File resultReport=new File("/sdcard/Airplane/Callfail_list.txt");
		resultReport.createNewFile();
		FileOutputStream inputResult=new FileOutputStream(resultReport);
		File newFile2=new File("/sdcard/Airplane/close_fail_list.text");
		newFile1.createNewFile();
		FileOutputStream fo1=new FileOutputStream(newFile2);
		sleep((long) 5000.0);
		 for(int i = 1 ; i < 400 ; i++){
		Process pc1 = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		sleep((long) 5000.0);
		/*UiScrollable appViews = new UiScrollable(new UiSelector()
         .scrollable(true));
		UiObject moreApp = appViews.getChildByText(new UiSelector()
         .className(android.widget.TextView.class.getName()), 
         "更多...");
		moreApp.click();*/
		UiObject allsteView = new UiObject(new UiSelector().text("全部设置"));
		allsteView.clickAndWaitForNewWindow();
		sleep((long) 5000.0);
    	  Process p = Runtime.getRuntime().exec("logcat -b radio -c");
    	  sleep((long) 1000.0);
    	  UiObject SendButton = new UiObject(new UiSelector().
	    		   className("android.widget.CheckBox").index(0).instance(0));
		SendButton.click();
  		sleep((long) 20000.0);
  		UiWatcher confirmDialogWatcher = new UiWatcher(){

			@Override
			public boolean checkForCondition() {
				UiObject okCancelDialog = new UiObject(new UiSelector().text("取消"));
				if(okCancelDialog.exists()){
					try {
						okCancelDialog.click();
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				return false;
			}
			
		};
		
		UiDevice.getInstance().registerWatcher("UIWATCHER", confirmDialogWatcher);
		UiDevice.getInstance().runWatchers();
		UiWatcher confirmDialogWatcher1 = new UiWatcher(){

			@Override
			public boolean checkForCondition() {
				UiObject okCancelDialog = new UiObject(new UiSelector().text("无法访问移动网络"));
				UiObject cancelButton = new UiObject(new UiSelector().className("android.widget.Button").text("确定"));
				if(okCancelDialog.exists()){
					try {
						cancelButton.click();
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return (okCancelDialog.waitUntilGone(5 * 1000));
				}
				return false;
			}
			
		};
		
		UiDevice.getInstance().registerWatcher("UIWATCHER", confirmDialogWatcher1);
		UiDevice.getInstance().runWatchers();
  		if(i%2 != 0)
  		{
    	  	String logcat;
			File logcatFile=new File("/sdcard/Airplane/open"+i+".txt");
			logcatFile.createNewFile();
			FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
			Process getLogcat = Runtime.getRuntime().exec("logcat -b radio -t 1000");
			DataInputStream inputLogcat = new DataInputStream(getLogcat.getInputStream());
			while ((logcat = inputLogcat.readLine()) != null){
				outputLogcat.write(logcat.getBytes());
			}
			BufferedReader bufferedReader = new BufferedReader(new FileReader(logcatFile));  
            String lineTXT ;
            lineTXT = bufferedReader.readLine();
            String log = lineTXT.toString().trim();
            int result=log.indexOf("AT+CFUN=0");
            if(result != -1)
            {
            	Log.v(AirCallBrowser.ACTIVITY_TAG, "Open Airplane successfully------->"+(i+1)/2);
		    	
		    }
		    else
		    {
		    	Log.v(AirCallBrowser.ACTIVITY_TAG, "Open Airplane fail------->"+(i+1)/2);
		    	String failnote = "Open Airplane fail_"+(i+1)/2;
  				SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
  				fo.write((date1.format(new Date())+"  \n"+failnote+"  \n").getBytes());
		    }
  		}
      else
      {
    	  int result1 = -1;
	    	String logcat;
	    	File logcatFile=new File("/sdcard/"+i+".txt");
			logcatFile.createNewFile();
	    	FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
	    	while(result1 == -1)
	    	{
	    		Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 10000");
	    		DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
				while ((logcat = inputLogcat1.readLine()) != null){
					outputLogcat.write(logcat.getBytes());				
				}
				BufferedReader bufferedReader1 = new BufferedReader(new FileReader(logcatFile));  
				String lineTXT1 ;
				lineTXT1 = bufferedReader1.readLine();
				String log1 = lineTXT1.toString().trim();
				result1=log1.indexOf("AT+CFUN=1");
				sleep((long) 3000.0);
				/*Log.v(AirCallBrowser.ACTIVITY_TAG, "sms send ------------"+i);*/
				System.out.println(result1);
	    	}
	    	Log.v(AirCallBrowser.ACTIVITY_TAG, "Close is successfully------------"+i/2);
		    	sleep((long) 20000.0);
		    	String callnumber = "tel:10086";
				Process startCall = Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d " + callnumber);
				UiObject MoreButton = new UiObject(new UiSelector().
			    		   className("android.widget.ToggleButton").index(0).instance(2));
				MoreButton.click();
				UiObject wait1App = new UiObject(new UiSelector().textContains("正在拨号"));
				wait1App.waitUntilGone((long) 60000.0);
				UiObject waitApp = new UiObject(new UiSelector().textContains("暂停通话"));

				if(waitApp.exists()&& waitApp.isEnabled()){
		    	  Log.v(AirCallBrowser.ACTIVITY_TAG, "call is successfully!" + i/2);
				sleep((long) 20000);
				UiObject endButton = new UiObject(new UiSelector().
			    		   className("android.widget.Button").index(1));

			    if(endButton.exists() && endButton.isEnabled()) 
			    {
			    	endButton.click();
			    }else{
			    	sleep((long) 10000.0);
					getUiDevice().pressHome();
			    }
				}
		      
				else{
					String str1;
	  				sleep((long) 20000);
	  				UiObject endButton = new UiObject(new UiSelector().
				    		   className("android.widget.Button").index(1));
	   				if (endButton.exists()) {
	   					endButton.click();
	   					sleep((long) 2000.0);
	   					getUiDevice().pressHome();
	   		        } else {
	   		        	getUiDevice().pressHome();
	   		        }
	   				Log.v(AirCallBrowser.ACTIVITY_TAG, "call is fail!" + i/2);
	  				String failnote = "call is fail_"+i/2;
	  				SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
					inputResult.write((date1.format(new Date())+"  \n"+failnote+"  \n").getBytes());
	  				sleep((long) 1000.0);
	  				try {
	  					String faill_logcat;
	  					File logcatFile1=new File("/sdcard/log"+i/2+".txt");
	  					logcatFile1.createNewFile();
	  					FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1);
	  					Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 2000");
	  					DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
	  					while ((faill_logcat = inputLogcat1.readLine()) != null){
	  						outputLogcat1.write((faill_logcat+"  \n").getBytes());
	  					}
	  				} catch (IOException e) {
	  					// TODO Auto-generated catch block
	  					e.printStackTrace();
	  				}
	  				sleep((long) 5000.0);
					}  
					Process p2 = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d " +
							"http://3g.sina.com.cn -n com.android.browser/.BrowserActivity -f 0x13800000");
					sleep((long) 10000.0);
					getUiDevice().click(360,800);
					sleep((long) 10000.0);
					Process closeBrower = Runtime.getRuntime().exec("am force-stop com.android.browser ");
					sleep((long) 1000.0);
		    	
		    }
		    
    	  
      }
      }
	}
	