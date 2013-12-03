package Xuejh;
public class listTest {
	

	
	public static void main(String[]args) {
		String[] args1 = {"http://sina.cn","http://m.baidu.com/news","http://m.taobao.com","http://m.yihaodian.com",
				"http://3g.ganji.com","http://m.elong.com","http://m.qidian.com","http://duokoo.baidu.com/news"};
		if (args1.length > 0) {
			for (int i = 0; i < args1.length; i++) {
				System.out.println(args1[i]);
			}
		}
	}

}