package com.bsptest;

import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class CallMo extends UiAutomatorTestCase 
{   

	@SuppressWarnings({ "unused", "deprecation" })
	public void test_MOCALL() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 500 ; i++)
		{
			String callnumber = "tel:15901274733";
			Process startCall = Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d " + callnumber);
			UiObject settingsValidation = new UiObject(new UiSelector()
	         .textContains("00:05"));
			settingsValidation.waitForExists((long) 20000.0);
			if(settingsValidation.exists())
			{
				sleep((long) 60000.0);
				UiObject endButton = new UiObject(new UiSelector().
			    		   className("android.view.View").index(1));
			    if(endButton.exists() && endButton.isEnabled()) 
			    {
			    	endButton.click();
			    }
			    sleep((long) 120000.0);
			}
			else
			{
				
			}
		}    	
	}
		  
}