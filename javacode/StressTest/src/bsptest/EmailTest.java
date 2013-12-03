package bsptest;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class EmailTest extends UiAutomatorTestCase
{
	@SuppressWarnings({ "unused" })
	public void test() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
      for(int i = 1 ; i < 200 ; i++){
    	  Process pc1 = Runtime.getRuntime().exec("am start -n com.android.email/.activity.EmailActivity");
  		sleep((long) 5000.0);
    	  UiObject addApp = new UiObject(new UiSelector()
          .description("写邮件"));
  		addApp.click();
  		sleep((long) 5000.0);
    	  UiObject sendButton = new UiObject(new UiSelector().
	    		   className("android.widget.MultiAutoCompleteTextView").index(1).instance(0));
		sendButton.setText("xuejinghao123@126.com");
  		sleep((long) 5000.0);
  		UiObject send1Button = new UiObject(new UiSelector().
	    		   className("android.widget.EditText").index(0).instance(0));
		send1Button.setText("test");
		sleep((long) 5000.0);
		UiObject send2Button = new UiObject(new UiSelector().
	    		   className("android.widget.EditText").index(0).instance(1));
		String t = "test"+i;
		send2Button.setText(t);
		sleep((long) 5000.0);
		UiObject sendApp = new UiObject(new UiSelector()
        .description("发送"));
		sendApp.click();
		sleep((long) 30000.0);
      }
	}
}
