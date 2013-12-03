package adsfaf.dfafasfd;
public class listStudy {  
private static String xjh;
public static void main(String[] args) {
	int temp;
	temp = add(1, 2, 3, 4, 5);
	String xu12ejh = add("http://sina.cn","http://m.baidu.com/news","http://m.taobao.com","http://m.yihaodian.com",
			"http://3g.ganji.com","http://m.elong.com","http://m.qidian.com","http://duokoo.baidu.com/news");
	String[] xuejh =  new String[add("http://sina.cn","http://m.baidu.com/news","http://m.taobao.com","http://m.yihaodian.com",
			"http://3g.ganji.com","http://m.elong.com","http://m.qidian.com","http://duokoo.baidu.com/news").length()];
	System.out.println(xuejh);
    }  

    public static int add( int ... args ){  

        int sum = 0;  

        for(int i=0; i<args.length; i++){  

            sum +=args[i];  

        }  

        return sum;  

    }  
    
	public static String add (String ... strings ){
		String getString = null;
		for(int i = 0; i <strings.length; i++){
			getString = strings[i];
			System.out.println(getString);
		}
		return getString;
	}

}
