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
public class ScreenIdleteset extends UiAutomatorTestCase{

	public void testNetwork() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {
		Process endall = Runtime.getRuntime().exec("am kill all background processes");
		getUiDevice().pressHome();
		Process settings = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        UiObject networkApp = appViews.getChild(new UiSelector().className("android.widget.CheckBox"));
 	    networkApp.clickAndWaitForNewWindow();
 	    sleep((long)200);
 	    UiObject displayapp = new UiObject(new UiSelector().className("android.widget.TextView").text("显示"));
 	    displayapp.clickAndWaitForNewWindow();
 	    sleep((long)200)
 	    ;
 	    UiObject displayapptab = new UiObject(new UiSelector().className("android.widget.TextView").text("休眠"));
 	    displayapptab.clickAndWaitForNewWindow();
 	    sleep((long)200);
 	   UiObject displayapptab1 = new UiObject(new UiSelector().className("miui.widget.CheckedTextView").text("永不"));
	    displayapptab1.clickAndWaitForNewWindow();
	    sleep((long)200);
	    
	    getUiDevice().pressHome();
	    
	   }
}
	  