package Xuejh;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXml1 {
	
	private static int getParameter(String packageName,String voidName,String parameterName) throws ParserConfigurationException, SAXException, IOException
	{
		int parameter = 0;
		File f = new File("G:\\XmlStudy\\settings.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(f);
		Element allList = doc.getDocumentElement();
		//获取一级目录的参数名
		NodeList nodeList = doc.getElementsByTagName("package");
		for (int i = 0;i < nodeList.getLength();i++){
			Node node = nodeList.item(i);
			Node dataXml = node.getAttributes().getNamedItem("name");
			String data = dataXml.getTextContent();
			if(data.equals(packageName)){
				//获取二级目录的参数名
				Element element = (Element)node;
				NodeList nodeList1 =  element.getElementsByTagName("method");
				for (int j = 0;j < nodeList1.getLength();j++){
					Node node2 = nodeList1.item(j);
					Node dataXml1 = node2.getAttributes().getNamedItem("name");
					String data1 = dataXml1.getTextContent();
					if(data1.equals(voidName)){
						//获取具体的参数
						Element element1 = (Element)node2;
						NodeList nodeList2=element1.getElementsByTagName(parameterName);
						String newname = nodeList2.item(0).getTextContent();
						parameter = Integer.parseInt(newname, 10);
					}
				}
			}
		}
		return parameter;
	}
	
	private static String getParameter1(String packageName,String voidName,String parameterName) throws ParserConfigurationException, SAXException, IOException
	{
		String parameter ;
		return parameter = ""+ getParameter(packageName,voidName,parameterName);
	}
	
public static void main(String arge[]) throws ParserConfigurationException, SAXException, IOException {
	System.out.println(getParameter("11111111","Date","looptimekk"));
	}
	
}