package xue;


public class Excel {
	   public static void main(String[] args) {
	        int num=1,row=4,column=5;
	        for (int i = 1; i <= row; i++) {
	            if (i%2==0) {
	                num = printEvenLine(num,column);
	            }else{
	                num = printOddLine(num,column);
	            }
	                
	        }
	    }

	    private static int printOddLine(int num, int column) {
	        for (int i = 0; i < column; i++) {
	            if (num<10) {
	                System.out.print("0");
	            }
	            System.out.print(num+" ");
	            num ++;
	        }
	        System.out.println();
	        return num;
	    }

	    private static int printEvenLine(int num, int column) {
	        int _num=num;
	        for (int i = 1; i <= column; i++) {
	            if (_num+column-i<10) {
	                System.out.print("0");
	            }
	            System.out.print(_num+column-i+" ");
	            num ++;
	        }
	        System.out.println();
	        return num;
	    }
}