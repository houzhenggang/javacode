package Bsptest;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class LockTest  extends UiAutomatorTestCase{
	public void testLock() throws UiObjectNotFoundException, IOException, ParserConfigurationException, SAXException, RemoteException{
		getUiDevice().sleep();
		sleep((long)2000);
	}

}


