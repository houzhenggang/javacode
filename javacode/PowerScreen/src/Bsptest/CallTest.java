/**
 * 
 */
/**
 * @author xiaomi
 *
 */
package Bsptest;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
@SuppressWarnings("unused")
public class CallTest extends UiAutomatorTestCase{
	public String ReadXmlString(String name) throws ParserConfigurationException, SAXException, IOException
	   {
	      DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
	      DocumentBuilder builder1 = factory1.newDocumentBuilder();
	      Document document1 = builder1.parse(new File("/sdcard/argument.xml"));
	      Element rootElement1 = document1.getDocumentElement();
	      NodeList list_loop = rootElement1.getElementsByTagName(name);
	      Element element_loop = (Element) list_loop.item(0);
	      String newname= element_loop.getChildNodes().item(0).getNodeValue();	      
	      return newname;
	   }
		
		public void testCallMO() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException{
	
				Process endall = Runtime.getRuntime().exec("am kill all background processes");
				sleep((long)2000);
				getUiDevice().pressHome();
				sleep((long)2000);
				String callnumber = ReadXmlString("callnum");
				Process startCall = Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel: " + callnumber);				
			    sleep((long)2000);
			    UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
		 	   UiObject endButton = new UiObject(new UiSelector().className("android.widget.Button").index(1));
		 		if(endButton.exists() && endButton.isEnabled()) {
		 		    endButton.click();
		 		   }
		 		sleep((long) 5000.0);
		 		}
		}