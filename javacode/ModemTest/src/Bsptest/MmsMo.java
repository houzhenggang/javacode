package Bsptest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import android.os.RemoteException; 
import android.util.Log;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class MmsMo extends UiAutomatorTestCase {  
	private static final String ACTIVITY_TAG="bsptest";
	@SuppressWarnings({  "unused", "deprecation" })
	public void test2SMSMO() throws ParserConfigurationException, SAXException, IOException, UiObjectNotFoundException {
		for(int i = 0 ; i < 50 ; i++){
			Process p0 = Runtime.getRuntime().exec("logcat -b radio -c");
			String smsNumber = "10086";
			String details = "testasdfasfasfasfasfasfasfasfasdfasdfasdfasdfadfafasfasdfasfasdfasfasfasfasfasfasdfasdfasdfasfasfasfasd" +
					"fasdfafasfasfasdfafasfasfasfafasfsdfasfafasdfasfasfsdfasfsfasdklfjaksfkasdkfasdfjhasdhfjnhasdhfjhnjknhflkjhnlkjs" +
					"sakdjfjkasfjaskldfjklsadfklsadkfjksjfkksaldfjksadjkfjsadjfjaskdlfjklsadjkfjksadjfkasjklfjklasjfklaskjlfjklasjfklasjdklf" +
					"klsdjfkaklsfjjkaslfjlkasjfklasjklfajsklfjklasjfkasjdklfjklsadfjklsdjklfjaskldfjklasdjkflasjklfjk" +
					"jsjfaksfjksajdfjasdfjasjkfjkalsdfjasdjklfjkasjfklajsdkfjkasdjkfjaksdfjsajdk" +
					"ksjdfjakslfjkasdjfjkalsdfjklasjlkfjlkasdjflksadjklfjaksdjfklasdjklfjkalsdfjlkasdjkfjkasdjfj" +
					"fasdfafasfasfasdfafasfasfasfafasfsdfasfafasdfasfasfsdfasfsfasdklfjaksfkasdkfasdfjhasdhfjnhasdhfjhnjknhflkjhnlkjs" +
					"sakdjfjkasfjaskldfjklsadfklsadkfjksjfkksaldfjksadjkfjsadjfjaskdlfjklsadjkfjksadjfkasjklfjklasjfklaskjlfjklasjfklasjdklf" +
					"klsdjfkaklsfjjkaslfjlkasjfklasjklfajsklfjklasjfkasjdklfjklsadfjklsdjklfjaskldfjklasdjkflasjklfjk" +
					"jsjfaksfjksajdfjasdfjasjkfjkalsdfjasdjklfjkasjfklajsdkfjkasdjkfjaksdfjsajdk" +
					"ksjdfjakslfjkasdjfjkalsdfjklasjlkfjlkasdjflksadjklfjaksdjfklasdjklfjkalsdfjlkasdjkfjkasdjfj" +
					"fasdfafasfasfasdfafasfasfasfafasfsdfasfafasdfasfasfsdfasfsfasdklfjaksfkasdkfasdfjhasdhfjnhasdhfjhnjknhflkjhnlkjs" +
					"sakdjfjkasfjaskldfjklsadfklsadkfjksjfkksaldfjksadjkfjsadjfjaskdlfjklsadjkfjksadjfkasjklfjklasjfklaskjlfjklasjfklasjdklf" +
					"klsdjfkaklsfjjkaslfjlkasjfklasjklfajsklfjklasjfkasjdklfjklsadfjklsdjklfjaskldfjklasdjkflasjklfjk" +
					"jsjfaksfjksajdfjasdfjasjkfjkalsdfjasdjklfjkasjfklajsdkfjkasdjkfjaksdfjsajdk" +
					"ksjdfjakslfjkasdjfjkalsdfjklasjlkfjlkasdjflksadjklfjaksdjfklasdjklfjkalsdfjlkasdjkfjkasdjfj" +
					"nhfljdjfjjkfkjasfsafjkskfs";
			Process runSmsMo = Runtime.getRuntime().exec("am start -a android.intent.action.SENDTO -d sms:" + smsNumber + " --es sms_body " + details);
			sleep((long) 5000.0);
			/*UiObject sendButton = new UiObject(new UiSelector()
	         .description("发送"));	
			if(sendButton.exists() && sendButton.isEnabled()) 
		    {
				sendButton.click();
		    }*/
			UiObject SendButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(1).instance(0));
			UiObject SendButton1 = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(3));
		    if (SendButton.exists()) {
		    	SendButton.click();
	        } 
		    sleep((long) 10000);
	    	int result1 = -1;
	    	String logcat;
	    	File logcatFile=new File("/sdcard/"+i+".txt");
			logcatFile.createNewFile();
	    	FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
	    	while(result1 == -1)
	    	{
	    		Process getLogcat1 = Runtime.getRuntime().exec("logcat -t 10000");
	    		DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
				while ((logcat = inputLogcat1.readLine()) != null){
					outputLogcat.write(logcat.getBytes());				
				}
				BufferedReader bufferedReader1 = new BufferedReader(new FileReader(logcatFile));  
				String lineTXT1 ;
				lineTXT1 = bufferedReader1.readLine();
				String log1 = lineTXT1.toString().trim();
				result1=log1.indexOf("Finished all transactions in queue. 1 success");
				sleep((long) 5000.0);
				Log.v(MmsMo.ACTIVITY_TAG, "mms send ------------"+i);

	    	}
	    	System.out.println("OK"+i);
	    	Process getLog= Runtime.getRuntime().exec("am force-stop com.android.mms");
	    	sleep((long) 30000.0);
		}
			
		}
	
}
