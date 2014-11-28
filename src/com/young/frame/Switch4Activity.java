package com.young.frame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import org.apache.http.util.ByteArrayBuffer;

import com.wayland.global.Command;
import com.wayland.internet.SocketInterface;
import com.wayland.internet.SocketInterfaceImp;
import com.wayland.internet.SocketRuleException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

@SuppressLint("HandlerLeak")
public class Switch4Activity extends Activity implements  OnCheckedChangeListener {
	private static Boolean mswitch4_Button_Push[]={false,false,false,false,false};
	private static final String TAG = "Switch4Activity" ;
	private static ToggleButton switch4_Button[]={null,null,null,null,null};
	//	private static final String HOST ="182.92.150.143" ;
//	private static final int PORT = 8480  ;
//	private static final String verification = "G0001@123123@1@APP1m" ;
	private static OutputStream mOutput ;
	private InputStream mInput ;
	private static Thread ThreadRun = null;  	
//	private static final String[] switch4on = {"bt230000000310m" ,"bt240000000400m", "bt22111m","bt22111m"} ;
//	private static final String[] switch4off = {"bt230000000300m" ,"bt240000000400m", "bt22101m", "bt22101m"} ;
	private Handler mHandler ;
	private String mswitch4InputMessage = "" ;	
	private byte switch4_one_status = 0;
	private static byte switch4_one_act = 0;
	private byte switch4_act_obj = 0;
	private byte switch4_msg_obj = 0;		
//	private static String messageContent="" ;
	private OutputStream out ;
	private String switch4_Dev_ID = "";
	private String switch4_Dev_Type ="";
	static Thread msgswitch4Thread = null;
	Button switch4up_activity_back ;
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switch4up_activity);
//		String commantMsg = comment.toString() + Command.BYT_DEV_ID_SIZE ;
		//自定义findView
		findView() ;
		LoginActivity login = new LoginActivity() ;
		
		WeakReference<LoginActivity> wy = new WeakReference<LoginActivity>(login);
//		switch4_ResumeButton_view ();
		mOutput = ((LoginActivity)wy.get()).getSocketOutPutStream() ;		
		switch4_Dev_ID =Command.DEV_Sixteen_SW_ID;//取得设备ID
		switch4_Dev_Type =Command.Dev_Type_Four_SW;//设定设备类型	
		//生成handle的消息值，规则：button序号&状态（button序号*10+ 状态）
		/**
		 * 根据网络返回的信息设置控制面板视图
		 * 
		 */	
		mHandler = new Handler(){
			public void handleMessage(Message msg) {
				switch4_SetButton_view(msg.what);
				switch4_one_act = 0;
			}
		};
		/**
		 * 启动一个副线程,延时等待接收网络返回的信息
		 * 
		 */
		msgswitch4Thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
			         Thread.currentThread();
					Thread.sleep(500); 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
//				Log.i(TAG, "msgswitch4Thread--Run!!!!") ;					
				mswitch4InputMessage= LoginActivity.getInputMessage(Command.Dev_Type_Sixteen_SW_byte,switch4_act_obj,switch4_Dev_ID);
//				Log.i(TAG, "mswitch1InputMessage_open:"+mswitch1InputMessage) ;							
				switch4_one_status = Command.Msg_Dev_switch_Decode(mswitch4InputMessage,Command.Dev_Switch_State_Pos);
				switch4_msg_obj	= Command.Msg_Dev_switch_Obj_Decode(mswitch4InputMessage,Command.Dev_Switch_State_Pos);
//				Log.i(TAG, "switch1_open_status:"+switch1_one_status) ;	
				int msg_handel = 0 ;
				msg_handel = switch4_msg_obj-0x30;
				if(switch4_one_status == '1'&& switch4_one_act == Command.Sw_Act_Open){
					mHandler.sendEmptyMessage(msg_handel*10+1) ;//handle=开关操作的对象&开关状态放，开关操作的对象放在handle的高位，开关状态放在低位
					mswitch4_Button_Push[msg_handel] = true;					
				}
				else if(switch4_one_status == '1'&& switch4_one_act == Command.Sw_Act_Close){
					mHandler.sendEmptyMessage(msg_handel*10+0) ;
					mswitch4_Button_Push[msg_handel] = false;
				}
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
		switch4_RefreshButton_view ();	
	}
	/**
	 * 获取视图
	 * 
	 */	
	private void findView() {
		// TODO Auto-generated method stub
			
		switch4_Button[Command.Dev_SW_Obj_1] =(ToggleButton)findViewById(R.id.switch4_1) ;
		switch4_Button[Command.Dev_SW_Obj_2] =(ToggleButton)findViewById(R.id.switch4_2) ;
		switch4_Button[Command.Dev_SW_Obj_3] =(ToggleButton)findViewById(R.id.switch4_3) ;
		switch4_Button[Command.Dev_SW_Obj_4] =(ToggleButton)findViewById(R.id.switch4_4) ;
		
		for(int i =1 ;i<5;i++){
			switch4_Button[i].setOnCheckedChangeListener(this);
		}
		
		switch4up_activity_back = (Button)findViewById(R.id.switch4up_activity_back) ;
		switch4up_activity_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	/**
	 * 控制操作响应处理
	 * 
	 */	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch4_act_obj  = 0;
//		Log.i(TAG, "onCheckedChanged!") ;	
		switch (buttonView.getId()) {
			case R.id.switch4_1:
				switch4_act_obj = Command.Cmd_Ctr_Object_N1_byte;					
				if(mswitch4_Button_Push[Command.Dev_SW_Obj_1]){
					String messageContent =  Command.Dev_Cmd_Creat(switch4_Dev_Type,switch4_Dev_ID,Command.Cmd_Type_Close,Command.Cmd_Ctr_Object_N1);
					switch4_one_act = Command.Sw_Act_Close;
					switch4_Cmd_Send(messageContent);
//					mswitch4_Button_Push[0] = false;
				}
				else{
					String messageContent =  Command.Dev_Cmd_Creat(switch4_Dev_Type,switch4_Dev_ID,Command.Cmd_Type_Open,Command.Cmd_Ctr_Object_N1);
					switch4_one_act = Command.Sw_Act_Open;
					switch4_Cmd_Send(messageContent);										
//					mswitch4_Button_Push[0] = true;					
				}
				break;
			case R.id.switch4_2:
				switch4_act_obj = Command.Cmd_Ctr_Object_N2_byte;					
				if(mswitch4_Button_Push[Command.Dev_SW_Obj_2]){
					String messageContent =  Command.Dev_Cmd_Creat(switch4_Dev_Type,switch4_Dev_ID,Command.Cmd_Type_Close,Command.Cmd_Ctr_Object_N2);
					switch4_one_act = Command.Sw_Act_Close;
					switch4_Cmd_Send(messageContent);
//					mswitch4_Button_Push[1] = false;
				}
				else{
					String messageContent =  Command.Dev_Cmd_Creat(switch4_Dev_Type,switch4_Dev_ID,Command.Cmd_Type_Open,Command.Cmd_Ctr_Object_N2);
					switch4_one_act = Command.Sw_Act_Open;
					switch4_Cmd_Send(messageContent);										
//					mswitch4_Button_Push[1] = true;					
				}
				break ;
			case R.id.switch4_3:
				switch4_act_obj = Command.Cmd_Ctr_Object_N3_byte;					
				if(mswitch4_Button_Push[Command.Dev_SW_Obj_3]){
					String messageContent =  Command.Dev_Cmd_Creat(switch4_Dev_Type,switch4_Dev_ID,Command.Cmd_Type_Close,Command.Cmd_Ctr_Object_N3);
					switch4_one_act = Command.Sw_Act_Close;
					switch4_Cmd_Send(messageContent);
//					mswitch4_Button_Push[2] = false;
				}
				else{
					String messageContent =  Command.Dev_Cmd_Creat(switch4_Dev_Type,switch4_Dev_ID,Command.Cmd_Type_Open,Command.Cmd_Ctr_Object_N3);
					switch4_one_act = Command.Sw_Act_Open;
					switch4_Cmd_Send(messageContent);										
//					mswitch4_Button_Push[2] = true;					
				}
				break ;
			case R.id.switch4_4 :
				switch4_act_obj = Command.Cmd_Ctr_Object_N4_byte;					
				if(mswitch4_Button_Push[Command.Dev_SW_Obj_4]){
					String messageContent =  Command.Dev_Cmd_Creat(switch4_Dev_Type,switch4_Dev_ID,Command.Cmd_Type_Close,Command.Cmd_Ctr_Object_N4);
					switch4_one_act = Command.Sw_Act_Close;
					switch4_Cmd_Send(messageContent);
//					mswitch4_Button_Push[3] = false;
				}
				else{
					String messageContent =  Command.Dev_Cmd_Creat(switch4_Dev_Type,switch4_Dev_ID,Command.Cmd_Type_Open,Command.Cmd_Ctr_Object_N4);
					switch4_one_act = Command.Sw_Act_Open;
					switch4_Cmd_Send(messageContent);										
//					mswitch4_Button_Push[3] = true;					
				}				
				break ;
			default:
				break;
		}
		
	}
	/**
	 * 控制命令发送
	 * 
	 */	
	private static  void switch4_Cmd_Send (String mSendMsg){
		try {
			mOutput.write(mSendMsg.getBytes()) ;
			ThreadRun = new Thread(msgswitch4Thread);  
			ThreadRun.start();				
//			Log.i(TAG, "设置打开开关控制命令成功") ;		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			Log.i(TAG, "设置打开开关控制命令失败") ;
		}
		return ;
	}
	/**
	 *根据线程传递给handle的消息值设置控制界面视图
	 * 
	 */
	private static  void switch4_SetButton_view (int value){
		int view_state = value%10;
		int Button_No = value/10 ;
//		Log.i(TAG, "view_state"+view_state) ;
//		Log.i(TAG, "Button_No"+Button_No) ;		
		if(view_state == 1){
			switch4_Button[Button_No]. setBackgroundResource(R.drawable.switch4up_activity_switch_on);				
		}else if(view_state == 0){
			switch4_Button[Button_No]. setBackgroundResource(R.drawable.switch4up_activity_switch_off);				
			
		}
	}
	
	/**
	 *根据开关控制状态信息刷新控制界面视图
	 * 
	 */
	private static  void switch4_RefreshButton_view (){
		byte Sw_Obj =0  ;
		byte switch4_view_state =0  ;		
		String mswitch4stateMessage="";
//		Log.i(TAG, "switch4_ResumeButton_view") ;			
		for(int i = 1;i<5;i++){
			Sw_Obj = (byte) (i+0x30);
			mswitch4stateMessage= LoginActivity.getInputMessage(Command.Dev_Type_Sixteen_SW_byte,Sw_Obj,Command.DEV_Sixteen_SW_ID);
//			Log.i(TAG, "mswitch4stateMessage"+mswitch4stateMessage) ;				 
			if(mswitch4stateMessage==""){
				switch4_view_state=0x30;
			}else{
				switch4_view_state = Command.Msg_Dev_switch_act_Decode(mswitch4stateMessage,Command.Dev_Switch_State_Pos);			 
			}
			
			if(switch4_view_state == 0x31){//开关处于开启状态
				switch4_Button[i]. setBackgroundResource(R.drawable.switch4up_activity_switch_on);				
				mswitch4_Button_Push[i]=true;
			}else if(switch4_view_state == 0x30){//开关处于关闭状态
				switch4_Button[i]. setBackgroundResource(R.drawable.switch4up_activity_switch_off);				
				mswitch4_Button_Push[i]=false;	
			}			
		}
	}	
}
