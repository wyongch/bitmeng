package com.wayland.global;

import org.apache.http.util.ByteArrayBuffer;

public class Command {

//	public static final String myname = "nihao" ;
	
	
	public static final String 	Dev_ID_SIZE		=	"00000000" ; //设备ID长度
//	byte[]  BYT_DEV_ID_SIZE = new Integer(Dev_ID_SIZE).
//	public static final String BYT_DEV_ID_SIZE = new Integer(Dev_ID_SIZE).toString() ;
	public static final String Msg_Head = "bt" ;
	public static final String Msg_Foot = "m" ;
////////////////////消息类型////////////////////////////
public static final String 	Msg_Type_Register	=	"1" ;	//认证消息
public static final String		Msg_Type_Ctr	=	"2" ;	//控制消息
public static final String 	Msg_Type_Upload		=	"3" ;	//升级消息
public static final String 	Msg_Type_State		=	"4" ;	//状态上报
public static final String 	Msg_Type_ListDev	=	"5"	 ;	//获取在线设备列表
public static final String 	Msg_Type_AddDev		=	"6"	 ;	//设备添加消息
public static final String 	Msg_Type_DelDEV		=	"7" ;	//设备删除消息
public static final String 	Msg_Type_IR_Learn	=	"8" ;	//红外学习消息
////////////////////设备类型////////////////////////////
public static final String 	Dev_Type_One_SW		=	"1"	;	//单键开关
public static final String 	Dev_Type_Four_SW	=	"2"	;	//四键开关
public static final String 	Dev_Type_Sixteen_SW	=	"3"	;	//十六键开关
public static final String 	Dev_Type_TV			=	"4"	;	//电视遥控
public static final String 	Dev_Type_Air		=	"5"	;	//空调遥控
public static final String 	Dev_Type_IR			=	"6"	;	//红外人体感应
public static final String 	Dev_Type_Gas		=	"7"	;	//危险气体检测
public static final String 	Dev_Type_CCD		=	"8"	;	//摄像头
public static final String 	Dev_Type_STB		=	"9"	;	//其他
public static final String 	Dev_Type_Oth		=	"a"	;	//其他
/////////////////////指令类型//////////////////////////
public static final String 	Cmd_Type_Close		=	"0"; 	//关
public static final String 	Cmd_Type_Open		=	"1" ;	//开
public static final String 	Cmd_Type_TEMP_I		=	"2"	;	//温度+
public static final String 	Cmd_Type_TEMP_R		=	"3"	;	//温度-
public static final String 	Cmd_Type_Air_Cool	=	"4"	;	//制冷模式
public static final String 	Cmd_Type_Air_HEAT	=	"5"	;	//制热模式
public static final String 	Cmd_Type_Windspeed	=	"6"	;	//风速
public static final String 	Cmd_Type_Windshifty	=	"7"	;	//摆风
public static final String 	Cmd_Type_Time		=	"8"	;	//定时
public static final String 	Cmd_Type_Programme_I=	"9"	;	//节目+
public static final String 	Cmd_Type_Programme_R=	"a"	;	//节目-
public static final String		Cmd_Type_TV_AV	=		"b";		//av/tv
//public static final String 	Cmd_Type_TV		=		'b'	;	//tv
//public static final String 	Cmd_Type_AV		=		'c'	;	//av
public static final String 	Cmd_Type_VOL_I		=	"d"		;//音量+
public static final String 	Cmd_Type_VOL_R		=	"e"	;	//音量-
public static final String 	Cmd_Type_Silent		=	"f"	;	//静音
public static final String 	Cmd_Type_Menu		=	"g"	;	//菜单
public static final String 	Cmd_Type_Quit		=	"h"	;	//退出
public static final String 	Cmd_Type_Dir_L		=	"i"	;	//方向左
public static final String 	Cmd_Type_Dir_R		=	"j"	;	//方向右
public static final String 	Cmd_Type_Dir_U		=	"k"	;	//方向上
public static final String 	Cmd_Type_Dir_D		=	"l"	;	//方向下
public static final String 	Cmd_Type_ACK		=	"m"	;	//确认
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
public static final String 	Cmd_Ctr_Object_N10	=	"a"	;	//对象10
public static final String 	Cmd_Ctr_Object_N11	=	"b"	;	//对象11
public static final String 	Cmd_Ctr_Object_N12	=	"c"	;	//对象12
public static final String 	Cmd_Ctr_Object_N13	=	"d"	;	//对象13
public static final String 	Cmd_Ctr_Object_N14	=	"e"	;	//对象14
public static final String 	Cmd_Ctr_Object_N15	=	"f"	;	//对象15
public static final String 	Cmd_Ctr_Object_N16	=	"g"	;	//对象16
	
	
	
}
