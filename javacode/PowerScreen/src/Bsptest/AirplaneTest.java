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
public class AirplaneTest extends UiAutomatorTestCase{
	
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

	public void testAirplane() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException{
		Process endall = Runtime.getRuntime().exec("am kill all background processes");
		getUiDevice().pressHome();
		String airplane = ReadXmlString("airplane");
		String openairplane = "open";
		String closeairplane = "close";
		Process settings = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		UiObject allsettingsTab = new UiObject(new UiSelector().className("android.widget.TextView").text("全部设置"));
		UiObject airButton = new UiObject(new UiSelector().className("android.widget.CheckBox").index(0));
		UiObject airopenButton = new UiObject(new UiSelector().className("android.widget.CheckBox").index(0).checked(true));
		UiObject aircloseButton = new UiObject(new UiSelector().className("android.widget.CheckBox").index(0).checked(false));
		if(airplane.equalsIgnoreCase(openairplane)){				
			if(airopenButton.exists()){
				System.out.println("Airplane is already opened!");
			}
			else if(aircloseButton.exists()){
				airButton.clickAndWaitForNewWindow();
				System.out.println("Airplane is opened!");
				sleep((long) 2000);
			}
		}
		else if(airplane.equalsIgnoreCase(closeairplane)){
			if(airopenButton.exists()){
				airButton.clickAndWaitForNewWindow();
				System.out.println("Airplane is closed!");
				sleep((long) 2000);
			}
			else if(aircloseButton.exists()){
				System.out.println("Airplane is already closed!");
			}
		}
	    getUiDevice().pressHome();
	    sleep((long)2000);
	    UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
	}
	
}

