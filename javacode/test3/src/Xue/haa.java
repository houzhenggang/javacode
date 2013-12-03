package Xue;

import java.io.UnsupportedEncodingException;


public class haa {
public static void main(String args[]) throws UnsupportedEncodingException{
	
	String s="学几个号";
	 byte[] b=s.getBytes("UTF-8");
	 s =new String(b,"UTF-8");
	System.out.print(s);
	
			
		}
}