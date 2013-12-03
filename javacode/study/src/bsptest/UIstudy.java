package bsptest;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class UIstudy extends UiAutomatorTestCase 
{   

public void testDemo() throws UiObjectNotFoundException 
   {   

      /*UiScrollable appViews = new UiScrollable(new UiSelector()
         .scrollable(true));
      appViews. flingToEnd (5);
      appViews.setAsHorizontalList();
      UiObject settingsApp = appViews.getChildByText(new UiSelector()
         .className(android.widget.TextView.class.getName()), 
         "测试");
      settingsApp.click();*/
	UiObject SendButton1 = new UiObject(new UiSelector().
 		   className("android.widget.Button").index(0).instance(3));
 	SendButton1.click();

 if(SendButton1.exists() && SendButton1.isEnabled()) 
 {
	 SendButton1.click();
 }
   }






  
}