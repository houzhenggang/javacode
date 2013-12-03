package bsptest.stability;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
import android.util.Log;
import android.widget.TextView;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
*Interface 接口的具体函数在米3 MIUI的具体实现。
* @version 1.0, 2013-08-23
* @author 薛敬浩
* @return true if successful else false
*/ 

class MI3 extends UiAutomatorTestCase implements IStability
{
	/**给全局变量赋值，值为{@value}*/
	private int i = 1;	
	/** 给全局变量赋值*/ 
	private static final String ACTIVITY_TAG="bsptest";
	/**
	*在XML配置文件中读取String类型的字符创
	* @return true if successful else false
	*/ 

	// 在XML中读取字符串
	private String ReadXmlString(String xmlname,String name) throws ParserConfigurationException, SAXException, IOException
	{
		File f = new File("/sdcard/settings.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(f);
		NodeList nl = doc.getElementsByTagName(xmlname);
		String newname = doc.getElementsByTagName(name).item(0).getFirstChild().getNodeValue();
		return newname;
	}
	/**
	*在XML配置文件中读取int类型的整型
	* @return true if successful else false
	*/ 
	// 在XML中读取int 类型的数值
	private int ReadXmlInt(String xmlname,String name) throws ParserConfigurationException, SAXException, IOException
	{
		File f = new File("/sdcard/settings.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(f);
		NodeList nl = doc.getElementsByTagName(xmlname);
		String newname = doc.getElementsByTagName(name).item(0).getFirstChild().getNodeValue();
		int loop = Integer.parseInt(newname, 10);
		return loop;
	}
	
	/**
	 *ReadXmlInt(string) 可以读取XML中的int型数据。<br> 
	 *ReadXmlString(string)可以读取XML中的string型字符串。<br> 
	 * *UiObject waitApp = new UiObject(new UiSelector().textContains("暂停通话"))
	 *的存在与否可以判断通话是否成功。<br> 
	 *Log.v(MI3.ACTIVITY_TAG, "Modemtest CallMo fail ------------"+i)
	 *会将运行fail的结果输入到logcat中<br>
	 *@param ReadXmlInt("loop") 为循环的Loop次数<br> 
	 *@param ReadXmlString("callNum") 为拨打电话的号码<br> 
	 *@return true if the device has a phone else false
	*/
	public void CallMotest() throws UiObjectNotFoundException, IOException, Exception, SAXException{
		for( i = 1; i <= ReadXmlInt("CallTest","loop");i++)
		{
			//预防在程序运行中出现的紧急窗口
			CrashBug();
			Process mkdir= Runtime.getRuntime().exec("mkdir /sdcard/Satibiility");
			sleep((long) 1000);
			Process startCall= Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel:"+ReadXmlString("CallTest","callNum"));
			sleep((long) 2000);
			UiObject MoreButton = new UiObject(new UiSelector().
					className("android.widget.ToggleButton").index(0).instance(2));
			if(MoreButton.exists())
			{
				MoreButton.click();
			}
			UiObject wait1App = new UiObject(new UiSelector().textContains("正在拨号"));
			wait1App.waitUntilGone((long) 60000.0);
			UiObject waitApp = new UiObject(new UiSelector().textContains("暂停通话"));

			if(waitApp.exists()&& waitApp.isEnabled())
			{
				Log.v(MI3.ACTIVITY_TAG, "Modemtest CallMo Ok ------------"+i);
				File logcatFile=new File("/sdcard/Satibiility/Pass_CallTest.txt");
				logcatFile.createNewFile();
				FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);
				String Calltest = "Calltest Pass "+i+"th.";
				SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
				outputLogcat.write((date1.format(new Date())+"---"+Calltest+"\n").getBytes());
				if(ReadXmlInt("CallTest","endcall") ==1)
				{
					sleep((long) ReadXmlInt("CallTest","calltime"));
					UiObject endButton = new UiObject(new UiSelector().
							className("android.widget.Button").index(1));
					if(endButton.exists()){
						endButton.click();
					}
				}else
				{
					sleep((long) ReadXmlInt("CallTest","calltime"));
				}
				
	
			}else
			{
				UiObject endButton = new UiObject(new UiSelector().
						className("android.widget.Button").index(1));
				if(endButton.exists()){
					endButton.click();
				}
				Log.v(MI3.ACTIVITY_TAG, "Modemtest CallMo fail ------------"+i);
				File logcatFile=new File("/sdcard/Satibiility/Fail_CallTest.txt");
				logcatFile.createNewFile();
				FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);
				String Calltest = "Calltest fail "+i;
				SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
				outputLogcat.write((date1.format(new Date())+"---"+Calltest+"\n").getBytes());
			}
			sleep((long) ReadXmlInt("CallTest","looptime"));
	    
	
		}
	}

	public void SmsMoTest() throws UiObjectNotFoundException, IOException, Exception, SAXException{
		for(i = 1; i <= ReadXmlInt("SmsTest","smsloop");i++)
		{
			CrashBug();
			Process startSms= Runtime.getRuntime().exec("am start -a android.intent.action.SENDTO -d sms:" + 
					ReadXmlString("SmsTest","smsNum") + " --es sms_body " + ReadXmlString("SmsTest","details"));
			sleep((long) 3000);
			UiObject SendButton = new UiObject(new UiSelector().
					className("android.widget.Button").index(0).instance(1));
			UiObject SendButton1 = new UiObject(new UiSelector().
					className("android.widget.Button").index(0).instance(3));
			if (SendButton1.exists()) {
				SendButton1.click();
			}else {
				SendButton.click();
			}
			sleep((long) 10000);
			int result1 = -1;
			String logcat;
			Process getLogcat2 = Runtime.getRuntime().exec("mkdir /sdcard/Satibiility");
			File logcatFile=new File("/sdcard/Satibiility/Sms"+i+".txt");
			logcatFile.createNewFile();
			FileOutputStream outputLogcat=new FileOutputStream(logcatFile,true);
			int j =0;
			while(result1 == -1 && j <=150 )
			{
				Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 10000");
				DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
				while ((logcat = inputLogcat1.readLine()) != null){
					outputLogcat.write(logcat.getBytes());
				}
				BufferedReader bufferedReader1 = new BufferedReader(new FileReader(logcatFile));
				String lineTXT1 ;
				lineTXT1 = bufferedReader1.readLine();
				String log1 = lineTXT1.toString().trim();
				result1=log1.indexOf("errorCode");
				sleep((long) 2000.0);
				Log.v(MI3.ACTIVITY_TAG, "sms send ------------"+i);
			}
			if(j == 150){
				Process KillSms= Runtime.getRuntime().exec("am force-stop com.android.mms");
				sleep((long) 2000.0);
				File logcatFile1=new File("/sdcard/Satibiility/Fail_Smstest.txt");
				logcatFile1.createNewFile();
				FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
				String Smstest = "SmsMotest fail "+i;
				SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
				outputLogcat1.write((date1.format(new Date())+"---"+Smstest+"\n").getBytes());
				Process clearLog= Runtime.getRuntime().exec("logcat -b radio -c");
				sleep((long) ReadXmlInt("SmsTest","smslooptime"));			
			}else{
				Process KillSms= Runtime.getRuntime().exec("am force-stop com.android.mms");
				sleep((long) 2000.0);
				File logcatFile1=new File("/sdcard/Satibiility/Pass_Smstest.txt");
				logcatFile1.createNewFile();
				FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
				String Calltest = "SmsMo Pass "+i+"th";
				SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
				outputLogcat1.write((date1.format(new Date())+"---"+Calltest+"\n").getBytes());
				sleep((long) 1000.0);
				Process DeleteTxt = Runtime.getRuntime().exec("rm /sdcard/Satibiility/Sms"+i+".txt");
				sleep((long) 2000.0);
				Process clearLog= Runtime.getRuntime().exec("logcat -b radio -c");
				sleep((long) ReadXmlInt("SmsTest","smslooptime"));
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void AirPlane() throws UiObjectNotFoundException, IOException, Exception, SAXException{
		CrashBug();
		Process startSetting = Runtime.getRuntime().exec("am start -n com.android.settings/.MiuiSettings");
		sleep((long) 2000.0);
		UiObject allsteView = new UiObject(new UiSelector().text("全部设置"));
		allsteView.clickAndWaitForNewWindow();
		sleep((long) 2000.0);
		Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/Satibiility");
		sleep((long) 1000.0);
		for(i = 1 ; i <=  ReadXmlInt("Airplane","Airplaneloop")*2 ; i++){
		CrashBug();
		Process p = Runtime.getRuntime().exec("logcat -b radio -c");
		sleep((long) 1000.0);
		UiObject SendButton = new UiObject(new UiSelector().
				className("android.widget.CheckBox").index(0).instance(0));
		SendButton.click();
		sleep((long) 10000.0);
		if(i%2 != 0)
		{
			int result1 = -1;
			String logcat;
			File logcatFile=new File("/sdcard/Satibiility/Air"+i+".txt");
			logcatFile.createNewFile();
			FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
			int j = 0;
			while(result1 == -1 && j <= 60)
			{
				Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 10000");
				DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
				while ((logcat = inputLogcat1.readLine()) != null){
					outputLogcat.write(logcat.getBytes());
				}
				BufferedReader bufferedReader1 = new BufferedReader(new FileReader(logcatFile));  
				String lineTXT1 ;
				lineTXT1 = bufferedReader1.readLine();
				String log1 = lineTXT1.toString().trim();
				result1=log1.indexOf("changed sending intent rule=3 showPlmn='false' plmn='null'");
				sleep((long) 2000.0);
				j++;
			}
			if(j == 60){
				File logcatFile1=new File("/sdcard/Satibiility/Fail_OpenAirplane.txt");
				logcatFile1.createNewFile();
				FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
				String Smstest = "Airplane fail "+i;
				SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
				outputLogcat1.write((date1.format(new Date())+"---"+Smstest+"\n").getBytes());
				Process clearLog= Runtime.getRuntime().exec("logcat -b radio -c");
				Log.v(MI3.ACTIVITY_TAG, "Open is timeout------------"+i/2);
				sleep((long) ReadXmlInt("Airplane","Airplanelooptime"));
			}
			else{
				File logcatFile1=new File("/sdcard/Satibiility/Pass_OpenAirplane.txt");
				logcatFile1.createNewFile();
				FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
				String Smstest = "OpenAirplane Pass "+i/2+ "th";
				SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
				outputLogcat1.write((date1.format(new Date())+"---"+Smstest+"\n").getBytes());
				Log.v(MI3.ACTIVITY_TAG, "Open is successfully------------"+i/2);
				Process DeleteTxt = Runtime.getRuntime().exec("rm /sdcard/Satibiility/Air"+i+".txt");
				sleep((long) ReadXmlInt("Airplane","Airplanelooptime"));
			}

		}
		else
		{
			int result1 = -1;
			String logcat;
			File logcatFile=new File("/sdcard/Satibiility/Air"+i+".txt");
			logcatFile.createNewFile();
			FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
			int j = 0;
			while(result1 == -1 && j <= 60)
			{
				Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 10000");
				DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
				while ((logcat = inputLogcat1.readLine()) != null){
					outputLogcat.write(logcat.getBytes());
				}
				BufferedReader bufferedReader1 = new BufferedReader(new FileReader(logcatFile));  
				String lineTXT1 ;
				lineTXT1 = bufferedReader1.readLine();
				String log1 = lineTXT1.toString().trim();
				result1=log1.indexOf("changed sending intent rule=1 showPlmn='false'");
				sleep((long) 2000.0);
			}
			if(j == 60){
				File logcatFile1=new File("/sdcard/Satibiility/Fail_CloseAirplane.txt");
				logcatFile1.createNewFile();
				FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
				String Smstest = "Airplane fail "+i;
				SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
				outputLogcat1.write((date1.format(new Date())+"---"+Smstest+"\n").getBytes());
				Process clearLog= Runtime.getRuntime().exec("logcat -b radio -c");
				Log.v(MI3.ACTIVITY_TAG, "Close is timeout------------"+i/2);
				sleep((long) ReadXmlInt("Airplane","Airplanelooptime"));
			}
			else{
				File logcatFile1=new File("/sdcard/Satibiility/Pass_CloseAirplane.txt");
				logcatFile1.createNewFile();
				FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
				String Smstest = "CloseAirplane Pass "+i/2+ "th";
				SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
				outputLogcat1.write((date1.format(new Date())+"---"+Smstest+"\n").getBytes());
				Process DeleteTxt = Runtime.getRuntime().exec("rm /sdcard/Satibiility/Air"+i+".txt");
				sleep((long) 1000.0);
				Log.v(MI3.ACTIVITY_TAG, "Close is successfully------------"+i/2);
				sleep((long) ReadXmlInt("Airplane","Airplanelooptime"));
			}
		}
		}
	}
	
	@SuppressWarnings({  "unused", "deprecation" })
	public void DataChange() throws UiObjectNotFoundException, IOException, Exception, SAXException {
		CrashBug();
		Process runSetting = Runtime.getRuntime().exec("am start com.android.phone/.MiuiMobileNetworkSettings");
		sleep((long) 5000.0);
		UiObject allsteView = new UiObject(new UiSelector().text("网络类型选择"));
		allsteView.clickAndWaitForNewWindow();
		sleep((long) 2000.0);
		UiObject add3App = new UiObject(new UiSelector().text("3G网络优先"));
		add3App.click();
		sleep((long) 1000.0);
		for(int i = 1 ; i <= ReadXmlInt("Date","Dateloop")*2; i++)
		{
			allsteView.clickAndWaitForNewWindow();
			sleep((long) 2000.0);
			Process mkdir = Runtime.getRuntime().exec("mkdir /sdcard/Satibiility");
			sleep((long) 1000);
			if(i%2 != 0)
			{
				Process getLog1= Runtime.getRuntime().exec("logcat -b radio -c");
				UiObject add1App = new UiObject(new UiSelector().text("仅使用2G网络(更省电)"));
				add1App.click();
				int result1 = -1;
				String logcat;
				File logcatFile=new File("/sdcard/Satibiility/Date"+i+".txt");
				logcatFile.createNewFile();
				FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
				int j = 0;
				while(result1 == -1 && j <= 150)
				{
		    		Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 10000");
		    		DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
					while ((logcat = inputLogcat1.readLine()) != null){
						outputLogcat.write(logcat.getBytes());
					}
					BufferedReader bufferedReader1 = new BufferedReader(new FileReader(logcatFile));  
					String lineTXT1 ;
					lineTXT1 = bufferedReader1.readLine();
					String log1 = lineTXT1.toString().trim();
					result1=log1.indexOf("changed sending intent rule=1 showPlmn='false'");
					sleep((long) 2000.0);
				}
				if(j == 150){
					File logcatFile1=new File("/sdcard/Satibiility/Fail_GsmDatetest.txt");
					logcatFile1.createNewFile();
					FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
					String Smstest = "DateChange fail "+i;
					SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
					outputLogcat1.write((date1.format(new Date())+"---"+Smstest+"\n").getBytes());
					Process clearLog= Runtime.getRuntime().exec("logcat -b radio -c");
					Log.v(MI3.ACTIVITY_TAG, "Change GSM only is timeout------------"+(i+1)/2);
					sleep((long) ReadXmlInt("Date","Datelooptime"));
				}
				else{
					File logcatFile1=new File("/sdcard/Satibiility/Pass_GsmDatetest.txt");
					logcatFile1.createNewFile();
					FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
					String Smstest = "GsmDatetest Pass "+i+ "th";
					SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
					outputLogcat1.write((date1.format(new Date())+"---"+Smstest+"\n").getBytes());
					Process DeleteTxt = Runtime.getRuntime().exec("rm /sdcard/Satibiility/Date"+i+".txt");
					Log.v(MI3.ACTIVITY_TAG, "Change GSM only is successfully------------"+(i+1)/2);
					sleep((long) ReadXmlInt("Date","Datelooptime"));
				}
    		}
			else{
					Process getLog1= Runtime.getRuntime().exec("logcat -b radio -c");
					UiObject add1App = new UiObject(new UiSelector()
					  .text("仅使用3G网络"));
					add1App.click();
					UiObject okButton = new UiObject(new UiSelector().text("确定"));
					if(okButton.exists()){
						okButton.clickAndWaitForNewWindow();
					}
					int result1 = -1;
					String logcat;
					File logcatFile=new File("/sdcard/Satibiility/Date"+i+".txt");
					logcatFile.createNewFile();
					FileOutputStream outputLogcat=new FileOutputStream(logcatFile);
					int j = 0;
					while(result1 == -1 && j<= 150)
					{
						Process getLogcat1 = Runtime.getRuntime().exec("logcat -b radio -t 10000");
						DataInputStream inputLogcat1 = new DataInputStream(getLogcat1.getInputStream());
						while ((logcat = inputLogcat1.readLine()) != null){
							outputLogcat.write(logcat.getBytes());
					}
					BufferedReader bufferedReader1 = new BufferedReader(new FileReader(logcatFile));  
					String lineTXT1 ;
					lineTXT1 = bufferedReader1.readLine();
					String log1 = lineTXT1.toString().trim();
					result1=log1.indexOf("changed sending intent rule=1 showPlmn='false'");
					sleep((long) 2000.0);
				}
				if(j == 150){
					File logcatFile1=new File("/sdcard/Satibiility/Fail_TdDatetest.txt");
					logcatFile1.createNewFile();
					FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
					String Smstest = "DateChange fail "+i;
					SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
					outputLogcat1.write((date1.format(new Date())+"---"+Smstest+"\n").getBytes());
					Process clearLog= Runtime.getRuntime().exec("logcat -b radio -c");
					Log.v(MI3.ACTIVITY_TAG, "Change TD only is timeout------------"+i/2);
					sleep((long) ReadXmlInt("Date","Datelooptime"));
				}
				else{
					File logcatFile1=new File("/sdcard/Satibiility/Pass_TdDatetest.txt");
					logcatFile1.createNewFile();
					FileOutputStream outputLogcat1=new FileOutputStream(logcatFile1,true);
					String Smstest = "TdDatetest Pass "+i+ "th";
					SimpleDateFormat date1 = new SimpleDateFormat("HH:mm:ss");
					outputLogcat1.write((date1.format(new Date())+"---"+Smstest+"\n").getBytes());
					Process DeleteTxt = Runtime.getRuntime().exec("rm /sdcard/Satibiility/Date"+i+".txt");
					Log.v(MI3.ACTIVITY_TAG, "Change TD only is successfully------------"+i/2);
					sleep((long) ReadXmlInt("Date","Datelooptime"));
				}
			}
		}
	}
	
	public void CrashBug()
	{
		UiWatcher confirmDialogWatcher = new UiWatcher(){
			@Override
			public boolean checkForCondition() {
				UiObject okCancelDialog = new UiObject(new UiSelector().text("取消"));
				if(okCancelDialog.exists()){
					try {	
						Process p1 = Runtime.getRuntime().exec("mkdir /sdcard/Satibiility ");
						SimpleDateFormat date1 = new SimpleDateFormat("HHmm");
						Process p2 = Runtime.getRuntime().exec("screencap -p /sdcard/Satibiility/" + date1.format(new Date()) + ".png");
						 sleep((long) 1000.0);
						okCancelDialog.click();
						 sleep((long) 1000.0);
						/*GetLog getLog = new GetLog();
						getLog.getBugreportLog();*/
					} catch (UiObjectNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				return false;
			}
			
		};
		
		UiDevice.getInstance().registerWatcher("UIWATCHER", confirmDialogWatcher);
		UiDevice.getInstance().runWatchers();
	}

	public void Alarm() throws UiObjectNotFoundException, IOException, RemoteException{
		for(int i = 1 ; i < 350 ; i++)
		{
			Process runSmsMo = Runtime.getRuntime().exec("am start com.android.deskclock/.DeskClockTabActivity");
			sleep((long) 5000.0);
			UiObject AlarmButton = new UiObject(new UiSelector().text("添加闹钟"));
			AlarmButton.clickAndWaitForNewWindow();
			SimpleDateFormat date1 = new SimpleDateFormat("ss");
			SimpleDateFormat date2 = new SimpleDateFormat("mm");
			int ss = Integer.parseInt(date1.format(new Date()), 10);
			int mm = Integer.parseInt(date2.format(new Date()), 10);
			int num = 60-ss;
			UiObject Set1Button = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(2).instance(2));
			UiObject Set2Button = new UiObject(new UiSelector().
		    		   className("android.widget.Button").index(2).instance(1));
			if(mm <= 59 && mm >= 56){
				Set2Button.click();
				for(int j = 0 ; j < 3 ; j++){
					Set1Button.click();
					
			}
			}else{
				for(int j = 0 ; j < 3 ; j++){
					Set1Button.click();
					/*sleep((long) 1000.0);*/
				}
			}
			
			UiObject OkButton = new UiObject(new UiSelector().text("确定"));
			OkButton.click();
			UiDevice.getInstance().pressHome();
			UiObject alarmAlert = new UiObject(new UiSelector().packageName("com.android.deskclock").
		    		   className("android.view.View").index(0));
			WaitFor(alarmAlert,(150+num));
			/*sleep((long) 10000.0);
			sleep((long) ((240+num)*1000));
			UiDevice.getInstance().sleep();
			sleep((long) ((240+num)*1000.0));*/
			sleep((long) 2000.0);
			Process p2 = Runtime.getRuntime().exec("screencap -p /sdcard/" + i + ".png");
			sleep((long) 3000.0);
			UiDevice.getInstance().swipe(215, 1717, 910, 1717, 10);
			sleep((long) 10000.0);
			Process closeGmae = Runtime.getRuntime().exec("am force-stop com.android.deskclock");
			sleep((long) 5000.0);
		
		}
	}

	
	public void WaitFor(UiObject uiObject,int tiemout) throws UiObjectNotFoundException, IOException{
		int i=0;
		while(!uiObject.exists() && (i < tiemout)){
			sleep((long) 1000.0);
			i++;
			Log.v(MI3.ACTIVITY_TAG, "signal-------"+i);
		
		}
		Log.v(MI3.ACTIVITY_TAG, "signalxxxxxxxx-------1");
	}
	
	public void StartCamera(){
		
		try {
			Process StartCamera = Runtime.getRuntime().exec("am start com.android.camera/.Camera");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void BackPhotograph()throws UiObjectNotFoundException{
		CrashBug();
		try {
			sleep((long) ReadXmlInt("Camera","BackPhotograph"));
			UiObject Photograp = new UiObject(new UiSelector()
	        .description("“快门”按钮"));
			if(Photograp.exists()){
				Photograp.click();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void AheadPhotograph()throws UiObjectNotFoundException{
		CrashBug();
		try {
			sleep((long) ReadXmlInt("Camera","AheadPhotograph"));
			UiObject ChangePhotograp = new UiObject(new UiSelector()
	        .description("前视和后视相机开关"));
			if(ChangePhotograp.exists()){
				ChangePhotograp.clickAndWaitForNewWindow();
			}
			sleep((long) 1000);
			UiObject Photograp = new UiObject(new UiSelector()
	        .description("“快门”按钮"));
			if(Photograp.exists()){
				Photograp.clickAndWaitForNewWindow();
			}
			sleep((long) 1000);
			if(ChangePhotograp.exists()){
				ChangePhotograp.clickAndWaitForNewWindow();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void BackVideo() throws UiObjectNotFoundException {
		CrashBug();
		try {
			sleep((long) ReadXmlInt("Camera","BackVideo"));
			UiObject ChangePhotograp1 = new UiObject(new UiSelector().
					className("android.widget.ImageView").index(0).instance(1));
			if(ChangePhotograp1.exists()){
				ChangePhotograp1.clickAndWaitForNewWindow();
			}
			UiObject Photograp = new UiObject(new UiSelector()
	        .description("“快门”按钮"));
			if(Photograp.exists()){
				Photograp.clickAndWaitForNewWindow();
				sleep((long) ReadXmlInt("Camera","BackVideo1"));	
				Photograp.clickAndWaitForNewWindow();
			}
			UiObject ChangePhotograp = new UiObject(new UiSelector().
					className("android.widget.ImageView").index(1));
			if(ChangePhotograp.exists()){
				ChangePhotograp.clickAndWaitForNewWindow();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void AheadVideo() throws UiObjectNotFoundException {
		CrashBug();
		try {
			sleep((long) ReadXmlInt("Camera","AheadVideo"));
			UiObject Change_Photograp = new UiObject(new UiSelector()
	        .description("前视和后视相机开关"));
			if(Change_Photograp.exists()){
				Change_Photograp.clickAndWaitForNewWindow();
			}
			UiObject ChangePhotograp1 = new UiObject(new UiSelector().
					className("android.widget.ImageView").index(0).instance(1));
			if(ChangePhotograp1.exists()){
				ChangePhotograp1.clickAndWaitForNewWindow();
			}
			UiObject Photograp = new UiObject(new UiSelector()
	        .description("“快门”按钮"));
			if(Photograp.exists()){
				Photograp.clickAndWaitForNewWindow();
				sleep((long) ReadXmlInt("Camera","AheadVideo1"));	
				Photograp.clickAndWaitForNewWindow();
			}
			sleep((long) 1000);
			UiObject ChangePhotograp = new UiObject(new UiSelector().
					className("android.widget.ImageView").index(1));
			if(ChangePhotograp.exists()){
				ChangePhotograp.clickAndWaitForNewWindow();
				Change_Photograp.clickAndWaitForNewWindow();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void CloseCamera() {
		try {
			Process closeGmae = Runtime.getRuntime().exec("am force-stop com.android.camera/.Camera");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// TODO Auto-generated method stub
		
	}
}
