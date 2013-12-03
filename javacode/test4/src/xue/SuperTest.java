package xue;

class Base {
	public int a ;
    public void Base() {
    	a = 100;
    	System.out.println("Base"+a);
    }
}

public class SuperTest extends Base {
	public int a ;
    public void SuperTest() {
       super.Base();//调用父类的构造方法，一定要放在方法的首个语句
       a = 12;
       System.out.println("di erci "+a);

    }
    public static void main(String argv[]) {
       SuperTest c = new SuperTest();
       c.SuperTest();

    }

}