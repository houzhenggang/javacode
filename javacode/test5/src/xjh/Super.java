package xjh;

class Super2
{
	public int xjh;
	public void f()
	{
		xjh = 10;
		System.out.println(xjh);
	}
	
}
class Super1 extends Super2
{
	public int xjh;
	public void f()
	{
		super.f();
		xjh = 101;
		System.out.println(xjh);
	}
}
public class Super
{
	public static void main(String[] args )
	{
		Super1 s = new Super1();
		s.f();
	}
}