package com.young.frame;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

import com.wayland.global.Command;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AirControlActivity extends Activity implements OnClickListener {
	
	private final static String TAG = "AIRCONTROLACTIVITY" ;
	private ImageButton btn_Power ;
	private ImageButton btn_temp_up ;
	private ImageButton btn_temp_down ;
	private ImageButton air_btn_mode_cool ;
	private ImageButton air_btn_mode_heat ;
	private ImageView vi_curren_temp ;
	private Vibrator mVibrator ;
	private int vibratorTime = 200 ;
	private OutputStream mOutPut ;
	private int[] image_temp= new int[]{R.drawable.img_temp_16 ,R.drawable.img_temp_17 ,R.drawable.img_temp_18 ,R.drawable.img_temp_19 ,R.drawable.img_temp_20 ,R.drawable.img_temp_21 ,
			R.drawable.img_temp_22 ,R.drawable.img_temp_23 ,R.drawable.img_temp_24 ,R.drawable.img_temp_25 ,R.drawable.img_temp_26 ,R.drawable.img_temp_27 ,R.drawable.img_temp_28 ,
			R.drawable.img_temp_29 ,R.drawable.img_temp_30 ,R.drawable.img_temp_31 } ;
	
	int currentid = 0 ;
	Boolean mIsOpenFlag = false ;
	
//	//控制指令
//	private static final String Air_Power_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Air+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//	private static final String Air_Power_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Air+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//	//温度控制
//	private static final String Air_Temp_Up = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Air+Command.Dev_ID_SIZE+Command.Cmd_Type_TEMP_I+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//	private static final String Air_Temp_Down = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Air+Command.Dev_ID_SIZE+Command.Cmd_Type_TEMP_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//	//空调模式
//	private static final String Air_Mode__Cool = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Air+Command.Dev_ID_SIZE+Command.Cmd_Type_Air_Cool+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//	private static final String Air_Mode__Heat = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Air+Command.Dev_ID_SIZE+Command.Cmd_Type_Air_HEAT+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//	//风速
//	private static final String Air_Type_Windspeed = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Air+Command.Dev_ID_SIZE+Command.Cmd_Type_Windspeed+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//	//摆风
//	private static final String Air_Type_Windshifty = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Air+Command.Dev_ID_SIZE+Command.Cmd_Type_Windshifty+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//	//定时
//	private static final String Air_Type_SetTime = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Air+Command.Dev_ID_SIZE+Command.Cmd_Type_Time+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	
	
	
	private void findView() {
		// TODO Auto-generated method stub
		btn_Power = (ImageButton)findViewById(R.id.btn_airpower);
		btn_Power.setOnClickListener(this);
		btn_temp_up = (ImageButton)findViewById(R.id.btn_temp_up);
		btn_temp_up.setOnClickListener(this);
		btn_temp_down = (ImageButton)findViewById(R.id.btn_temp_down);
		btn_temp_down.setOnClickListener(this);
		air_btn_mode_cool = (ImageButton)findViewById(R.id.air_mode_cool) ;
		air_btn_mode_cool.setOnClickListener(this);
		air_btn_mode_heat = (ImageButton)findViewById(R.id.air_mode_heat) ;
		air_btn_mode_heat.setOnClickListener(this);
		
		
		vi_curren_temp = (ImageView)findViewById(R.id.vi_current_temp) ;
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_air_control);
		findView();
		mVibrator = (Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE) ;
		
		LoginActivity mLoginActivity = new LoginActivity() ;
		WeakReference reference = new WeakReference(mLoginActivity) ;
		mOutPut = ((LoginActivity)reference.get()).getSocketOutPutStream() ;
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.btn_airpower :
			mVibrator.vibrate(vibratorTime);
			if(!mIsOpenFlag){
				try {
					mOutPut.write(Command.Air_Command_Power_Open.getBytes());
					Log.i(TAG, "发送空调控制指令成功") ;
					mIsOpenFlag = true ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.i(TAG, "发送空调控制指令失败") ;
					e.printStackTrace();
				}
				
			}else{
				try {
					mOutPut.write(Command.Air_Command_Power_Close.getBytes());
					Log.i(TAG, "发送空调控制指令成功") ;
					mIsOpenFlag = false ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.i(TAG, "发送空调控制指令失败") ;
					e.printStackTrace();
				}
			}
			
			
			
//			startActivity(new Intent(this , TVActivity.class));
			
			
			
			break ;
		case R.id.btn_temp_up :
			mVibrator.vibrate(vibratorTime);
//			int currentid = vi_curren_temp.getId();
//			for(int i = 0 ; i<image_temp.length ;i++){
//				if(currentid == image_temp[i]){
//					vi_curren_temp.setImageResource(image_temp[++i]);
//					Log.i("wyongch", "wwwwwwwwww");
//				}
//			}
			
//			int currentid = 0 +currentid ;
//			if(currentid == 0){
//				vi_curren_temp.setImageResource(image_temp[0]);
//			}
			//边界值问题
			
			try {
				mOutPut.write(Command.Air_Command_Temp_Increase.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(currentid == image_temp.length-1){
				vi_curren_temp.setImageResource(image_temp[0]);
				currentid = 0 ;
				Toast.makeText(this, "length"+image_temp.length +"currentid"+currentid , Toast.LENGTH_LONG).show();
			}
			
			else if(currentid <image_temp.length ){
				
			vi_curren_temp.setImageResource(image_temp[++currentid]);
			Toast.makeText(this, "length"+image_temp.length +"currentid"+currentid , Toast.LENGTH_LONG).show();
			}
//			else if(currentid == image_temp.length) {
//				vi_curren_temp.setImageResource(image_temp[0]);
//			}
			
			break ;
		case R.id.btn_temp_down :
			mVibrator.vibrate(vibratorTime);
			
			try {
				mOutPut.write(Command.Air_Command_Temp_Reduce.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (currentid == 0){
				vi_curren_temp.setImageResource(image_temp[currentid]);
				currentid = currentid+1 ;
			}
			else if(currentid>0){
				if(currentid <image_temp.length ){
					
					vi_curren_temp.setImageResource(image_temp[--currentid]);
					
					}
			}
			
			Toast.makeText(this, "length"+image_temp.length +"currentid"+currentid , Toast.LENGTH_LONG).show();
			break ;
			
		case R.id.air_mode_cool:
			mVibrator.vibrate(vibratorTime);
			try {
				mOutPut.write(Command.Air_Command_Mode_Cool.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break ;
	
		case R.id.air_mode_heat:
			mVibrator.vibrate(vibratorTime);
			try {
				mOutPut.write(Command.Air_Command_Mode_Heat.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break ;
			
		}
		
		

	}
		
	}


