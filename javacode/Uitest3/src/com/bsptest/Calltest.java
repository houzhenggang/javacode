package com.bsptest;

import java.io.*;

import android.os.RemoteException;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class Calltest extends UiAutomatorTestCase {   
	protected void setUp() throws UiObjectNotFoundException, RemoteException, IOException {
		getUiDevice().sleep();
	    sleep((long) 2000.0);
		getUiDevice().wakeUp();
		sleep((long) 2000.0);
	    getUiDevice().swipe(380, 935, 380, 1235, 3);
	    sleep((long) 2000.0);
	    getUiDevice().pressHome();
	    sleep((long) 5000.0);			
		}
	
	@SuppressWarnings("unused")
	protected void tearDown() throws UiObjectNotFoundException, RemoteException, IOException { 
		getUiDevice().click(360,1191);
		sleep((long) 5000.0);
		getUiDevice().pressHome();
		Runtime rt = Runtime.getRuntime();
		Process p4 = rt.exec("logcat -c");
		}
	



	@SuppressWarnings({  "unused", "deprecation" })
	public void testA1() throws UiObjectNotFoundException, RemoteException, IOException{
		File newFile1=new File("/sdcard/MIUI/fail_list.txt");
		newFile1.createNewFile();
		FileOutputStream fo=new FileOutputStream(newFile1);
		for(int i = 0 ; i < 3 ; i++){
			Process p1 = Runtime.getRuntime().exec("am start -a android.intent.action.CALL -d tel:10086");
			sleep((long) 10000.0);
			String str;
			Process p2 = Runtime.getRuntime().exec("dumpsys telephony.registry |busybox grep mCallState= ");
		    DataInputStream p4 = new DataInputStream(p2.getInputStream());
		    str = p4.readLine();

			int result = p4.readLine().indexOf("  mCallState=2");
			if(result>=0){
				getUiDevice().click(360,1191);
				sleep((long) 5000.0);
				}
			else{
				String str1;
				newFile1.createNewFile();
				sleep((long) 3000.0);
				
				String fail = "call is fail " + i;
				fo.write(("fail" + "  \n" ).getBytes());
				sleep((long) 5000.0);
				getUiDevice().click(360,1191);
				sleep((long) 5000.0);
				File newFile=new File("/sdcard/MIUI/" + i + ".txt");
				newFile.createNewFile();
				sleep((long) 3000.0);
				FileOutputStream fos=new FileOutputStream(newFile);
				Process p3 = Runtime.getRuntime().exec("logcat -t 600");
				DataInputStream p5 = new DataInputStream(p3.getInputStream());
				while ((str1 = p5.readLine()) != null){
					fos.write((str1+"  \n").getBytes());
				}
				}
		    	
			}
		}

	public static void main(String args[]){ 
		junit.textui.TestRunner.run(Calltest.class);
}
   
	
}