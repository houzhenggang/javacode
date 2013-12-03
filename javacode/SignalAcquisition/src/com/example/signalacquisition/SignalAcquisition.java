package com.example.signalacquisition;

import java.io.File;
import java.io.FileWriter;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignalAcquisition extends Activity
{
	private Button myButton = null;
	private Button myButton1 = null;
	private EditText factorOne ;
	private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signal_acquisition);
        text = (TextView)findViewById(R.id.text);
        text.setTextColor(R.drawable.black);
        text.setText("必须设置获取信号的间隔时间（单位：分钟）");
        text.setTextSize(17);
        factorOne = (EditText)findViewById(R.id.factorOne);
        myButton = (Button)findViewById(R.id.myButton);
        myButton.setText("获取GSM信号");
        myButton.setOnClickListener(new MyButtonListener());
        myButton1 = (Button)findViewById(R.id.myButton1);
        myButton1.setText("获取CDMA信号");
        myButton1.setOnClickListener(new MyButtonListener1());
    }
    class MyButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			String factorOneStr = factorOne.getText().toString();
			Intent intent = new Intent();
			intent.putExtra("one",factorOneStr);
			intent.setClass(SignalAcquisition.this, Signal.class);
			SignalAcquisition.this.startActivity(intent);
			
		}
    	
    }
    class MyButtonListener1 implements OnClickListener{
		@Override
		public void onClick(View v) {
			String factorOneStr = factorOne.getText().toString();
			Intent intent = new Intent();
			intent.putExtra("one",factorOneStr);
			intent.setClass(SignalAcquisition.this, Signal11.class);
			SignalAcquisition.this.startActivity(intent);
			
		}
    	
    }
    
    }



