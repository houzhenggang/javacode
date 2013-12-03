package xjh;

public class haha
{	
	private static int a = 4;
	public static void testxjh()
	{
		for(int i = 1; i<100;i++){
			System.out.println(i);
		}
	}
	public static void main(String[] agrs){
		/*int b = 6;
		if(b >5)
		{
			int a =10;
			a = a;
			System.out.println("输出"+a);
		}else
		{
			System.out.println("输出"+a);
		}
		System.out.println("输出"+a+b);
		int b = 15;
		a = a + b;
		b = a - b;
		a = a -b;
		System.out.println(a+" "+ b);*/
		testxjh();
		String str="你好";
	     char[] ch=str.toCharArray();
	     String ss=null;
	     for (int i = 0; i < ch.length; i++) {
	       ss="\\u"+Integer.toHexString(ch[i]);
	      System.out.println(ss);
	  }
	}
}
