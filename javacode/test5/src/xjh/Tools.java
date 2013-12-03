package xjh;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Tools {
	private Button wdVersion = new Button("稳定版");
	private Button kfVersion = new Button("开发版");
	private Button connectPhoneButton  = new Button("请连接手机");
	private JTextArea informationArea =new JTextArea("注意：点击对应版本，会根据机型刷成目前最新的开发版或稳定版。"+"\n");
	private  String Command;
	private String CmdDetailInformation;

	
	public static void main (String args[]){
		Tools tools= new Tools();
		tools.init();
	}
	
	public void init(){
		wdVersion.addActionListener(new WdButtonListenner());
		kfVersion.addActionListener(new KfButtonListenner());
		JFrame jFrame = new JFrame("测试组内部刷机助手");
		jFrame.setBounds(500, 500, 500, 500);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel phoneStatusPanel = new JPanel();
		informationArea.add(connectPhoneButton);
		informationArea.setEditable(true);
		informationArea.setSize(500, 420);
		jFrame.add(informationArea, BorderLayout.CENTER);

		JPanel jLeftButtonJPanel = new JPanel();
		jLeftButtonJPanel.add(wdVersion);
		jLeftButtonJPanel.add(kfVersion);
		jFrame.add(jLeftButtonJPanel,BorderLayout.SOUTH);
		jFrame.setVisible(true);
	}
	public  String getCommand(){
		return Command;
	}
	public void setCommand(String str){
		Command = str;
	}
	public String getCmdDetailInformation(){	
		return this.CmdDetailInformation;
	}
	
	public void setCmdDetailInformation(String str){
		this.CmdDetailInformation = str;
	}

 class RunCmdCommand implements Runnable{	
	 public  String  CmdCommand(String commands){
		String str1;
		try {
			Runtime rt= Runtime.getRuntime();
			Process pro= rt.exec(commands);
			BufferedReader br =new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((str1=br.readLine() )!=null) {
				setCmdDetailInformation(str1); 
				System.out.print(getCmdDetailInformation());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("errors .....");
		}
		System.out.print(getCmdDetailInformation());
		return getCmdDetailInformation();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
}	
	
	class WdButtonListenner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			RunCmdCommand rcc =new RunCmdCommand();
			informationArea.append(rcc.CmdCommand("adb devices")+"1235");
		}
		
	}
	class KfButtonListenner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub	
		}	
	} 
	
}
