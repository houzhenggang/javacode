package bsptest;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class Uidevices extends UiAutomatorTestCase {

	public void testUiWatcher() throws  UiObjectNotFoundException{
		UiWatcher confirmDialogWatcher = new UiWatcher(){

			@Override
			public boolean checkForCondition() {
				UiObject okCancelDialog = new UiObject(new UiSelector().textStartsWith("提高“我的位置”精确度"));
				if(okCancelDialog.exists()){
					UiObject cancelButton = new UiObject(new UiSelector().className("android.widget.Button").text("取消"));
					try {
						cancelButton.click();
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return (okCancelDialog.waitUntilGone(5 * 1000));
				}
				return false;
			}
			
		};
		
		UiDevice.getInstance().registerWatcher("UIWATCHER", confirmDialogWatcher);
		UiDevice.getInstance().runWatchers();
		
		UiObject appIcon = new UiObject(new UiSelector().text("地图"));
		appIcon.clickAndWaitForNewWindow();
		UiObject textField = new UiObject(new UiSelector().textStartsWith("地点"));
		textField.click();
		
	}
}