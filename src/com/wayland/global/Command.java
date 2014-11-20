package com.wayland.global;

import org.apache.http.util.ByteArrayBuffer;

public class Command {

//	public static final String myname = "nihao" ;
	
	
	public static final String 	Dev_ID_SIZE		=	"00000003" ; //设备ID长度
	public static final String 	Dev_Smoke_ID	=	"00000006" ; //设备ID长度	
//	byte[]  BYT_DEV_ID_SIZE = new Integer(Dev_ID_SIZE).
//	public static final String BYT_DEV_ID_SIZE = new Integer(Dev_ID_SIZE).toString() ;
	public static final String Msg_Head = "bt" ;
	public static final String Msg_Foot = "m" ;
	
	public static final String Response_Msg_Foot = "m" ;
	public static final String Response_Msg_Head = "zt" ;
	public static final String Response_Msg_Head2 = "z" ;
	
	public static final int MESSAGESIZE = 64 ;
	public static final int Msg_Type_Pos = 0 ;
	public static final int Dev_Type_Pos = 1 ;
	public static final int Dev_ID_Pos = 2 ;
	public static final int Dev_ID_Size = 8 ;
	public static final int Dev_Sensor_State_Pos = 12 ;	
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
public static final String 	Dev_Type_Four_SW	=	"3"	;	//四键开关
public static final String 	Dev_Type_Sixteen_SW	=	"3"	;	//十六键开关
public static final String 	Dev_Type_TV			=	"4"	;	//电视遥控
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



//传感器
public static final String	Sensor_Human_Effective = "1"	;	//人体感应检测有人入侵
public static final String	Sensor_Human_Invalid = 	"0"	  ;      //人体感应检测无人入侵
public static final String	Sensor_Human_Effective_Msg = "人体感应检测--有人入侵"	;	//人体感应检测有人入侵
public static final String	Sensor_Human_Invalid_Msg = "人体感应检测--无人入侵"	;


public static final String	Sensor_Smoke_Effective =	"1"	;	//烟感
public static final String	Sensor_Smoke_Invalid =	"0" ;		//烟感
public static final String	Sensor_Smoke_Effective_Msg =	"烟感检测有着火"	;	//烟感
public static final String	Sensor_Smoke_Invalid_Msg =	"烟感检测无着火" ;		//烟感


	
	
//空调控制指令 
//控制对象N1对应的是电源
public static final String Air_Command_Power_Open = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Open+ Cmd_Ctr_Object_N1+ Msg_Foot ;
public static final String Air_Command_Power_Close = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Open+ Cmd_Ctr_Object_N1+ Msg_Foot ;
//控制对象N2对应的是模式
public static final String Air_Command_Mode_Cool = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Air_Cool+ Cmd_Ctr_Object_N2+ Msg_Foot ;
public static final String Air_Command_Mode_Heat = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Air_HEAT+ Cmd_Ctr_Object_N2+ Msg_Foot ;
//控制对象N3对应的是温度
public static final String Air_Command_Temp_Increase = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_TEMP_I+ Cmd_Ctr_Object_N3+ Msg_Foot ;
public static final String Air_Command_Temp_Reduce = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_TEMP_R+ Cmd_Ctr_Object_N3+ Msg_Foot ;
//控制对象N4对应的是风类
public static final String Air_Command_Windspeed = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Open+ Cmd_Ctr_Object_N4+ Msg_Foot ;
public static final String Air_Command_Power_Windshifty = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Open+ Cmd_Ctr_Object_N4+ Msg_Foot ;


//电视控制指令

public static final String TV_Power_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Power_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;


public static final String TV_Mode_TV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_TV_AV+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Mode_AV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_TV_AV+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

public static final String TV_Programme_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Programme_I+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Programme_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Programme_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//音量加减
public static final String TV_VOL_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_VOL_I+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_VOL_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_VOL_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//静音，菜单
public static final String TV_VOL_Silent = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Silent+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Menu = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Menu+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

public static final String TV_Type_Quit = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Quit+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//方向
public static final String TV_Type_Dir_L = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Dir_L+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Dir_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_U = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Dir_U+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_D = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Dir_D+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_OK = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_ACK+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;


	
}
