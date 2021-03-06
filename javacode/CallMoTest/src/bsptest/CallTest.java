package bsptest;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class CallTest extends UiAutomatorTestCase
{
	// 在XML中读取字符串
		private static String getParameterStr(String packageName,String voidName,String parameterName) throws Exception
		{
			String parameter = "";
			File f = new File("/sdcard/settings.xml");
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
							parameter = nodeList2.item(0).getTextContent();
						}
					}
				}
			}
			
			return parameter ;
		}
		/**
		*在XML配置文件中读取int类型的整型
		* @return true if successful else false
		*/ 
		private static int getParameterInt(String packageName,String voidName,String parameterName) throws Exception
		{
			int parameter = 0;
			return parameter = Integer.parseInt(getParameterStr(packageName,voidName,parameterName), 10); 
		}
		
	public void testCallMotest() throws Exception{
		for( int i = 1; i <= getParameterInt("Stability","CallTest","loop");i++)
		{
			sleep((long) 1000);
			Process startCall= Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel:"+ getParameterStr("Stability","CallTest","number"));
			sleep((long) getParameterInt("Stability","CallTest","calltime"));
			UiObject endButton = new UiObject(new UiSelector().
					className("android.widget.Button").index(1));
			if(endButton.exists())endButton.click();
			sleep((long) getParameterInt("Stability","CallTest","looptime"));
		}
		
	}

}
