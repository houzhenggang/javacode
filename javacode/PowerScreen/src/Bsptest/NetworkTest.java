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
public class NetworkTest extends UiAutomatorTestCase{
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

	public void testNetwork() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {
		Process endall = Runtime.getRuntime().exec("am kill all background processes");
		getUiDevice().pressHome();
		String network = ReadXmlString("network");
		String GSMonly = "2Gonly";
		String WCDMAonly = "3Gonly";
		String WCDMAprefer = "3Gprefer";		
		Process settings = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        UiObject networkApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "移动网络");
 	    networkApp.clickAndWaitForNewWindow();
 	    sleep((long)2000);
 	    UiObject networktypeTab = new UiObject(new UiSelector().className("android.widget.TextView").text("网络类型选择"));
 	    networktypeTab.clickAndWaitForNewWindow();
 	    sleep((long)2000);
		UiObject type1Button = new UiObject(new UiSelector().className("android.widget.TextView").text("3G网络优先"));
		UiObject type2Button = new UiObject(new UiSelector().className("android.widget.TextView").text("仅使用2G网络(更省电)"));
		UiObject type3Button = new UiObject(new UiSelector().className("android.widget.TextView").text("仅使用3G网络"));
		type3Button.clickAndWaitForNewWindow();
		sleep((long)2000);
	    UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_POWER);
		
		}
	 
}
