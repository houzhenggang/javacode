package xjh;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class huai
{
	public static void main(String [] args)
	{
		String Signal1 ="31211212121211222";
		File resultReport=new File("D:\\changyong\\Signal.txt");
		try {
			resultReport.createNewFile();
			FileOutputStream inputResult=new FileOutputStream(resultReport,true);
			inputResult.write((Signal1+ "  \n").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}


}
