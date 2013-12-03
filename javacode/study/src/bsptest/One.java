package bsptest;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiWatcher;

import android.widget.TextView;
import com.android.uiautomator.core.UiObject;

import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
public class One extends  UiAutomatorTestCase
{
	public void testUiwatcher() throws UiObjectNotFoundException{
		UiDevice device = getUiDevice();

		UiWatcher okCancelDialogWatcher = new  UiWatcher(){

		@Override
		public boolean checkForCondition() {

		UiObject okCancelDialog = new UiObject(new UiSelector().textStartsWith("Lorem ipsum"));
		if(okCancelDialog.exists()){

			UiObject SendButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(1));
		try{
			SendButton.click();
		}catch(UiObjectNotFoundException e){
		e.printStackTrace();
		}
		return (okCancelDialog.waitUntilGone(5*1000));
		}

		return false;
		}

		};
		String xjh = "haha";
		device.registerWatcher(xjh, okCancelDialogWatcher);
		UiObject SendButton = new UiObject(new UiSelector().
	    		   className("android.widget.Button").index(0).instance(4));
		device.runWatchers();
		SendButton.click();

}
}