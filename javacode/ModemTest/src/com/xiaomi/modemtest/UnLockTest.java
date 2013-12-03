package com.xiaomi.modemtest;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UnLockTest  extends UiAutomatorTestCase{

	public void testLock() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException, RemoteException{
		getUiDevice().wakeUp();
		sleep((long)2000);
		try {
			Process SendEvent1 = Runtime.getRuntime().exec("sendevent /dev/input/event1 1 158 1");
			Process SendEvent2 = Runtime.getRuntime().exec("sendevent /dev/input/event1 0 0 0");
			Process SendEvent3 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 1");
			Process SendEvent4 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
			Process SendEvent5 = Runtime.getRuntime().exec("sendevent /dev/input/event0 1 115 0");
			Process SendEvent6 = Runtime.getRuntime().exec("sendevent /dev/input/event0 0 0 0");
			Process SendEvent7 = Runtime.getRuntime().exec("sendevent /dev/input/event1 1 158 0");
			Process SendEvent8 = Runtime.getRuntime().exec("sendevent /dev/input/event1 0 0 0");
			}
		catch (IOException e) {
			e.printStackTrace();
			}
	}


}
