package bsptest;

import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class CallMT extends UiAutomatorTestCase {   

	public void test_MOCALL() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 200 ; i++){
			UiObject sendApp = new UiObject(new UiSelector()
	        .text("北京"));
			sendApp.waitForExists((long) 200000.0);
			sleep((long) 2000.0);
			if(sendApp.exists()) 
		    {
				getUiDevice().swipe(360, 1040, 650, 1040, 10);
		    }
			sleep((long) 15000.0);
			UiObject endButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(1));

		    if(endButton.exists() && endButton.isEnabled()) 
		    {
		    	endButton.click();
		    }
		    else
		    {
		    	sleep((long) 5000.0);
		    }
		}	 				 	
	}
}

		
		
