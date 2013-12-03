package com.xiaomi.power;

import java.io.IOException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

 class NewCase extends UiAutomatorTestCase implements  Case
{
	 public void CallMotest() throws UiObjectNotFoundException, IOException{
			for(int i = 0 ; i < 500 ; i++)
			{

				String callnumber = "tel:10086";
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
					getUiDevice().pressHome();
				}
			}		
		}
	 public void GameTest() throws UiObjectNotFoundException, IOException{
			for(int i = 0 ; i < 500 ; i++)
			{
				getUiDevice().pressHome();
				String callnumber = "tel:10086";
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
					getUiDevice().pressHome();
				}
			}		
		}

}
