package xjh;  
 
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.InputStreamReader;  
 

public class txt {  
    public static void main(String arg[]) {  
        try {  
            String encoding = "GBK"; // 字符编码(可解决中文乱码问题 )  
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
              System.out.println("找不到指定的文件！");              }  
        } catch (Exception e) {  
            System.out.println("读取文件内容操作出错");  
            e.printStackTrace();  
        }  
    }  
}  