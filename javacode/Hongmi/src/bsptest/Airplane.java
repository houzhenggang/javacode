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
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Airplane extends UiAutomatorTestCase {   	
	@SuppressWarnings({ "unused", "deprecation" })
	public void test() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
/*		Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/Airplane");
		File newFile1=new File("/sdcard/Airplane/open_fail_list.text");
		newFile1.createNewFile();
		FileOutputStream fo=new FileOutputStream(newFile1);
		File newFile2=new File("/sdcard/Airplane/close_fail_list.text");
		newFile1.createNewFile();
		FileOutputStream fo1=new FileOutputStream(newFile2);*/
		getUiDevice().pressHome();
		Process pc1 = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		sleep((long) 5000.0);
		UiObject allApp = new UiObject(new UiSelector()
        .text("全部设置"));
		allApp.click();
		sleep((long) 5000.0);
      for(int i = 1 ; i < 400 ; i++){
    	  Process p = Runtime.getRuntime().exec("logcat -c");
    	  sleep((long) 1000.0);
    	  UiObject endButton = new UiObject(new UiSelector().
	    		   className("android.widget.CheckBox").index(0));

	    if(endButton.exists() && endButton.isEnabled()) 
	    {
	    	endButton.click();
	    }
  		sleep((long) 20000.0);
  		System.out.println(i);
  		/*if(i%2 != 0)
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
		    	System.out.println("Open Airplane successfully------->"+i);				    	
		    }
		    else
		    {
		    	System.out.println("Open Airplane fail------->"+i);
		    	String failnote = "Open Airplane fail_"+i;
  				SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
  				fo.write((date1.format(new Date())+"  \n"+failnote+"  \n").getBytes());
		    }
  		}
      else
      {
    	  String logcat;
			File logcatFile=new File("/sdcard/Airplane/close"+i+".txt");
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
          int result=log.indexOf("AT+CFUN=1");
          if(result != -1)
          {
		    	System.out.println("Close Airplane successfully------->"+i);
		    	UiObject airPlane1App = new UiObject(new UiSelector()
		         .text("取消"));
		    	if(airPlane1App.exists())
		    	{
		    		airPlane1App.click();	    		
		    	}
		    	sleep((long) 5000.0);
		    	
		    }
		    else
		    {
		    	System.out.println("Close Airplane fail------->"+i);
		    	String failnote = "Close Airplane fail_"+i;
		    	 UiObject airPlane1App = new UiObject(new UiSelector()
		         .text("取消"));
		    	if(airPlane1App.exists())
		    	{
		    		airPlane1App.click();	    		
		    	}
		    	sleep((long) 5000.0);
				SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
				fo.write((date1.format(new Date())+"  \n"+failnote+"  \n").getBytes());
		    }
    	  
      }*/
      }
	}
	}