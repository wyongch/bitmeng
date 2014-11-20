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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class LoginActivity extends Activity{
	
	private static final String TAG = "LOGINACTIVITY" ;
	private SocketInterface mSocketInterface ;
	
	
	private static final String HOST ="182.92.150.143" ;
	private static final int PORT = 8480  ;
//	private static final String HOST ="10.0.2.2" ;
//	private static final String HOST ="192.168.169.110" ;
//	private static final int PORT = 8888  ;
	private static final String verification = "G0001@123123@1@APP1m" ;
	private OutputStream mOutput ;
	private InputStream mInput ;
	private static final String[] switch4on = {"bt230000000310m" ,"bt240000000400m", "bt22111m","bt22111m"} ;
	private static final String[] switch4off = {"bt230000000300m" ,"bt240000000400m", "bt22101m", "bt22101m"} ;
	private static String messageContent="" ;
	
	public static OutputStream socketOutPutStream ;
	public static InputStream socketInputStream ;
	public BufferedReader bufferReader ;
//	public String inputMessage = "wyongch"  ;
	public String outputMessage = "G0001@123123@1@APP1m" ;
	private Handler mHandler ;
	public  String  childInputMessage ;
	public String[] mInputStream = {"ddddddddddd"} ;
	
//	public static StringBuffer inputMessage ;
	public static String inputMessage="" ;
	private String messageString_user = "" ;
	private String getSocketInputString_user = "" ;//从Socket获取到的有效字符串
	private static String Msg_Dev_Type_One_SW ="";
	private static String Msg_Dev_Type_Four_SW="";
	private static String Msg_Dev_Type_Sixteen_SW ="";
	private static String Msg_Dev_Type_Air ="";
	private static String[] Msg_Dev_Type_IR={"","","","","","","","","",""} ;
	private static String[] Msg_Dev_Type_Gas={"","","","","","","","","",""} ;
	private static String Msg_Dev_Type_TV ="";	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
//		inputMessage = new StringBuffer() ;
		
		mSocketInterface = new SocketInterfaceImp() ;
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
//				super.handleMessage(msg);
				if(msg.what == 123){
//					inputMessage = new StringBuffer(childInputMessage) ;
//					inputMessage =childInputMessage ;
					if(childInputMessage.contains(Command.Response_Msg_Head)){
						
						getSocketInputString_user = getUseBufferSting(childInputMessage);
						Log.i(TAG, "getSocketInputString_user---------:"+getSocketInputString_user) ;						
					}
					else{
						Log.i(TAG, "getSocketInputString_user---null!!!") ;						
						return ;
					}


					byte[] netMessageChar =getSocketInputString_user.getBytes();
					byte Msg_Type = netMessageChar[Command.Msg_Type_Pos]; 
					byte Dev_Type = netMessageChar[Command.Dev_Type_Pos];
					String Dev_Id = new String(netMessageChar , Command.Dev_ID_Pos ,Command.Dev_ID_Size ) ;					
					byte[] Dev_ID_Buff =Dev_Id.getBytes();
					int Msg_Dev_Pos = 	(int) (Dev_ID_Buff[Command.Dev_ID_Size-2]- 0x30);// Dev_ID倒数第二个定位存储
					Log.i(TAG,"this is Dev_Type: " + Dev_Type) ;
					Log.i(TAG,"this is Dev_Id: " + Dev_Id) ;
					Log.i(TAG,"this is Msg_Dev_Pos: " + Msg_Dev_Pos) ;						
					switch(Dev_Type){
						case Command.Dev_Type_One_SW_byte:
							Msg_Dev_Type_One_SW = getSocketInputString_user;
//							Log.i(TAG,"this is Msg_Dev_Type_One_SW: " + Msg_Dev_Type_One_SW) ;							
							break;
						case Command.Dev_Type_Four_SW_byte:
							Msg_Dev_Type_Four_SW = getSocketInputString_user;
//							Log.i(TAG,"this is Msg_Dev_Type_Four_SW: " + Msg_Dev_Type_Four_SW) ;							
							
							break;
						case Command.Dev_Type_Sixteen_SW_byte:
							Msg_Dev_Type_Sixteen_SW = getSocketInputString_user;
//							Log.i(TAG,"this is Msg_Dev_Type_Sixteen_SW: " + Msg_Dev_Type_Sixteen_SW) ;													
							break;
						case Command.Dev_Type_Air_byte:
							Msg_Dev_Type_Air = getSocketInputString_user;	
//							Log.i(TAG,"this is Msg_Dev_Type_Air: " + Msg_Dev_Type_Air) ;							
							
							break;
						case Command.Dev_Type_IR_byte:
							Msg_Dev_Type_IR[Msg_Dev_Pos] = getSocketInputString_user;
//							Log.i(TAG,"this is Msg_Dev_Type_IR: " + Msg_Dev_Type_IR[Msg_Dev_Pos]) ;							
							
							break;
						case Command.Dev_Type_TV_byte:
							Msg_Dev_Type_TV = getSocketInputString_user;
//							Log.i(TAG,"this is Msg_Dev_Type_TV: " + Msg_Dev_Type_TV) ;							
							
							break;
						case Command.Dev_Type_Gas_byte:
							Msg_Dev_Type_Gas[Msg_Dev_Pos] = getSocketInputString_user;
//							Log.i(TAG,"this is Msg_Dev_Type_Gas: " + Msg_Dev_Type_Gas[Msg_Dev_Pos]) ;							
							
							break;
						default:
							Log.i(TAG,"this is Msg_Dev_Type_Null!!!" ) ;														
							break;					
					}					
				}				
			}
		} ;
		
		
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
				socketInputStream = mSocketInterface.getSocketInputStream() ;
				socketOutPutStream = mSocketInterface.getSocketOutPutStream() ;
				Log.i(TAG, "正在执行") ;
				while(true){
//											try {
//												Thread.sleep(5000) ;
//											} catch (InterruptedException e) {
//												// TODO Auto-generated catch block
//												e.printStackTrace();
//											}
											Log.i(TAG, "正在执行ssssssssss") ;
											
											bufferReader = new BufferedReader(new InputStreamReader(socketInputStream)) ;
											char[] readChar =new char[Command.MESSAGESIZE] ;
											
											try {
												bufferReader.read(readChar, 0, Command.MESSAGESIZE) ;
												
												CharBuffer buffer = CharBuffer.wrap(readChar);
												childInputMessage = buffer.toString() ;
//							Log.i(TAG,"this is bufferReader " + buffer.toString()) ;
//							Log.i(TAG, "this is in thread childInputMessage " + childInputMessage) ;
//							Log.i(TAG,"this is in thread inputMessage " + inputMessage) ;
//							Log.i(TAG,"this is in thread mInputStream[0] " + mInputStream[0]) ;
													mHandler.sendEmptyMessage(123) ;
												
											} catch (IOException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											
//											try {
//												if((childInputMessage = bufferReader.readLine()) != null){
//													
//													 ;
////													inputMessage = bufferReader.readLine() ;
//													Log.i(TAG, "this is in thread childInputMessage " + childInputMessage) ;
//													Log.i(TAG,"this is in thread inputMessage " + inputMessage) ;
//													Log.i(TAG,"this is in thread mInputStream[0] " + mInputStream[0]) ;
//													mHandler.sendEmptyMessage(123) ;
//													
//													
//												}
//												
//											} catch (IOException e) {
//												// TODO Auto-generated catch block
//												e.printStackTrace();
//											}
//											
//											
											try {
												Thread.sleep(1000);
												Log.i(TAG, "wwwwwwww") ;
											} catch (InterruptedException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											try {
												//可以先发送验证消息
												socketOutPutStream.write(outputMessage.getBytes());
												Log.i(TAG, "send commend message success") ;
											} catch (IOException e) {
												// TODO Auto-generated catch block
												Log.i(TAG, "send commend message fail") ;
												e.printStackTrace();
											}
					
					
				}
				
				
				
			}
		}) ;
		
		socketThread.start();
		
//		ImageButton loginButton = (ImageButton)findViewById(R.id.loginButton);
		Button loginButton = (Button)findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				
//				outputMessage = "outoutooutotoouotuoa" ;
				
	//			Log.i(TAG, inputMessage.toString()) ;
				Intent intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	
	public OutputStream getSocketOutPutStream(){
		
		return socketOutPutStream;
		
	}
	
	public InputStream getSocketInPutStream(){
		
		return socketInputStream;
		
	}
	
	public static String getInputMessage (byte Dev_Type,String Dev_ID){
		
		byte[] Dev_ID_Buff =Dev_ID.getBytes();
		int Dev_Pos = 	(int) (Dev_ID_Buff[Command.Dev_ID_Size-2]- 0x30);// Dev_ID倒数第二个定位存储
		switch(Dev_Type){
		case Command.Dev_Type_One_SW_byte:
			inputMessage = Msg_Dev_Type_One_SW;
			break;
		case Command.Dev_Type_Four_SW_byte:
			inputMessage = Msg_Dev_Type_Four_SW;							
			break;
		case Command.Dev_Type_Sixteen_SW_byte:
			inputMessage = Msg_Dev_Type_Sixteen_SW;							
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
	
	private static String getUseBufferSting (String _mInputBuffer){
		String useBuffer = "" ;
		String str = _mInputBuffer ;
		  str = str.substring(str.indexOf(Command.Response_Msg_Head2) + 2, str.lastIndexOf(Command.Response_Msg_Foot));
		  return str ;
		}
	
}
