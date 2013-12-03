package xjh;

public class sdfafasfdasf
{
	public static void main(String[] args) {
	    try {
	        String cmdStr = "cmd /c copy d:\\book\\21.txt e:\\" ;
	        Runtime.getRuntime().exec(cmdStr);
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	}

}
