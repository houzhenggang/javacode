package xjh;  
 
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.InputStreamReader;  
 

public class txt {  
    public static void main(String arg[]) {  
        try {  
            String encoding = "GBK"; // �ַ�����(�ɽ�������������� )  
            File file = new File("F:\\ui\\3.txt");  
            if (file.isFile() && file.exists()) {  
                InputStreamReader read = new InputStreamReader(  
                        new FileInputStream(file), encoding);  
                BufferedReader bufferedReader = new BufferedReader(read);  
                String lineTXT;  
                if ((lineTXT = bufferedReader.readLine()) != null) {  
                     String xju = lineTXT.toString().trim();
                     System.out.println(xju);
                     int result=xju.indexOf("errorCode");
                     System.out.println(result);
                }  
                read.close();
                }else{  
              System.out.println("�Ҳ���ָ�����ļ���");              }  
        } catch (Exception e) {  
            System.out.println("��ȡ�ļ����ݲ�������");  
            e.printStackTrace();  
        }  
    }  
}  