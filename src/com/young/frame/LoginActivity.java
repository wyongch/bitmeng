package com.young.frame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.CharBuffer;
import com.wayland.global.Command;
import com.wayland.internet.SocketInterface;



import com.wayland.internet.SocketInterfaceImp;
import fragment.MonitorFragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;


public class LoginActivity extends Activity{
	
	private static final String TAG = "LOGINACTIVITY" ;
	private SocketInterface mSocketInterface ;
	
	
	private static final String HOST ="182.92.150.143" ;
	private static final int PORT = 8480  ;
	private static OutputStream socketOutPutStream ;
	private static InputStream socketInputStream ;
	private BufferedReader bufferReader ;
//	public String outputMessage = "G0001@123123@1@APP1m" ;
	private static String RegisterMessage = "" ;
	private Handler mHandler ;
	private  String  childInputMessage ;
	public String[] mInputStream = {"ddddddddddd"} ;
	
//	public static StringBuffer inputMessage ;
	public static String inputMessage="" ;
	private String getSocketInputString_user = "" ;//从Socket获取到的有效字符串
//	private byte[] netMessageChar ;
	private static String Msg_Dev_Type_One_SW ="";
	private static String[] Msg_Dev_Type_Four_SW={"","","","",""};
	private static String[] Msg_Dev_Type_Sixteen_SW ={"","","","","","","","","","","","","","","","",""};
	private static String Msg_Dev_Type_Air ="";
	private static String[] Msg_Dev_Type_IR={"","","","","","","","","",""} ;
	private static String[] Msg_Dev_Type_Gas={"","","","","","","","","",""} ;
	private static String Msg_Dev_Type_TV ="";	
	
	private EditText username;
	private EditText password;
	private Boolean Login_Button_Push = false; 
	private boolean stopRegisterFlag = false; 
	private static boolean PassedRegisterFlag = false; //保存上次登陆成功标志
	private BufferedReader RegisterReader ;
	private String RegisterInputMessage ="";
	private static String musername ="";	
	private static String mpassword ="";
	private Notification mNotification;
	private NotificationManager mNotificationManager;  
	private final static int NOTIFICATION_ID = 0x0002; 
	private Context mContext; 
	@SuppressLint("HandlerLeak")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		mSocketInterface = new SocketInterfaceImp() ;
		mContext = LoginActivity.this; 
		
	    mNotification = new Notification(R.drawable.icon,"监控信息告警",System.currentTimeMillis());  
        mNotificationManager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);  				
		/**
		 * 根据设备类型和控制对象分类存储从网络上获取的状态信息
		 * 
		 */		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what == Command.RegisterPassed_msg){		//登陆成功进入到主页面	
					PassedRegisterFlag = true;
					Log.i(TAG, "Register success") ;					
					Toast.makeText(LoginActivity.this, "连接云服务器成功！！！" , Toast.LENGTH_LONG).show();
					Intent intent = new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
					finish();								
				}else if(msg.what == Command.RegisterFailed_msg){
					Log.i(TAG, "Register failed") ;	
					Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新输入！！！" , Toast.LENGTH_LONG).show();
				}
				else if(msg.what == Command.InputStream_msg){			
					socketInputStream_Receive();	//有效消息处理
//					Log.i(TAG, "Msg_Dev_Type Save!") ;					
				}				
			}
		} ;
				
		/**
		 * 启动一个副线程，从网络获取socket数据流
		 * 
		 */
		final Thread socketThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mSocketInterface.ConnectSocket(HOST, PORT);
				socketInputStream = mSocketInterface.getSocketInputStream() ;
				socketOutPutStream = mSocketInterface.getSocketOutPutStream() ;
//				Log.i(TAG, "正在执行") ;
				/**
				 * 用户登录验证
				 * 
				 */
				while(!stopRegisterFlag){
					if(PassedRegisterFlag){ //程序未完全退出，上次登陆成功标志有效直接发送验证消息无需再次输入用户信息
						Register_socket();
					}else{
						if(Login_Button_Push){
							Login_Button_Push = false;
							Register_socket();
						}
					}
				}
				/**
				 * 登陆验证成功之后socket数据流接收
				 * 
				 */
				while(stopRegisterFlag){
					Log.i(TAG, "InputStreamReader") ;	
					bufferReader = new BufferedReader(new InputStreamReader(socketInputStream)) ;
					char[] readChar =new char[Command.MESSAGESIZE] ;
					
					try {
						bufferReader.read(readChar, 0, Command.MESSAGESIZE) ;
						
						CharBuffer buffer = CharBuffer.wrap(readChar);
						childInputMessage = buffer.toString() ;
						//判断socket流是否包含有效数据的帧头和帧尾
						if(childInputMessage.contains(Command.Response_Msg_Head)
								&&childInputMessage.contains(Command.Response_Msg_Foot)){
							mHandler.sendEmptyMessage(Command.InputStream_msg) ; //有效信息通知主线程处理							
						}						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}										
				}												
			}
		}) ;
		
		socketThread.start();


		
		Button loginButton = (Button)findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				//从登陆框获取用户信息
				musername = username.getText().toString() ;
				mpassword = password.getText().toString() ;
				RegisterMessage =musername+"@"+mpassword+"@"+"@1@APP1m"; 
//				Log.i(TAG, "RegisterMessage:"+RegisterMessage) ;
				stopRegisterFlag =false;
				Login_Button_Push = true;

			}
		});
		
		Button testButton = (Button)findViewById(R.id.testButton);
		testButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
				//从登陆框获取用户信息
//				musername = username.getText().toString() ;
//				mpassword = password.getText().toString() ;
				RegisterMessage ="G0001@123123@1@APP1m"; 
//				Log.i(TAG, "RegisterMessage:"+RegisterMessage) ;
				stopRegisterFlag =false;
				Login_Button_Push = true;

			}
		});
	}
	
	public OutputStream getSocketOutPutStream(){
		
		return socketOutPutStream;
		
	}
	
	public InputStream getSocketInPutStream(){
		
		return socketInputStream;
		
	}
	/**
	 * 分类处理有效的socket数据信息
	 * 
	 */	
	public void socketInputStream_Receive(){
		getSocketInputString_user = getUseBufferSting(childInputMessage);
		byte[] netMessageChar =getSocketInputString_user.getBytes();
		Log.i(TAG,"this is getSocketInputString_user: " + getSocketInputString_user) ;	
		byte Msg_Type = netMessageChar[Command.Msg_Type_Pos]; 
		byte Dev_Type = netMessageChar[Command.Dev_Type_Pos];
		String Dev_Id = new String(netMessageChar , Command.Dev_ID_Pos ,Command.Dev_ID_Size ) ;					
		byte[] Dev_ID_Buff =Dev_Id.getBytes();
		int Msg_Dev_Pos = 	(int) (Dev_ID_Buff[Command.Dev_ID_Size-2]- 0x30);// Dev_ID倒数第二个定位存储
		int Msg_Dev_SW_Obj = (int)(netMessageChar[getSocketInputString_user.length()-2])-0x30;																	
		switch(Dev_Type){
			case Command.Dev_Type_One_SW_byte:
				Msg_Dev_Type_One_SW = getSocketInputString_user;
//				Log.i(TAG,"this is Msg_Dev_Type_One_SW: " + Msg_Dev_Type_One_SW) ;							
				break;
			case Command.Dev_Type_Four_SW_byte:
				Msg_Dev_Type_Four_SW[Msg_Dev_SW_Obj] = getSocketInputString_user;
//				Log.i(TAG,"this is Msg_Dev_Type_Four_SW: " + Msg_Dev_Type_Four_SW) ;														
				break;
			case Command.Dev_Type_Sixteen_SW_byte:
				Msg_Dev_Type_Sixteen_SW[Msg_Dev_SW_Obj] = getSocketInputString_user;
//				Log.i(TAG,"this is Msg_Dev_Type_Sixteen_SW: " + Msg_Dev_Type_Sixteen_SW[Msg_Dev_SW_Obj]) ;													
				break;
			case Command.Dev_Type_Air_byte:
				Msg_Dev_Type_Air = getSocketInputString_user;	
//				Log.i(TAG,"this is Msg_Dev_Type_Air: " + Msg_Dev_Type_Air) ;							
				break;
			case Command.Dev_Type_IR_byte:
				Msg_Dev_Type_IR[Msg_Dev_Pos] = getSocketInputString_user;
				byte Dev_IR_State =(getSocketInputString_user.getBytes())[Command.Dev_Sensor_State_Pos]; //解析传感器当前状态	
				if(Dev_IR_State == Command.FeadBack_Effective){
					statusNotifi();
				}
//				Log.i(TAG,"this is Msg_Dev_Type_IR: " + Msg_Dev_Type_IR[Msg_Dev_Pos]) ;														
				break;
			case Command.Dev_Type_TV_byte:
				Msg_Dev_Type_TV = getSocketInputString_user;
				Log.i(TAG,"this is Msg_Dev_Type_TV: " + Msg_Dev_Type_TV) ;														
				break;
			case Command.Dev_Type_Gas_byte:
				Msg_Dev_Type_Gas[Msg_Dev_Pos] = getSocketInputString_user;
				Msg_Dev_Type_IR[Msg_Dev_Pos] = getSocketInputString_user;
				byte Dev_Gas_State =(getSocketInputString_user.getBytes())[Command.Dev_Sensor_State_Pos]; //解析传感器当前状态	
				if(Dev_Gas_State == Command.FeadBack_Effective){
					statusNotifi();
				}
				//							Log.i(TAG,"this is Msg_Dev_Type_Gas: " + Msg_Dev_Type_Gas[Msg_Dev_Pos]) ;														
				break;
			default:
				Log.i(TAG,"this is Msg_Dev_Type_Null!!!" ) ;														
				break;					
		}		
	}
	/**
	 * 向云端发送用户信息验证
	 * 
	 */	
	public void Register_socket(){
		RegisterReader = new BufferedReader(new InputStreamReader(socketInputStream)) ;
		char[] RegisterChar =new char[Command.MESSAGESIZE] ;
		for(int i=0;i<Command.Register_requests_num;i++){ //最多尝试登陆Register_requests_num次数
			try {
		        Thread.currentThread();
				Thread.sleep(100); 
			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}	
			try {
				//可以先发送验证消息
				socketOutPutStream.write(RegisterMessage.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//等待云端回送验证结果
			try {
				RegisterReader.read(RegisterChar, 0, Command.MESSAGESIZE) ;
				
				CharBuffer buffer = CharBuffer.wrap(RegisterChar);
			    RegisterInputMessage = buffer.toString() ;				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				stopRegisterFlag = false;
				e1.printStackTrace();
			}
			//处理验证结果： “passed” 即验证成功
			if(RegisterInputMessage.contains(Command.Register_passed)){  //登录验证通过置有效标志
				stopRegisterFlag = true ;
				mHandler.sendEmptyMessage(Command.RegisterPassed_msg) ; //验证成功消息通知
				break;
			}else{
				stopRegisterFlag = false;
				PassedRegisterFlag = false;

			}
		}
		
		if(!stopRegisterFlag){
			mHandler.sendEmptyMessage(Command.RegisterFailed_msg) ; //验证失败消息通知							
		}		
	}

	/**
	 * 根据设备类型和控制对象返回网络数据
	 * 
	 */		
	public static String getInputMessage (byte Dev_Type,byte Dev_Obj,String Dev_ID){
//		Log.i(TAG, "getInputMessage-RUN") ;		
		byte[] Dev_ID_Buff =Dev_ID.getBytes();
		int Dev_Pos = 	(int) (Dev_ID_Buff[Command.Dev_ID_Size-2]- 0x30);// Dev_ID倒数第二个定位存储
		switch(Dev_Type){
		case Command.Dev_Type_One_SW_byte:
			inputMessage = Msg_Dev_Type_One_SW;
			break;
		case Command.Dev_Type_Four_SW_byte:
			inputMessage = Msg_Dev_Type_Four_SW[Dev_Obj-0x30];							
			break;
		case Command.Dev_Type_Sixteen_SW_byte:
			inputMessage = Msg_Dev_Type_Sixteen_SW[Dev_Obj-0x30];							
			break;
		case Command.Dev_Type_Air_byte:
			inputMessage = Msg_Dev_Type_Air;							
			break;
		case Command.Dev_Type_IR_byte:
			inputMessage = Msg_Dev_Type_IR[Dev_Pos];							
			break;
		case Command.Dev_Type_TV_byte:
			inputMessage = Msg_Dev_Type_TV;							
			break;
		case Command.Dev_Type_Gas_byte:
			inputMessage = Msg_Dev_Type_Gas[Dev_Pos];							
			break;
		default:
			inputMessage="";
			break;	
		}
		return inputMessage ;
	}
	
	/**
	 * 根据帧头帧尾提取有效的网络数据
	 * 
	 */		
	private static String getUseBufferSting (String _mInputBuffer){
		String str = _mInputBuffer ;
		str = str.substring(str.indexOf(Command.Response_Msg_Head2) + 2, str.lastIndexOf(Command.Response_Msg_Foot));
		return str ;
	}
	 private void statusNotifi(){  
			Log.i(TAG, "statusNotifi----") ;       
        
//	        Intent mIntent = new Intent(mContext,TVActivityUp.class);  
	        Intent mIntent = new Intent(mContext,MainActivity.class);  
	        mIntent.putExtra("fragmentId", 123) ;
	        startActivity(mIntent);
	        //这里需要设置Intent.FLAG_ACTIVITY_NEW_TASK属性  
	        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);      
	        PendingIntent mContentIntent = PendingIntent.getActivity(mContext,0, mIntent, 0);  
	        //1，消息通知，使用setLatestEventInfo  
	        //这里必需要用setLatestEventInfo(上下文,标题,内容,PendingIntent)不然会报错.  
	        //  mNotification.setLatestEventInfo(mContext, "新消息", "主人，您孙子给你来短息了", mContentIntent);  
	          
	          
	        //2,消息通知，使用远程视图 
	        mNotification.contentView = new RemoteViews(this.getPackageName(),R.layout.notice);  
	        mNotification.contentView.setImageViewResource(R.id.status_icon,R.drawable.icon);  
	        mNotification.contentView.setTextViewText(R.id.status_text, "主人，监测信息出现告警，请点击查看确认!!!");  
	          
	        //使用默认的声音，闪屏，振动效果  
//			mNotification.defaults = Notification.DEFAULT_ALL;  
//			mNotification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE| Notification.DEFAULT_LIGHTS;  
			mNotification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE| Notification.DEFAULT_LIGHTS;  
	        //添加震动  
//	        long[] vibreate= new long[]{1000,1000,1000,1000};  
//	        mNotification.vibrate = vibreate;  
	              
	        //添加led  
	        mNotification.ledARGB = Color.RED;  
	        mNotification.ledOffMS= 0;  
	        mNotification.ledOnMS = 1;  
	        mNotification.flags = mNotification.flags | Notification.FLAG_SHOW_LIGHTS;  
	          
	        //手动设置contentView属于时，必须同时也设置contentIntent不然会报错  
	        mNotification.contentIntent = mContentIntent;   //消息通知
	          
	        //触发通知(消息ID,通知对象)  
	        mNotificationManager.notify(NOTIFICATION_ID, mNotification);     
	          
	          
	    }  	
}
