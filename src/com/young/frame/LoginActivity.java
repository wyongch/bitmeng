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
	private String getSocketInputString_user = "" ;//��Socket��ȡ������Ч�ַ���
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
	private static boolean PassedRegisterFlag = false; //�����ϴε�½�ɹ���־
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
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		mSocketInterface = new SocketInterfaceImp() ;
		mContext = LoginActivity.this; 
		
	    mNotification = new Notification(R.drawable.icon,"�����Ϣ�澯",System.currentTimeMillis());  
        mNotificationManager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);  				
		/**
		 * �����豸���ͺͿ��ƶ������洢�������ϻ�ȡ��״̬��Ϣ
		 * 
		 */		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(msg.what == Command.RegisterPassed_msg){		//��½�ɹ����뵽��ҳ��	
					PassedRegisterFlag = true;
					Log.i(TAG, "Register success") ;					
					Toast.makeText(LoginActivity.this, "�����Ʒ������ɹ�������" , Toast.LENGTH_LONG).show();
					Intent intent = new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
					finish();								
				}else if(msg.what == Command.RegisterFailed_msg){
					Log.i(TAG, "Register failed") ;	
					Toast.makeText(LoginActivity.this, "�û���������������������룡����" , Toast.LENGTH_LONG).show();
				}
				else if(msg.what == Command.InputStream_msg){			
					socketInputStream_Receive();	//��Ч��Ϣ����
//					Log.i(TAG, "Msg_Dev_Type Save!") ;					
				}				
			}
		} ;
				
		/**
		 * ����һ�����̣߳��������ȡsocket������
		 * 
		 */
		final Thread socketThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mSocketInterface.ConnectSocket(HOST, PORT);
				socketInputStream = mSocketInterface.getSocketInputStream() ;
				socketOutPutStream = mSocketInterface.getSocketOutPutStream() ;
//				Log.i(TAG, "����ִ��") ;
				/**
				 * �û���¼��֤
				 * 
				 */
				while(!stopRegisterFlag){
					if(PassedRegisterFlag){ //����δ��ȫ�˳����ϴε�½�ɹ���־��Чֱ�ӷ�����֤��Ϣ�����ٴ������û���Ϣ
						Register_socket();
					}else{
						if(Login_Button_Push){
							Login_Button_Push = false;
							Register_socket();
						}
					}
				}
				/**
				 * ��½��֤�ɹ�֮��socket����������
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
						//�ж�socket���Ƿ������Ч���ݵ�֡ͷ��֡β
						if(childInputMessage.contains(Command.Response_Msg_Head)
								&&childInputMessage.contains(Command.Response_Msg_Foot)){
							mHandler.sendEmptyMessage(Command.InputStream_msg) ; //��Ч��Ϣ֪ͨ���̴߳���							
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
				// TODO �Զ����ɵķ������
				
				//�ӵ�½���ȡ�û���Ϣ
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
				// TODO �Զ����ɵķ������
				
				//�ӵ�½���ȡ�û���Ϣ
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
	 * ���ദ����Ч��socket������Ϣ
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
		int Msg_Dev_Pos = 	(int) (Dev_ID_Buff[Command.Dev_ID_Size-2]- 0x30);// Dev_ID�����ڶ�����λ�洢
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
				byte Dev_IR_State =(getSocketInputString_user.getBytes())[Command.Dev_Sensor_State_Pos]; //������������ǰ״̬	
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
				byte Dev_Gas_State =(getSocketInputString_user.getBytes())[Command.Dev_Sensor_State_Pos]; //������������ǰ״̬	
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
	 * ���ƶ˷����û���Ϣ��֤
	 * 
	 */	
	public void Register_socket(){
		RegisterReader = new BufferedReader(new InputStreamReader(socketInputStream)) ;
		char[] RegisterChar =new char[Command.MESSAGESIZE] ;
		for(int i=0;i<Command.Register_requests_num;i++){ //��ೢ�Ե�½Register_requests_num����
			try {
		        Thread.currentThread();
				Thread.sleep(100); 
			} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}	
			try {
				//�����ȷ�����֤��Ϣ
				socketOutPutStream.write(RegisterMessage.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//�ȴ��ƶ˻�����֤���
			try {
				RegisterReader.read(RegisterChar, 0, Command.MESSAGESIZE) ;
				
				CharBuffer buffer = CharBuffer.wrap(RegisterChar);
			    RegisterInputMessage = buffer.toString() ;				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				stopRegisterFlag = false;
				e1.printStackTrace();
			}
			//������֤����� ��passed�� ����֤�ɹ�
			if(RegisterInputMessage.contains(Command.Register_passed)){  //��¼��֤ͨ������Ч��־
				stopRegisterFlag = true ;
				mHandler.sendEmptyMessage(Command.RegisterPassed_msg) ; //��֤�ɹ���Ϣ֪ͨ
				break;
			}else{
				stopRegisterFlag = false;
				PassedRegisterFlag = false;

			}
		}
		
		if(!stopRegisterFlag){
			mHandler.sendEmptyMessage(Command.RegisterFailed_msg) ; //��֤ʧ����Ϣ֪ͨ							
		}		
	}

	/**
	 * �����豸���ͺͿ��ƶ��󷵻���������
	 * 
	 */		
	public static String getInputMessage (byte Dev_Type,byte Dev_Obj,String Dev_ID){
//		Log.i(TAG, "getInputMessage-RUN") ;		
		byte[] Dev_ID_Buff =Dev_ID.getBytes();
		int Dev_Pos = 	(int) (Dev_ID_Buff[Command.Dev_ID_Size-2]- 0x30);// Dev_ID�����ڶ�����λ�洢
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
	 * ����֡ͷ֡β��ȡ��Ч����������
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
	        //������Ҫ����Intent.FLAG_ACTIVITY_NEW_TASK����  
	        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);      
	        PendingIntent mContentIntent = PendingIntent.getActivity(mContext,0, mIntent, 0);  
	        //1����Ϣ֪ͨ��ʹ��setLatestEventInfo  
	        //�������Ҫ��setLatestEventInfo(������,����,����,PendingIntent)��Ȼ�ᱨ��.  
	        //  mNotification.setLatestEventInfo(mContext, "����Ϣ", "���ˣ������Ӹ�������Ϣ��", mContentIntent);  
	          
	          
	        //2,��Ϣ֪ͨ��ʹ��Զ����ͼ 
	        mNotification.contentView = new RemoteViews(this.getPackageName(),R.layout.notice);  
	        mNotification.contentView.setImageViewResource(R.id.status_icon,R.drawable.icon);  
	        mNotification.contentView.setTextViewText(R.id.status_text, "���ˣ������Ϣ���ָ澯�������鿴ȷ��!!!");  
	          
	        //ʹ��Ĭ�ϵ���������������Ч��  
//			mNotification.defaults = Notification.DEFAULT_ALL;  
//			mNotification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE| Notification.DEFAULT_LIGHTS;  
			mNotification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE| Notification.DEFAULT_LIGHTS;  
	        //�����  
//	        long[] vibreate= new long[]{1000,1000,1000,1000};  
//	        mNotification.vibrate = vibreate;  
	              
	        //���led  
	        mNotification.ledARGB = Color.RED;  
	        mNotification.ledOffMS= 0;  
	        mNotification.ledOnMS = 1;  
	        mNotification.flags = mNotification.flags | Notification.FLAG_SHOW_LIGHTS;  
	          
	        //�ֶ�����contentView����ʱ������ͬʱҲ����contentIntent��Ȼ�ᱨ��  
	        mNotification.contentIntent = mContentIntent;   //��Ϣ֪ͨ
	          
	        //����֪ͨ(��ϢID,֪ͨ����)  
	        mNotificationManager.notify(NOTIFICATION_ID, mNotification);     
	          
	          
	    }  	
}
