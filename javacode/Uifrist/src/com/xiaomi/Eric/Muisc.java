package com.xiaomi.Eric;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class Muisc extends UiAutomatorTestCase {   

   public void test1() throws UiObjectNotFoundException {   
      getUiDevice().pressHome();
      
      UiObject allAppsButton = new UiObject(new UiSelector()
         .description("Apps"));

      allAppsButton.clickAndWaitForNewWindow();
      UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
      UiObject settingsApp = appViews.getChildByText(new UiSelector()
         .className(android.widget.TextView.class.getName()),"Music");
      settingsApp.clickAndWaitForNewWindow();
      
      // Validate that the package name is the expected one
      UiObject settingsValidation = new UiObject(new UiSelector()
         .packageName("com.android.music"));
      assertTrue("Unable to detect Settings", settingsValidation.exists());   
  } 
   public void test2() throws UiObjectNotFoundException {
	   getUiDevice().pressHome();
	      
	   UiObject allAppsButton = new UiObject(new UiSelector()
	         .description("Apps"));

	   allAppsButton.clickAndWaitForNewWindow();
	   UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
	   UiObject settingsApp = appViews.getChildByText(new UiSelector()
	         .className(android.widget.TextView.class.getName()),"Settings");
	   settingsApp.clickAndWaitForNewWindow();
	      
	      // Validate that the package name is the expected one
	   UiObject settingsValidation = new UiObject(new UiSelector()
	         .packageName("com.android.settings"));
	   assertTrue("Unable to detect Settings", 
	   settingsValidation.exists());
	 	  
   }
}