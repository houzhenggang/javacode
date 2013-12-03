package com.bsrtest;

import java.io.*;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import android.os.RemoteException;

/**
 * 程序的基本信息介绍。 
*@author 薛敬浩（BspTest）
*@version 1.0 2012/12/19
*/
public class Readxml extends UiAutomatorTestCase {
	/**
	*读取手机ＸＭＬ参数的配置变量
	*@see #testA1
	*如有不懂之处可以随时询问
	*@param newFile1 　case 失败的列表
	*@param str2 dat tel:10086
	*@param str1 act android.intent.action.CALL
	*@param str3 开启打电话给10086
	*执行拨打电话case
	*@exception java.lang.exceptionthrowwhenswitchis1
	*/
	@SuppressWarnings({  "unused", "deprecation" })
	public void testA1() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 3 ; i++){
		getUiDevice().sleep();
	    sleep((long) 2000.0);
		getUiDevice().wakeUp();
		sleep((long) 2000.0);
	    getUiDevice().swipe(380, 935, 380, 1235, 3);
	    sleep((long) 2000.0);
	    File newFile1=new File("/sdcard/MIUI/fail_list.txt");
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
			String str1;
			newFile1.createNewFile();
			sleep((long) 3000.0);
			FileOutputStream fo=new FileOutputStream(newFile1);
			String fail = "call is fail " + i+ "\n";
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
	 
	    
	public static void main(String args[]){ 
		/**
		*在main( )方法中使用的显示用字符串
		*用来运行case
		*@see #main(java.lang.String[])
		*/
		junit.textui.TestRunner.run(Readxml.class);
}
}
