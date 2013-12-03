package bsptest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class BTtest extends UiAutomatorTestCase {   	
	@SuppressWarnings({ "unused", "deprecation" })
	public void test() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		Process pc1 = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		sleep((long) 5000.0);
		UiScrollable appViews = new UiScrollable(new UiSelector()
         .scrollable(true));
		UiObject moreApp = appViews.getChildByText(new UiSelector()
         .className("android.widget.TextView"),"蓝牙");
		moreApp.click();
		sleep((long) 5000.0);
      for(int i = 1 ; i < 1000 ; i++){
    	  UiObject openButton = new UiObject(new UiSelector().
	    		   className("android.widget.Switch").index(1).instance(0));
	    	  openButton.click();
  		sleep((long) 20000.0);
  		if(i%2 != 0)
  		{
  			System.out.println("Open BT he qun niu bi--------->"+(i+1)/2);
  		}
  		else
  		{
  			System.out.println("Close BT he qun niu bi--------->"+i/2);
  		}   
      }
	}
}
