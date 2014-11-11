package com.young.frame;

import java.io.InputStream;
import java.io.OutputStream;

import com.wayland.internet.SocketInterface;
import com.wayland.internet.SocketInterfaceImp;
import com.wayland.internet.SocketRuleException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class LoginActivity extends Activity{
	
	private static final String TAG = "LOGINACTIVITY" ;
	private SocketInterface mSocketInterface ;
	
	
//	private static final String HOST ="182.92.150.143" ;
//	private static final int PORT = 8480  ;
	private static final String HOST ="10.0.2.2" ;
	private static final int PORT = 8888  ;
	private static final String verification = "G0001@123123@1@APP1m" ;
	private OutputStream mOutput ;
	private InputStream mInput ;
	private static final String[] switch4on = {"bt230000000310m" ,"bt240000000400m", "bt22111m","bt22111m"} ;
	private static final String[] switch4off = {"bt230000000300m" ,"bt240000000400m", "bt22101m", "bt22101m"} ;
	private static String messageContent="" ;
	
	public static OutputStream socketOutPutStream ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		mSocketInterface = new SocketInterfaceImp() ;
		
		/**
		 * 启动一个副线程
		 * 
		 */
		Thread socketThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mSocketInterface.ConnectSocket(HOST, PORT);
//				mSocketInterface.ExecuteVerify(verification);
//				try {
//					mSocketInterface.IsPassVerify() ;
//				} catch (SocketRuleException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				socketOutPutStream = mSocketInterface.getSocketOutPutStream() ;
				
			}
		}) ;
		
		socketThread.start();
		
//		ImageButton loginButton = (ImageButton)findViewById(R.id.loginButton);
		Button loginButton = (Button)findViewById(R.id.loginButton);
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

	
	public OutputStream getSocketOutPutStream(){
		
		return socketOutPutStream;
		
	}
	
}
