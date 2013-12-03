package xue;

class base01
{
	public int a;
	public void test1()
	{
		a = 100;
		System.out.println("a = "+a);
	}
   
}

public class zhao extends base01
{	
	public int a ;
	public void test2()
	{
		super.test1();
		a = 200;	
		System.out.println ("Hello World!"+a);
	}

	public static void main(String[] args)
	{
		zhao c = new zhao();
		c.test2();
	}

}
	