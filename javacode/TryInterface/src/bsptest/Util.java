package bsptest;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.os.RemoteException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class Util extends UiAutomatorTestCase
{
	@SuppressWarnings("unused")
	public void testUnlock()
	{
		try {
			getUiDevice().sleep();
			sleep((long) 2000.0);
			getUiDevice().wakeUp();
			sleep((long) 1000.0);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	    	//米2A解锁 通过向手机发送event
		    Process SendEvent1 = Runtime.getRuntime().exec("sendevent /dev/input/event0 4 4 9");
		    Process SendEvent2 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 1");
		    Process SendEvent3 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
		    Process SendEvent4 = Runtime.getRuntime().exec("sendevent /dev/input/event2 1 158 1");
		    Process SendEvent5 = Runtime.getRuntime().exec("sendevent /dev/input/event2 0 0 0");
		    Process SendEvent6 = Runtime.getRuntime().exec("sendevent /dev/input/event0 4 4 9");
		    Process SendEvent7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 0");
		    Process SendEvent8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
		    Process SendEvent9 = Runtime.getRuntime().exec("sendevent /dev/input/event2 1 158 0");
		    Process SendEvent10 = Runtime.getRuntime().exec("sendevent /dev/input/event2 0 0 0");
		    //米2解锁
		    /*Process SendEvent1 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 1");
		    Process SendEvent2 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
		    Process SendEvent3 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 1");
		    Process SendEvent4 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
		    Process SendEvent5 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 0");
		    Process SendEvent6 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
		    Process SendEvent7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 0");
		    Process SendEvent8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void testLog()
	{
		try {
			String logcat;
			File logcatFile=new File("/sdcard/MIUI/name.txt");
			logcatFile.createNewFile();
			FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
			Process getLogcat = Runtime.getRuntime().exec("logcat -t 600");
			DataInputStream inputLogcat = new DataInputStream(getLogcat.getInputStream());
			while ((logcat = inputLogcat.readLine()) != null){
				outputLogcat.write(logcat.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
		
	public void testResdxml() throws IOException, ParserConfigurationException, SAXException
	{
		 try {
			  //读取xml配置参数
		   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder builder = factory.newDocumentBuilder();
		   Document document = builder.parse(new File("E:\\javadai\\Uitest4\\string.xml"));
		   Element rootElement = document.getDocumentElement();
		   NodeList list_callnum = rootElement.getElementsByTagName("callnum");
		   NodeList list_name = rootElement.getElementsByTagName("name");
		   //转化成字符串
		   Element element_callnum = (Element) list_callnum.item(0);
		   Element element_name = (Element) list_name.item(0);
		   System.out.println(element_callnum.getChildNodes().item(0).getNodeValue());
		   System.out.println(element_name.getChildNodes().item(0).getNodeValue());

		  } catch (Exception e) {
		   System.out.println("exception:" + e.getMessage());
		  }
	}
	
	@SuppressWarnings("unused")
	public void testScreenshot() throws IOException
	{
		Process SendEvent1 = Runtime.getRuntime().exec("sendevent /dev/input/event0: 1 139 1");
		Process SendEvent2 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
		Process SendEvent3 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 114 1");
		Process SendEvent4 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
		Process SendEvent5 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 114 0");
		Process SendEvent6 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
		Process SendEvent7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 139 0");
		Process SendEvent8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
	}
	
}
