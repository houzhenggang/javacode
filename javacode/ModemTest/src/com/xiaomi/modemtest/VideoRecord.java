package com.xiaomi.modemtest;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
@SuppressWarnings("unused")
public class VideoRecord extends UiAutomatorTestCase{

	public void testCamera() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {
		Process endall = Runtime.getRuntime().exec("am kill all background processes");
		getUiDevice().pressHome();
		Process camera = Runtime.getRuntime().exec("am start -n com.android.camera");
		getUiDevice().click(466,89);
 	    sleep((long)200);
 	    
 	    getUiDevice().click(540,1770);
 	    sleep((long)30000);
 	    getUiDevice().click(540,1770);
	    sleep((long)200);

	    
	   }
}
	    