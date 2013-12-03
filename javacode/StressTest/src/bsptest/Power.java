package bsptest;
//��2 & ��2A power��ѹ������ 

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Power extends UiAutomatorTestCase {   

   @SuppressWarnings({"unused", "deprecation" })
public void test1() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 500 ; i++){
			Process p5 = Runtime.getRuntime().exec("logcat -c");
		   getUiDevice().pressKeyCode(26);
		   sleep((long) 2000.0);
		   Process p = Runtime.getRuntime().exec("cat /sys/class/leds/lcd-backlight/brightness");
		   DataInputStream p2 = new DataInputStream(p.getInputStream());
			int result = p2.readLine().indexOf("0");
			if(result>=0){
				getUiDevice().pressKeyCode(26);
				   sleep((long) 2000.0);

				}
			else{
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
				File newFile=new File("/sdcard/fail" + df1.format(new Date()) + ".txt");
				System.out.println("fail" + i);
				newFile.createNewFile();
				String str2 = new String();
				FileOutputStream fos=new FileOutputStream(newFile);
				Process p3 = Runtime.getRuntime().exec("logcat -t 1000");
				DataInputStream p4 = new DataInputStream(p3.getInputStream());
				while ((str2 = p4.readLine()) != null){
					fos.write((str2+ "  \n").getBytes());
				}
				getUiDevice().pressKeyCode(26);
				   sleep((long) 2000.0);		
				}    	
		}
  } 
}