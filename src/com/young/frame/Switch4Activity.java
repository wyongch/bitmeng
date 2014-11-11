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
//	private static final String HOST ="182.92.150.143" ;
//	private static final int PORT = 8480  ;
//	private static final String verification = "G0001@123123@1@APP1m" ;
	private OutputStream mOutput ;
	private InputStream mInput ;
//	private static final String[] switch4on = {"bt230000000310m" ,"bt240000000400m", "bt22111m","bt22111m"} ;
//	private static final String[] switch4off = {"bt230000000300m" ,"bt240000000400m", "bt22101m", "bt22101m"} ;
	
		
	private static String messageContent="" ;
	private OutputStream out ;
	
//	byte[] comment ={ Command.Msg_Type_Ctr ,Command.Dev_Type_One_SW, Command.Dev_ID_SIZE, Command.Cmd_Type_Open, Command.Cmd_Ctr_Object_N1  } ;
	//一键开关
	private static final String switch_one_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_One_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String switch_one_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_One_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	
	//四键开关
	private static final String switch4one_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Four_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String switch4two_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Four_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N2+Command.Msg_Foot ;
	private static final String switch4three_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Four_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N3+Command.Msg_Foot ;
	private static final String switch4four_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Four_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N4+Command.Msg_Foot ;
	
	private static final String switch4one_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Four_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String switch4two_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Four_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N2+Command.Msg_Foot ;
	private static final String switch4three_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Four_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N3+Command.Msg_Foot ;
	private static final String switch4four_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_Four_SW+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N4+Command.Msg_Foot ;
	
	
	private static final String[] switch4on = {switch4one_on ,switch4two_on, switch4three_on, switch4four_on} ;
	private static final String[] switch4off = {switch4one_off ,switch4two_off, switch4three_off, switch4four_off} ;

	
	@Override
	protected void onCreate(Bundle savedInstanceState)  {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switch4_activity);
//		String commantMsg = comment.toString() + Command.BYT_DEV_ID_SIZE ;
		//自定义findView
		findView() ;
//		Socket socket = new Socket() ;
//		private  Switch4Activity loginActivity ;
//		private WeakReference<Activity> mActivity ;
//		mActivity = new WeakReference<Activity>(loginActivity) ;
		
		
		LoginActivity login = new LoginActivity() ;
		
		WeakReference wy = new WeakReference(login);
		mOutput = ((LoginActivity)wy.get()).getSocketOutPutStream() ;
		
		
//		try {
//			out.write(comment);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		SocketInterface mySockt = new SocketInterfaceImp() ;
//		
//		try {
//			mySockt.ConnectSocket(HOST, PORT) ;
//			mySockt.IsPassVerify(verification) ;
//		} catch (SocketRuleException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			Log.i(TAG, "没有通过验证") ;
//		}
		
		
		
	/*	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mSocket = new Socket (HOST , PORT) ;
					Log.i(TAG, "socket链接成功！") ;
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "socket链接失败！") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "socket链接失败！") ;
				}
				
				
				try {
					mOutput = mSocket.getOutputStream() ;
					Log.i(TAG, "获取输出流成功") ;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					Log.i(TAG, "获取输出流失败") ;
				}
				
				
				try {
					mOutput.write(verification.getBytes());
					Log.i(TAG, "outputstream验证消息成功") ;
					//判断是否通过验证
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "outputstream验证消息失败") ;
				}
				
				try {
					mInput = mSocket.getInputStream() ;
					Log.i(TAG, "获取验证消息返回值：" + mInput.toString()) ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "获取验证消息返回值失败") ;
				}
				
			}
		}).start() ;
		
		*/
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
					Log.i(TAG, "设置打开开关控制命令成功") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "设置打开开关控制命令失败") ;
				}
				
			}else {
				messageContent = switch4off[0] ;
				try {
					mOutput.write(messageContent.getBytes()) ;
					Log.i(TAG, "设置关闭开关控制命令成功") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "设置关闭开关控制命令失败") ;
				}
			}
			
			
			
			break;
		case R.id.switch4_two:
			
			if(switch4_two.isChecked()){
				messageContent = switch4on[1] ;
				try {
					mOutput.write(messageContent.getBytes()) ;
					Log.i(TAG, "设置打开开关控制命令成功") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "设置打开开关控制命令失败") ;
				}
				
			}else {
				messageContent = switch4off[1] ;
				try {
					mOutput.write(messageContent.getBytes()) ;
					Log.i(TAG, "设置关闭开关控制命令成功") ;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i(TAG, "设置关闭开关控制命令失败") ;
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
