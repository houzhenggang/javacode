package xjh;

interface Runner //定义接口
 {
         int i=3;
         public void start();
         void run();
         void stop();
 }
 interface Eater extends Runner //接口间可以继承
 {
         public final static int j=4;
         void openMouth();
         void upAndDown();
         void goIn();
 }
 class TT implements Eater //引用接口
 {
         public void start()
         {
                 System.out.println("---------start()-------");
         }
         public void run()
         {
                 System.out.println("---------run()-------");
         }
         public void stop()
         {
                 System.out.println("---------stop()-------");
         }
         public void openMouth()
         {
                 System.out.println("---------openMouth()-------");
         }
         public void upAndDown()
         {
                 System.out.println("---------upAndDown()-------");
         }
         public void goIn()
         {
                 System.out.println("---------goIn()-------");
         }
 }
 public class Interfce
 {
         @SuppressWarnings("static-access")
		public static void main(String[] args)
         {
                 Runner tt=new TT();//接口的引用指向实现的对象
                 System.out.println(tt.i);
                 System.out.println(Runner.i);
                 tt.start();
                 Eater ee=new TT();
                 System.out.println(ee.j);
                 System.out.println(Eater.j);
                 ee.goIn();
         }
 }