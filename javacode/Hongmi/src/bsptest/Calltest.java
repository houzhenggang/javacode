package bsptest;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.Date;
import java.text.SimpleDateFormat;
import android.os.RemoteException;
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Calltest extends UiAutomatorTestCase {   
	private int index;
	private int instance;
	public int getIndex()
	{
		return index;
	}
	public void setIndex(int index)
	{
		this.index = index;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	public void test1MOCALL() throws UiObjectNotFoundException, RemoteException, ParserConfigurationException, SAXException, IOException{
		Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/callMo");
		File resultReport=new File("/sdcard/fail_list.txt");
		resultReport.createNewFile();
		FileOutputStream inputResult=new FileOutputStream(resultReport);
		for(int i = 1 ; i < 500 ; i++){
			String callnum = "10010";
			Process startCall = Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel:"+callnum);
		    sleep((long) 15000);
		    UiObject settingsValidation = new UiObject(new UiSelector()
	         .textContains("00:"));
	      if(settingsValidation.exists()){
			System.out.println("call is successfully!" + i);			
			sleep((long) 20000);
			Calltest index1 = new Calltest();
			index1.setIndex(1);
			UiObject endButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(index1.getIndex()));

		    if(endButton.exists() && endButton.isEnabled()) 
		    {
		    	endButton.click();
		    }
			sleep((long) 5000.0);
			getUiDevice().pressHome();
			}
	      
			else{
				String str1;
				sleep((long) 20000);
  				UiObject endButton = new UiObject(new UiSelector().
  		    		   className("android.widget.Button").index(1));
   				if (endButton.exists()) {
   					endButton.click();
   					sleep((long) 2000.0);
   					getUiDevice().pressHome();
   		        } else {
   		        	getUiDevice().pressHome();
   		        }
   				System.out.println("call is fail!" + i);
  				String failnote = "call is fail_"+i;
  				SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
				inputResult.write((date1.format(new Date())+"  \n"+failnote+"  \n").getBytes());
  				sleep((long) 1000.0);
  				try {
  					String faill_logcat;
  					File logcatFile1=new File("/sdcard/log"+i+".txt");
  					logcatFile1.createNewFile();
  					FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1);
  					Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 2000");
  					DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
  					while ((faill_logcat = inputLogcat1.readLine()) != null){
  						outputLogcat1.write((faill_logcat+"  \n").getBytes());
  					}
  				} catch (IOException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				}
  				sleep((long) 10000.0);
				}    	
			}
		  
			}
	/*@SuppressWarnings({  "unused", "deprecation" })
	public void test2SMSMO() throws ParserConfigurationException, SAXException, IOException, UiObjectNotFoundException {
		Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/smsMo");
		File resultReport=new File("/sdcard/smsMo/fail_list.txt");
		resultReport.createNewFile();
		FileOutputStream inputResult=new FileOutputStream(resultReport);
		for(int i = 1 ; i < 300 ; i++){
			Process p0 = Runtime.getRuntime().exec("logcat -b radio -c");
			String smsNumber = "10086";
			String details = "cxyl";
			Process runSmsMo = Runtime.getRuntime().exec("am start -a android.intent.action.SENDTO -d sms:" + smsNumber + " --es sms_body " + details);
			sleep((long) 5000.0);
			UiObject SendButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(1));
			UiObject SendButton1 = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(3));
		    if (SendButton1.exists()) {
		    	SendButton1.click();
	        } else {
	        	SendButton.click();
	        }
		    sleep((long) 20000);
			String logcat;
			File logcatFile=new File("/sdcard/smsMo/"+i+".txt");
			logcatFile.createNewFile();
			FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
			Process getLogcat = Runtime.getRuntime().exec("logcat -b radio -t 1500");
			DataInputStream inputLogcat = new DataInputStream(getLogcat.getInputStream());
			while ((logcat = inputLogcat.readLine()) != null){
				outputLogcat.write(logcat.getBytes());				
			}
			sleep((long) 3000.0);
			try {  
	            String encoding = "GBK"; 
	            if (logcatFile.isFile() && logcatFile.exists()) {  
	                InputStreamReader read = new InputStreamReader(new FileInputStream(logcatFile), encoding);  
	                BufferedReader bufferedReader = new BufferedReader(new FileReader(logcatFile));  
	                String lineTXT;  
	                if ((lineTXT = bufferedReader.readLine()) != null) {  
	                	 String log = lineTXT.toString().trim();
	                     int result=log.indexOf("errorCode");
	                     if(result != -1)
	                     { 
	                    	sleep((long) 2000.0);
	         				System.out.println("SMS_Send  is successfully!");
	         				sleep((long) 2000.0);
	         				getUiDevice().pressHome();
	         				sleep((long) 2000.0);
	         				Process p5 = Runtime.getRuntime().exec("am force-stop com.android.mms");		
	         			    sleep((long) 20000);
	                     }
	                     else
	                     {
	                    	String str1;
	          				sleep((long) 2000.0);
	          				getUiDevice().pressHome();
	          				System.out.println("SMS_Send  is fail!");
	          				String failnote = "SMS_Send  is fail_"+i;
	          				SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
	        				inputResult.write((date1.format(new Date())+"  \n"+failnote+"  \n").getBytes());
	          				sleep((long) 3000.0);
	          				Process p6 = Runtime.getRuntime().exec("am force-stop com.android.mms");
	          				sleep((long) 1000.0);
	          				try {
	          					String faill_logcat;
	          					File logcatFile1=new File("/sdcard/smsMo/log"+i+".txt");
	          					logcatFile1.createNewFile();
	          					FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1);
	          					Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 1000");
	          					DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
	          					while ((faill_logcat = inputLogcat1.readLine()) != null){
	          						outputLogcat1.write((faill_logcat+"  \n").getBytes());
	          					}
	          				} catch (IOException e) {
	          					// TODO Auto-generated catch block
	          					e.printStackTrace();
	          				}		
	        			    sleep((long) 10000);
	                     }
	                }  
	                }else{  
	              System.out.println("failed");              }  
	        } catch (Exception e) {  
	            System.out.println("failed1");  
	            e.printStackTrace();  
	        }
			}
			
		}*/
		}

		
		
