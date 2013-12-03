package Xue;
public class test2 {
	
		static void print(String s, int i) {
			System.out.println(
			"String: " + s +
			", int: " + i);
			}
			static void print(int i, String s) {
			System.out.println(
			"int: " + i +
			", String: " + s);
			}
			public static void main(String[] args) {
			print("String first", 01);
			print(99, "Int first");
		
		/*for(int i = 1; i < 101; i++)
		{
			if(1 == 100) break;
			System.out.println(i);
		}*/
	}
	
	/*static boolean test1(int val) {
		System.out.println("test1(" + val + ")");
		System.out.println("result: " + (val < 1));
		return val < 1;
		}
		static boolean test2(int val) {
		System.out.println("test2(" + val + ")");
		System.out.println("result: " + (val < 2));
		return val < 2;
		}
		static boolean test3(int val) {
		System.out.println("test3(" + val + ")");
		System.out.println("result: " + (val < 3));
		return val < 3;
		}
		public static void main(String[] args) {
		if(test1(0) && test2(2) && test3(2))
		System.out.println("expression is true");
		else
		System.out.println("expression is false");
		}*/
	/*public static void main(String[] args) {
		Number n1 = new Number();
		Number n2 = new Number();
		n1.i = 47;
		n2.i = 47;
		//1: n1.i: 9 , n2.i: 47
		System.out.println("1: n1.i: " + n1.i +
		", n2.i: " + n2.i);
		n1 = n2;
		//2: n1.i: 47 , n2.i: 47
		System.out.println("2: n1.i: " + n1.i +
		", n2.i: " + n2.i);
		n1.i = 27;
		//3: n1.i: 27 , n2.i: 47
		System.out.println("3: n1.i: " + n1.i +
		", n2.i: " + n2.i);

		System.out.println(n1 == n2);
		System.out.println(n1 != n2);
		}*/

}
