package xjh;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class qwer
{
	public static void main(String[] args) 
	{
		String regex = "errorCode=0";
		String input = "asdfasdfasfasfasdfasfasdfasdfasdfafaerrorde=0";
		int result=input.indexOf("errorCode");
		/*Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		m.find();
		System.out.println("---" + m.group());*/
		System.out.println(result);

	}


}
