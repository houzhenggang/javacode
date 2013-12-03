package bsptest;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiWatcher;

import android.widget.TextView;
import com.android.uiautomator.core.UiObject;

import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class watcherStudy extends UiAutomatorTestCase
{
	public void testUiwatcher() throws UiObjectNotFoundException{
		
		
		UiWatcher okCancelDialogWatcher = new  UiWatcher(){

			@Override
			public boolean checkForCondition() {

				UiObject okCancelDialog = new UiObject(new UiSelector().textStartsWith("短信"));
				if(okCancelDialog.exists()){
					UiObject SendButton = new UiObject(new UiSelector().
				    		   className("android.widget.Button").index(0).instance(1));
					try{
						System.out.println("1");
						SendButton.click();
					}catch(UiObjectNotFoundException e){
						e.printStackTrace();
					}
					return (okCancelDialog.waitUntilGone(5*1000));
				}
				
				return false;
			}
			
		};
		String xjh = "xuejinghao";
		getUiDevice().registerWatcher(xjh, okCancelDialogWatcher);
    	System.out.println("2");
    	getUiDevice().runWatchers();
    	UiObject SendButton1 = new UiObject(new UiSelector().
    		   className("android.widget.Button").index(0).instance(3));
    	SendButton1.click();
    	System.out.println("3");
		System.out.println("4");
        
     }

		}




