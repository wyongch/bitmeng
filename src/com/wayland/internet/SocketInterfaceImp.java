package com.wayland.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class SocketInterfaceImp implements SocketInterface {

	// private static final
	private static final String TAG = "SOCKETINTERFACEIMP";
	private static final String NOTPASSVERIFY = "验证信息错误";
	private static final String CONNECTSUCCESS = "链接Socket成功";
	private static final String EXECUTEVERIFYSUCCESS = "执行验证成功" ;
	private static final String SUCCESSPASSVERIFY = "通过验证" ;
	private static final String FAILPASSVERIFY = "没有通过验证" ;
	private String host;
	private int port;
	private Socket socket;
	private String verifyMSG;
	private OutputStream outPutStream;
	private InputStream inputStream;
	private String verifyMessage = "";
	private BufferedReader bufferedReader ;

	// 执行Socket链接
	@Override
	public void ConnectSocket(String _host, int _port) {

		// TODO Auto-generated method stub
		this.host = _host;
		this.port = _port;

		Log.i(TAG, host);
		Log.i(TAG, new String().valueOf(port));

		try {
			socket = new Socket(host, port);
			Log.i(TAG, CONNECTSUCCESS);
			// socket = new Socket(host, port) ;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "没有找到主机");
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "IO异常");
			e.printStackTrace();

		}

		// return null;
	}

	@Override
	public InputStream getSocketInputStream() {
		// TODO Auto-generated method stub
		
		try {
			inputStream = socket.getInputStream() ;
			Log.i(TAG, "获取输入流成功") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i(TAG, "获取输入流失败") ;
			e.printStackTrace();
		}
		
		return inputStream;
	}
	
	public Socket getSocket(){
		return socket ;
	}
	
	/*
	
	// 获得服务器返回的验证消息
	@Override
	public String getServerVerifyMSG() {
		// TODO Auto-generated method stub

		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream)) ;
			verifyMessage = bufferedReader.readLine() ;
//			verifyMessage = socket.getInputStream().toString();
			Log.i(TAG, verifyMessage) ;
			Log.i(TAG, "获取服务器验证消息") ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return verifyMessage;
	}

	@Override
	public void ExecuteVerify(String _verifyMSG) {
		// TODO Auto-generated method stub
		String verifyMSG = _verifyMSG;
		String serverMSG = getServerVerifyMSG();
		if (serverMSG.contains("athentication")) {
			try {
				outPutStream = socket.getOutputStream();
				Log.i(TAG, "获取输出流") ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				outPutStream.write(verifyMSG.getBytes());
				Log.i(TAG, "执行验证") ;
				Log.i(TAG, EXECUTEVERIFYSUCCESS) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			Log.i(TAG, serverMSG) ;
			return;
		}

	}

	@Override
	public Boolean IsPassVerify() throws SocketRuleException {
		// TODO Auto-generated method stub
		String serverMSG = getServerVerifyMSG();
		if (serverMSG.contains("passed")) {
			Log.i(TAG, SUCCESSPASSVERIFY) ;
			return true;
		} else if (serverMSG.contains("athentication")) {
			Log.i(TAG, FAILPASSVERIFY) ;
			return false;

			// return false ;
		} else {
			throw new SocketRuleException(NOTPASSVERIFY);
		}

	}
	
	*/

	@Override
	public OutputStream getSocketOutPutStream() {
		// TODO Auto-generated method stub
		
		try {
			outPutStream = socket.getOutputStream();
			Log.i(TAG, "获取输出流") ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return outPutStream;
	}

	

}
