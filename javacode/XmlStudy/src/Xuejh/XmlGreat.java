package Xuejh;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Method;
public class XmlGreat {
private Document document;
private String filename;

private String getClassName(){
	return XmlGreat.class.getName().toString();
}



private  String clazz = this.getClass().getName();


public XmlGreat(String name) throws ParserConfigurationException{
	filename=name;
	DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	DocumentBuilder builder=factory.newDocumentBuilder();
	document=builder.newDocument();
	}
public void toWrite(String mycontent1,String mycontent2,String mycontent3){
	Element root=document.createElement("xml");
	document.appendChild(root);
	Element title=document.createElement(clazz);
	title.appendChild(document.createTextNode(""));
	root.appendChild(title);
	Element title1=document.createElement("task11111list");
	title.appendChild(document.createTextNode(""));
	root.appendChild(title1);
	Element content1=document.createElement("projectid");
	content1.appendChild(document.createTextNode(mycontent1));
	title.appendChild(content1);
	Element content2=document.createElement("projectype");
	content2.appendChild(document.createTextNode(mycontent2));
	title.appendChild(content2);
	Element content3=document.createElement("task");
	content3.appendChild(document.createTextNode(mycontent3));
	title1.appendChild(content3);
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

public static String getTraceInfo(){  
    StringBuffer sb = new StringBuffer();   
      
    StackTraceElement[] stacks = new Throwable().getStackTrace();  
    int stacksLen = stacks.length;  
    sb.append("class: " ).append(stacks[1].getClassName()).append("; method: ").append(stacks[1].getMethodName()).append("; number: ").append(stacks[1].getLineNumber());  
      
    return sb.toString();  
} 

public static void main(String args[]){
	try{
		
	 XmlGreat myxml=new XmlGreat("d:\\9.xml");
	myxml.toWrite("1","testThreading","task");
	myxml.toSave();
	System.out.println("Your writing is successful.");

	}
	catch(ParserConfigurationException exp){
	exp.printStackTrace();
	System.out.println("Your writing is failed.");
	}

	String clazz = Thread.currentThread()
            .getStackTrace()[1].getClassName();
	System.out.println(clazz);
	String method = Thread.currentThread()
	                .getStackTrace()[1].getMethodName();
	System.out.println(method);
	}


}

