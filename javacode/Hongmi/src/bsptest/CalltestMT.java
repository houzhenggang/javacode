package bsptest;

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

public class CalltestMT extends UiAutomatorTestCase 
{   

	@SuppressWarnings({ "unused", "deprecation" })
	public void test_MOCALL() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 500 ; i++)
		{
			/*String callnumber = "tel:15011584703";*/
			String callnumber = "tel:13911255376";
			Process startCall = Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d " + callnumber);
			UiObject settingsValidation = new UiObject(new UiSelector()
	         .textContains("00:"));
			settingsValidation.waitForExists((long) 20000.0);
			sleep((long) 20000.0);
			UiObject endButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(1));

		    if(endButton.exists() && endButton.isEnabled()) 
		    {
		    	endButton.click();
		    }
		    sleep((long) 5000.0);
		}    	
	}
		  
}