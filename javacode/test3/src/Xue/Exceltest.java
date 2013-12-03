package Xue;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


public class Exceltest{

	
	public static void main(String [] args){
		Calculator cal = new Calculator();
		cal.devide(6, 1);
		String LOG_TAG = Exceltest.class.getSimpleName();
		System.out.println(LOG_TAG);
	}
	
}
class Calculator {
	public int devide(int num1, int num2) {
		//判断除数是否为0
		if(num2 == 0) {
			throw new IllegalArgumentException("除数不能为零==---=-=-=--=-=---=-=");
		}
		System.out.println(num1/num2);
		return num1/num2;
	}
}