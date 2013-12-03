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

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
@SuppressWarnings("unused")
public class ReplayVideo extends UiAutomatorTestCase{
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


	public void testVideo() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {
		Process camera = Runtime.getRuntime().exec("am start -n com.android.camera/.Camera");
		sleep((long)2000);
		 UiObject cameraapp = new UiObject(new UiSelector().className("android.widget.ImageView").description("最新照片"));
		 cameraapp.clickAndWaitForNewWindow();
	 	    sleep((long)2000);
	 	 
	 	 getUiDevice().click(540,960);
	 	    sleep((long)30000);
	 	 getUiDevice().pressHome();
	 	   
	    
	   }
}
	    