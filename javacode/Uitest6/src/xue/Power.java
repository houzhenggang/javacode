package xue;
//��2 & ��2A power��ѹ������ 

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Power extends UiAutomatorTestCase {   

   @SuppressWarnings({"unused", "deprecation" })
public void test1() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
	    /*getUiDevice().sleep();
	    sleep((long) 2000.0);
	    getUiDevice().wakeUp();
	    sleep((long) 1000.0);
	  //��2A����
	    Process r1 = Runtime.getRuntime().exec("sendevent /dev/input/event0 4 4 9");
	    Process r2 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 1");
	    Process r3 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
	    Process r4 = Runtime.getRuntime().exec("sendevent /dev/input/event2 1 158 1");
	    Process r5 = Runtime.getRuntime().exec("sendevent /dev/input/event2 0 0 0");
	    Process r6 = Runtime.getRuntime().exec("sendevent /dev/input/event0 4 4 9");
	    Process r7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 0");
	    Process r8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
	    Process r9 = Runtime.getRuntime().exec("sendevent /dev/input/event2 1 158 0");
	    Process r10 = Runtime.getRuntime().exec("sendevent /dev/input/event2 0 0 0");
	    //��2����
	    Process r1 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 1");
	    Process r2 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
	    Process r3 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 1");
	    Process r4 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
	    Process r5 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 0");
	    Process r6 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
	    Process r7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 0");
	    Process r8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
	    sleep((long) 2000.0);
		DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder1 = factory1.newDocumentBuilder();
	    Document document1 = builder1.parse(new File("/sdcard/MIUI/string.xml"));
	    Element rootElement1 = document1.getDocumentElement();
	    NodeList list2 = rootElement1.getElementsByTagName("loopnum");
	    Element element2 = (Element) list2.item(0);
	    String str;
	    str = element2.getChildNodes().item(0).getNodeValue();
	    int num = Integer.parseInt(str, 10);*/
		for(int i = 0 ; i < 10000 ; i++){
			Process p5 = Runtime.getRuntime().exec("logcat -c");
		   getUiDevice().pressKeyCode(26);
		   sleep((long) 2000.0);
		   Process p = Runtime.getRuntime().exec("cat /sys/class/leds/button-backlight/brightness");
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