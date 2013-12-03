package xjh;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

import java.io.*;
public class xml{
 public static void main(String args[]) throws ParserConfigurationException, FileNotFoundException, TransformerException{
	 File file =new File("E:\\testjava\\textnode2.xml");
	 DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
	   DocumentBuilder builder=factory.newDocumentBuilder();
	   Document document=builder.newDocument();
	   document.setXmlVersion("1.0");
	  for(int i = 0; i < 10; i++){
   String train1[]={"计算机入门"+ i};
   String train2[]={"清华出版社"+i};
   String train3[]={"34.5"}; 
   document.setXmlVersion("1.0");
   Element root=document.createElement("书");
   document.appendChild(root);
   for(int k=1;k<=train1.length;k++){
	    root.appendChild(document.createElement("书名"));
	    }
   for(int k=1;k<=train2.length;k++){
    root.appendChild(document.createElement("出版社"));
    }
   for(int k=1;k<=train3.length;k++){
    root.appendChild(document.createElement("价格"));
    }
    NodeList nodeList=document.getElementsByTagName("书");
    int size=nodeList.getLength();
    nodeList=document.getElementsByTagName("书名");
    size=nodeList.getLength();
    for(int k=0;k<size;k++){
        Node node=nodeList.item(k);
        if(node.getNodeType()==Node.ELEMENT_NODE){
         Element elementNode=(Element)node;
         elementNode.appendChild(document.createTextNode(train1[k]));
        }
       }
    nodeList=document.getElementsByTagName("出版社");
    size=nodeList.getLength();
    for(int k=0;k<size;k++){
     Node node=nodeList.item(k);
     if(node.getNodeType()==Node.ELEMENT_NODE){
      Element elementNode=(Element)node;
      elementNode.appendChild(document.createTextNode(train2[k]));
     }
    }
    
    nodeList=document.getElementsByTagName("价格");
    size=nodeList.getLength();
    for(int k=0;k<size;k++){
     Node node=nodeList.item(k);
     if(node.getNodeType()==Node.ELEMENT_NODE){
      Element elementNode=(Element)node;
      elementNode.appendChild(document.createTextNode(train3[k]));
     }
    }
  
    TransformerFactory transFactory=TransformerFactory.newInstance();
    Transformer transformer=transFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    DOMSource domSource=new DOMSource(document);
    FileOutputStream out=new FileOutputStream(file);
    StreamResult xmlResult=new StreamResult(out);
    transformer.transform(domSource,xmlResult);
	  }
  }

 }