package com.wayland.global;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

import org.apache.http.util.ByteArrayBuffer;

import com.young.frame.LoginActivity;

import android.util.Log;

public class Command {

//	public static final String myname = "nihao" ;
	
	
//	byte[]  BYT_DEV_ID_SIZE = new Integer(Dev_ID_SIZE).
//	public static final String BYT_DEV_ID_SIZE = new Integer(Dev_ID_SIZE).toString() ;
	
	private static final String TAG = "Command" ;
	
	public static final String Msg_Head = "bt" ;
	public static final String Msg_Foot = "m" ;
	
	public static final String Response_Msg_Foot = "m" ;
	public static final String Response_Msg_Head = "zt" ;
	public static final String Response_Msg_Head2 = "z" ;
	public static final String Register_passed 	  ="passed";
	public static final String Register_failed 	  ="failed";
	public static final int mMonitorFragment_pos = 3 ;
	public static final int MESSAGESIZE = 64 ;
	public static final int Msg_Type_Pos = 0 ;
	public static final int Dev_Type_Pos = 1 ;
	public static final int Dev_ID_Pos = 2 ;
	public static final int Dev_ID_Size = 8 ;
	public static final int Dev_Sensor_State_Pos = 12 ;	
	public static final int Dev_Switch_State_Pos = 12 ;	
	public static final int Dev_Air_State_Pos = 12 ;
	public static final int Dev_Air_State_Size = 5 ;

	public static final int Register_requests_num = 5 ; //同一账号密码重复请求登陆次数
	public static final int InputStream_msg = 111 ; //接收到有效socket数据流消息标志
	public static final int RegisterPassed_msg = 221 ; //接收到注册数据流消息标志
	public static final int RegisterFailed_msg = 220 ; //接收到注册数据流消息标志	
	
	public static final int Air_Run_status_Pos = 0;			//控制状态反馈信息--运行状态字位置
	public static final int Air_Runmode_status_Pos = 1;		//控制状态反馈信息--运行模式字位置
	public static final int Air_Windmode_status_Pos = 2;	//控制状态反馈信息--风类模式字位置
	public static final int Air_time_status_Pos = 3;		//控制状态反馈信息--定时模式字位置
	public static final int Air_temp_value_Pos = 4;			//控制状态反馈信息--温度值字位置
	
	public static final int Dev_SW_Obj_all = 0 ; // 开关对象所有
	public static final int Dev_SW_Obj_1 = 1 ; // 开关对象1
	public static final int Dev_SW_Obj_2 = 2 ; // 开关对象2
	public static final int Dev_SW_Obj_3 = 3 ; // 开关对象3
	public static final int Dev_SW_Obj_4 = 4 ; // 开关对象4
	public static final int Dev_SW_Obj_5 = 5 ; // 开关对象5
	public static final int Dev_SW_Obj_6 = 6 ; // 开关对象6
	public static final int Dev_SW_Obj_7 = 7 ; // 开关对象7
	public static final int Dev_SW_Obj_8 = 8 ; // 开关对象8
	public static final int Dev_SW_Obj_9 = 9 ; // 开关对象9
	public static final int Dev_SW_Obj_10 = 10 ; // 开关对象10
	public static final int Dev_SW_Obj_11 = 11 ; // 开关对象11
	public static final int Dev_SW_Obj_12 = 12 ; // 开关对象12
	public static final int Dev_SW_Obj_13 = 13 ; // 开关对象13
	public static final int Dev_SW_Obj_14 = 14 ; // 开关对象14
	public static final int Dev_SW_Obj_15 = 15 ; // 开关对象15
	public static final int Dev_SW_Obj_16 = 16 ; // 开关对象16
////////////////////消息类型////////////////////////////
public static final String 	Msg_Type_Register	=	"1" ;	//认证消息
public static final String	Msg_Type_Ctr		=	"2" ;	//控制消息
public static final String 	Msg_Type_Upload		=	"3" ;	//升级消息
public static final String 	Msg_Type_State		=	"4" ;	//状态上报
public static final String 	Msg_Type_ListDev	=	"5"	 ;	//获取在线设备列表
public static final String 	Msg_Type_AddDev		=	"6"	 ;	//设备添加消息
public static final String 	Msg_Type_DelDEV		=	"7" ;	//设备删除消息
public static final String 	Msg_Type_IR_Learn	=	"8" ;	//红外学习消息
////////////////////设备类型////////////////////////////
public static final String 	Dev_Type_One_SW		=	"1"	;	//单键开关
public static final String 	Dev_Type_Four_SW	=	"3"	;	//四键开关
public static final String 	Dev_Type_Sixteen_SW	=	"3"	;	//十六键开关
public static final String 	Dev_Type_TV			=	"4"	;	//电视/机顶盒遥控
public static final String 	Dev_Type_Air		=	"5"	;	//空调遥控
public static final String 	Dev_Type_IR			=	"6"	;	//红外人体感应
public static final String 	Dev_Type_Gas		=	"7"	;	//危险气体检测
public static final String 	Dev_Type_CCD		=	"8"	;	//摄像头
public static final String 	Dev_Type_STB		=	"9"	;	//其他
public static final String 	Dev_Type_Oth		=	"a"	;	//其他

////////////////////反馈状态////////////////////////////
public static final byte 	FeadBack_Invalid	=	'0'	;	//无效
public static final byte 	FeadBack_Effective	=	'1'	;	//有效

////////////////////设备类型////////////////////////////
public static final byte 	Dev_Type_One_SW_byte		=	'1'	;	//单键开关
public static final byte 	Dev_Type_Four_SW_byte		=	'2'	;	//四键开关
public static final byte 	Dev_Type_Sixteen_SW_byte	=	'3'	;	//十六键开关
public static final byte 	Dev_Type_TV_byte			=	'4'	;	//电视遥控
public static final byte 	Dev_Type_Air_byte			=	'5'	;	//空调遥控
public static final byte 	Dev_Type_IR_byte			=	'6'	;	//红外人体感应
public static final byte 	Dev_Type_Gas_byte			=	'7'	;	//危险气体检测
public static final byte 	Dev_Type_CCD_byte			=	'8'	;	//摄像头
public static final byte 	Dev_Type_STB_byte			=	'9'	;	//其他
public static final byte 	Dev_Type_Oth_byte			=	'a'	;	//其他
public static final String 	DEV_One_SW_ID	 			=	"00000001"	;		//单键开关设备ID
public static final String	DEV_Four_SW_ID	 			=	"00000002"	;		//四键开关设备ID 
public static final String 	DEV_Sixteen_SW_ID			=	"00000003"	;		//十六键开关ID 
public static final String	DEV_IRCtr_ID	 			=	"00000004"	;		//红外遥控设备ID 
public static final String	DEV_Sensor_Human0_ID	 	=	"00000005"	;		//人体感应设备ID 
public static final String	DEV_Sensor_Human1_ID	 	=	"00000015"	;		//人体感应设备ID 
public static final String	DEV_Sensor_Human2_ID	 	=	"00000025"	;		//人体感应设备ID 
public static final String	DEV_Sensor_Human3_ID	 	=	"00000035"	;		//人体感应设备ID 
public static final String 	DEV_Sensor_Smoke0_ID	 	=	"00000006"	;		//烟感设备ID 
public static final String 	DEV_Sensor_Smoke1_ID	 	=	"00000016"	;		//烟感设备ID 
public static final String 	DEV_Sensor_Smoke2_ID	 	=	"00000026"	;		//烟感设备ID 
public static final String 	DEV_Sensor_Smoke3_ID	 	=	"00000036"	;		//烟感设备ID 
/////////////////////指令类型//////////////////////////
public static final String 	Cmd_Type_Close		=	"0"; 	//关
public static final String 	Cmd_Type_Open		=	"1" ;	//开
public static final String 	Cmd_Type_TEMP_I		=	"2"	;	//温度+
public static final String 	Cmd_Type_TEMP_R		=	"3"	;	//温度-
public static final String 	Cmd_Type_Air_Cool	=	"4"	;	//制冷模式
public static final String 	Cmd_Type_Air_HEAT	=	"5"	;	//制热模式
public static final String 	Cmd_Type_WindVount	=	"6"	;	//通风
public static final String 	Cmd_Type_Windmode	=	"7"	;	//摆风
public static final String 	Cmd_Type_Time		=	"8"	;	//定时

public static final String 	Cmd_Type_Tv_power	=	"0"; 	//电视机开关
public static final String 	Cmd_Typee_Tv_VOL_I	=	"1"	;	//电视机音量+
public static final String 	Cmd_Typee_Tv_VOL_R	=	"2"	;	//电视机音量-
public static final String	Cmd_Type_TV_AV		=		"3";	//av/tv

public static final String 	Cmd_Type_STB_power	=	"4"; 	//机顶盒开关
public static final String 	Cmd_Type_Silent		=	"5"	;	//机顶盒静音
public static final String 	Cmd_Type_STB_CH0	=	"C"	;	//机顶盒频道
public static final String 	Cmd_Type_STB_CH1	=	"6"	;	//机顶盒频道
public static final String 	Cmd_Type_STB_CH2	=	"7"	;	//机顶盒频道
public static final String 	Cmd_Type_STB_CH3	=	"8"	;	//机顶盒频道
public static final String 	Cmd_Type_STB_CH4	=	":"	;	//机顶盒频道
public static final String 	Cmd_Type_STB_CH5	=	";"	;	//机顶盒频道
public static final String 	Cmd_Type_STB_CH6	=	"<"	;	//机顶盒频道
public static final String 	Cmd_Type_STB_CH7	=	">"	;	//机顶盒频道
public static final String 	Cmd_Type_STB_CH8	=	"?"	;	//机顶盒频道
public static final String 	Cmd_Type_STB_CH9	=	"@"	;	//机顶盒频道
public static final String 	Cmd_Typee_STB_VOL_I	=	"A"	;	//机顶盒音量+
public static final String 	Cmd_Typee_STB_VOL_R	=	"E"	;	//机顶盒音量-
public static final String 	Cmd_Type_Programme_I=	"9"	;	//机顶盒节目+
public static final String 	Cmd_Type_Programme_R=	"="	;	//机顶盒节目-
public static final String 	Cmd_Type_TV			=	"F"	;	//电视
public static final String 	Cmd_Type_INFO		=	"D"	;	//信息
public static final String 	Cmd_Type_ACK		=	"B"	;	//确认
public static final String 	Cmd_Type_Back		=	"I"	;	//返回
public static final String 	Cmd_Type_Custom1	=	"G"	;	//自定义1
public static final String 	Cmd_Type_Custom2	=	"H"	;	//自定义1

//public static final String 	Cmd_Type_TV		=		'b'	;	//tv
//public static final String 	Cmd_Type_AV		=		'c'	;	//av
public static final String 	Cmd_Type_VOL_I		=	"d"	;//音量+
public static final String 	Cmd_Type_VOL_R		=	"e"	;	//音量-

public static final String 	Cmd_Type_Menu		=	"g"	;	//菜单
public static final String 	Cmd_Type_Quit		=	"h"	;	//退出
public static final String 	Cmd_Type_Dir_L		=	"i"	;	//方向左
public static final String 	Cmd_Type_Dir_R		=	"j"	;	//方向右
public static final String 	Cmd_Type_Dir_U		=	"k"	;	//方向上
public static final String 	Cmd_Type_Dir_D		=	"l"	;	//方向下

/////////////////////指令对象//////////////////////////
public static final String 	Cmd_Ctr_Object_ALL	=	"0" ;	//所有对象
public static final String 	Cmd_Ctr_Object_N1	=	"1" ;	//对象1
public static final String 	Cmd_Ctr_Object_N2	=	"2"	;	//对象2
public static final String 	Cmd_Ctr_Object_N3	=	"3"	;	//对象3
public static final String 	Cmd_Ctr_Object_N4	=	"4"	;	//对象4
public static final String 	Cmd_Ctr_Object_N5	=	"5"	;	//对象5
public static final String 	Cmd_Ctr_Object_N6	=	"6"	;	//对象6
public static final String 	Cmd_Ctr_Object_N7	=	"7"	;	//对象7
public static final String 	Cmd_Ctr_Object_N8	=	"8"	;	//对象8
public static final String 	Cmd_Ctr_Object_N9	=	"9"	;	//对象9
public static final String 	Cmd_Ctr_Object_N10	=	":"	;	//对象10
public static final String 	Cmd_Ctr_Object_N11	=	";"	;	//对象11
public static final String 	Cmd_Ctr_Object_N12	=	"<"	;	//对象12
public static final String 	Cmd_Ctr_Object_N13	=	"="	;	//对象13
public static final String 	Cmd_Ctr_Object_N14	=	">"	;	//对象14
public static final String 	Cmd_Ctr_Object_N15	=	"?"	;	//对象15
public static final String 	Cmd_Ctr_Object_N16	=	"@"	;	//对象16

public static final byte 	Cmd_Ctr_Object_ALL_byte	=	0x30 ;	//所有对象
public static final byte 	Cmd_Ctr_Object_N1_byte	=	0x31 ;	//对象1
public static final byte 	Cmd_Ctr_Object_N2_byte	=	0x32	;	//对象2
public static final byte 	Cmd_Ctr_Object_N3_byte	=	0x33	;	//对象3
public static final byte 	Cmd_Ctr_Object_N4_byte	=	0x34	;	//对象4
public static final byte 	Cmd_Ctr_Object_N5_byte	=	0x35	;	//对象5
public static final byte 	Cmd_Ctr_Object_N6_byte	=	0x36	;	//对象6
public static final byte 	Cmd_Ctr_Object_N7_byte	=	0x37	;	//对象7
public static final byte 	Cmd_Ctr_Object_N8_byte	=	0x38	;	//对象8
public static final byte 	Cmd_Ctr_Object_N9_byte	=	0x39	;	//对象9
public static final byte 	Cmd_Ctr_Object_N10_byte	=	0x3a	;	//对象10
public static final byte 	Cmd_Ctr_Object_N11_byte	=	0x3b	;	//对象11
public static final byte 	Cmd_Ctr_Object_N12_byte	=	0x3c	;	//对象12
public static final byte 	Cmd_Ctr_Object_N13_byte	=	0x3d	;	//对象13
public static final byte 	Cmd_Ctr_Object_N14_byte	=	0x3e	;	//对象14
public static final byte 	Cmd_Ctr_Object_N15_byte	=	0x3f	;	//对象15
public static final byte 	Cmd_Ctr_Object_N16_byte	=	0x40	;	//对象16

public static final byte 	Sw_Act_Close		=	'0'; 	//关
public static final byte 	Sw_Act_Open		=	'1' ;	//开

public static final byte 	Tv_Act_Push		=	'1' ;	//开
public static final byte 	Tv_Act_Null		=	'0' ;	//开
//传感器
public static final String	Sensor_Human_Effective 		= "1"	;	//人体感应检测有人入侵
public static final String	Sensor_Human_Invalid 		= 	"0"	  ;      //人体感应检测无人入侵
public static final String	Sensor_Human_Effective_Msg	= "入侵检测-有人入侵!"	;	//人体感应检测有人入侵
public static final String	Sensor_Human_Invalid_Msg	= "入侵检测-无人入侵!"	;


public static final String	Sensor_Smoke_Effective =	"1"	;	//烟感
public static final String	Sensor_Smoke_Invalid =	"0" ;		//烟感
public static final String	Sensor_Smoke_Effective_Msg =	"烟感检测-有着火!"	;	//烟感
public static final String	Sensor_Smoke_Invalid_Msg =	"烟感检测-无着火!" ;		//烟感

//电视控制指令

public static final String TV_Power_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Power_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;


public static final String TV_Mode_TV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_TV_AV+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Mode_AV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_TV_AV+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

public static final String TV_Programme_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Programme_I+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Programme_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Programme_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//音量加减
public static final String TV_VOL_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_VOL_I+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_VOL_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_VOL_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//静音，菜单
public static final String TV_VOL_Silent = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Silent+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Menu = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Menu+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

public static final String TV_Type_Quit = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Quit+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//方向
public static final String TV_Type_Dir_L = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Dir_L+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Dir_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_U = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Dir_U+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_D = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Dir_D+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_OK = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_ACK+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

//生成控制指令
public static String Dev_Cmd_Creat (String Dev_Type,String DEV_ID,String DEV_Act,String DEV_Act_Obj){
	String Dev_Cmd= "" ;
	Dev_Cmd = Command.Msg_Head+Command.Msg_Type_Ctr+Dev_Type+DEV_ID+DEV_Act+DEV_Act_Obj+Command.Msg_Foot ;
	
	  return Dev_Cmd ;
	}

//解析开关控制反馈当前状态
public static byte Msg_Dev_switch_Decode (String _mInputMsg,int Dev_State_Pos){
	byte DevswitchState = 0x00 ;
	if(_mInputMsg.length()>Dev_State_Pos){
		DevswitchState = (_mInputMsg.getBytes())[Dev_State_Pos];			
	}
	  return DevswitchState ;
	}


//解析开关控制反馈当前位置
public static byte Msg_Dev_switch_Obj_Decode (String _mInputMsg,int Dev_State_Pos){
	byte DevswitchObj = 0x00 ;
	if(_mInputMsg.length()>Dev_State_Pos	){
		DevswitchObj = (_mInputMsg.getBytes())[Dev_State_Pos-1];			
	}
	  return DevswitchObj ;
	}

//解析开关控制反馈当前操作
public static byte Msg_Dev_switch_act_Decode (String _mInputMsg,int Dev_State_Pos){
	byte Devswitchact = 0x00 ;
	if(_mInputMsg.length()>Dev_State_Pos	){
		Devswitchact = (_mInputMsg.getBytes())[Dev_State_Pos-2];			
	}
	  return Devswitchact ;
	}
/**
 * 从网络反馈信息中提取有效的空调状态信息方法
 * 
 */	
public static  String Air_State_FeadBack(String mSendMsg){
	String Air_State="";
	if(mSendMsg !=""){
		Air_State = new String(mSendMsg.getBytes(),Command.Dev_Air_State_Pos,Command.Dev_Air_State_Size) ;//截取字符串							
	}
	return Air_State;
}
}
