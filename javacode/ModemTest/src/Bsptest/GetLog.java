package Bsptest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.AlarmManager;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;



public class GetLog extends UiAutomatorTestCase
{	
	@SuppressWarnings({ "unused", "deprecation" })
	public void getModemLog() throws IOException, UiObjectNotFoundException, RemoteException
	{	
		Process getLog= Runtime.getRuntime().exec("am force-stop com.android.contacts");
		sleep((long) 3000.0);
		Process startCall= Runtime.getRuntime().exec("am start com.android.contacts/.activities.TwelveKeyDialer");
		sleep((long) 3000.0);
		UiObject Button11 = new UiObject(new UiSelector()
        .description("拨号"));
		Button11.click();
		sleep((long) 3000.0);
		UiObject Button1 = new UiObject(new UiSelector()
        .description("星形符号"));
		UiObject Button2 = new UiObject(new UiSelector()
        .description("英镑符号"));
		UiObject Button3 = new UiObject(new UiSelector()
        .description("二"));
		UiObject Button4 = new UiObject(new UiSelector()
        .description("四"));
		UiObject Button5 = new UiObject(new UiSelector()
        .description("七"));
		UiObject Button6 = new UiObject(new UiSelector()
        .description("八"));
		Button1.click();
		Button2.click();
		Button1.click();
		Button2.click();
		Button3.click();
		Button5.click();
		Button3.click();
		Button6.click();
		Button4.click();
		Button2.click();
		Button1.click();
		Button2.click();
		Button1.click();
		sleep((long) 300000.0);
		Process getLog1= Runtime.getRuntime().exec("am force-stop com.android.contacts");
		
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	public void getBugreportLog() throws IOException, UiObjectNotFoundException, RemoteException
	{	
		Process getLog= Runtime.getRuntime().exec("am force-stop com.android.contacts");
		sleep((long) 3000.0);
		Process startCall= Runtime.getRuntime().exec("am start com.android.contacts/.activities.TwelveKeyDialer");
		sleep((long) 3000.0);
		UiObject Button11 = new UiObject(new UiSelector()
        .description("拨号"));
		Button11.click();
		sleep((long) 3000.0);
		UiObject Button1 = new UiObject(new UiSelector()
        .description("星形符号"));
		UiObject Button2 = new UiObject(new UiSelector()
        .description("英镑符号"));
		UiObject Button3 = new UiObject(new UiSelector()
        .description("二"));
		UiObject Button4 = new UiObject(new UiSelector()
        .description("四"));
		UiObject Button5 = new UiObject(new UiSelector()
        .description("七"));
		UiObject Button6 = new UiObject(new UiSelector()
        .description("八"));
		Button1.click();
		Button2.click();
		Button1.click();
		Button2.click();
		Button3.click();
		Button6.click();
		Button4.click();
		Button2.click();
		Button1.click();
		Button2.click();
		Button1.click();
		sleep((long) 70000.0);
		Process getLog1= Runtime.getRuntime().exec("am force-stop com.android.contacts");
	}

	


}

