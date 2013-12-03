package com.bsptest;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class Activator extends UiAutomatorTestCase {   
	protected void setUp() throws UiObjectNotFoundException, RemoteException, IOException {
		getUiDevice().sleep();
	    sleep((long) 2000.0);
		getUiDevice().wakeUp();
		sleep((long) 2000.0);
	    getUiDevice().swipe(380, 935, 380, 1235, 3);
	    sleep((long) 2000.0);
	    getUiDevice().pressHome();
	    sleep((long) 5000.0);			
		}
	
	@SuppressWarnings("unused")
	protected void tearDown() throws UiObjectNotFoundException, RemoteException, IOException { 
		getUiDevice().click(360,1191);
		sleep((long) 5000.0);
		getUiDevice().pressHome();
		Runtime rt = Runtime.getRuntime();
		Process p4 = rt.exec("logcat -c");
		}
	
	@SuppressWarnings({  "unused", "deprecation" })
	public void testA1() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		File newFile1=new File("/sdcard/MIUI/fail_list.txt");
		for(int i = 0 ; i < 3 ; i++){
		    DocumentBuilderFactory factory = DocumentBuilderFactory
		    	     .newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = builder.parse(new File("/sdcard/MIUI/string.xml"));
		    Element rootElement = document.getDocumentElement();
		    NodeList list1 = rootElement.getElementsByTagName("callnum");
		    NodeList list = rootElement.getElementsByTagName("name");
		    Element element = (Element) list.item(0);
		    Element element1 = (Element) list1.item(0);
		    String str5;
		    str5 = element.getChildNodes().item(0).getNodeValue();
		    String str2;
		    str2= element1.getChildNodes().item(0).getNodeValue();
		    String str3;
		    str3 = "am start -a " + str5 + " -d " + str2;
			Process p1 = Runtime.getRuntime().exec(str3);
			sleep((long) 10000.0);
			String str;
			Process p2 = Runtime.getRuntime().exec("dumpsys telephony.registry |busybox grep mCallState= ");
		    DataInputStream p4 = new DataInputStream(p2.getInputStream());
		    str = p4.readLine();

			int result = p4.readLine().indexOf("  mCallState=2");
			if(result>=0){
				System.out.println("call is successfully!");
				getUiDevice().click(360,1191);
				sleep((long) 5000.0);
				}
			else{
				String str1 = new String();
				newFile1.createNewFile();
				sleep((long) 3000.0);
				FileOutputStream fo=new FileOutputStream(newFile1);
				String fail = new String();
				fail = "call is fail " + i+ "\n";
				fo.write(fail.getBytes());
				sleep((long) 5000.0);
				getUiDevice().click(360,1191);
				sleep((long) 5000.0);
				File newFile=new File("/sdcard/MIUI/" + i + ".txt");
				newFile.createNewFile();
				sleep((long) 3000.0);
				FileOutputStream fos=new FileOutputStream(newFile);
				Process p3 = Runtime.getRuntime().exec("logcat -t 600");
				DataInputStream p5 = new DataInputStream(p3.getInputStream());
				while ((str1 = p5.readLine()) != null){
					fos.write(str1.getBytes());
				}
				}    	
			}
		}	
	public void testA2(){
		try
		{
			WritableWorkbook book = Workbook.createWorkbook(new File("/sdcard/MIUI/result.xls"));
			WritableSheet sheet = book.createSheet(" µÚÒ»Ò³ ", 0);
			for(int i = 0; i < 10; i++){
				Label label = new Label( 0, i, "case" + i);
				sheet.addCell(label);
				jxl.write.Number number = new jxl.write.Number(1, i, i);
				sheet.addCell(number);
			}
				book.write();
				book.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public static void main(String args[]){ 
		junit.textui.TestRunner.run(Activator.class);
}
   
	
}