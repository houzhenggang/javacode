package Bsptest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class Airplane extends UiAutomatorTestCase {  
	public int i;
	private static final String ACTIVITY_TAG="bsptest";
	@SuppressWarnings({ "unused", "deprecation" })
	public void test() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		Process pc1 = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		sleep((long) 2000.0);
		UiObject allsteView = new UiObject(new UiSelector().text("全部设置"));
		allsteView.clickAndWaitForNewWindow();
		sleep((long) 2000.0);
		for(i = 1 ; i <  ReadXmlInt("Airplaneloop")*2 ; i++){
		CrashBug();
    	  Process p = Runtime.getRuntime().exec("logcat -b radio -c");
    	  sleep((long) 1000.0);
    	  UiObject SendButton = new UiObject(new UiSelector().
	    		   className("android.widget.CheckBox").index(0).instance(0));
		SendButton.click();
  		sleep((long) 10000.0);
  		if(i%2 != 0)
  		{
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
				result1=log1.indexOf("changed sending intent rule=3 showPlmn='false' plmn='null'");
				sleep((long) 2000.0);
	    	}
	    	Log.v(Airplane.ACTIVITY_TAG, "Open is successfully------------"+i/2);
	    	Process DeleteTxt = Runtime.getRuntime().exec("rm /sdcard/"+i+".txt");
	    	sleep((long) ReadXmlInt("Airplanelooptime"));

	    	
  		}
      else
      {
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
	    	}
	    	Process DeleteTxt = Runtime.getRuntime().exec("rm /sdcard/"+i+".txt");
	    	Log.v(Airplane.ACTIVITY_TAG, "Close is successfully------------"+i/2);
	    	sleep((long) ReadXmlInt("Airplanelooptime"));
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
