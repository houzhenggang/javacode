package com.bsptest;


import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.Date;
import java.text.SimpleDateFormat;

import junit.framework.Test;
import junit.framework.TestSuite;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


/**
 * @author xuejh
 *
 */
public class newCall extends UiAutomatorTestCase {  
	  public static Test suite() {
          TestSuite suite = new TestSuite();
          
           //逐一添加testCase类 
          suite.addTestSuite(CallMo.class);
          return suite;
  }
	public static void main(String args[]){ 
		junit.textui.TestRunner.run(newCall.class);
	}
   
	/*@SuppressWarnings("unused")
	public void unLock() throws IOException
	{
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
		
	}*/
	

}

