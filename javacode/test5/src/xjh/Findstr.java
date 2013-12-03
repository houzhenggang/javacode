package xjh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Findstr{
	     /**
	     * fileName 要查找的文本，str要查找的字符串
	     **/
	public static void main(String arg[]) {
	    	int count = 0;
	        try {
	        	int line = 0;//记录行数
				BufferedReader br = new BufferedReader(new FileReader("F:\\ui\\1.txt"));
				String tmp = br.readLine();
				while(br.readLine()!=null){
					line ++;
					int index = tmp.indexOf("errorCode", 0);
					while(index>=0){
						count ++;
						System.out.println("在"+line+"行，第"+index+"列，找到字符："+"errorCode");
						index = tmp.indexOf("errorCode", index+"errorCode".length());
					}
					tmp = br.readLine();
				}
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

	}	     
}