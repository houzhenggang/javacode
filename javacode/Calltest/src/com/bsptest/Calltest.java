package com.bsptest;

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

	@SuppressWarnings({ "unused", "deprecation" })
	public void test_MOCALL() throws UiObjectNotFoundException, RemoteException, ParserConfigurationException, SAXException, IOException{
		/*Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/callMo");*/
		File resultReport=new File("/sdcard/fail_list.txt");
		resultReport.createNewFile();
		FileOutputStream inputResult=new FileOutputStream(resultReport);
		DocumentBuilderFactory factory1 = DocumentBuilderFactory
	    	     .newInstance();
	    DocumentBuilder builder1 = factory1.newDocumentBuilder();
	    Document document1 = builder1.parse(new File("/sdcard/string.xml"));
	    Element rootElement1 = document1.getDocumentElement();
	    NodeList list_loop = rootElement1.getElementsByTagName("loopnum");
	    Element element_loop = (Element) list_loop.item(0);
	    String loopnum = element_loop.getChildNodes().item(0).getNodeValue();
	    double loop = Integer.parseInt(loopnum, 10);
		for(int i = 1 ; i < loop ; i++){
		   DocumentBuilderFactory factory = DocumentBuilderFactory
		    	     .newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(new File("/sdcard/string.xml"));
		    Element rootElement = document.getDocumentElement();
		    NodeList list_callnum = rootElement.getElementsByTagName("callnum");
		    NodeList list_packagename = rootElement.getElementsByTagName("name");
		    NodeList list_calltime = rootElement.getElementsByTagName("calltime");
		    Element element_package = (Element) list_packagename.item(0);
		    Element element_callnum = (Element) list_callnum.item(0);
		    Element element_calltime = (Element) list_calltime.item(0);
		    String packagename;
		    packagename = element_package.getChildNodes().item(0).getNodeValue();
		    String callnumber;
		    callnumber= element_callnum.getChildNodes().item(0).getNodeValue();
			Process startCall = Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d " + callnumber);
			String calltime;
			calltime= element_calltime.getChildNodes().item(0).getNodeValue();
			int time = Integer.parseInt(calltime, 10);
		    sleep((long) time);
		    UiObject settingsValidation = new UiObject(new UiSelector()
	         .textContains("00:"));
	      if(settingsValidation.exists()){
			System.out.println("call is successfully!" + i);
			Element rootElement11 = document1.getDocumentElement();
		    NodeList list_loop1 = rootElement11.getElementsByTagName("takttime");
		    Element element_loop1 = (Element) list_loop1.item(0);
		    String loopnum1 = element_loop1.getChildNodes().item(0).getNodeValue();
		    double timetakt = Integer.parseInt(loopnum1, 10);
			/*NodeList list_takttime = element_loop.getElementsByTagName("takttime");
			Element element_takttime = (Element) list_takttime.item(0);
			String takttime;
			takttime = element_takttime.getChildNodes().item(0).getNodeValue();
			int timetakt = Integer.parseInt(takttime, 10);*/			
			sleep((long) timetakt);
			UiObject endButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(1));

		    if(endButton.exists() && endButton.isEnabled()) 
		    {
		    	endButton.click();
		    }
			sleep((long) 30000.0);
			getUiDevice().pressHome();
			/*Element rootElement11 = document1.getDocumentElement();
		    NodeList list_loop1 = rootElement11.getElementsByTagName("takttime");
		    Element element_loop1 = (Element) list_loop1.item(0);
		    String loopnum1 = element_loop1.getChildNodes().item(0).getNodeValue();
		    double timetakt = Integer.parseInt(loopnum1, 10);
			NodeList list_takttime = element_loop.getElementsByTagName("takttime");
			Element element_takttime = (Element) list_takttime.item(0);
			String takttime;
			takttime = element_takttime.getChildNodes().item(0).getNodeValue();
			int timetakt = Integer.parseInt(takttime, 10);			
			sleep((long) timetakt);*/
			}
	      
			else{
				String str1;
  				Element rootElement11 = document1.getDocumentElement();
  			    NodeList list_loop1 = rootElement11.getElementsByTagName("takttime");
  			    Element element_loop1 = (Element) list_loop1.item(0);
  			    String loopnum1 = element_loop1.getChildNodes().item(0).getNodeValue();
  			    double timetakt = Integer.parseInt(loopnum1, 10);
  				/*NodeList list_takttime = element_loop.getElementsByTagName("takttime");
  				Element element_takttime = (Element) list_takttime.item(0);
  				String takttime;
  				takttime = element_takttime.getChildNodes().item(0).getNodeValue();
  				int timetakt = Integer.parseInt(takttime, 10);*/			
  				sleep((long) timetakt);
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
  				/*Element rootElement11 = document1.getDocumentElement();
  			    NodeList list_loop1 = rootElement11.getElementsByTagName("takttime");
  			    Element element_loop1 = (Element) list_loop1.item(0);
  			    String loopnum1 = element_loop1.getChildNodes().item(0).getNodeValue();
  			    double timetakt = Integer.parseInt(loopnum1, 10);			
  				sleep((long) timetakt);*/
  				sleep((long) 30000.0);
				}    	
			}
		  
			}
		}

		
		
