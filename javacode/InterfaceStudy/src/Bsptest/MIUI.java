package Bsptest;

import java.io.IOException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

class MIUI implements Interface1
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
				
			}
		}		
	}
	
	public void SmsMoTest() throws UiObjectNotFoundException, IOException{
		for(int i = 1 ; i < 100 ; i++)
		{
			String smsNumber = "10086";
			String details = "cxyl";
			Process runSmsMo = Runtime.getRuntime().exec("am start -a android.intent.action.SENDTO -d sms:" + smsNumber + " --es sms_body " + details);
			sleep((long) 5000.0);
			UiObject SendButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(1));
			UiObject SendButton1 = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(3));
		    if (SendButton1.exists()) {
		    	SendButton1.click();
	        } else {
	        	SendButton.click();
	        }
		
		}
	}

	private void sleep(long l) {
		// TODO Auto-generated method stub
		
	}
}
