package xjh;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;


public class timenow
{
	public static void main(String [] args)
	{
		SimpleDateFormat date1 = new SimpleDateFormat("ss");
		int loop = Integer.parseInt(date1.format(new Date()), 10);
		int a = 60 - loop;
		Timer timer = new Timer();
		timer.schedule(new TimerTask(), a*1000, 60000);
		/*while(true){
			try{
				int ch = System.in.read();
				if(ch - 'c' == 0){
					timer.cancel();
				}
			}catch(IOException e){
				
			}*/
			
		}
		
		
		
}
	
	class TimerTask extends java.util.TimerTask 
	{
		public void run()
		{

			
			System.out.println("jajajajaj");
			
		}

	}

