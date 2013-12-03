package bsptest;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import bsptest.Util;

/**
* ����Ļ�����Ϣ���ܡ� 
*@author Ѧ���ƣ�BspTest��
*@version 0.1 2013/2/27
* ����ѹ������SMSmoģ��
*
*/
public class SmsMo extends UiAutomatorTestCase {  
	
	//׼��������ʵ�ֽ������ܡ�
	protected void setUp() throws UiObjectNotFoundException, RemoteException, IOException {
		getUiDevice().sleep();
	    sleep((long) 2000.0);
	    getUiDevice().wakeUp();
	    sleep((long) 1000.0);
	    //����Util�еĽ�������
	    Util util = new Util();
	    util.testUnlock();
	    sleep((long) 2000.0);		
		}
	
	//caseִ�����Ժ���������ֻ��������״̬
	protected void tearDown() throws UiObjectNotFoundException, RemoteException, IOException { 
		getUiDevice().sleep();
	    sleep((long) 2000.0);
		}
	
	/**
	*SMSMO ����
	*@see #test
	*@param newFile1 ��case ʧ�ܵ��б�
	*@exception java.lang.exceptionthrowwhenswitchis1
	*@see /sdcard/string.xml xmlΪ ���������ļ����ļ��п�������case��ѭ������Loopnumber,Smsnumber,Details
	*@see setUp ׼��������ʵ�ֽ������ܡ�
	*@see tearDown caseִ�����Ժ���������ֻ��������״̬��
	*import Util������
	*/
	@SuppressWarnings({  "unused", "deprecation" })
	public void test_SMSMO() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder1 = factory1.newDocumentBuilder();
	    Document readXml = builder1.parse(new File("/sdcard/MIUI/string.xml"));
	    Element element_loop = readXml.getDocumentElement();
	    NodeList list_loop = element_loop.getElementsByTagName("loopnum");
	    Element element_loopnum = (Element) list_loop.item(0);
	    String loopnum = element_loopnum.getChildNodes().item(0).getNodeValue();
	    int loop = Integer.parseInt(loopnum, 10);
		for(int i = 0 ; i < loop ; i++){
			Process p0 = Runtime.getRuntime().exec("logcat -b radio -c");
		    NodeList list_smsnum = element_loop.getElementsByTagName("Smsnum");
		    NodeList list_details = element_loop.getElementsByTagName("Details");
		    Element element_smsnum = (Element) list_details.item(0);
		    Element element_details = (Element) list_smsnum.item(0);
		    String smsNumber = element_smsnum.getChildNodes().item(0).getNodeValue();
		    String details = element_details.getChildNodes().item(0).getNodeValue();
			Process runSmsMo = Runtime.getRuntime().exec("am start -a android.intent.action.SENDTO -d " + smsNumber + " --es " + details);
			sleep((long) 5000.0);
			getUiDevice().click(673,1223);
			sleep((long) 20000.0);
			//��ȡ��Ϣ�Ƿ��ͳɹ�����Ϣ
			Process getLogcat = Runtime.getRuntime().exec("logcat -b radio |busybox grep errorCode");
			sleep((long) 2000.0);
		    DataInputStream logcat = new DataInputStream(getLogcat.getInputStream());
		    String outputLogcat = logcat.readLine();
		    /*assertTrue("Unable to detect wifi", logcat.readLine() != null);
			int result = logcat.readLine().indexOf("SEND_SMS");*/
			if(logcat.readLine() != null){
				sleep((long) 2000.0);
				System.out.println("SMS_Send  is successfully!");
				sleep((long) 2000.0);
				getUiDevice().pressHome();
				sleep((long) 2000.0);
				Process p5 = Runtime.getRuntime().exec("am kill com.android.mms");
				}
			else{
				String str1;
				sleep((long) 2000.0);
				getUiDevice().pressHome();
				System.out.println("SMS_Send  is fail!");
				sleep((long) 3000.0);
				Process p6 = Runtime.getRuntime().exec("am kill com.android.mms");
				//����util���е�testLog������ץȡlogcat
				Util util = new Util();
				util.testLog();
				}    	
			}
		}
	public static void main(String args[]){ 
		junit.textui.TestRunner.run(SmsMo.class);
		}
   
	
}
