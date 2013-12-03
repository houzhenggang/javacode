package bsptest.stability;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import android.os.RemoteException;
import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
* 调用接口的具体函数<br>
* For example:<br>
* IStability interface1 = new MI3();<br>
* interface1.CallMotest();<br>
* @see bsptest.stability.IStability
* @version 1.0, 2013-08-23
* @author 薛敬浩
* @return
*/ 

public class StabilityTest extends UiAutomatorTestCase 
{
	/**给全局变量赋值，值为{@value}*/
	private int k = 1;
	/**给接口确定具体的实现方法，值为{@value}*/
	public static IStability interface1 = new MI3();
	
	// 在XML中读取int 类型的数值
	private int ReadXmlInt(String xmlname,String name) throws ParserConfigurationException, SAXException, IOException
	{
		File f = new File("/sdcard/settings.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(f);
		NodeList nl = doc.getElementsByTagName(xmlname);
		String newname = doc.getElementsByTagName(name).item(0).getFirstChild().getNodeValue();
		int loop = Integer.parseInt(newname, 10);
		return loop;
	}
	
	@SuppressWarnings("unused")
	public void setUp() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		UiDevice.getInstance().pressHome();
		sleep((long) 1000.0); 
	}
	
	
	@SuppressWarnings("unused")
	public void tearDown() throws UiObjectNotFoundException, RemoteException, IOException 
	{ 
		UiDevice.getInstance().pressHome();
		sleep((long) 1000.0);
		Process SendEvent1 = Runtime.getRuntime().exec(" am Kill all background processes.");
		sleep((long) 1000.0);  		
		Log.v("Mi3 stability test", "case --------------"+k);
		k++;
	}
	
	/**
	 *ReadXmlInt(string) 可以读取XML中的int型数据。<br> 
	 *ReadXmlString(string)可以读取XML中的string型字符串。<br> 
	 * *UiObject waitApp = new UiObject(new UiSelector().textContains("暂停通话"))
	 *的存在与否可以判断通话是否成功。<br> 
	 *Log.v(MI3.ACTIVITY_TAG, "Modemtest CallMo fail ------------"+i)
	 *会将运行fail的结果输入到logcat中<br>
	 *@param ReadXmlInt("loop") 为循环的Loop次数<br> 
	 *@param ReadXmlString("callNum") 为拨打电话的号码<br> 
	 *@return true if the device has a phone else false
	*/
	public void test01Calltest() throws Exception 
	{
		interface1.CallMotest();	
	}
	
	public void test02SmsMoTest() throws Exception 
	{
		interface1.SmsMoTest();	
	}
	
	public void test03AirPlane() throws Exception 
	{
		interface1.AirPlane();	
	}
	
	public void test04DataChange() throws Exception 
	{
		interface1.DataChange();	
	}
	
	public void test05Camera() throws Exception 
	{
		for(int i = 1 ; i <= ReadXmlInt("Camera","Cameraloop"); i++)
		{
			interface1.StartCamera();
			sleep((long) 1000);
			interface1.BackPhotograph();
			sleep((long) 1000);
			interface1.AheadPhotograph();
			sleep((long) 1000);
			interface1.BackVideo();
			sleep((long) 1000);
			interface1.AheadVideo();
			sleep((long) 1000);
			interface1.CloseCamera();
		}
	}
}
