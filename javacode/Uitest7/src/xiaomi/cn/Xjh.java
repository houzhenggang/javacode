package xiaomi.cn;


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.xml.sax.SAXException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Xjh extends UiAutomatorTestCase {   
   @SuppressWarnings({"unused" })
public void test1() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{ 
	   for(int i = 0; i < 1000; i++){ 
	   getUiDevice().sleep();
	    sleep((long) 2000.0);
	    getUiDevice().wakeUp();
	    sleep((long) 1000.0);
	   }
	    
  } 
}
