package Bsptest;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import android.os.RemoteException;
import android.util.Log;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

public class CallTest extends UiAutomatorTestCase
{	
	int i;
	private static final String ACTIVITY_TAG="bsptest";
	public String ReadXmlString(String name) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory factory1 = DocumentBuilderFactory
	    	     .newInstance();
	    DocumentBuilder builder1 = factory1.newDocumentBuilder();
	    Document document1 = builder1.parse(new File("/sdcard/string.xml"));
	    Element rootElement1 = document1.getDocumentElement();
	    NodeList list_loop = rootElement1.getElementsByTagName(name);
	    Element element_loop = (Element) list_loop.item(0);
	    String newname= element_loop.getChildNodes().item(0).getNodeValue();
		
	    return newname;
	}
	public int ReadXmlInt(String name) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory factory1 = DocumentBuilderFactory
	    	     .newInstance();
	    DocumentBuilder builder1 = factory1.newDocumentBuilder();
	    Document document1 = builder1.parse(new File("/sdcard/string.xml"));
	    Element rootElement1 = document1.getDocumentElement();
	    NodeList list_loop = rootElement1.getElementsByTagName(name);
	    Element element_loop = (Element) list_loop.item(0);
	    String newname= element_loop.getChildNodes().item(0).getNodeValue();
	    int loop = Integer.parseInt(newname, 10);
		
	    return loop;
	}
	@SuppressWarnings({ "unused" })
	public void testCallMo() throws IOException, UiObjectNotFoundException, RemoteException, ParserConfigurationException, SAXException
	{	
		for(i = 0; i < ReadXmlInt("loop");i++)
		{
			CrashBug();
			Process startCall= Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel:"+ReadXmlString("callNum"));
			UiObject MoreButton = new UiObject(new UiSelector().
		    		   className("android.widget.ToggleButton").index(0).instance(2));
			MoreButton.click();
			UiObject wait1App = new UiObject(new UiSelector().textContains("正在拨号"));
			wait1App.waitUntilGone((long) 60000.0);
			UiObject waitApp = new UiObject(new UiSelector().textContains("暂停通话"));

			if(waitApp.exists()&& waitApp.isEnabled())
			{
				Log.v(CallTest.ACTIVITY_TAG, "Modemtest CallMo Ok ------------"+i);
				if(ReadXmlInt("endcall") ==1)
				{
					sleep((long) ReadXmlInt("calltime"));
					UiObject endButton = new UiObject(new UiSelector().
				    		   className("android.widget.Button").index(1));
					if(endButton.exists()){
						endButton.click();
					}
				}else
				{
					sleep((long) ReadXmlInt("calltime"));
				}
				
	
			}else
			{
				UiObject endButton = new UiObject(new UiSelector().
			    		   className("android.widget.Button").index(1));
				if(endButton.exists()){
					endButton.click();
				}
				Log.v(CallTest.ACTIVITY_TAG, "Modemtest CallMo fail ------------"+i);
			}
			sleep((long) ReadXmlInt("looptime"));
	    
	
		}
		
	}
	
	/*public String ReadXmlString(String name) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory factory1 = DocumentBuilderFactory
	    	     .newInstance();
	    DocumentBuilder builder1 = factory1.newDocumentBuilder();
	    Document document1 = builder1.parse(new File("/sdcard/string.xml"));
	    Element rootElement1 = document1.getDocumentElement();
	    NodeList list_loop = rootElement1.getElementsByTagName(name);
	    Element element_loop = (Element) list_loop.item(0);
	    String newname= element_loop.getChildNodes().item(0).getNodeValue();
		
	    return newname;
	}
	*/
	/*public int ReadXmlInt(String name) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory factory1 = DocumentBuilderFactory
	    	     .newInstance();
	    DocumentBuilder builder1 = factory1.newDocumentBuilder();
	    Document document1 = builder1.parse(new File("/sdcard/string.xml"));
	    Element rootElement1 = document1.getDocumentElement();
	    NodeList list_loop = rootElement1.getElementsByTagName(name);
	    Element element_loop = (Element) list_loop.item(0);
	    String newname= element_loop.getChildNodes().item(0).getNodeValue();
	    int loop = Integer.parseInt(newname, 10);
		
	    return loop;
	}
*/
	
	public void CrashBug()
	{
		UiWatcher confirmDialogWatcher = new UiWatcher(){
			@Override
			public boolean checkForCondition() {
				UiObject okCancelDialog = new UiObject(new UiSelector().text("取消"));
				if(okCancelDialog.exists()){
					try {	
						Process p2 = Runtime.getRuntime().exec("screencap -p /sdcard/" + i + ".png");
						 sleep((long) 1000.0);
						okCancelDialog.click();
						 sleep((long) 1000.0);
						/*GetLog getLog = new GetLog();
						getLog.getBugreportLog();*/
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				return false;
			}
			
		};
		
		UiDevice.getInstance().registerWatcher("UIWATCHER", confirmDialogWatcher);
		UiDevice.getInstance().runWatchers();
	}

}
