package bsptest;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TouchScreen extends UiAutomatorTestCase
{
	public void test(){
		for(int i = 0; i < 1000; i++){
			UiDevice.getInstance().swipe(80, 400, 900, 400, 2);
			UiDevice.getInstance().swipe(900, 400, 80, 400, 2);
		}
	}

}
