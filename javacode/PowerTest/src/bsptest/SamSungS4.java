package bsptest;

import bsptest.Util;
import java.io.*;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;



public class SamSungS4 extends UiAutomatorTestCase
{
	private String logcat;
	private File logcatFile=new File("/sdcard/Power.txt");


	@SuppressWarnings("unused")
	protected void setUp() throws UiObjectNotFoundException, RemoteException, IOException 
	{
		getUiDevice().pressHome();
		sleep((long) 1000.0);
		Process SendEvent1 = Runtime.getRuntime().exec(" am Kill all background processes.");
		sleep((long) 1000.0);    
	}
	

	@SuppressWarnings("unused")
	protected void tearDown() throws UiObjectNotFoundException, RemoteException, IOException 
	{ 
		getUiDevice().pressHome();
		sleep((long) 1000.0);
		Process SendEvent1 = Runtime.getRuntime().exec(" am Kill all background processes.");
		sleep((long) 1000.0);  
	}
	
	//切水果游戏测试
	@SuppressWarnings({ "unused", "deprecation" })
	public void test1PlayGame() throws IOException, UiObjectNotFoundException
	{
		logcatFile.createNewFile();
		FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
		Process getPower_start = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		DataInputStream inputLogcat1 = new DataInputStream(getPower_start.getInputStream());
		while ((logcat = inputLogcat1.readLine()) != null){
			outputLogcat.write(("Start_RunCase:"+logcat+ "\n").getBytes());
		}		
		Process runGame = Runtime.getRuntime().exec("am start com.halfbrick.fruitninja/com.halfbrick.mortar.MortarGameActivity");
		sleep((long) 15000.0);
		getUiDevice().swipe(700, 850, 1100, 600, 10);
		sleep((long) 10000.0);
		getUiDevice().swipe(1050, 940, 1400, 650, 10);
		sleep((long) 2000.0);
		for(int i = 0; i < 19;i++)
		{
			for(int j = 0; j < 25;j++)
			{
				getUiDevice().swipe(80, 400, 1700, 400, 10);
				sleep((long) 2000.0);
			}
			sleep((long) 21000.0);
			UiObject backButton = new UiObject(new UiSelector().
		    		   className("android.widget.ImageButton").index(1));
			if(backButton.exists())
        	{
        		backButton.click();
        	}
        	sleep((long) 10000);
        	UiObject skipButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").text("跳过"));
        	if(skipButton.exists())
        	{
        		skipButton.click();
        	}		
			sleep((long) 10000.0);
			getUiDevice().swipe(770, 990, 1190, 750, 10);
			sleep((long) 2000.0);
		}
		Process closeGmae = Runtime.getRuntime().exec("am force-stop com.halfbrick.fruitninja");
		sleep((long) 5000.0);
		Process getPower_end = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		DataInputStream inputLogcat2 = new DataInputStream(getPower_end.getInputStream());
		while ((logcat = inputLogcat2.readLine()) != null){
			outputLogcat.write(("End_Game:"+logcat+ "\n").getBytes());
		}
	
		
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	public void test2Music() throws UiObjectNotFoundException,  RemoteException, Throwable
	{

		FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);		
		Process runMusic = Runtime.getRuntime().exec("am start com.sec.android.app.music");
		sleep((long) 4000.0);
		UiObject refreshView = new UiObject(new UiSelector().text("随机播放"));
		refreshView.clickAndWaitForNewWindow();
		sleep((long) 10000.0);
		getUiDevice().sleep();
	    sleep((long) 3550000.0);
	    getUiDevice().wakeUp();
	    sleep((long) 5000.0);
	    getUiDevice().swipe(590, 1630, 590, 760, 10);
	    sleep((long) 5000.0);
	    Process closeMusic = Runtime.getRuntime().exec("am force-stop com.sec.android.app.music");
	    sleep((long) 5000.0);
		Process getPower_end = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		sleep((long) 5000.0);
		DataInputStream inputLogcat2 = new DataInputStream(getPower_end.getInputStream());
		while ((logcat = inputLogcat2.readLine()) != null){
			outputLogcat.write(("End_Music:"+logcat+ "\n").getBytes());
		}
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	public void test3Video() throws UiObjectNotFoundException, IOException, RemoteException
	{		
		FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);	
		getUiDevice().pressHome();
		sleep((long) 3000.0);
		UiObject addApp = new UiObject(new UiSelector()
         .description("应用程序"));
 		addApp.click();
 		sleep((long) 3000.0);
 		UiScrollable appViews = new UiScrollable(new UiSelector()
        .scrollable(true));
 		appViews.setAsHorizontalList();
 		UiObject settingsApp = appViews.getChildByText(new UiSelector()
        .className(android.widget.TextView.class.getName()), 
        "视频");
     settingsApp.clickAndWaitForNewWindow();
		sleep((long) 11000.0);
		UiObject videoView = new UiObject(new UiSelector().text("test"));
		videoView.clickAndWaitForNewWindow();
		sleep((long) 1770000.0);
		Process closeVideoPlayer = Runtime.getRuntime().exec("am force-stop com.samsung.everglades.video");
		sleep((long) 10000.0);
		Process getPower_end = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		sleep((long) 5000.0);
		DataInputStream inputLogcat2 = new DataInputStream(getPower_end.getInputStream());
		while ((logcat = inputLogcat2.readLine()) != null){
			outputLogcat.write(("End_PlayerVideo:"+logcat+ "\n").getBytes());
		}
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	public void test4ReadBook() throws IOException, UiObjectNotFoundException
	{
		FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);	
		Process openReader= Runtime.getRuntime().exec("am start com.duokan.reader/.DkReaderActivity");
		sleep((long) 10000.0);
		UiObject booknameView = new UiObject(new UiSelector().text("鲁迅全集·节选"));
		booknameView.clickAndWaitForNewWindow();
		sleep((long) 5000.0);
		for(int i = 0; i < 59;i++)
		{
			getUiDevice().click(900,1500);
			sleep((long) 30000.0);
		}
		sleep((long) 5000.0);
		Process closeVideoPlayer = Runtime.getRuntime().exec("am force-stop com.duokan.reader");
		sleep((long) 5000.0);
		Process getPower_end = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		sleep((long) 5000.0);
		DataInputStream inputLogcat2 = new DataInputStream(getPower_end.getInputStream());
		while ((logcat = inputLogcat2.readLine()) != null){
			outputLogcat.write(("End_ReadBook:"+logcat+ "\n").getBytes());
		}
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	public void test5Weibo() throws IOException, UiObjectNotFoundException
	{
		FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);
		Process openWeibo= Runtime.getRuntime().exec("am start com.sina.weibo/.SplashActivity");
		sleep((long) 5000.0);
		for(int i = 0; i < 143;i++)
		{
			UiObject buttonView = new UiObject(new UiSelector().text("随便看看"));
			buttonView.clickAndWaitForNewWindow();
			sleep((long) 10000.0);
			getUiDevice().swipe(20, 1700, 20, 500, 10);
			sleep((long) 5000.0);
			getUiDevice().swipe(20, 1700, 20, 500, 10);
			sleep((long) 5000.0);
			getUiDevice().swipe(20, 1700, 20, 500, 10);
			sleep((long) 5000.0);
			getUiDevice().pressBack();
		}
		sleep((long) 5000.0);
		Process closeWeibo = Runtime.getRuntime().exec("am force-stop com.sina.weibo ");
		sleep((long) 5000.0);
		Process getPower_end = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		sleep((long) 5000.0);
		DataInputStream inputLogcat2 = new DataInputStream(getPower_end.getInputStream());
		while ((logcat = inputLogcat2.readLine()) != null){
			outputLogcat.write(("End_Weibo:"+logcat+ "\n").getBytes());
		}
	}

	@SuppressWarnings({ "unused", "deprecation" })
	public void test6Call() throws IOException, UiObjectNotFoundException, RemoteException
	{
		FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);	
		String Callnum = "15901274733";
		Process startCall= Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel:"+Callnum);
		sleep((long) 5000.0);
		UiObject addApp = new UiObject(new UiSelector()
        .description("通话"));
		addApp.clickAndWaitForNewWindow();
		sleep((long) 20000.0);
		getUiDevice().sleep();
		sleep((long) 1740000.0);
	    getUiDevice().pressKeyCode(26);
	    sleep((long) 4000.0);
	    UiObject endButton = new UiObject(new UiSelector().text("结束通话"));
	    if(endButton.exists() && endButton.isEnabled()) 
	    {
	    	endButton.click();
	    }
	    sleep((long) 5000.0);
	    getUiDevice().swipe(590, 1630, 590, 760, 10);
	    sleep((long) 10000.0);
		Process getPower_end = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		sleep((long) 5000.0);
		DataInputStream inputLogcat2 = new DataInputStream(getPower_end.getInputStream());
		while ((logcat = inputLogcat2.readLine()) != null){
			outputLogcat.write(("End_CallMo:"+logcat+ "\n").getBytes());
		}
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	public void test7Brower() throws IOException, UiObjectNotFoundException
	{
		FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);
		Process openSetting = Runtime.getRuntime().exec("am start com.android.settings");
		sleep((long) 5000.0);
		UiObject allsteView = new UiObject(new UiSelector().text("连接"));
		allsteView.clickAndWaitForNewWindow();
		sleep((long) 5000.0);
		UiObject wifiView = new UiObject(new UiSelector().text("WLAN"));
		wifiView.clickAndWaitForNewWindow();				
		sleep((long) 5000.0);
		UiObject endButton = new UiObject(new UiSelector().
		 		   className("android.widget.Switch").index(1));

		 if(endButton.exists() && endButton.isEnabled()) 
		 {
		 	endButton.click();
		 }	
		sleep((long) 5000.0);
		Process closeSetting = Runtime.getRuntime().exec("am force-stop com.android.settings");
		sleep((long) 10000.0);
		for(int i = 0; i < 29;i++)
		{
			Process Brower1 = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d " +
		    		"http://3g.sina.com.cn -f 0x13800000");
		    sleep((long) 15000.0);
		    getUiDevice().click(360,800);
		    sleep((long) 15000.0);
		    Process Brower2 = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d " +
		    		"http://3g.qq.com -f 0x13800000");
		    sleep((long) 15000.0);
		    getUiDevice().click(323,956);
		    sleep((long) 15000.0);
		}
		Process closeBrower = Runtime.getRuntime().exec("am force-stop com.android.browser ");
		sleep((long) 5000.0);
		Process getPower_end = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		sleep((long) 5000.0);
		DataInputStream inputLogcat2 = new DataInputStream(getPower_end.getInputStream());
		while ((logcat = inputLogcat2.readLine()) != null){
			outputLogcat.write(("End_Brower:"+logcat+ "\n").getBytes());
		}
		sleep((long) 3000.0);
		Process openSetting1 = Runtime.getRuntime().exec("am start com.android.settings");
		sleep((long) 5000.0);
		UiObject allsteView1 = new UiObject(new UiSelector().text("连接"));
		allsteView1.clickAndWaitForNewWindow();
		sleep((long) 5000.0);
		UiObject wifiView1 = new UiObject(new UiSelector().text("WLAN"));
		wifiView1.clickAndWaitForNewWindow();				
		sleep((long) 5000.0);
		UiObject endButton1 = new UiObject(new UiSelector().
				className("android.widget.Switch").index(1));

		 if(endButton1.exists() && endButton1.isEnabled()) 
		 {
		 	endButton1.click();
		 }	
		sleep((long) 5000.0);
		Process closeSetting1 = Runtime.getRuntime().exec("am force-stop com.android.settings");
		sleep((long) 10000.0);
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	public void test8Camera() throws IOException, UiObjectNotFoundException
	{
		FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);	
		Process openWeixin= Runtime.getRuntime().exec("am start com.sec.android.app.camera");
		sleep((long) 10000.0);
		for(int i = 1;i < 2; i++)
		{
			getUiDevice().click(1780,540);
			sleep((long) 30000.0);
		}
		sleep((long) 5000.0);
		Process closeWeibo = Runtime.getRuntime().exec("am force-stop com.sec.android.app.camera");
		sleep((long) 5000.0);
		Process getPower_end = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		sleep((long) 5000.0);
		DataInputStream inputLogcat2 = new DataInputStream(getPower_end.getInputStream());
		while ((logcat = inputLogcat2.readLine()) != null){
			outputLogcat.write(("End_Camera:"+logcat+ "\n").getBytes());
		}
		
	}
	
	public static void main(String args[])
	{ 
		junit.textui.TestRunner.run(SamSungS4.class);
	}
	

}

