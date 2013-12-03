package Bsptest;
import java.io.*;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import android.os.RemoteException; 
import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Data extends UiAutomatorTestCase {  
	
	public int i;
	private static final String ACTIVITY_TAG="bsptest";	
	@SuppressWarnings({  "unused", "deprecation" })
	public void test2SMSMO() throws ParserConfigurationException, SAXException, IOException, UiObjectNotFoundException {
		Process runSmsMo = Runtime.getRuntime().exec("am start com.android.settings/.TestingSettings");
		sleep((long) 5000.0);
		UiObject allsteView = new UiObject(new UiSelector().text("手机信息"));
		allsteView.clickAndWaitForNewWindow();
		sleep((long) 5000.0);
		for(int i = 1 ; i < ReadXmlInt("Dateloop")*2; i++)
		{
			Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/DateTest");
			UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
	  		appViews.scrollToEnd(2);
		    sleep((long) 20000);
		    if(i%2 != 0)
	  		{
		    	Process getLog1= Runtime.getRuntime().exec("logcat -b radio -c");
		  		 UiObject settingsApp = appViews.getChildByText(new UiSelector()
		         .className(android.widget.TextView.class.getName()), 
		         "TD-SCDMA only");
		  		settingsApp.click();
		  		UiObject add1App = new UiObject(new UiSelector()
		          .text("GSM only"));
		  		add1App.click();
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
					result1=log1.indexOf("changed sending intent rule=1 showPlmn='false'");
					sleep((long) 2000.0);
					System.out.println("xuejh--------------"+ i );
		    	}
		    	Process DeleteTxt = Runtime.getRuntime().exec("rm /sdcard/"+i+".txt");
		    	Log.v(Data.ACTIVITY_TAG, "Change GSM only is successfully------------"+(i+1)/2);
		    	sleep((long) ReadXmlInt("Datelooptime"));
    		}
		    else{
		    	Process getLog1= Runtime.getRuntime().exec("logcat -b radio -c");
		    	UiObject settingsApp = appViews.getChildByText(new UiSelector()
		         .className(android.widget.TextView.class.getName()), 
		         "GSM only");
		  		settingsApp.click();
		  		UiObject add1App = new UiObject(new UiSelector()
		          .text("TD-SCDMA only"));
		  		add1App.click();
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
					result1=log1.indexOf("changed sending intent rule=1 showPlmn='false'");
					sleep((long) 2000.0);
					System.out.println("xuejh--------------"+ i );
		    	}
		    	Process DeleteTxt = Runtime.getRuntime().exec("rm /sdcard/"+i+".txt");
		    	Log.v(Data.ACTIVITY_TAG, "Change TD only is successfully------------"+i/2);
		    	sleep((long) ReadXmlInt("Datelooptime"));
		    }
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
						
						okCancelDialog.click();
					} catch (UiObjectNotFoundException e) {
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
