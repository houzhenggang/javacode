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

public class SmsTest extends UiAutomatorTestCase
{	
	private static int i;
	private static final String ACTIVITY_TAG="bsptest";
	@SuppressWarnings({ "unused", "deprecation" })
	public void testCallMo() throws IOException, UiObjectNotFoundException, RemoteException, ParserConfigurationException, SAXException
	{	
		for(i = 0; i < ReadXmlInt("smsloop");i++)
		{
			CrashBug();
			Process startCall= Runtime.getRuntime().exec("am start -a android.intent.action.SENDTO -d sms:" + 
		                    ReadXmlString("smsNum") + " --es sms_body " + ReadXmlString("details"));
			sleep((long) 3000);
			UiObject SendButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(1));
			UiObject SendButton1 = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(3));
		    if (SendButton1.exists()) {
		    	SendButton1.click();
	        } else {
	        	SendButton.click();
	        }
		    sleep((long) 10000);
	    	int result1 = -1;
	    	String logcat;
	    	File logcatFile=new File("/sdcard/"+i+".txt");
			logcatFile.createNewFile();
	    	FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
	    	while(result1 == -1)
	    	{
	    		Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 10000");
	    		DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
				while ((logcat = inputLogcat1.readLine()) != null){
					outputLogcat.write(logcat.getBytes());				
				}
				BufferedReader bufferedReader1 = new BufferedReader(new FileReader(logcatFile));  
				String lineTXT1 ;
				lineTXT1 = bufferedReader1.readLine();
				String log1 = lineTXT1.toString().trim();
				result1=log1.indexOf("errorCode");
				sleep((long) 3000.0);
				Log.v(SmsTest.ACTIVITY_TAG, "sms send ------------"+i);
				System.out.println(result1);
	    	}
	    	Process getLog= Runtime.getRuntime().exec("am force-stop com.android.mms");
	    	sleep((long) 3000.0);
	    	Process getLog1= Runtime.getRuntime().exec("logcat -b radio -c");
	    	sleep((long) ReadXmlInt("smslooptime"));
	    
	
		}
		
	}
	
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
						GetLog getLog = new GetLog();
						getLog.getBugreportLog();
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
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

