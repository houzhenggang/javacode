package com.bsptest;
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

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class Util extends UiAutomatorTestCase
{
	@SuppressWarnings("unused")
	public void testUnlock() 
	{

	    try {
	    	try {
				getUiDevice().sleep();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    sleep((long) 2000.0);
		    try {
				getUiDevice().wakeUp();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    sleep((long) 1000.0);
			Process r1 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 1");
			Process r2 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
		    Process r3 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 1");
		    Process r4 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
		    Process r5 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 0");
		    Process r6 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
		    Process r7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 0");
		    Process r8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
		    sleep((long) 2000.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void testgetLog()
	{
		String str1;
		File newFile=new File("/sdcard/MIUI/xxx.txt");
		try {
			newFile.createNewFile();
			sleep((long) 3000.0);
			FileOutputStream fos=new FileOutputStream(newFile);
			Process getLog = Runtime.getRuntime().exec("logcat -t 600");
			DataInputStream p5 = new DataInputStream(getLog.getInputStream());
			while ((str1 = p5.readLine()) != null){
				fos.write(str1.getBytes());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public void testCall() throws IOException
	{
		Process p1 = Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel:10086");
		sleep((long) 10000.0);
		getUiDevice().click(360,1191);
	}	
		
	@SuppressWarnings("unused")
	public void testResdxml() throws IOException, ParserConfigurationException, SAXException
	{
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
	}
	
	@SuppressWarnings("unused")
	public void testScreenshot()
	{
		try {
			//手机截屏event
			Process eventSend1 = Runtime.getRuntime().exec("sendevent /dev/input/event0: 1 139 1");
			Process eventSend2 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
			Process eventSend3 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 114 1");
			Process eventSend4 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
			Process eventSend5 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 114 0");
			Process eventSend6 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
			Process eventSend7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 139 0");
			Process eventSend8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
