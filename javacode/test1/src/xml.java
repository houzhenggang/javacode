import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class xml {
 @SuppressWarnings("unused")
public static void main(String[] args) {
  try {
	  //∂¡»°xml≈‰÷√≤Œ ˝
   DocumentBuilderFactory factory = DocumentBuilderFactory
     .newInstance();
   DocumentBuilder builder = factory.newDocumentBuilder();
   Document document = builder.parse(new File("E:\\javadai\\Uitest4\\string.xml"));
   Element rootElement = document.getDocumentElement();
   NodeList list1 = rootElement.getElementsByTagName("callnum");

   NodeList list = rootElement.getElementsByTagName("name");
   Element element = (Element) list.item(0);
   Element element1 = (Element) list1.item(0);
   System.out.println(element.getChildNodes().item(0).getNodeValue());
   System.out.println(element1.getChildNodes().item(0).getNodeValue());

  } catch (Exception e) {
   System.out.println("exception:" + e.getMessage());
  }
 }
}