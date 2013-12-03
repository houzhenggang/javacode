package bsptest;

import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import android.os.RemoteException;	

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class ChangeNetwork extends UiAutomatorTestCase{	
		@SuppressWarnings("unused")
		public void test() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
			for(int i = 0 ; i < 200 ; i++){
				sleep((long) 3000.0);
				Process p1 = Runtime.getRuntime().exec("am start com.android.settings/.TestingSettings");
				sleep((long) 5000.0);
				UiObject addApp = new UiObject(new UiSelector()
		          .text("手机信息"));
		  		addApp.click();
		  		sleep((long) 3000.0);
		  		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
		  		appViews.scrollToEnd(2);
		  		 UiObject settingsApp = appViews.getChildByText(new UiSelector()
		         .className(android.widget.TextView.class.getName()), 
		         "TD-SCDMA only");
		  		settingsApp.click();
		  		sleep((long) 3000.0);
		  		UiObject add1App = new UiObject(new UiSelector()
		          .text("GSM only"));
		  		add1App.click();
		  		sleep((long) 15000.0);
		  		add1App.click();
		  		sleep((long) 3000.0);	  		
		  		UiObject add2App = new UiObject(new UiSelector()
		          .text("TD-SCDMA only"));
		  		add2App.click();
		  		sleep((long) 15000.0);
		  		Process p2 = Runtime.getRuntime().exec("am force-stop com.android.settings");
		  		sleep((long) 3000.0);
		  		 
				}
			}
		
		
		
}
