package Bsptest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.os.RemoteException;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Demo extends UiAutomatorTestCase
{
	private static String fileName = "/data/local/tmp/UIautomatorStudy.jar";
	private static String s_xmlpath="/src/Bsptest/9.xml"; 
	private static String newname;
	/*//url 为xml的路径 name 为读取String参数的具体名称
	private String ReadXmlString(String url, String name) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder1 = factory1.newDocumentBuilder();
		Document document1 = builder1.parse(new File(url));
		Element rootElement1 = document1.getDocumentElement(); 
		NodeList list_loop = rootElement1.getElementsByTagName(name);
		Element element_loop = (Element) list_loop.item(0);
		String newname= element_loop.getChildNodes().item(0).getNodeValue();
		
		return newname;
	}
	
	// 在XML中读取int 类型的数值
		private int ReadXmlInt(String url ,String name) throws ParserConfigurationException, SAXException, IOException
		{
			DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder1 = factory1.newDocumentBuilder();
			Document document1 = builder1.parse(new File(url));
			Element rootElement1 = document1.getDocumentElement();
			NodeList list_loop = rootElement1.getElementsByTagName(name);
			Element element_loop = (Element) list_loop.item(0);
			String newname= element_loop.getChildNodes().item(0).getNodeValue();
			int loop = Integer.parseInt(newname, 10);
			return loop;
		}*/
	
	private static void readFile(InputStream input) throws Exception {   
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(isr);      
        String line;      
        while ((line = reader.readLine()) != null) {      
            System.out.println(line);      
        }      
        reader.close();      
    } 
	
	private static String getParameter1(String packageName,String voidName,String parameterName) throws Exception
	{
		JarFile jarFile  = new JarFile(fileName);
		JarEntry entry = jarFile.getJarEntry(s_xmlpath);  
        InputStream input = jarFile.getInputStream(entry);
        readFile(input);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(input);
		//获取一级目录的参数名
		NodeList nl = doc.getElementsByTagName("package");
		for (int i = 0;i < nl.getLength();i++){
			NamedNodeMap attributes = nl.item(i).getAttributes(); 
			Node detailNode = attributes.item(0);
			String deta = detailNode.getNodeValue();
			if(deta.equals(packageName)){
				//获取二级目录的参数名
				NodeList nl1 = doc.getElementsByTagName("method");
					for (int j = 0;j < nl1.getLength();j++){
					NamedNodeMap attributes1 = nl1.item(j).getAttributes(); 
					Node detailNode1 = attributes1.item(0);
					String deta1 = detailNode1.getNodeValue();
					if(deta1.equals(voidName)){
						//返回获取的参数
						NodeList namelist1=doc.getElementsByTagName(parameterName);
						for(int z=0;z<namelist1.getLength();z++){
							newname = doc.getElementsByTagName(parameterName).item(z).getFirstChild().getNodeValue();
						}
					}
				}
			}
		}
		return newname;
	}
	
	public void testDemo() throws Exception
	{
/*		//点击Home键
		getUiDevice().pressHome();
		getUiDevice().sleep();
		//在CDM中输出字符串
		System.out.println("Click Home Button ");
		
		UiCollection videos = new UiCollection(new UiSelector()
		   .className("android.widget.LinearLayout"));
		int count = videos.getChildCount(new UiSelector()
		   .className("android.widget.LinearLayout"));
		System.out.println(count);
		 getUiDevice().setOrientationRight();
		 getUiDevice().unfreezeRotation();
		UiScrollable appViews = new UiScrollable(new UiSelector()
		.scrollable(true));
		UiObject settingsApp = appViews.getChildByInstance(new UiSelector().className(android.widget.Spinner.class.getName()).index(26),0);
		settingsApp.clickAndWaitForNewWindow();
		System.out.println(ReadXmlString("/sdcard/string.xml","loop"));
		sleep((long) 1000.0); 
		sleep((long) 1000.0);
		UiDevice.getInstance().pressKeyCode(KeyEvent.KEYCODE_BACK);
		UiObject Settings = new UiObject( new UiSelector().index(0));
		String sshhs = Settings.getPackageName();
		Settings.clickAndWaitForNewWindow();
		String xjh = UiDevice.getInstance().getLastTraversedText();
		System.out.println(sshhs);
		sleep((long) 1000.0);*/
		/*try {
			UiObject Button = new UiObject(new UiSelector().textContains("相机"));
			Button.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				//调用System.setErr()方法，将out流写入文件，因为printStackTrace是把消息输出到System.err的，所以要只要重定向System.err即可
				//如果不重新定向，e.printStackTrace()只作为cmd端口输出
				//然后根据文件的路径可以编辑文件保存的路径和文件截图的名字，下面是以时间命名的
				System.setErr(new PrintStream(new File("/sdcard/Satibiility/"+getTime("HH_mm_ss")+".txt")));
				e.printStackTrace();
				UiDevice.getInstance().takeScreenshot(new File("/sdcard/Satibiility/"+getTime("HH_mm_ss")+".png"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				//try catch中可以嵌套多个try catch
				e1.printStackTrace();
			}
		}finally{
			// finally 的作用是，如果UiObject Button 中的Text"相机",没有找到，会跳转到第一个catch中，运行完catch的方法后，会接着finally的方法，
			//而不是接着运行Button.click();这个方法。
			//常见的用法就是，在打开一个文件，或者一个应用后，抛出异常后可以在此方法中关闭文件或者应用
			UiDevice.getInstance().pressHome();
		}*/
		/*Process getLogcat1 = Runtime.getRuntime().exec(new String[]{"/system/bin/sh","-c","logcat -b radio -t 2000|grep -i 40b80930"});
		InputStream is = getLogcat1.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line;
		String xjh="";
		while ((line = reader.readLine()) != null){
			xjh = xjh+line+"\n";
		}
		System.out.println(xjh);
		int result1=xjh.indexOf("xjh");
		System.out.println(result1);
	}
	获取当前的时间
	public String getTime(String time){
		SimpleDateFormat date1 = new SimpleDateFormat(time);
		String Time = date1.format(new Date());
		return Time;
	}*//*UiObject Button1 = new UiObject(new UiSelector().textContains("天气"));
		assertTrue("Switch to Simple mode is failed.",Button1.click());
		UiObject Button = new UiObject(new UiSelector().textContains("相机"));
		Button.click();*/
		System.out.println(getParameter1("adsfaf.dfafasfd.XmlGreat","getMethod","screencap"));
	
	}
}
