package adsfaf.dfafasfd;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
public class XmlGreat {
private Document document;
private String filename = "d:\\9.xml";
private static String getClazzName = Thread.currentThread().getStackTrace()[0].getClassName();
private static String getMethodName = Thread.currentThread().getStackTrace()[0].getMethodName();;
private static Element root;
private static Element title;
public void toWrite(String ... strings) throws Exception{
	if (root == null){
		//Great xml 
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		document=builder.newDocument();
		//to write xml
		root=document.createElement("bsptest");
		document.appendChild(root);
		title=document.createElement("package");
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


public static void main(String args[]) throws Exception{
	try{
		
	XmlGreat xkj = new XmlGreat();
	int j = 1;
	int z = 1;
	while((getMethodName).equals("getThreadStackTrace") || (getMethodName).equals("getStackTrace")){
		getMethodName = Thread.currentThread().getStackTrace()[j].getMethodName();
		j++;
	}
	while((getClazzName).equals("dalvik.system.VMStack") || (getClazzName).equals("java.lang.Thread")){
		getClazzName = Thread.currentThread().getStackTrace()[z].getClassName();
		z++;
	}
	xkj.toWrite("ok","ok","ok","ok","ok","ok","ok","ok");
	xkj.toWrite("fail","ok","ok","ok","ok","ok","ok","ok","ok","ok","ok","ok","ok","ok");
	System.out.println("Your writing is successful.");
	}
	catch(ParserConfigurationException exp){
	exp.printStackTrace();
	System.out.println("Your writing is failed.");
	}
	}


}

