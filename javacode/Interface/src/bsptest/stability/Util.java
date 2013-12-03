package bsptest.stability;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import java.io.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
* 程序中一些的公共方法<br>
* @version 1.0, 2013-10-14
* @author 薛敬浩
* @return
*/ 

public class Util extends UiAutomatorTestCase
{
	/**
	* 通过getevent&sendevent的方法实现米3的解锁<br>
	* @return 没有返回值的内容 
	*/
	private Document document;
	private String filename = "d:\\9.xml";
	private static String parameterStr;
	private static int parameterInt;
	private static String getClazzName = Thread.currentThread().getStackTrace()[0].getClassName();
	private static String getMethodName = Thread.currentThread().getStackTrace()[0].getMethodName();;
	private static Element root;
	
	
	@SuppressWarnings("unused")
	public void testUnlockMi3()
	{
	    try {
			Process SendEvent1 = Runtime.getRuntime().exec("sendevent /dev/input/event1 1 158 1");
			Process SendEvent2 = Runtime.getRuntime().exec("sendevent /dev/input/event1 0 0 0");
			Process SendEvent3 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 1");
			Process SendEvent4 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
			Process SendEvent5 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 0");
			Process SendEvent6 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
			Process SendEvent7 = Runtime.getRuntime().exec("sendevent /dev/input/event1 1 158 0");
			Process SendEvent8 = Runtime.getRuntime().exec("sendevent /dev/input/event1 0 0 0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	* 通过getevent&sendevent的方法实现米2的解锁<br>
	* @return 没有返回值的内容 
	*/
	@SuppressWarnings("unused")
	public void testUnlockMi2()
	{
	    try {
			Process SendEvent1 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 1");
			Process SendEvent2 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
			Process SendEvent3 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 1");
			Process SendEvent4 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
			Process SendEvent5 = Runtime.getRuntime().exec("sendevent /dev/input/event5 1 115 0");
			Process SendEvent6 = Runtime.getRuntime().exec("sendevent /dev/input/event5 0 0 0");
			Process SendEvent7 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 158 0");
			Process SendEvent8 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	* 读取XML中的配置文件<br>
	* @return int类型数值 
	*/
	// 在XML中读取字符串
	static String getParameterStr(String packageName,String voidName,String parameterName) throws ParserConfigurationException, SAXException, IOException
		{
			File f = new File("/sdcard/settings.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
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
								parameterStr = doc.getElementsByTagName(parameterName).item(z).getFirstChild().getNodeValue();
							}
						}
					}
				}
			}
			return parameterStr;
		}
		/**
		*在XML配置文件中读取int类型的整型
		* @return true if successful else false
		*/ 
	static int getParameterInt(String packageName,String voidName,String parameterName) throws ParserConfigurationException, SAXException, IOException
		{
			File f = new File("/sdcard/settings.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
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
								String newname = doc.getElementsByTagName(parameterName).item(j).getFirstChild().getNodeValue();
								parameterInt = Integer.parseInt(newname, 10);
						}
					}
				}
			}
			return parameterInt;
		}
	
	static String[] Url = new String[]{
		"http://sina.cn","http://m.baidu.com/news","http://m.taobao.com","http://m.yihaodian.com",
		"http://3g.ganji.com","http://m.elong.com","http://m.qidian.com","http://duokoo.baidu.com/news"};

	public void toWrite(String ... strings) throws Exception{
		if (root == null){
			//Great xml 
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			DocumentBuilder builder=factory.newDocumentBuilder();
			document=builder.newDocument();
			//to write xml
			root=document.createElement("bsptest");
			document.appendChild(root);
			Element title=document.createElement("package");
			title.appendChild(document.createTextNode(""));
			title.setAttribute("name", getClazzName);
			root.appendChild(title);
			Element title1=document.createElement("method");
			title1.appendChild(document.createTextNode(""));
			title.appendChild(title1);
			title1.setAttribute("name", getMethodName);
			Element content1=document.createElement("result");
			content1.appendChild(document.createTextNode(strings[0]));
			title1.appendChild(content1);
			Element content2=document.createElement("screencap");
			content2.appendChild(document.createTextNode(strings[1]));
			title1.appendChild(content2);
			Element content3=document.createElement("exception");
			content3.appendChild(document.createTextNode(strings[2]));
			title1.appendChild(content3);
			for(int i = 3 ;i <strings.length; i++){
				Element content4=document.createElement("data");
				content4.appendChild(document.createTextNode(strings[i]));
				title1.appendChild(content4);
				content4.setAttribute("name", "passrate");
			}
			//to save xml
			TransformerFactory tf=TransformerFactory.newInstance();
			Transformer transformer=tf.newTransformer();
			DOMSource source=new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			PrintWriter pw=new PrintWriter(new FileOutputStream(filename));
			StreamResult result=new StreamResult(pw);
			transformer.transform(source,result);
		}else{
			//Great xml 
			Element title=document.createElement("package");
			title.appendChild(document.createTextNode(""));
			title.setAttribute("name", getClazzName);
			root.appendChild(title);
			Element title1=document.createElement("method");
			title1.appendChild(document.createTextNode(""));
			title.appendChild(title1);
			title1.setAttribute("name", getMethodName);
			Element content1=document.createElement("result");
			content1.appendChild(document.createTextNode(strings[0]));
			title1.appendChild(content1);
			Element content2=document.createElement("screencap");
			content2.appendChild(document.createTextNode(strings[1]));
			title1.appendChild(content2);
			Element content3=document.createElement("exception");
			content3.appendChild(document.createTextNode(strings[2]));
			title1.appendChild(content3);
			for(int i = 3 ;i <strings.length; i++){
				Element content4=document.createElement("data");
				content4.appendChild(document.createTextNode(strings[i]));
				title1.appendChild(content4);
				content4.setAttribute("name", "passrate");
			}
			//to save xml
			TransformerFactory tf=TransformerFactory.newInstance();
			Transformer transformer=tf.newTransformer();
			DOMSource source=new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			PrintWriter pw=new PrintWriter(new FileOutputStream(filename));
			StreamResult result=new StreamResult(pw);
			transformer.transform(source,result);
		}
		}
	
}
