package xjh;

interface Runner //����ӿ�
 {
         int i=3;
         public void start();
         void run();
         void stop();
 }
 interface Eater extends Runner //�ӿڼ���Լ̳�
 {
         public final static int j=4;
         void openMouth();
         void upAndDown();
         void goIn();
 }
 class TT implements Eater //���ýӿ�
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
                 Runner tt=new TT();//�ӿڵ�����ָ��ʵ�ֵĶ���
                 System.out.println(tt.i);
                 System.out.println(Runner.i);
                 tt.start();
                 Eater ee=new TT();
                 System.out.println(ee.j);
                 System.out.println(Eater.j);
                 ee.goIn();
         }
 }