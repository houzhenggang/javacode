package xjh;

import java.util.Date;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
public class time 
{
	public static void main(String[] args) throws IOException 
	{
	//获取系统当前时间
	SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HHmmss");
	File newFile1=new File("E:\\testjava\\"+df.format(new Date())+".txt");
	newFile1.createNewFile();
	FileOutputStream fo=new FileOutputStream(newFile1);
	for(int i = 0 ; i < 1000 ; i++)
	{
		int j = 2;
		if(i%j == 0){
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
			String fail= "call is fail " + i+ "\n";
			fo.write((df1.format(new Date())+"  \n"+fail+"  \n").getBytes());		
		}
		else
		{
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
			System.out.println("xuejh"+ df1.format(new Date()));
		}
	}
	}
}