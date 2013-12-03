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
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
@SuppressWarnings("unused")
public class BlueToothTest extends UiAutomatorTestCase {

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

	public void testBT() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {		
		Process endall = Runtime.getRuntime().exec("am kill all background processes");
		getUiDevice().pressHome();
		String bluetooth = ReadXmlString("bluetooth");
		String openbt = "open";
		String closebt = "close";
		Process settings = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        UiObject btApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "蓝牙");
 	    btApp.clickAndWaitForNewWindow();
 	    UiObject btButton = new UiObject(new UiSelector().className("android.widget.CheckBox").index(0));
		UiObject btopenButton = new UiObject(new UiSelector().className("android.widget.CheckBox").index(0).checked(true));
		UiObject btcloseButton = new UiObject(new UiSelector().className("android.widget.CheckBox").index(0).checked(false));
		if(bluetooth.equalsIgnoreCase(openbt)){				
			if(btopenButton.exists() && btopenButton.isEnabled()){
				System.out.println("BlueTooth is already opened!");
			}
			else if(btcloseButton.exists() && btcloseButton.isEnabled()){
				btButton.clickAndWaitForNewWindow();
				System.out.println("BlueTooth is opened!");
				sleep((long) 2000);
			}
			UiObject searchButton = new UiObject(new UiSelector().className("android.widget.Button").text("搜索设备"));	
			if(searchButton.exists() && searchButton.isEnabled()){
				searchButton.clickAndWaitForNewWindow();
			}
			String btname = ReadXmlString("btname");
			UiObject btnameTab = new UiObject(new UiSelector().className("android.widget.TextView").text(btname));
			if(btnameTab.exists() && btnameTab.isEnabled()){
				btnameTab.clickAndWaitForNewWindow();
				UiObject btmatchTab = new UiObject(new UiSelector().className("android.widget.Button").text("配对"));
				if(btmatchTab.exists() && btmatchTab.isEnabled()){
					btmatchTab.clickAndWaitForNewWindow();
				}
		    }
		}
		else if(bluetooth.equalsIgnoreCase(closebt)){
			if(btopenButton.exists() && btopenButton.isEnabled()){
				btButton.clickAndWaitForNewWindow();
				System.out.println("BlueTooth is closed!");
				sleep((long) 2000);
			}
			else if(btcloseButton.exists() && btcloseButton.isEnabled()){
				System.out.println("BlueTooth is already closed!");
			}
		}
	    getUiDevice().pressHome();
	    sleep((long)2000);
	    UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
	}
}

