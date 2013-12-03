package xue;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Loopnum
{
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

	DocumentBuilderFactory factory1 = DocumentBuilderFactory
   	     .newInstance();
   DocumentBuilder builder1 = factory1.newDocumentBuilder();
   Document document1 = builder1.parse(new File("E:\\javadai\\Uitest4\\string.xml"));
   Element rootElement1 = document1.getDocumentElement();
   NodeList list2 = rootElement1.getElementsByTagName("loopnum");
   Element element2 = (Element) list2.item(0);
   String str6;
   str6 = element2.getChildNodes().item(0).getNodeValue();
   int aa = Integer.parseInt(str6, 10);
	for(int i = 0 ; i < aa ; i++){
		System.out.println("xuexuexue" + i);
		
	}
}
}
