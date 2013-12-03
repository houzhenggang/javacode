import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class test extends UiAutomatorTestCase
{
	
	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) throws DocumentException
	{

		Document document = new SAXReader().read("E:\\javadai\\Uitest4\\string.xml").getDocument();

		for(Element txtbook : (List<Element>)document.getRootElement().elements())
		{
			String str1;
			str1 = txtbook.element("name").getText();
			String str2;
			str2= txtbook.element("callnum").getText();
			String str3;
			str3 = "am start -a " + str1 + " -d " + str2;
			System.out.println(str3); 
		}
		
	}

}