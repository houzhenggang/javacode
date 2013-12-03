package Bsptest;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.android.uiautomator.core.UiObjectNotFoundException;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class CallTest extends UiAutomatorTestCase 
{ 
	AllFactory allfactory = new MIUIFactory();
	Interface1 interface1 = allfactory.create();
	protected void setUp() throws UiObjectNotFoundException, IOException 
	{
	
	}
	
	protected void tearDown() throws UiObjectNotFoundException, IOException 
	{ 

	}
	
	public void test02Calltest() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException 
	{
		interface1.CallMotest();	
	}
	public void test03Smstest() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException 
	{
		interface1.SmsMoTest();
	}
}
