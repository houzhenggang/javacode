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
		for(int i = 0 ; i < 500 ; i++){
			UiObject sendApp = new UiObject(new UiSelector()
	        .textContains("133 7013 2026"));
			sendApp.waitForExists((long) 200000.0);
			if(sendApp.exists()) 
		    {
				getUiDevice().swipe(540, 1490, 140, 1490, 8);
		    }
			sleep((long) 2000.0);
		}	 				 	
	}
}

		
		
