package xue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class txtput
{
	public static void main(String[] agrs) throws IOException{
		File newFile1=new File("E:\\testjava\\xuejh.txt");
		newFile1.createNewFile();
		FileOutputStream fo=new FileOutputStream(newFile1);
		for(int i = 0 ; i < 10 ; i++)
		{
		System.out.println("fail"+i);
		String xjh = "fail"+i;
		fo.write((xjh +"  \n" ).getBytes());
		}	
		fo.close(); 
	}

}
