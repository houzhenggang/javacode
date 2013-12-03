package xjh;

public class Za
{
	public static void main(String[] args)
	{
		int a, b, c;
		for(int i = 101; i< 1000; i++)
		{
			c = i/100;
			b = i%100/10;
			a = i%10;
			if((a*a*a+ b*b*b +c*c*c) ==i)
			{
				System.out.println(i);
			}
		}
	}

}
