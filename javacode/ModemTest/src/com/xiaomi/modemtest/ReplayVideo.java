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
public class ReplayVideo extends UiAutomatorTestCase{

	public void testVideo() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {
		 UiObject cameraapp = new UiObject(new UiSelector().className("android.widget.ImageView").index(2).
				 childSelector(new UiSelector().description("最新照片")));
		 cameraapp.clickAndWaitForNewWindow();
	 	    sleep((long)200);
	 	 
	 	 getUiDevice().click(540,960);
	 	    sleep((long)30000);
	 	    
	 	   
	    
	   }
}
	    