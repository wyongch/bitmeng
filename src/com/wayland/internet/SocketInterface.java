package com.wayland.internet;

import java.io.InputStream;
import java.io.OutputStream;



public interface SocketInterface {
	
	public abstract void ConnectSocket(String _host , int _port) ;
	public abstract InputStream getSocketInputStream() ;
//	public abstract String getServerVerifyMSG() ;
//	public abstract void ExecuteVerify(String _verifyMSG) ;
//	public abstract Boolean IsPassVerify() throws SocketRuleException ;
	public abstract OutputStream getSocketOutPutStream() ;
//	public abstract OutputStream getSocketOutPutStream() ;
	

}
