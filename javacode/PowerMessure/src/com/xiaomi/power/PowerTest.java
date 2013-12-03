package com.xiaomi.power;


import java.io.*;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;



public class PowerTest extends UiAutomatorTestCase{

	
	public void test01Calltest() throws UiObjectNotFoundException, IOException
	{
		Case case1 = new  NewCase();
		case1.CallMotest();
	
	}

}




