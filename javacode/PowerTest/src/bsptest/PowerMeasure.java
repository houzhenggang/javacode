package bsptest;

import bsptest.Util;
import java.io.*;
import android.os.RemoteException;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;



public class PowerMeasure extends UiAutomatorTestCase
{
	private String logcat;
	private File logcatFile=new File("/sdcard/name.txt");


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
	
	/*@SuppressWarnings({ "unused", "deprecation" })
	public void test1PlayGame() throws IOException
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
		getUiDevice().swipe(480, 580, 900, 490, 3);
		sleep((long) 10000.0);
		getUiDevice().swipe(690, 620, 1010, 460, 3);
		sleep((long) 2000.0);
		for(int i = 0; i < 22;i++)
		{
			for(int j = 0; j < 25;j++)
			{
				getUiDevice().swipe(80, 400, 1200, 400, 10);
				sleep((long) 2000.0);
			}
			sleep((long) 10000.0);
			getUiDevice().click(1190,114);	
			sleep((long) 2000.0);
			getUiDevice().click(640,640);		
			sleep((long) 15000.0);
			getUiDevice().swipe(480, 630, 800, 500, 3);
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
		Process runMusic = Runtime.getRuntime().exec("am start com.miui.player/.ui.MusicBrowserActivity");
		sleep((long) 2000.0);
		getUiDevice().pressMenu();
		sleep((long) 2000.0);
		UiObject refreshView = new UiObject(new UiSelector().text("刷新"));
		refreshView.clickAndWaitForNewWindow();
		sleep((long) 10000.0);
		UiObject playView = new UiObject(new UiSelector().text("歌曲"));
		playView.clickAndWaitForNewWindow();
		sleep((long) 2000.0);
		UiObject play_allView = new UiObject(new UiSelector().text("顺序播放全部歌曲"));
		play_allView.clickAndWaitForNewWindow();
		sleep((long) 2000.0);
		getUiDevice().sleep();
	    sleep((long) 3570000.0);
	    getUiDevice().wakeUp();
	    sleep((long) 2000.0);
	    Util util = new Util();
	    util.testUnlock();	
	    sleep((long) 5000.0);
	    Process closeMusic = Runtime.getRuntime().exec("am force-stop com.miui.player");
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
		Process openFileManager= Runtime.getRuntime().exec("am start com.android.fileexplorer/.FileExplorerTabActivity");
		sleep((long) 5000.0);
		UiObject phoneView = new UiObject(new UiSelector().text("手机"));
		phoneView.clickAndWaitForNewWindow();
		sleep((long) 2000.0);
		UiObject folderView = new UiObject(new UiSelector().text("Alarms"));
		folderView.clickAndWaitForNewWindow();
		sleep((long) 2000.0);
		UiObject videoView = new UiObject(new UiSelector().text("test.mp4"));
		videoView.clickAndWaitForNewWindow();
		sleep((long) 1770000.0);
		Process closeVideoPlayer = Runtime.getRuntime().exec("am force-stop com.miui.videoplayer");
		sleep((long) 5000.0);
		Process closeFileManager = Runtime.getRuntime().exec("am force-stop com.android.fileexplorer");
		sleep((long) 5000.0);
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
		Process openReader= Runtime.getRuntime().exec("am start com.duokan.reader/.ReaderBrowserActivity");
		sleep((long) 10000.0);
		UiObject booknameView = new UiObject(new UiSelector().text("鲁迅全集·节选"));
		booknameView.clickAndWaitForNewWindow();
		sleep((long) 5000.0);
		for(int i = 0; i < 59;i++)
		{
			getUiDevice().click(630,970);
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
	}*/

	@SuppressWarnings({ "unused", "deprecation" })
	public void test5Weibo() throws IOException, UiObjectNotFoundException
	{
		FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);
		Process openWeibo= Runtime.getRuntime().exec("am start com.sina.weibo/.SplashActivity");
		sleep((long) 5000.0);
		for(int i = 0; i < 102;i++)
		{
			UiObject buttonView = new UiObject(new UiSelector().text("随便看看"));
			buttonView.clickAndWaitForNewWindow();
			sleep((long) 10000.0);
			getUiDevice().swipe(20, 1000, 20, 500, 3);
			sleep((long) 5000.0);
			getUiDevice().swipe(20, 1000, 20, 500, 3);
			sleep((long) 5000.0);
			getUiDevice().pressBack();
			UiScrollable appViews = new UiScrollable(new UiSelector()
	         .scrollable(true));
			 UiObject MoreButton = appViews.getChildByText(new UiSelector()
	         .className(android.widget.TextView.class.getName()), 
	         "更多");
			 MoreButton.clickAndWaitForNewWindow();
			sleep((long) 10000.0);
			getUiDevice().swipe(20, 1000, 20, 500, 3);
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
		Process startCall= Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel:15301396055");
		sleep((long) 2000.0);
		getUiDevice().sleep();
	    sleep((long) 3570000.0);
	    getUiDevice().wakeUp();
	    sleep((long) 2000.0);
	    Util util = new Util();
	    util.testUnlock();	
	    sleep((long) 5000.0);
	    UiObject endButton = new UiObject(new UiSelector().
	    		   className("android.widget.Button").index(1));

	    if(endButton.exists() && endButton.isEnabled()) 
	    {
	    	endButton.click();
	    }
		sleep((long) 5000.0);
		Process closeCallPlayer = Runtime.getRuntime().exec("am force-stop com.android.contacts ");
		sleep((long) 5000.0);
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
		Process openSetting = Runtime.getRuntime().exec("am start com.android.settings/.MiuiSettings");
		sleep((long) 5000.0);
		UiObject allsteView = new UiObject(new UiSelector().text("全部设置"));
		allsteView.clickAndWaitForNewWindow();
		sleep((long) 5000.0);
		UiObject wifiView = new UiObject(new UiSelector().text("WLAN"));
		wifiView.clickAndWaitForNewWindow();				
		sleep((long) 5000.0);
		UiObject endButton = new UiObject(new UiSelector().
		 		   className("android.widget.CheckBox").index(0));

		 if(endButton.exists() && endButton.isEnabled()) 
		 {
		 	endButton.click();
		 }	
		sleep((long) 5000.0);
		Process closeSetting = Runtime.getRuntime().exec("am force-stop com.android.settings");
		sleep((long) 10000.0);
		for(int i = 0; i < 100;i++)
		{
			Process Brower1 = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d " +
		    		"http://3g.sina.com.cn -n com.android.browser/.BrowserActivity -f 0x13800000");
		    sleep((long) 20000.0);
		    getUiDevice().click(360,800);
		    sleep((long) 20000.0);
		    Process Brower2 = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d " +
		    		"http://3g.qq.com -n com.android.browser/.BrowserActivity -f 0x13800000");
		    sleep((long) 20000.0);
		    getUiDevice().click(323,956);
		    sleep((long) 20000.0);
		}
		Process closeBrower = Runtime.getRuntime().exec("am force-stop com.android.browser ");
		sleep((long) 5000.0);
		Process getPower_end = Runtime.getRuntime().exec("cat /sys/class/power_supply/battery/capacity");
		sleep((long) 5000.0);
		DataInputStream inputLogcat2 = new DataInputStream(getPower_end.getInputStream());
		while ((logcat = inputLogcat2.readLine()) != null){
			outputLogcat.write(("End_Brower:"+logcat+ "\n").getBytes());
		}
		Process openSetting1 = Runtime.getRuntime().exec("am start com.android.settings/.MiuiSettings");
		sleep((long) 5000.0);
		UiObject allsteView1 = new UiObject(new UiSelector().text("全部设置"));
		allsteView1.clickAndWaitForNewWindow();
		sleep((long) 5000.0);
		UiObject wifiView1 = new UiObject(new UiSelector().text("WLAN"));
		wifiView1.clickAndWaitForNewWindow();				
		sleep((long) 5000.0);
		UiObject endButton1 = new UiObject(new UiSelector().
		 		   className("android.widget.CheckBox").index(0));

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
		Process openWeixin= Runtime.getRuntime().exec("am start com.android.camera/.Camera");
		sleep((long) 5000.0);
		UiObject button1View = new UiObject(new UiSelector().description("前视和后视相机开关"));
		button1View.clickAndWaitForNewWindow();
		sleep((long) 5000.0);
		for(int i = 1;i < 59; i++)
		{
			UiObject button2View = new UiObject(new UiSelector().description("“快门”按钮"));
			button2View.clickAndWaitForNewWindow();
			sleep((long) 30000.0);
		}
		sleep((long) 5000.0);
		Process closeWeibo = Runtime.getRuntime().exec("am force-stop com.android.camera");
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
		junit.textui.TestRunner.run(PowerMeasure.class);
	}
	

}
