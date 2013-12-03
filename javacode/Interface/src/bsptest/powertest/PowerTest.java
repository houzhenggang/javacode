package bsptest.powertest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
* 电池续航的具体测试case，测试case7个，时长5.5小时，目前支持版本米2、米3MIUI版本
* 运行case前需要安装一些APK，具体信息参照各个文件夹下的Bat文件<br>
* 注意事项：<br>
* 1）安装文件时，如果手机内有多看、微博、切水果APP时，要先卸载程序，在运行Bat文件安装程序，手机屏幕亮屏时间设置为最大。<br>
* 2）生成Jar后push到手机的/data/local/tmp/目录下。<br>
* 3）运行PowerTest这个文件，指令adb shell uiautomator runtest ***.jar -c bsptest.powertest.PowerTest --nohup<br>
* 4）程序运行结束后会将结果生成到手机的sdcard的根目录下，Power.txt文件中。第二次运行时记得清除此文件。<br>
* @version 1.0, 2013-10-14
* @author 薛敬浩
* @return
*/ 

public class PowerTest extends UiAutomatorTestCase
{
	/**给全局变量赋值，值为{@value}*/
	IPowerTest IPUT;
	private Document document;
	private String filename;
	private static int LoopStr = 1;
	private File logcatFile1=new File("/sdcard/power.txt");
	/**
	 * setup准备工作，获取手机的具体信息，目前脚本支持M2&M3两款手机的MIUI版本<br>
	 * 在程序运行前将后台所有程序Kill掉，避免一些程序在后台浪费电量。<br>
	 * * @see #setUp()
	 */
	@SuppressWarnings("unused")
	protected void setUp() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		String product = this.getPhoneType();
		System.out.println(product);
       /* if (product.equals("pisces"))*/
		if (product.equals("cancro"))
            IPUT = new MI3();
        else if (product.equals("aries"))
            IPUT = new MI2();
		getUiDevice().pressHome();
		sleep((long) 1000.0);
		//kill掉所有后台信息
		Process SendEvent1 = Runtime.getRuntime().exec(" am Kill all background processes.");
		sleep((long) 1000.0);    
	}
	
	/**
	 * tearDown在没run 一个case 以后的准备工作<br>
	 * 在程序运行后将后台所有程序Kill掉，避免一些程序在后台浪费电量。<br>
	 * @see #tearDown()
	 */
	@SuppressWarnings("unused")
	protected void tearDown() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		getUiDevice().pressHome();
		sleep((long) 1000.0);
		Process SendEvent1 = Runtime.getRuntime().exec(" am Kill all background processes.");
		sleep((long) 1000.0);  
	}
	
	/**
	 * test01游戏测试，时间0.5小时<br>
	 * 测试说明：测试游戏切水果<br>
	 * Comment：屏幕（260nits）、WIFI（OFF）、扬声器（中等）
	 * @see bsptest.powertest.MI3#PlayGame()
	 */
	public void test01PlayGame() throws Throwable 
	{
		IPUT.PlayGame();
		grestTxt(getA(1));
	}
	
	/**
	 * test02播放音乐测试，时间1小时<br>
	 * 测试说明：手机自带音乐播放器，播放模式设置为'顺序播放全部歌曲'<br>
	 * Comment：屏幕（关闭）、WIFI（OFF）、扬声器（OFF）
	 * @see bsptest.powertest.MI3#Music()
	 */
	public void test02Music() throws Throwable 
	{
		IPUT.Music();
		grestTxt(getA(2));
	}
	
	/**
	 * test03本地视频测试，时间0.5小时<br>
	 * 测试说明：本地视频播放，MP4格式<br>
	 * Comment：屏幕（260nits）、WIFI（OFF）、扬声器（OFF）
	 * @see bsptest.powertest.MI3#Video()
	 */
	public void test03Video() throws Throwable 
	{
		IPUT.Video();
		grestTxt(getA(3));
	}
	
	/**
	 * test04电子书测试，时间0.5小时<br>
	 * 测试说明：阅读器选择多看<br>
	 * Comment：屏幕（260nits）、WIFI（OFF）、扬声器（OFF）
	 * @see bsptest.powertest.MI3#ReadBook()
	 */
	public void test04ReadBook() throws Throwable 
	{
		IPUT.ReadBook();
		grestTxt(getA(4));
	}
	
	/**
	 * test05微博测试，时间1小时<br>
	 * 测试说明：数据类型3G优先<br>
	 * Comment：屏幕（260nits）、WIFI（OFF）、扬声器（OFF）
	 * @see bsptest.powertest.MI3#Weibo()
	 */
	public void test05Weibo() throws Throwable 
	{
		IPUT.Weibo();
		grestTxt(getA(5));
	}
	
	/**
	 * test06微博测试，时间0.5小时<br>
	 * 测试说明：WIFI数据上网<br>
	 * Comment：屏幕（260nits）、WIFI（ON）、扬声器（OFF）
	 * @see bsptest.powertest.MI3#Brower()
	 */
	public void test06Brower() throws Throwable 
	{
		IPUT.Brower();
		grestTxt(getA(6));
	}
	
	/**
	 * test07拍照测试，时间0.5小时<br>
	 * 测试说明：手机拍照时手机悬空<br>
	 * Comment：屏幕（260nits）、WIFI（OFF）、扬声器（OFF）
	 * @see bsptest.powertest.MI3#Camera()
	 */
	public void test07Camera() throws Throwable 
	{
		IPUT.Camera();
		grestTxt(getA(7));
		grestTxt(getA(8));
	}
	
	public void test08PowerTest() throws Exception 
	{
		PowerTest myxml = new PowerTest();
		myxml.FailName("/sdcard/Power.xml");
		myxml.toWrite(getA(1),getA(2), getA(3), getA(4), getA(5), getA(6),getA(7),getA(8));
		myxml.toSave();
	}
	/**
	 * 获得手机的具体型号信息<br>
	 * 目前只有米3跟米2
	 * @throws Exception 
	 */
	
	public void grestTxt(String string) throws Exception{
		int i = LoopStr++;
		logcatFile1.createNewFile();
		FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
		outputLogcat1.write((i+"-------"+string+"\n").getBytes());
		
	}
	
	
	public String getA(int i){
		MI3 mi3 = new MI3();
		String Str1 = mi3.a[i-1];
		String Str2 = mi3.a[i];
		int intStr1 = Integer.parseInt(Str1, 10);
		int intStr2 = Integer.parseInt(Str2, 10);
		int power = intStr1 - intStr2;
		String power1 = String.valueOf(power);
		return power1;
	}
	
	public String getPhoneType() throws IOException
    {
      Process p = Runtime.getRuntime().exec("getprop ro.product.name");
      InputStream is = p.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String line;
      line = reader.readLine();
      is.close();
      reader.close();
      p.destroy();
      return line;
    }

	public void FailName(String name) throws ParserConfigurationException{
		filename=name;
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		document=builder.newDocument();
		}
	public void toWrite(String mycontent1,String mycontent2,String mycontent3,
			String mycontent4,String mycontent5,String mycontent6,String mycontent7,String mycontent8){
		Element root=document.createElement("xml");
		document.appendChild(root);
		Element title=document.createElement("PowerTest");
		title.appendChild(document.createTextNode(""));
		root.appendChild(title);
		Element content1=document.createElement("Game");
		content1.appendChild(document.createTextNode(mycontent1));
		title.appendChild(content1);
		Element content2=document.createElement("Music");
		content2.appendChild(document.createTextNode(mycontent2));
		title.appendChild(content2);
		Element content3=document.createElement("PlayerVideo");
		content3.appendChild(document.createTextNode(mycontent3));
		title.appendChild(content3);
		Element content4=document.createElement("ReadBook");
		content4.appendChild(document.createTextNode(mycontent4));
		title.appendChild(content4);
		Element content5=document.createElement("Weibo");
		content5.appendChild(document.createTextNode(mycontent5));
		title.appendChild(content5);
		Element content6=document.createElement("Brower");
		content6.appendChild(document.createTextNode(mycontent6));
		title.appendChild(content6);
		Element content7=document.createElement("Camera");
		content7.appendChild(document.createTextNode(mycontent7));
		title.appendChild(content7);
		Element content8=document.createElement("Remain");
		content8.appendChild(document.createTextNode(mycontent8));
		title.appendChild(content8);
		}
	public void toSave(){
		try{
		TransformerFactory tf=TransformerFactory.newInstance();
		Transformer transformer=tf.newTransformer();
		DOMSource source=new DOMSource(document);
		transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		PrintWriter pw=new PrintWriter(new FileOutputStream(filename));
		StreamResult result=new StreamResult(pw);
		transformer.transform(source,result);
		}
		catch(TransformerException mye){
		mye.printStackTrace();
		}
		catch(IOException exp){
		exp.printStackTrace();
		}
	}
	
}
