package com.bsp.xue;
import java.io.*;

import android.os.RemoteException;
import com.android.uiautomator.core.UiObject; 		
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
public class Mi2browser {
	public class Test2 extends UiAutomatorTestCase {   
		
		public void testA1() throws UiObjectNotFoundException, RemoteException, IOException{
			for(int i = 0 ; i < 1 ; i++){
				getUiDevice().pressHome();
			    sleep((long) 2000.0);
			    getUiDevice().sleep();
			    sleep((long) 2000.0);
			    getUiDevice().wakeUp();
			    getUiDevice().swipe(380, 935, 380, 1235, 3);
			    sleep((long) 2000.0);
			    sleep((long) 2000.0);
			    Process p1 = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d http://3g.sina.com.cn -n com.android.browser/.BrowserActivity -f 0x13800000");
			    sleep((long) 30000.0);
			    getUiDevice().click(360,800);
			    sleep((long) 30000.0);
			    Process p2 = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d http://3g.qq.com -n com.android.browser/.BrowserActivity -f 0x13800000");
			    sleep((long) 30000.0);
			    getUiDevice().click(360,800);
			    sleep((long) 30000.0);

				}

		}
		}
		
}