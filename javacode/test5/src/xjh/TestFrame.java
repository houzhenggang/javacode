package xjh;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.jar.JarFile;

import javax.swing.JFileChooser;
public class TestFrame
{
	public static void main(String args[]){
		Frame f = new Frame("My test");
		f.setSize(170,100);
		f.setLocation(500, 200);
		f.setBackground(Color.blue);
		f.setVisible(true);
		Button button = new Button("Push jar file");
		Monitor bb = new Monitor();
		button.addActionListener(bb);
		Button button1 = new Button("Start test");
		Monitor1 bb1 = new Monitor1();
		button1.addActionListener(bb1);
		f.add(button, "North");
		f.add(button1, "Center");
		f.pack();
		f.setVisible(true);
		
		
	}

}

class Monitor implements ActionListener{
	public void actionPerformed(ActionEvent e){
		JFileChooser fDialog = new JFileChooser();
		//设置文件选择框的标题 
		fDialog.setDialogTitle("请选择音频文件");
		//弹出选择框
		int returnVal = fDialog.showOpenDialog(null);
		// 如果是选择了文件
		if(JFileChooser.APPROVE_OPTION == returnVal){
		       //打印出文件的路径，你可以修改位 把路径值 写到 textField 中
			 String command = "cmd /k adb push "+ fDialog.getSelectedFile() +" /data/local/tmp/ ";
			try {
				Runtime.getRuntime().exec(command);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("adb push "+ fDialog.getSelectedFile() +" /data/local/tmp/ ");
		}
	}
}

class Monitor1 implements ActionListener{
	public void actionPerformed(ActionEvent e){
		JFileChooser fDialog = new JFileChooser();
		try {
			JarFile jarFile = new JarFile(fDialog.getSelectedFile());
			jarFile.getClass().getResource("test");
			System.out.println(jarFile.getClass().getResource("test"));
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	}
}
