package BspTest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class TDStabilityTest extends UiAutomatorTestCase 
{  
	
	//清空logcat信息
	protected void setUp() throws UiObjectNotFoundException, IOException 
	{
		getUiDevice().pressHome();
		Process ClearLog = Runtime.getRuntime().exec("logcat -b radio -c");
		System.out.println("Steup");
	}
	
	//判断是否掉网
	@SuppressWarnings("deprecation")
	protected void tearDown() throws UiObjectNotFoundException,IOException 
	{ 
		System.out.println("tearDown");
		SimpleDateFormat date1 = new SimpleDateFormat("yyyyMMddHH:mm:ss");

		File resultReport=new File("/sdcard/FailList.txt");
		resultReport.createNewFile();
		FileOutputStream inputResult=new FileOutputStream(resultReport,true);
		String logcat;
		File logcatFile=new File("/sdcard/"+(date1.format(new Date()))+".txt");
		logcatFile.createNewFile();
		FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);
		Process getLogcat = Runtime.getRuntime().exec("logcat -b radio -t 1000");
		DataInputStream inputLogcat = new DataInputStream(getLogcat.getInputStream());
		while ((logcat = inputLogcat.readLine()) != null){
			outputLogcat.write(logcat.getBytes());				
		}
		try {  
            String encoding = "GBK"; 
            InputStreamReader read = new InputStreamReader(new FileInputStream(logcatFile), encoding);  
            BufferedReader bufferedReader = new BufferedReader(read);  
            String lineTXT;  
            if ((lineTXT = bufferedReader.readLine()) != null) {  
                 String log = lineTXT.toString().trim();
                 int result=log.indexOf("CARDSTATE_ABSENT");
                 if(result != -1)
                 {
                	sleep((long) 2000.0);
                 }
                 else
                 {
                	 String note = "Off the net";
             		inputResult.write((date1.format(new Date())+"  \n"+note+"  \n").getBytes());
                 }
                 }
            } catch (Exception e) 
            {  
	            System.out.println("failed1");  
	            e.printStackTrace(); 
	            }
		}

	/*//联系人中添加删除模块
	@SuppressWarnings({  "unused", "deprecation" })
	public void test01() throws ParserConfigurationException, SAXException, IOException, UiObjectNotFoundException 
	{
		for(int i = 1 ; i < 2 ; i++)
		{
			Process runPeopleActivity = Runtime.getRuntime().exec("am start com.android.contacts/.activities.PeopleActivity");
			sleep((long) 5000.0);
			UiObject PeopleButton = new UiObject(new UiSelector()
	         .textContains("联系人"));
			PeopleButton.click();
			sleep((long) 2000.0);
			 UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
			UiObject settingsApp = appViews.getChildByText(new UiSelector()
	         .className(android.widget.TextView.class.getName()),"新建联系人");
	      settingsApp.clickAndWaitForNewWindow();
			sleep((long) 2000.0);
			UiObject GreatNameButton = new UiObject(new UiSelector().
		    		   className("android.widget.EditText").index(0).instance(0));
			GreatNameButton.setText("test");
			sleep((long) 5000.0);
			UiObject GreatCallNumberButton = new UiObject(new UiSelector().
		    		   className("android.widget.EditText").index(0).instance(2));
			GreatCallNumberButton.setText("000000");
			UiObject OkButton = new UiObject(new UiSelector()
	         .textContains("确定"));
			OkButton.click();
			getUiDevice().pressBack();
			sleep((long) 2000.0);
			getUiDevice().pressMenu();
			sleep((long) 2000.0);
			UiObject SettingButton = new UiObject(new UiSelector()
	         .textContains("设置"));
			SettingButton.click();
			sleep((long) 2000.0);
			getUiDevice().swipe(20, 1000, 20, 500, 10);
			sleep((long) 2000.0);
			UiObject TidyButton = new UiObject(new UiSelector()
	         .textContains("联系人整理"));;
	         TidyButton.clickAndWaitForNewWindow();
			sleep((long) 2000.0);
			UiObject DeleteButton = new UiObject(new UiSelector()
	         .textContains("批量删除联系人"));;
			DeleteButton.clickAndWaitForNewWindow();
			UiObject People1Button = new UiObject(new UiSelector()
	         .textContains("test"));
			People1Button.clickAndWaitForNewWindow();
			OkButton.clickAndWaitForNewWindow();
			sleep((long) 2000.0);
			UiObject Delete1App = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(1));
			Delete1App.clickAndWaitForNewWindow();
			sleep((long) 2000.0);
			Process killPeopleActivity = Runtime.getRuntime().exec("am force-stop com.android.contacts");
			sleep((long) 2000.0);
		}		
	}
	
	//打电话测试模块
	@SuppressWarnings({ "unused" })
	public void test02() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 100 ; i++)
		{
			String callnumber = "tel:10086";
			Process startCall = Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d " + callnumber);
			UiObject settingsValidation = new UiObject(new UiSelector()
	         .textContains("00:"));
			settingsValidation.waitForExists((long) 20000.0);
			sleep((long) 10000.0);
			UiObject endButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(1));
		    if(endButton.exists() && endButton.isEnabled()) 
		    {
		    	endButton.click();
		    }
		    sleep((long) 5000.0);
		}    	
	}
	
	//发信息模块
	@SuppressWarnings({ "unused" })
	public void test03() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 1 ; i < 20 ; i++)
		{
			String smsNumber = "10086";
			String details = "cxyl";
			Process runSmsMo = Runtime.getRuntime().exec("am start -a android.intent.action.SENDTO -d sms:" + smsNumber + " --es sms_body " + details);
			sleep((long) 5000.0);
			UiObject SendButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(1));
			UiObject SendButton1 = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(0).instance(3));
		    if (SendButton1.exists()) 
		    {
		    	SendButton1.click();
	        } 
		    else 
		    {
	        	SendButton.click();
	        }
		    sleep((long) 5000.0);
		}
	}
	
	//发彩信模块
		@SuppressWarnings({ "unused" })
		public void test04() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
			for(int i = 1 ; i < 20 ; i++)
			{
				String smsNumber = "10086";
				Process runSmsMo = Runtime.getRuntime().exec("am start -a android.intent.action.SENDTO -d sms:" + smsNumber );
				sleep((long) 5000.0);
				UiObject EnclosureButton = new UiObject(new UiSelector().
			    		   className("android.widget.Button").index(0).instance(0));
				UiObject Enclosure1Button = new UiObject(new UiSelector().
			    		   className("android.widget.Button").index(0).instance(2));
			    if (Enclosure1Button.exists()) 
			    {
			    	Enclosure1Button.click();
		        } 
			    else 
			    {
			    	EnclosureButton.click();
		        }
			    UiObject CamButton = new UiObject(new UiSelector().
			    		   className("android.widget.LinearLayout").index(4));
			    CamButton.clickAndWaitForNewWindow();
			    UiObject PhotographButton = new UiObject(new UiSelector()
		          .description("“快门”按钮"));
			    PhotographButton.click();
			    sleep((long) 5000.0);
			    UiObject OkButton = new UiObject(new UiSelector().
			    		   className("android.widget.ImageView").index(1));
			    OkButton.click();
			    sleep((long) 5000.0);
			    UiObject SendButton = new UiObject(new UiSelector().
			    		   className("android.widget.Button").index(1));			    
			    if(SendButton.exists())
			    {
		        	SendButton.click();
		        }
			    
			    sleep((long) 5000.0);
			}
		}
	
	//发邮件模块
	@SuppressWarnings({ "unused" })
	public void test05() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
      for(int i = 1 ; i < 2 ; i++){
    	  Process runEmail = Runtime.getRuntime().exec("am start com.android.email/.activity.Welcome");
  		sleep((long) 5000.0);
  		UiObject GreatButton = new UiObject(new UiSelector()
        .textContains("写邮件"));;
        GreatButton.click();
  		sleep((long) 5000.0);
    	  UiObject WriteButton = new UiObject(new UiSelector().
	    		   className("android.widget.MultiAutoCompleteTextView").index(1).instance(0));
    	  WriteButton.setText("xuejinghao123@126.com");
  		sleep((long) 5000.0);
  		UiObject Write1Button = new UiObject(new UiSelector().
	    		   className("android.widget.EditText").index(0).instance(0));
  		Write1Button.setText("test");
		sleep((long) 5000.0);
		UiObject Write2Button = new UiObject(new UiSelector().
	    		   className("android.widget.EditText").index(0).instance(1));
		String t = "test"+i;
		Write2Button.setText(t);
		sleep((long) 5000.0);
		UiObject sendButton = new UiObject(new UiSelector().
	    		   className("android.widget.FrameLayout").index(1).instance(1));
        sendButton.click();
		sleep((long) 5000.0);  
		Process killPeopleActivity = Runtime.getRuntime().exec("am force-stop com.android.email");
		sleep((long) 2000.0);  
      }
	}
	
	//发邮件带附件模块
	@SuppressWarnings({ "unused" })
	public void test06() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
      for(int i = 1 ; i < 2 ; i++){
    	  Process runEmail = Runtime.getRuntime().exec("am start com.android.email/.activity.Welcome");
  		sleep((long) 5000.0);
  		UiObject GreatButton = new UiObject(new UiSelector()
        .textContains("写邮件"));;
        GreatButton.click();
  		sleep((long) 5000.0);
    	  UiObject WriteButton = new UiObject(new UiSelector().
	    		   className("android.widget.MultiAutoCompleteTextView").index(1).instance(0));
    	  WriteButton.setText("xuejinghao123@126.com");
  		sleep((long) 5000.0);
  		UiObject Write1Button = new UiObject(new UiSelector().
	    		   className("android.widget.EditText").index(0).instance(0));
  		Write1Button.setText("test");
		sleep((long) 5000.0);
		UiObject Write2Button = new UiObject(new UiSelector().
	    		   className("android.widget.EditText").index(0).instance(1));
		String t = "test"+i;
		Write2Button.setText(t);
		sleep((long) 5000.0);
		getUiDevice().pressMenu();
		sleep((long) 2000.0);
		UiObject GreatAddButton = new UiObject(new UiSelector()
        .textContains("添加附件"));
        GreatAddButton.clickAndWaitForNewWindow();
        UiObject Write3Button = new UiObject(new UiSelector().
	    		   className("android.widget.ImageView").index(0).instance(2));
        Write3Button.clickAndWaitForNewWindow();
        UiObject DCIMButton = new UiObject(new UiSelector()
        .textContains("DCIM"));
        DCIMButton.clickAndWaitForNewWindow();
        UiObject CameraButton = new UiObject(new UiSelector()
        .textContains("Camera"));
        CameraButton.clickAndWaitForNewWindow();
        UiObject Write4Button = new UiObject(new UiSelector().
	    		   className("android.widget.ImageView").index(0).instance(0));
        Write4Button.clickAndWaitForNewWindow();
		UiObject sendButton = new UiObject(new UiSelector().
	    		   className("android.widget.FrameLayout").index(1).instance(1));
        sendButton.click();
		sleep((long) 5000.0);  
		Process killPeopleActivity = Runtime.getRuntime().exec("am force-stop com.android.email");
		sleep((long) 2000.0);  
      }
	}
	
	//删除邮件模块
	@SuppressWarnings({ "unused" })
	public void test07() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
      for(int i = 1 ; i < 2 ; i++){
    	  Process runEmail = Runtime.getRuntime().exec("am start com.android.email/.activity.Welcome");
  		sleep((long) 5000.0);
    	  UiObject WriteButton = new UiObject(new UiSelector().
	    		   className("android.view.View").index(0).instance(0));
    	  WriteButton.clickAndWaitForNewWindow();
  		sleep((long) 3000.0);
  		UiObject MoreButton = new UiObject(new UiSelector()
        .textContains("更多"));
  		MoreButton.clickAndWaitForNewWindow();
  		UiObject DeleteButton = new UiObject(new UiSelector()
        .textContains("删除"));
  		DeleteButton.clickAndWaitForNewWindow();
		sleep((long) 5000.0);  
		Process killPeopleActivity = Runtime.getRuntime().exec("am force-stop com.android.email");
		sleep((long) 2000.0);  
	  }
	}
	
	//打开特定web网页
	@SuppressWarnings("unused")
	public void test08() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 5 ; i++){
			Process OpenBrowser = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d " +
					"http://3g.sina.com.cn -n com.android.browser/.BrowserActivity -f 0x13800000");
			UiObject BrowserView = new UiObject(new UiSelector()
	        .textContains("手机新浪网"));
			BrowserView.waitForExists((long) 20000.0);
			sleep((long) 5000.0);
			Process killBrowser = Runtime.getRuntime().exec("am force-stop com.android.browser");
		}
	}
	
	//网页下载文件
	@SuppressWarnings("unused")
	public void test09() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 5 ; i++){
			Process OpenBrowser = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d " +
					"http://121.11.93.131/file/MDAwMDAwMDHCvbW7iXEpAcA4WC6cNz_PH38FGcv4ohWpNIGOKJnkuw.." +
	    			"/c1f6d8f233c1c017f6d4e938cc200784d9/read.rar?key=AAABQFGmBu1XAhfw&p=&a=2288995-72ff0387-4804" +
	    			"9-0/010100&mode=download -n com.android.browser/.BrowserActivity");
			sleep((long) 5000.0);
			Process killBrowser = Runtime.getRuntime().exec("am force-stop com.android.browser");
		}
	}
	
	//网页下载音乐
	@SuppressWarnings("unused")
	public void test10() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 5 ; i++){
			Process OpenBrowser = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d " +
					"http://122.136.46.216/file/MDAwMDAwMDE9hUzOQ1FS-8Ih0sUHfa6EaxMbuF5hkV" +
					"Mp8XqTel0-Ig../f3fa24e16bf828210a35e025703153217b300/%E8%B6%8A%E6%9D%A5" +
					"%E8%B6%8A%E5%A5%BD%20%20-%20%E9%93%83%E5%A3%B0.mp3?key=AAABQFGmCy6jEuyj&p=&" +
					"a=2288995-72ff0387-48049-0/010100&mode=download -n com.android.browser/.BrowserActivity");
			sleep((long) 10000.0);
			Process killBrowser = Runtime.getRuntime().exec("am force-stop com.android.browser");
		}
	}
	
	//网页下载图片
	@SuppressWarnings("unused")
	public void test11() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 5 ; i++){
			Process OpenBrowser = Runtime.getRuntime().exec("am start -a android.intent.action.VIEW -d " +
					"http://121.11.93.131/file/MDAwMDAwMDG9qloE11PPk8aItHE9h" +
					"p0xr_10RoiiHMVt1-REURQRdQ../ed410aaf591b5c8d39c2af5134a16bfbb08b" +
					"1/qqq.png?key=AAABQFGmC4z_lmZS&p=&a=2288995-72ff0387-48049-0/010" +
					"100&mode=download -n com.android.browser/.BrowserActivity");
			sleep((long) 10000.0);
			Process killBrowser = Runtime.getRuntime().exec("am force-stop com.android.browser");
		}
	}
	
	//删除下载文件
	@SuppressWarnings("unused")
	public void test12() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 1 ; i++){
			Process Download = Runtime.getRuntime().exec("am start com.android.providers.downloads.ui/.DownloadList");
			sleep((long) 3000.0);
			UiObject BrowserView = new UiObject(new UiSelector()
	        .textContains("已下载"));
			BrowserView.clickAndWaitForNewWindow();
			UiObject WriteButton = new UiObject(new UiSelector().
		    		   className("android.widget.ImageView").index(0).instance(0));
			WriteButton.longClick();
	    	UiObject DeleteButton = new UiObject(new UiSelector()
		        .textContains("删除"));
	    	DeleteButton.clickAndWaitForNewWindow();
	    	sleep((long) 3000.0);
	    	UiObject Delete1Button = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(1).instance(0));
	    	Delete1Button.clickAndWaitForNewWindow();
	    	sleep((long) 3000.0);
			Process killBrowser = Runtime.getRuntime().exec("am force-stop com.android.providers.downloads.ui");
			sleep((long) 3000.0);
		}
	}
	
	//日历添加事件
	@SuppressWarnings("unused")
	public void test13() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 5 ; i++){
			Process OpenCalendar = Runtime.getRuntime().exec("am start com.android.calendar/.AllInOneActivity");
			sleep((long) 3000.0);
			UiObject AddButton = new UiObject(new UiSelector()
	        .textContains("新建"));
			AddButton.clickAndWaitForNewWindow();
			UiObject WriteButton = new UiObject(new UiSelector().
		    		   className("android.widget.EditText").index(0).instance(0));
	    	  WriteButton.setText("test"+i);
	    	UiObject OkButton = new UiObject(new UiSelector()
		        .textContains("完成"));
	    	OkButton.clickAndWaitForNewWindow();
	    	sleep((long) 3000.0);
			Process killBrowser = Runtime.getRuntime().exec("am force-stop com.android.calendar");
			sleep((long) 3000.0);
		}
	}
	
	//添加闹钟
	@SuppressWarnings("unused")
	public void test14() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 5 ; i++){
			Process OpenClock = Runtime.getRuntime().exec("am start com.android.deskclock/.DeskClockTabActivity");
			sleep((long) 3000.0);
			UiObject AddButton = new UiObject(new UiSelector()
	        .textContains("闹钟"));
			AddButton.clickAndWaitForNewWindow();
			UiObject Add1Button = new UiObject(new UiSelector()
	        .textContains("添加闹钟"));
			Add1Button.clickAndWaitForNewWindow();
	    	UiObject OkButton = new UiObject(new UiSelector()
		        .textContains("确定"));
	    	OkButton.clickAndWaitForNewWindow();
	    	sleep((long) 3000.0);
			Process killClock = Runtime.getRuntime().exec("am force-stop com.android.deskclock");
			sleep((long) 3000.0);
			}
		}
	
	//删除闹钟
	@SuppressWarnings("unused")
	public void test15() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 5 ; i++){
			Process OpenClock = Runtime.getRuntime().exec("am start com.android.deskclock/.DeskClockTabActivity");
			sleep((long) 3000.0);
			UiObject WriteButton = new UiObject(new UiSelector().
		    		   className("android.widget.ImageView").index(2).instance(0));
			WriteButton.longClick();
			UiObject Add1Button = new UiObject(new UiSelector()
	        .textContains("删除闹钟"));
			Add1Button.clickAndWaitForNewWindow();
	    	UiObject DeleteButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(1).instance(0));
	    	DeleteButton.clickAndWaitForNewWindow();
	    	sleep((long) 3000.0);
			Process killClock = Runtime.getRuntime().exec("am force-stop com.android.deskclock");
			sleep((long) 3000.0);
		}
	}
	
	//录音、播放、删除
	@SuppressWarnings("unused")
	public void test16() throws UiObjectNotFoundException, RemoteException, IOException, ParserConfigurationException, SAXException{
		for(int i = 0 ; i < 5 ; i++){
			Process OpenClock = Runtime.getRuntime().exec("am start com.android.soundrecorder/.SoundRecorder");
			sleep((long) 3000.0);
			UiObject StartButton = new UiObject(new UiSelector().
		    		   className("android.widget.ImageButton").index(1).instance(0));
			StartButton.click();
			sleep((long) 5000.0);
			UiObject StopButton = new UiObject(new UiSelector().
		    		   className("android.widget.ImageButton").index(0).instance(0));
			StopButton.click();
			sleep((long) 2000.0);
			UiObject PlayButton = new UiObject(new UiSelector().
		    		   className("android.widget.ImageButton").index(0).instance(0));
			PlayButton.click();
			sleep((long) 16000.0);
			UiObject listButton = new UiObject(new UiSelector().
		    		   className("android.widget.ImageButton").index(2).instance(0));
			listButton.clickAndWaitForNewWindow();
			UiObject DeleteButton = new UiObject(new UiSelector().
		    		   className("android.widget.ImageView").index(2).instance(0));
			DeleteButton.longClick();
			UiObject Delete1Button = new UiObject(new UiSelector()
	        .textContains("删除"));
			Delete1Button.clickAndWaitForNewWindow();
			UiObject OkButton = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(1).instance(0));
			OkButton.clickAndWaitForNewWindow();
	    	sleep((long) 3000.0);
			Process killClock = Runtime.getRuntime().exec("am force-stop com.android.soundrecorder");
			sleep((long) 3000.0);
		}
	}*/
}
