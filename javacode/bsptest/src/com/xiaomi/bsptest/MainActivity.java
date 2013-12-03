package com.xiaomi.bsptest;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{

	private Button myButton = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = (Button)findViewById(R.id.myButton);
    }
    class MyButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Other.class);
			MainActivity.this.startActivity(intent);

		}
    	
    }
}
