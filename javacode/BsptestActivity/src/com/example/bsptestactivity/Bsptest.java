package com.example.bsptestactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bsptest extends Activity {

	private Button myButton = null;
	private EditText factorOne ;
	private EditText factortwo ;
	private TextView text;
	private TextView text1;
	PowerManager powerManager = null;    
    WakeLock wakeLock = null;  

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        text = (TextView)findViewById(R.id.text);
        text.setTextColor(R.drawable.black);
        text.setText("时间必须大于1(>1S)");
        text.setTextSize(17);
        text1 = (TextView)findViewById(R.id.text1);
        text1.setTextColor(R.drawable.black);
        text1.setText("设置从第几张播放");
        text.setTextSize(17);
        factorOne = (EditText)findViewById(R.id.factorOne);
        factortwo = (EditText)findViewById(R.id.factortwo);
        myButton = (Button)findViewById(R.id.myButton);
        myButton.setText("开始运行-->于磊加油");
        myButton.setOnClickListener(new MyButtonListener());
    }
    class MyButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			String factorOneStr = factorOne.getText().toString();
			Intent intent = new Intent();
			intent.putExtra("one",factorOneStr);
			String factortwoStr = factortwo.getText().toString();
			intent.putExtra("two",factortwoStr);


			intent.setClass(Bsptest.this, EX07_03.class);
			Bsptest.this.startActivity(intent);
			
		}
    	
    }
    
   
}