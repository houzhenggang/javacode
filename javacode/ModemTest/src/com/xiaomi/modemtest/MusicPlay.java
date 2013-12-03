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
public class MusicPlay extends UiAutomatorTestCase{

	public void testNetwork() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {
		Process endall = Runtime.getRuntime().exec("am kill all background processes");
		getUiDevice().pressHome();
		Process settings = Runtime.getRuntime().exec("am start -n com.miui.music");
	
		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));

 	    UiObject displayapp = new UiObject(new UiSelector().className("android.widget.TextView").text("播放"));
 	    displayapp.clickAndWaitForNewWindow();
 	    sleep((long)200000)
 	    ;

	    getUiDevice().pressHome();
	    
	   }
}
	  