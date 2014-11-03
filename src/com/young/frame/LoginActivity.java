package com.young.frame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class LoginActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		ImageButton loginButton = (ImageButton)findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
