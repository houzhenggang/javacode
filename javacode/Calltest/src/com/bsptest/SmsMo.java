package com.bsptest;

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
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class SmsMo extends UiAutomatorTestCase {  
	

	/*protected void setUp() throws UiObjectNotFoundException, RemoteException, IOException {
		getUiDevice().sleep();
	    sleep((long) 2000.0);
	    getUiDevice().wakeUp();
	    sleep((long) 1000.0);
	    Util util = new Util();
	    util.testUnlock();		
		}
	

	protected void tearDown() throws UiObjectNotFoundException, RemoteException, IOException { 
		getUiDevice().sleep();
	    sleep((long) 2000.0);
		}*/

	@SuppressWarnings({  "unused", "deprecation" })
	public void test_SMSMO() throws ParserConfigurationException, SAXException, IOException, UiObjectNotFoundException {
		Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/smsMo");
		File resultReport=new File("/sdcard/smsMo/fail_list.txt");
		resultReport.createNewFile();
		FileOutputStream inputResult=new FileOutputStream(resultReport);
		DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder1 = factory1.newDocumentBuilder();
	    Document readXml = builder1.parse(new File("/sdcard/string.xml"));
	    Element element_loop = readXml.getDocumentElement();
	    NodeList list_loop = element_loop.getElementsByTagName("loopnum");
	    Element element_loopnum = (Element) list_loop.item(0);
	    String loopnum = element_loopnum.getChildNodes().item(0).getNodeValue();
	    int loop = Integer.parseInt(loopnum, 10);
		for(int i = 1 ; i < loop ; i++){
			Process p0 = Runtime.getRuntime().exec("logcat -b radio -c");
		    NodeList list_smsnum = element_loop.getElementsByTagName("Smsnum");
		    NodeList list_details = element_loop.getElementsByTagName("Details");
		    Element element_smsnum = (Element) list_details.item(0);
		    Element element_details = (Element) list_smsnum.item(0);
		    String details = element_smsnum.getChildNodes().item(0).getNodeValue();
		    String smsNumber = element_details.getChildNodes().item(0).getNodeValue();
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
		    NodeList list_sendtime = element_loop.getElementsByTagName("sendtime");
			Element element_sendtime = (Element) list_sendtime.item(0);
			String sendtime = element_sendtime.getChildNodes().item(0).getNodeValue();
			int timesend = Integer.parseInt(sendtime, 10);			
		    sleep((long) timesend);
			String logcat;
			File logcatFile=new File("/sdcard/smsMo/"+i+".txt");
			logcatFile.createNewFile();
			FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
			Process getLogcat = Runtime.getRuntime().exec("logcat -b radio -t 1000");
			DataInputStream inputLogcat = new DataInputStream(getLogcat.getInputStream());
			while ((logcat = inputLogcat.readLine()) != null){
				outputLogcat.write(logcat.getBytes());				
			}
			sleep((long) 3000.0);
			try {  
	            String encoding = "GBK"; 
	            if (logcatFile.isFile() && logcatFile.exists()) {  
	                InputStreamReader read = new InputStreamReader(new FileInputStream(logcatFile), encoding);  
	                BufferedReader bufferedReader = new BufferedReader(read);  
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
	         				NodeList list_takttime = element_loop.getElementsByTagName("takttime");
	         				Element element_takttime = (Element) list_takttime.item(0);
	         				String takttime = element_takttime.getChildNodes().item(0).getNodeValue();
	         				int timetakt = Integer.parseInt(takttime, 10);			
	         			    sleep((long) timetakt);
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
	          					String log1 = "bugreport>/sdcard/xue"+i+".txt";
	          					Process p1 = Runtime.getRuntime().exec("\"" + log1+"\"");
	          					sleep((long) 60000.0);
	          					/*String faill_logcat;
	          					File logcatFile1=new File("/sdcard/smsMo/log"+i+".txt");
	          					logcatFile1.createNewFile();
	          					FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1);
	          					Process getLogcat1 = Runtime.getRuntime().exec("logcat -t 2000");
	          					DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
	          					while ((faill_logcat = inputLogcat1.readLine()) != null){
	          						outputLogcat1.write((faill_logcat+"  \n").getBytes());
	          					}*/
	          				} catch (IOException e) {
	          					// TODO Auto-generated catch block
	          					e.printStackTrace();
	          				}
	          				NodeList list_takttime = element_loop.getElementsByTagName("takttime");
	        				Element element_takttime = (Element) list_takttime.item(0);
	        				String takttime = element_takttime.getChildNodes().item(0).getNodeValue();
	        				int timetakt = Integer.parseInt(takttime, 10);			
	        			    sleep((long) timetakt);
	                     }
	                }  
	                read.close();
	                }else{  
	              System.out.println("failed");              }  
	        } catch (Exception e) {  
	            System.out.println("failed1");  
	            e.printStackTrace();  
	        }
			}
			
		}
	public static void main(String args[])
	{ 
		junit.textui.TestRunner.run(SmsMo.class);
	}
   
	
}
