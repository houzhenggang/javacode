package com.bsptest;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class Uctest extends UiAutomatorTestCase
{
	public void testUnlock() throws RemoteException, IOException 
	{
		getUiDevice().sleep();
		sleep((long) 2000.0);
		getUiDevice().wakeUp();
		sleep((long) 1000.0);
		//com.UCMobile/.main.UCMobile
		Process p1 = Runtime.getRuntime().exec("am start -a com.UCMobile/.main.UCMobile");
		sleep((long) 10000.0);
	}
	
		
	
}