package xjh;

public class equalsTest
{
	public static final String xjh = "xuejinghao";
	private static String xuejh  = "xuejinghao";
	
	public static String getString(String xjh){
		String xjh111 = xjh +"@@@@******";
		return xjh111;
	}
	public static void main(String[] args){
		if(xjh.equals("xuejinghao")){
			System.out.println("Ok");
			System.out.println(getString("xuexuexue"));
			
		}else{
			System.out.println("Fail");
		}
	}

}
