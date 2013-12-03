package com.xiaomi.bsptestview;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
		.create();
		alert.setTitle("BspTest"); // 设置对话框的标题
		alert.setMessage("北京小米"); // 设置要显示的内容
		alert.show();*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
