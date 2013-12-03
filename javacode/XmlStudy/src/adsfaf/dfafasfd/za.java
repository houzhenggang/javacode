package adsfaf.dfafasfd;

import junit.framework.TestCase;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/*
 * 读取指定jar包里的指定文件
 */
public class za {      
    
    /**    
     * @param args    
     */     
    public static void main(String[] args) throws Exception {      
        String jarName="G:\\javadai\\UIautomatorStudy\\bin\\UIautomatorStudy.jar";//Jar包所在的位置   
        String fileName="9.xml";//文件在jar包里的路径      
        JarFile jarFile = new JarFile(jarName);//读入jar文件    
        JarEntry entry = jarFile.getJarEntry(fileName);       
        InputStream input = jarFile.getInputStream(entry);//读入需要的文件   
        readFile(input);      
        jarFile.close();      
    }      
     
    private static void readFile(InputStream input) throws Exception {   
        InputStreamReader isr = new InputStreamReader(input);
        BufferedReader reader = new BufferedReader(isr);      
        String line;      
        while ((line = reader.readLine()) != null) {      
            System.out.println(line);      
        }      
        reader.close();      
    }     
}  
