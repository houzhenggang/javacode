package bsptest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.util.Log;
import bsptest.Settings;
import bsptest.Util;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.android.uiautomator.core.UiObjectNotFoundException;

public class Interface extends UiAutomatorTestCase {
	
	/*****************
	 * init values	 * 
	 *****************/
	private static final String LOG_TAG  = "BS";
	private UiDevice device = null;
	private int windowWidth = 0;
	private int windowHight = 0;
	private int testLoop = Settings.Loop;
	private UiWatcher browserCrashUiWatcher = null;
	private List<String> urlHistory = new ArrayList<String>();
	private Date startDatetime = null;
	
	
	/********************
	 * set up             *
	 * test methods   *
	 * tear down       *
	 ********************/
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		device = this.getUiDevice();
		//device.pressHome();
		windowWidth = device.getDisplayWidth();
		windowHight = device.getDisplayHeight() - 110;
		startDatetime = new Date();		
		urlHistory.add("urlHistory");
		browserCrashUiWatcher = new UiWatcher(){

			@Override
			public boolean checkForCondition() {
				UiObject ANRButton = new UiObject(new UiSelector().text("确定"));
				getScreenshot("browserCrash_ANR");
				System.out.println("iwatcher is activated!" + ANRButton.exists());
				if(ANRButton.exists()){
					try {
						ANRButton.click();
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
				return false;
			}
			
		};
		
	}
	
	public void testBroswer() throws UiObjectNotFoundException, IOException{
		
		device.registerWatcher("Browser Crash UiWatcher", browserCrashUiWatcher);
		device.runWatchers();
				
		//String pid = executeCMD(new String[]{"sh", "-c", "ps | busybox grep com.android.browser"}, true).split("\\s+")[1];
		//System.out.println("browswer pid is : " + pid);
		
		for(int i = 0; i < testLoop; i++){
			startPage();
			Util util = new Util();
			util.testLog(); 
			//check browser crash or not
			checkCoreDump();
			
			//random click start with "Baidu page" 20 times
			for(int j = 0; j < 20; j++){
				randomClick();
				if(!device.getCurrentPackageName().equals("com.android.browser"))
					device.pressBack();
				String tmpUrl = parseLog();
				if(tmpUrl.endsWith("jpg"))// || tmpUrl.equals(""))
					break;
			}
	
		}
		
	}
		
	

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		//take snap shot
		System.out.println("Current Package name is " + device.getCurrentPackageName());
		getScreenshot("browserCrash");
		
		long diff = (new Date()).getTime() - startDatetime.getTime();
		long day = diff/(24 * 60 * 60 * 1000);
		long hour = diff/(60 * 60 * 1000) -  day * 24;
		long min = diff/(60 * 1000) - (day * 24 + hour) * 60;
		long second = diff/1000 - (day * 24 * 60 + hour * 60 + min) * 60;
		System.out.println("Uesd time : " + hour + " hours, " + min + " minutes, "+ second + " seconds" );
		sleep(3 * 1000);
		
		device.pressMenu();
		UiObject quitItem = new UiObject(new UiSelector().text("退出"));
		quitItem.click();
		UiObject okButton = new UiObject(new UiSelector().text("确定"));
		if(okButton.exists())
			okButton.click();
		
		//device.pressBack();	
	}
	
	/*********************
	 * called methods *
	 *********************/
	protected void log(String msg){
		Log.i(LOG_TAG, msg);
	}
	
	protected void checkCoreDump() throws UiObjectNotFoundException{
		UiObject extendAllPage;
		assertTrue(device.getCurrentPackageName().equals("com.android.browser"));
		extendAllPage = new UiObject(new UiSelector().className("android.widget.LinearLayout").index(3).childSelector(new UiSelector().className("android.widget.TextView").index(3)));
		extendAllPage.click();
		extendAllPage.click();
	}

	
	protected void startPage(){
		//generate random URL
		int randValue = (int)Math.floor(Math.random()*(Settings.keyWordList.length));
		String RandomURL = "http://www.baidu.com/s?wd=" + Settings.keyWordList[randValue];
		
		//start browser activity with keyword
		String cmd = "am start -a android.intent.action.VIEW -d " + RandomURL;
		System.out.println(cmd);
		
		executeCMD(cmd, false);
		sleep(2 * 1000);		
	}
	
	protected void randomClick(){
		int x, y;
		
		x = (int)Math.floor(Math.random() * windowWidth);
		y = (int)Math.floor(Math.random() * windowHight) + 155;
		y = ( y > windowHight-50) ? windowHight-50 : y;

		device.swipe(windowWidth/2, windowHight/2, windowWidth/2, windowHight/3, 10);
		device.click(x, y);
		System.out.println("(x,y) : (" + x + ", " + y + " )");
		sleep(2*1000);
	}

	
	protected String parseLog(){
		String[] log = {};
		String lastUrl = "";
		int i ;
		int clickTimes = 10;
		
		for(i =0 ; i < clickTimes; i++){
			//get log info array and reverse
			log = executeCMD(new String[]{"sh", "-c", "logcat -v time -t 400 | busybox grep onPage"}, true).split("01-");
			if(log.length < 2)
				continue;
			String tmp = log[0];
			for(int k = 1; k < log.length; k++){
				 log[k-1] = log[k];
			}
			log[log.length-1] = tmp;
			
			if (log[1].contains("onPageFinished"))
				break;		
			sleep(500);
		}
		
		if( i ==clickTimes){
			UiObject stopLoad = new UiObject(new UiSelector().description("刷新网页"));
			try {
				stopLoad.click();
			} catch (UiObjectNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			lastUrl = log[1].split("url=")[1];
			System.out.println("lastUrl : " + lastUrl);
			System.out.println("urlHistory : " + urlHistory.toString() + "  " + (urlHistory.size()-1));
			if (urlHistory.get(urlHistory.size()-1).equals(lastUrl) == false)
				urlHistory.add(lastUrl);
		}
		
		return lastUrl;
	}
	
	protected String executeCMD(String command, Boolean returnInfo){
		String returnValue = "";
		
		if(returnInfo == false){
			try {
				Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				System.out.println("Error happend in execute command : " + command);
			}
		}
		else{
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream()));
				StringBuffer sb = new StringBuffer();
				String line;
				while((line = br.readLine()) != null)
					sb.append(line);
				returnValue = sb.toString();
			} catch (IOException e) {
				System.out.println("Error happend in execute command : " + command);
			}
		}	
		return returnValue;
	}
	
	protected String executeCMD(String[] command, Boolean returnInfo){
		String returnValue = "";
		
		if(returnInfo == false){
			try {
				Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				System.out.println("Error happend in execute command : " + command);
			}
		}
		else{
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream()));
				StringBuffer sb = new StringBuffer();
				String line;
				while((line = br.readLine()) != null){
					sb.append(line);
				}
				returnValue = sb.toString();
			} catch (IOException e) {
				System.out.println("Error happend in execute command : " + command);
			}
		}	
		return returnValue;
	}
	
	public void getScreenshot(String filename){
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("screencap -p /sdcard/" + filename + ".png");
		} catch (IOException e) {
			System.out.println("Take snap shot error! --> " + filename);
			e.printStackTrace();
		}
	}
}
