package xjh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Findstr{
	     /**
	     * fileName Ҫ���ҵ��ı���strҪ���ҵ��ַ���
	     **/
	public static void main(String arg[]) {
	    	int count = 0;
	        try {
	        	int line = 0;//��¼����
				BufferedReader br = new BufferedReader(new FileReader("F:\\ui\\1.txt"));
				String tmp = br.readLine();
				while(br.readLine()!=null){
					line ++;
					int index = tmp.indexOf("errorCode", 0);
					while(index>=0){
						count ++;
						System.out.println("��"+line+"�У���"+index+"�У��ҵ��ַ���"+"errorCode");
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