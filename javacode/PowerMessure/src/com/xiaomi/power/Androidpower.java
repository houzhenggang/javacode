/**
 * 
 */
/**
 * @author xiaomi
 *
 */
package com.xiaomi.power;

import java.io.IOException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

class Androidpower extends UiAutomatorTestCase implements Case
{

	public void CallMotest() throws UiObjectNotFoundException, IOException{
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
	public void GameTest() throws UiObjectNotFoundException, IOException{
		/*getUiDevice().pressHome();
		UiObject mainButton = new UiObject(new UiSelector().
	    		   className("android.widget.TextView").index(2));
		mainButton.click();
		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        appViews.setAsHorizontalList();
        UiObject appsTab = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()) , "水果忍者");
        appsTab.clickAndWaitForNewWindow();
        for(int i=0 ; i < 20 ; i++){
        	sleep((long) 2000);
            getUiDevice().swipe(222, 777, 500, 1270, 10);
            sleep((long) 2000);
            getUiDevice().swipe(660, 900, 888, 1280, 10);
            sleep((long) 2000);
       
        	 for(int j = 0 ; j < 20 ; j++){
             	getUiDevice().swipe(230, 215, 725, 1670, 10);
             	sleep(2000);
             	getUiDevice().swipe(240, 1600, 750, 260, 10);
             	sleep((long) 2000);
             }
        	
        	UiObject backButton = new UiObject(new UiSelector().
		    		   className("android.widget.ImageButton").index(1));
        	if(backButton.exists() && backButton.isEnabled())
        	{
        		backButton.click();
		    	sleep((long) 3000);
        	}
        	UiObject skipButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").text("跳过"));
        	if(skipButton.exists() && skipButton.isEnabled())
        	{
        		skipButton.click();
		    	sleep((long) 3000);
        	}
        	getUiDevice().swipe(200, 1270, 230, 1720, 10);
        	sleep((long) 2000);//退出
        }
        getUiDevice().swipe(165, 1500, 230, 1880, 10);
        sleep((long) 2000);
        UiObject exitButton = new UiObject(new UiSelector().
        		className("android.widget.Button").text("退出"));
        Process endAll = Runtime.getRuntime().exec("am force-stop com.halfbrick.fruitninja");
		//得到上面具体类：# logcat | busybox grep com
		        	  */
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