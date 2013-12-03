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
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
* ����Ļ�����Ϣ���ܡ� 
*@author Ѧ���ƣ�BspTest��
*@version 0.1 2013/2/27
* ����ѹ������call_M0ģ��
*/
public class aaa extends UiAutomatorTestCase {   
	@SuppressWarnings("unused")
	//׼��������ʵ�ֽ������ܡ�
	protected void setUp() throws UiObjectNotFoundException, RemoteException, IOException {
		getUiDevice().sleep();
	    sleep((long) 2000.0);
	    getUiDevice().wakeUp();
	    sleep((long) 1000.0);
	    //��2A���� ͨ�����ֻ�����event
	    Process SendEvent1 = Runtime.getRuntime().exec("sendevent /dev/input/event0 4 4 9");
	    Process SendEvent2 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 1");
	    Process SendEvent3 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
	    Process SendEvent4 = Runtime.getRuntime().exec("sendevent /dev/input/event2 1 158 1");
	    Process SendEvent5 = Runtime.getRuntime().exec("sendevent /dev/input/event2 0 0 0");
	    Process SendEvent6 = Runtime.getRuntime().exec("sendevent /dev/input/event0 4 4 9");
	    Process SendEvent7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 0");
	    Process SendEvent8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
	    Process SendEvent9 = Runtime.getRuntime().exec("sendevent /dev/input/event2 1 158 0");
	    Process SendEvent10 = Runtime.getRuntime().exec("sendevent /dev/input/event2 0 0 0");
	    //��2����
	    /*Process SendEvent1 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 1");
	    Process SendEvent2 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
	    Process SendEvent3 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 1");
	    Process SendEvent4 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
	    Process SendEvent5 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 0");
	    Process SendEvent6 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
	    Process SendEvent7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 0");
	    Process SendEvent8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");*/
	    sleep((long) 2000.0);		
		}
	//caseִ�����Ժ���������ֻ��������״̬
	protected void tearDown() throws UiObjectNotFoundException, RemoteException, IOException { 
		getUiDevice().sleep();
	    sleep((long) 2000.0);
		}
		@SuppressWarnings({  "unused", "deprecation" })
		/**
		*call mo ����
		*@see #test
		*@param newFile1 ��case ʧ�ܵ��б�
		*@exception java.lang.exceptionthrowwhenswitchis1
		*@see /sdcard/string.xml xmlΪ ���������ļ����ļ��п�������case��ѭ������Loopnumber,callnumber
		*@see SimpleDateFormat ��ȡ��ǰʱ��
		*/
	public void test_MOCALL() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		//����������Ľ������
		File resultReport=new File("/sdcard/Music/fail_list.txt");
		resultReport.createNewFile();
		FileOutputStream inputResult=new FileOutputStream(resultReport);
		//��ȡXML�����ļ��Ĳ���
		DocumentBuilderFactory factory1 = DocumentBuilderFactory
	    	     .newInstance();
	    DocumentBuilder builder1 = factory1.newDocumentBuilder();
	    Document document1 = builder1.parse(new File("/sdcard/string.xml"));
	    Element rootElement1 = document1.getDocumentElement();
	    //��ȡ�أͣ��ļ��е�ѭ������
	    NodeList list_loop = rootElement1.getElementsByTagName("loopnum");
	    Element element_loop = (Element) list_loop.item(0);
	    String loopnum = element_loop.getChildNodes().item(0).getNodeValue();
	    //���ַ���ת������ֵ��
	    int loop = Integer.parseInt(loopnum, 10);
		for(int i = 0 ; i < loop ; i++){
			//��ȡstring.xml�ļ��е�callnumber packagenanme
		   DocumentBuilderFactory factory = DocumentBuilderFactory
		    	     .newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    //���·���ǿ��Ըı�ģ�������push���ļ���·��
		    Document document = builder.parse(new File("/sdcard/string.xml"));
		    Element rootElement = document.getDocumentElement();
		    NodeList list_callnum = rootElement.getElementsByTagName("callnum");
		    NodeList list_packagename = rootElement.getElementsByTagName("name");
		    Element element_package = (Element) list_packagename.item(0);
		    Element element_callnum = (Element) list_callnum.item(0);
		    //�������ļ��е�ֵ���и�ֵ�����ַ�����ֵ
		    String packagename;
		    packagename = element_package.getChildNodes().item(0).getNodeValue();
		    String callnumber;
		    callnumber= element_callnum.getChildNodes().item(0).getNodeValue();
		    String str3;
		    /*str3 = "am start -a " + packagename + " -d " + callnumber;
		    str3 = "am start -a android.intent.action.CALL -d tel:10086";*/
		    //����shellָ��
			Process startCall = Runtime.getRuntime().exec("am start -a " + packagename + " -d " + callnumber);
			/*sleep((long) 30000.0);
			String stra;
			Process p2 = Runtime.getRuntime().exec("dumpsys telephony.registry |busybox grep mCallState= ");
		    DataInputStream p4 = new DataInputStream(p2.getInputStream());
		    stra = p4.readLine();

			int result = p4.readLine().indexOf("  mCallState=2");
			if(result>=0){*/
		    sleep((long) 15000.0);
		    //�ж��Ƿ��ǽ�ͨ�绰����ȡtextview
		    UiObject settingsValidation = new UiObject(new UiSelector()
	         .textContains("00:"));
	      if(settingsValidation.exists()){
	    	  System.out.println("successfully");
			/*UiObject callView = new UiObject(new UiSelector()
			 .description("�Ҷ�"));
			callView.clickAndWaitForNewWindow();*/
			System.out.println("call is successfully!");
			//mi 2 �Ҷϼ�����
			sleep((long) 60000.0);
			getUiDevice().click(360,1191);
			sleep((long) 5000.0);
			getUiDevice().pressHome();
			}
			else{
				String logcat = new String();
				String failnote= "call is fail " + i+ "\n";
				//��ȡϵͳ��ǰʱ��� ����
				SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");
				inputResult.write((date1.format(new Date())+"  \n"+failnote+"  \n").getBytes());
				getUiDevice().click(360,1191);
				sleep((long) 10000.0);
				getUiDevice().pressHome();
				SimpleDateFormat date2 = new SimpleDateFormat("yyyyMMddHH_mm_ss");
				File newFile=new File("/sdcard/Music/"+date2.format(new Date()) + i + ".txt");
				newFile.createNewFile();
				sleep((long) 1000.0);
				FileOutputStream newlogcat=new FileOutputStream(newFile);
				//��������ִ���ʱ���һǧ��logcat��ץȡlogcatȻ���ӡ����
				Process getLogcat = Runtime.getRuntime().exec("logcat -t 1000");
				DataInputStream putoutLogcat = new DataInputStream(getLogcat.getInputStream());
				while ((logcat = putoutLogcat.readLine()) != null){
					//������Ķ�����ӡ
					newlogcat.write((logcat+"  \n").getBytes());
				}
				}    	
			}
		}
	public static void main(String args[]){ 
		//������������junit�����ĵ���.ʵ��setup tearDown�����ĵ���
		junit.textui.TestRunner.run(aaa.class);
	}
		}
