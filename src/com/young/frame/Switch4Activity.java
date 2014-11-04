package com.young.frame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class Switch4Activity extends Activity implements  OnCheckedChangeListener {
	
	private static final String TAG = "Switch4Activity" ;
	private ToggleButton switch4_one , switch4_two , switch4_three , switch4_four ;
	private Socket mSocket ;
	private static final String HOST ="182.92.150.143" ;
	private static final int PORT = 8480  ;
	private static final String verification = "G0001@123123@1@APP1m" ;
	private OutputStream mOutput ;
	private InputStream mInput ;
	private static final String[] switch4on = {"bt230000000310m" ,"bt240000000400m", "bt22111m","bt22111m"} ;
	private static final String[] switch4off = {"bt230000000300m" ,"bt240000000400m", "bt22101m", "bt22101m"} ;
	private static String messageContent="" ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switch4_activity);
		
		//�Զ���findView
		findView() ;
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mSocket = new Socket (HOST , PORT) ;
					Log.i(TAG, "socket���ӳɹ���") ;
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "socket����ʧ�ܣ�") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "socket����ʧ�ܣ�") ;
				}
				
				
				try {
					mOutput = mSocket.getOutputStream() ;
					Log.i(TAG, "��ȡ������ɹ�") ;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					Log.i(TAG, "��ȡ�����ʧ��") ;
				}
				
				
				try {
					mOutput.write(verification.getBytes());
					Log.i(TAG, "outputstream��֤��Ϣ�ɹ�") ;
					//�ж��Ƿ�ͨ����֤
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "outputstream��֤��Ϣʧ��") ;
				}
				
				try {
					mInput = mSocket.getInputStream() ;
					Log.i(TAG, "��ȡ��֤��Ϣ����ֵ��" + mInput.toString()) ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "��ȡ��֤��Ϣ����ֵʧ��") ;
				}
				
			}
		}).start() ;
		
		
	}

	private void findView() {
		// TODO Auto-generated method stub
		
		switch4_one = (ToggleButton)findViewById(R.id.switch4_one) ;		
		switch4_two = (ToggleButton)findViewById(R.id.switch4_two) ;
		switch4_three = (ToggleButton)findViewById(R.id.switch4_three) ;
		switch4_four = (ToggleButton)findViewById(R.id.switch4_four) ;
		
		switch4_one.setOnCheckedChangeListener(this);
		switch4_two.setOnCheckedChangeListener(this);
		switch4_three.setOnCheckedChangeListener(this);
		switch4_four.setOnCheckedChangeListener(this);
		
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
		switch (buttonView.getId()) {
		case R.id.switch4_one:
			
			if(switch4_one.isChecked()){
				messageContent = switch4on[0] ;
				try {
					mOutput.write(messageContent.getBytes()) ;
					Log.i(TAG, "���ô򿪿��ؿ�������ɹ�") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "���ô򿪿��ؿ�������ʧ��") ;
				}
				
			}else {
				messageContent = switch4off[0] ;
				try {
					mOutput.write(messageContent.getBytes()) ;
					Log.i(TAG, "���ùرտ��ؿ�������ɹ�") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "���ùرտ��ؿ�������ʧ��") ;
				}
			}
			
			
			
			break;
		case R.id.switch4_two:
			
			if(switch4_two.isChecked()){
				messageContent = switch4on[1] ;
				try {
					mOutput.write(messageContent.getBytes()) ;
					Log.i(TAG, "���ô򿪿��ؿ�������ɹ�") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "���ô򿪿��ؿ�������ʧ��") ;
				}
				
			}else {
				messageContent = switch4off[1] ;
				try {
					mOutput.write(messageContent.getBytes()) ;
					Log.i(TAG, "���ùرտ��ؿ�������ɹ�") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "���ùرտ��ؿ�������ʧ��") ;
				}
			}
			
			
			
			break ;
		case R.id.switch4_three:
			
			break ;
		case R.id.switch4_four :
			
			break ;

		default:
			break;
		}
		
	}

}
