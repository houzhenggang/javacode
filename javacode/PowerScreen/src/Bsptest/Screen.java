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

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;



@SuppressWarnings("unused")
public class Screen extends UiAutomatorTestCase{
	private int X=1;
	private int X1=1;
	private int Y=1;
	private int X2=1;
	private int Y2=1;
	private int Y3=1;
	
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

	public void testScreen() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {
		Process endall = Runtime.getRuntime().exec("am kill all background processes");
		getUiDevice().pressHome();
		sleep((long)2000);
	   }

	public void testScreen1() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {
		for (int i=0; i<3000; i++){
			getUiDevice().swipe( 100,300, 800,300, 10);
			sleep((long)200);
			getUiDevice().swipe( 800,300, 100,300, 10);
		}
		getUiDevice().pressHome();
		}
	public void testScreen2() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException
	   {
		Process endall = Runtime.getRuntime().exec("am start com.android.contacts");
         sleep((long)2000);	
         for (int i=0; i<3000; i++){
 			getUiDevice().swipe( 400,150, 400,1500, 10);
 			sleep((long)200);
 			getUiDevice().swipe( 400,1500, 400,150, 10);
 		}
 		getUiDevice().pressHome();
 		sleep((long)2000);
	   }
 		
	 }