package xjh;

import junit.framework.Assert;
import junit.framework.TestCase;

public class xjhtesta extends TestCase
{
	public boolean main(String[]args){
		int a = 1+2;
		Assert.assertEquals(a, 3);
		return true;
	}
}
