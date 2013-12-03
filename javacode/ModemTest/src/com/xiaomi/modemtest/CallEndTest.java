package com.xiaomi.modemtest;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class CallEndTest extends UiAutomatorTestCase {
	public void testCallEnd() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException{
	UiObject endButton = new UiObject(new UiSelector().className("android.widget.Button").index(1));
	if(endButton.exists() && endButton.isEnabled()) {
	    endButton.click();
	   }
	sleep((long) 5000.0);
	}
}
