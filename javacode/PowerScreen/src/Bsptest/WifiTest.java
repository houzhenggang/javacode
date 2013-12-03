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
public class WifiTest extends UiAutomatorTestCase{
	
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

	public void testWifi() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {		
		Process endall = Runtime.getRuntime().exec("am kill all background processes");
		getUiDevice().pressHome();
		String wifi = ReadXmlString("wifi");
		String openwifi = "open";
		String closewifi = "close";
		Process settings = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        UiObject wifiApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "WLAN");
    	wifiApp.clickAndWaitForNewWindow();
    	UiObject wifiButton = new UiObject(new UiSelector().className("android.widget.CheckBox").index(0));
		UiObject wifiopenButton = new UiObject(new UiSelector().className("android.widget.CheckBox").index(0).checked(true).enabled(true));
		UiObject wificloseButton = new UiObject(new UiSelector().className("android.widget.CheckBox").index(0).checked(false).enabled(true));
		if(wifi.equalsIgnoreCase(openwifi)){				
			if(wifiopenButton.exists() && wifiopenButton.isEnabled()){
				System.out.println("Wifi is already opened!");
			}
			else if(wificloseButton.exists()&& wificloseButton.isEnabled()){
				wifiButton.clickAndWaitForNewWindow();
				System.out.println("Wifi is opened!");
				sleep((long) 2000);
			}
			String wifiname = ReadXmlString("wifiname");
			UiObject wifinameTab = new UiObject(new UiSelector().className("android.widget.TextView").text(wifiname));
			if(wifinameTab.exists() && wifinameTab.isEnabled()){
				wifinameTab.clickAndWaitForNewWindow();
				String admin = ReadXmlString("wifiadmin");
				String password = ReadXmlString("wifipassword");
				UiObject adminTab = new UiObject(new UiSelector().className("android.widget.TextView").text("身份"));
				UiObject passwordTab = new UiObject(new UiSelector().className("android.widget.TextView").text("密码"));
				UiObject sendTab = new UiObject(new UiSelector().className("android.widget.EditText").index(1).instance(0));
				UiObject send1Tab = new UiObject(new UiSelector().className("android.widget.EditText").index(1).instance(1));
				if(adminTab.exists() && passwordTab.exists()){
					sendTab.setText(admin);
					sleep((long)2000);
					send1Tab.setText(password);
					sleep((long)2000);
				}
				else if(sendTab.exists()){
					sendTab.setText(password);
					sleep((long)2000);
				}
				UiObject sendButton = new UiObject(new UiSelector().className("android.widget.Button").text("连接"));
				if(sendButton.exists()){sendButton.clickAndWaitForNewWindow();}
				sleep((long) 10000);
			}
		}
		else if(wifi.equalsIgnoreCase(closewifi)){
			if(wifiopenButton.exists() && wifiopenButton.isEnabled()){
				wifiButton.clickAndWaitForNewWindow();
				System.out.println("Wifi is closed!");
				sleep((long) 2000);
			}
			else if(wificloseButton.exists()&& wificloseButton.isEnabled()){
				System.out.println("Wifi is already closed!");
			}
		}
	    sleep((long)2000);
	    UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
	   }
	}
