package xjh;


import java.awt.geom.Area;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class jarTest {
	// javaClass表示类文件；filepath表示文件的相对路径
	public static void readJar_URL(Class javaClass, String filepath) {
		URL url = javaClass.getResource(filepath);// 获取定位资源URL对象
		System.out.println("资源的URL: " + url);
		try {
			InputStream stream = url.openStream();// 打开fileURL对应的文件流
			BufferedReader bufferedreader = new BufferedReader(// 创建一个缓冲字符输入流
					new InputStreamReader(stream));
			String str;
			while ((str = bufferedreader.readLine()) != null) {// 从缓冲字符输入流中按行读取信息
				System.out.println(str);
			}
			stream.close();// 关闭流
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
	}
	// 读Jar包中的资源信息，其中：javaClass表示类文件；filepath表示文件的相对路径
	public static void readJar_stream(Class javaClass, String filepath) {
		InputStream stream = javaClass.getResourceAsStream(filepath);// 获取该资源输入流的引用
		if (stream != null) {
			// 创建一个缓冲字符输入流
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(stream));
			String str;
			try {
				// 从缓冲字符输入流中按行读取信息
				while ((str = bufferedreader.readLine()) != null) {
					System.out.println(str);
				}
				stream.close();
			} catch (IOException ioexception) {
				ioexception.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		System.out.println("以该class文件的位置为准，以URL的方式读取Jar包信息的内容如下：");
		/*Class javaClass = CallTest.class;//在该文件的目录下要存在aa.class文件
		String filePath = "C:\\Users\\xjh\\Desktop\\code\\call\\ModemTest.jar";//filePath的值是要读取的jar包名称
		jarTest.readJar_URL(javaClass, filePath);
		System.out.println();
		System.out.println("以该class文件的位置为准，以直接获取资源输入流的引用的方式读取Jar包信息的内容如下：");
		filePath = "bdc/config1.conf";//filePath的值是要读取的jar包名称
		jarTest.readJar_stream(javaClass, filePath);
		System.out.println();
		System.out.println("以该jar文件的位置为准，以URL的方式读取Jar包信息的内容如下：：");
		filePath = "/jarTest/abc/bdc/config1.conf";//filePath的值是要读取的jar包名称
		jarTest.readJar_URL(javaClass, filePath);*/
	}
}
