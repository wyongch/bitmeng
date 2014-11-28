package com.young.frame;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

import com.wayland.global.Command;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class AirControlActivity extends Activity implements OnClickListener {
	
	private final static String TAG = "AIRCONTROLACTIVITY" ;
	private static ImageButton btn_Power ;
	private ImageButton btn_temp_up ;
	private ImageButton btn_temp_down ;
	private ImageButton air_btn_mode_cool ;
	private ImageButton air_btn_mode_heat ;
	private static ImageView vi_curren_temp ;
	private ImageButton air_control_set_time ;
	private ImageButton air_mode_wind ;
	private static ImageButton btn_wind_type1 ;
	private ImageButton btn_wind_type2;
	private static ImageButton air_control_show_mode ;
	private static ImageButton air_control_show_wind_type;
	private Vibrator mVibrator ;
	private int vibratorTime = 200 ;
	private static TextView air_control_show_set_time ;
	private static int[] image_temp= new int[]{R.drawable.img_temp_20 ,R.drawable.img_temp_21 ,R.drawable.img_temp_22 ,R.drawable.img_temp_23 ,R.drawable.img_temp_24 ,R.drawable.img_temp_25 ,R.drawable.img_temp_26 ,R.drawable.img_temp_27 ,R.drawable.img_temp_28 } ;
	private static int[] image_Runmode = new int[]{R.drawable.mode_cold_white,R.drawable.mode_warm_white,R.drawable.mode_warm_wind};
	private static int[] image_Run = new int[]{R.drawable.power_close,R.drawable.power_open};
	private static int[] image_Windmode = new int[]{R.drawable.img_air_direction_mid,R.drawable.img_air_direction_mid2};
	private static int[] image_button_Windmode = new int[]{R.drawable.wind_vertical_horizontal_normal,R.drawable.wind_vertical_normal};
	static int currentid = 0 ;
	static Boolean mIsOpenFlag = false ;
	int timeStatus ;
	private static String[] air_set_time_content ;
	private static Thread ThreadRun = null;  
	private Handler mHandler ;
	private String AirInputMessage = "" ;	
	private String Airstatus = "" ;	
	private static String Air_Dev_ID = "";
	private static String Air_Dev_Type ="";
	static Thread AirThread = null;
	private static OutputStream mOutput ;	
	private static RelativeLayout relative ;
	/**
	 * 获取视图
	 * 
	 */		
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
		air_mode_wind = (ImageButton)findViewById(R.id.air_mode_wind) ;
		air_mode_wind.setOnClickListener(this);
		btn_wind_type1 = (ImageButton)findViewById(R.id.btn_wind_type1) ;
		btn_wind_type1.setOnClickListener(this);
//		btn_wind_type2 = (ImageButton)findViewById(R.id.btn_wind_type2) ;
//		btn_wind_type2.setOnClickListener(this);
		air_control_show_set_time = (TextView)findViewById(R.id.air_control_show_set_time) ;
		
		air_control_show_mode = (ImageButton)findViewById(R.id.air_control_show_mode) ;
		air_control_show_mode.setOnClickListener(this);
		
		air_control_show_wind_type = (ImageButton)findViewById(R.id.air_control_show_wind_type);
		air_control_show_wind_type.setOnClickListener(this);
		
		air_control_set_time = (ImageButton)findViewById(R.id.air_control_set_time);
		air_control_set_time.setOnClickListener(this);
		vi_curren_temp = (ImageView)findViewById(R.id.vi_current_temp) ;
		relative = (RelativeLayout)findViewById(R.id.air_show_info) ;
//		relative.setBackgroundColor(Color.GRAY);
		relative.setBackgroundColor(Color.rgb(85, 85, 85));		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_air_control);
		
		air_set_time_content = getResources().getStringArray(R.array.air_control_set_time) ;
		
		findView();
		mVibrator = (Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE) ;
		
		LoginActivity mLoginActivity = new LoginActivity() ;
		WeakReference<LoginActivity> reference = new WeakReference<LoginActivity>(mLoginActivity) ;
		mOutput = ((LoginActivity)reference.get()).getSocketOutPutStream() ;
		  Air_Dev_ID = Command.DEV_IRCtr_ID; //取得设备ID
		  Air_Dev_Type =Command.Dev_Type_Air;//设定设备类型		 
		/**
		 * 根据网络返回的信息刷新控制面板视图
		 * 
		 */		
		  mHandler = new Handler(){
			public void handleMessage(Message msg) {			
				if(msg.what==123){
					Air_RefreshButton_view(Airstatus);	
				}

			}
		};
		/**
		 * 启动一个副线程,延时等待接收网络返回的信息
		 * 
		 */
		AirThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
			         Thread.currentThread();
					Thread.sleep(800); 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
//				Log.i(TAG, "AirThread--Run!!!!") ;					
				AirInputMessage= LoginActivity.getInputMessage(Command.Dev_Type_Air_byte,Command.Cmd_Ctr_Object_N1_byte,Command.DEV_IRCtr_ID);
//				Log.i(TAG, "AirInputMessage:"+AirInputMessage) ;							
				Airstatus = Command.Air_State_FeadBack(AirInputMessage);				
//				Log.i(TAG, "Airstatus:"+Airstatus) ;
				mHandler.sendEmptyMessage(123) ;	
			}												
		})	;			
		
	}
	
	/**
	 * onResume时刷新控制界面视图
	 * 
	 */	
	@Override
	public void onResume() {
		super.onResume();
		String AirstateMessage="";
		AirstateMessage= Command.Air_State_FeadBack(LoginActivity.getInputMessage(Command.Dev_Type_Air_byte,(byte) 0x00,Air_Dev_ID));
		Air_RefreshButton_view (AirstateMessage);	
	}
	
	/**
	 * 控制操作响应处理
	 * 
	 */		
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String messageContent ="";
		switch (v.getId()) {
		case R.id.btn_airpower :
			mVibrator.vibrate(vibratorTime);
			if(!mIsOpenFlag){			
				 messageContent = Command.Dev_Cmd_Creat(Air_Dev_Type, Air_Dev_ID, Command.Cmd_Type_Open,Command.Cmd_Ctr_Object_N1);
				 Air_Cmd_Send(messageContent);			
			}else{
				 messageContent = Command.Dev_Cmd_Creat(Air_Dev_Type, Air_Dev_ID, Command.Cmd_Type_Close,Command.Cmd_Ctr_Object_N1);
				 Air_Cmd_Send(messageContent);
			}															
			break ;
		case R.id.btn_temp_up :
			mVibrator.vibrate(vibratorTime);
			messageContent = Command.Dev_Cmd_Creat(Air_Dev_Type, Air_Dev_ID, Command.Cmd_Type_TEMP_I,Command.Cmd_Ctr_Object_N1);
			Air_Cmd_Send(messageContent);				
			break ;
		case R.id.btn_temp_down :
			mVibrator.vibrate(vibratorTime);			
			messageContent = Command.Dev_Cmd_Creat(Air_Dev_Type, Air_Dev_ID, Command.Cmd_Type_TEMP_R,Command.Cmd_Ctr_Object_N1);
			Air_Cmd_Send(messageContent);		
			break ;
		case R.id.air_control_set_time:
			mVibrator.vibrate(vibratorTime);
//			Log.i(TAG, "定时操作") ;
			messageContent = Command.Dev_Cmd_Creat(Air_Dev_Type, Air_Dev_ID, Command.Cmd_Type_Time,Command.Cmd_Ctr_Object_N1);
			Air_Cmd_Send(messageContent);			
			break ;
		case R.id.air_mode_cool:
			mVibrator.vibrate(vibratorTime);
			messageContent = Command.Dev_Cmd_Creat(Air_Dev_Type, Air_Dev_ID, Command.Cmd_Type_Air_Cool,Command.Cmd_Ctr_Object_N1);
			Air_Cmd_Send(messageContent);
			break ;			
		case R.id.air_mode_wind: 
			mVibrator.vibrate(vibratorTime);
			messageContent = Command.Dev_Cmd_Creat(Air_Dev_Type, Air_Dev_ID, Command.Cmd_Type_WindVount,Command.Cmd_Ctr_Object_N1);
			Air_Cmd_Send(messageContent);
			break ;
		case R.id.air_mode_heat:
			mVibrator.vibrate(vibratorTime);
			messageContent = Command.Dev_Cmd_Creat(Air_Dev_Type, Air_Dev_ID, Command.Cmd_Type_Air_HEAT,Command.Cmd_Ctr_Object_N1);
			Air_Cmd_Send(messageContent);			
			break ;
		case R.id.btn_wind_type1:
			mVibrator.vibrate(vibratorTime);
			messageContent = Command.Dev_Cmd_Creat(Air_Dev_Type, Air_Dev_ID, Command.Cmd_Type_Windmode,Command.Cmd_Ctr_Object_N1);
			Air_Cmd_Send(messageContent);			
			break ;
		default:
			break;
		}
	}
	
	/**
	 * 控制命令发送
	 * 
	 */		
	private static  void Air_Cmd_Send (String mSendMsg){
		try {
			mOutput.write(mSendMsg.getBytes()) ;	//socket流发送控制命令
//			Log.i(TAG, "mSendMsg"+mSendMsg) ;				
			ThreadRun = new Thread(AirThread);  //命令发送完new一个新线程等待接收网络数据
			ThreadRun.start();					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;
	}	

	

	/**
	 * 根据空调状态信息刷新控制界面视图
	 * 
	 */	
	private static  void Air_RefreshButton_view (String mAirstateMessage){
		int Air_Run_status_view = 0;
		int Air_Runmode_status_view = 0;
		int Air_Windmode_status_view = 0;
		int Air_time_status_view = 0;
		int Air_temp_value_view = 0;		
//		Log.i(TAG, "Air_ResumeButton_view") ;			
//		Log.i(TAG, "AirstateMessage"+mAirstateMessage) ;				 
		if(mAirstateMessage.length()!=Command.Dev_Air_State_Size){
			Air_Run_status_view=0;
			Air_Runmode_status_view =0 ;
			Air_Windmode_status_view =0;
			Air_time_status_view = 0;
			Air_temp_value_view = 6;
		}else{
			Air_Run_status_view = mAirstateMessage.getBytes()[Command.Air_Run_status_Pos]-0x30;				//第0字节，运行状态
			Air_Runmode_status_view = mAirstateMessage.getBytes()[Command.Air_Runmode_status_Pos]-0x30;		//第1字节，运行模式
			Air_Windmode_status_view = mAirstateMessage.getBytes()[Command.Air_Windmode_status_Pos]-0x30;	//第2字节，风向状态
			Air_time_status_view = mAirstateMessage.getBytes()[Command.Air_time_status_Pos]-0x30;			//第3字节，定时状态
			Air_temp_value_view = mAirstateMessage.getBytes()[Command.Air_temp_value_Pos]-0x30;				//第4字节，温度值
		}
		
		if(Air_Run_status_view == 1){
			mIsOpenFlag = true;
//			relative.setBackgroundColor(Color.BLUE);
			relative.setBackgroundColor(Color.rgb(170, 245, 250));

		}else if(Air_Run_status_view == 0){
			mIsOpenFlag = false;
//			relative.setBackgroundColor(Color.GRAY);
			relative.setBackgroundColor(Color.rgb(85, 85, 85));			
		}
		currentid =Air_temp_value_view;
//		Log.i(TAG, "Air_Run_status_view:"+Air_Run_status_view) ;	
//		Log.i(TAG, "Air_Runmode_status_view:"+Air_Runmode_status_view) ;	
//		Log.i(TAG, "Air_Windmode_status_view:"+Air_Windmode_status_view) ;	
//		Log.i(TAG, "Air_time_status_view:"+Air_time_status_view) ;	
//		Log.i(TAG, "Air_temp_value_view:"+Air_temp_value_view) ;	
		btn_Power.setImageResource(image_Run[Air_Run_status_view]);
		vi_curren_temp.setImageResource(image_temp[Air_temp_value_view]);
		air_control_show_mode.setBackgroundResource(image_Runmode[Air_Runmode_status_view]);
		air_control_show_set_time.setText(air_set_time_content[Air_time_status_view]);
		air_control_show_wind_type.setBackgroundResource(image_Windmode[Air_Windmode_status_view]);
		btn_wind_type1.setImageResource(image_button_Windmode[Air_Windmode_status_view]);

	}	
	
}


