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

public class Airplane extends UiAutomatorTestCase {   
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
		for(int i = 1 ; i < 400 ; i++){
    	  Process p = Runtime.getRuntime().exec("logcat -b radio -c");
    	  sleep((long) 1000.0);
    	  UiObject SendButton = new UiObject(new UiSelector().
	    		   className("android.widget.CheckBox").index(0).instance(0));
		SendButton.click();
  		sleep((long) 10000.0);
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
            	Log.v(Airplane.ACTIVITY_TAG, "Open Airplane successfully------->"+(i+1)/2);
		    	
		    }
		    else
		    {
		    	Log.v(Airplane.ACTIVITY_TAG, "Open Airplane fail------->"+(i+1)/2);
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
				result1=log1.indexOf("changed sending intent rule=1 showPlmn='false'");
				sleep((long) 3000.0);
				System.out.println(result1);
	    	}
	    	Log.v(Airplane.ACTIVITY_TAG, "Close is successfully------------"+i/2);
      }
		    
    	  
      }
      }
	}
	