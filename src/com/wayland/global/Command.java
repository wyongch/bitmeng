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

	public static final int Register_requests_num = 5 ; //ͬһ�˺������ظ������½����
	public static final int InputStream_msg = 111 ; //���յ���Чsocket��������Ϣ��־
	public static final int RegisterPassed_msg = 221 ; //���յ�ע����������Ϣ��־
	public static final int RegisterFailed_msg = 220 ; //���յ�ע����������Ϣ��־	
	
	public static final int Air_Run_status_Pos = 0;			//����״̬������Ϣ--����״̬��λ��
	public static final int Air_Runmode_status_Pos = 1;		//����״̬������Ϣ--����ģʽ��λ��
	public static final int Air_Windmode_status_Pos = 2;	//����״̬������Ϣ--����ģʽ��λ��
	public static final int Air_time_status_Pos = 3;		//����״̬������Ϣ--��ʱģʽ��λ��
	public static final int Air_temp_value_Pos = 4;			//����״̬������Ϣ--�¶�ֵ��λ��
	
	public static final int Dev_SW_Obj_all = 0 ; // ���ض�������
	public static final int Dev_SW_Obj_1 = 1 ; // ���ض���1
	public static final int Dev_SW_Obj_2 = 2 ; // ���ض���2
	public static final int Dev_SW_Obj_3 = 3 ; // ���ض���3
	public static final int Dev_SW_Obj_4 = 4 ; // ���ض���4
	public static final int Dev_SW_Obj_5 = 5 ; // ���ض���5
	public static final int Dev_SW_Obj_6 = 6 ; // ���ض���6
	public static final int Dev_SW_Obj_7 = 7 ; // ���ض���7
	public static final int Dev_SW_Obj_8 = 8 ; // ���ض���8
	public static final int Dev_SW_Obj_9 = 9 ; // ���ض���9
	public static final int Dev_SW_Obj_10 = 10 ; // ���ض���10
	public static final int Dev_SW_Obj_11 = 11 ; // ���ض���11
	public static final int Dev_SW_Obj_12 = 12 ; // ���ض���12
	public static final int Dev_SW_Obj_13 = 13 ; // ���ض���13
	public static final int Dev_SW_Obj_14 = 14 ; // ���ض���14
	public static final int Dev_SW_Obj_15 = 15 ; // ���ض���15
	public static final int Dev_SW_Obj_16 = 16 ; // ���ض���16
////////////////////��Ϣ����////////////////////////////
public static final String 	Msg_Type_Register	=	"1" ;	//��֤��Ϣ
public static final String	Msg_Type_Ctr		=	"2" ;	//������Ϣ
public static final String 	Msg_Type_Upload		=	"3" ;	//������Ϣ
public static final String 	Msg_Type_State		=	"4" ;	//״̬�ϱ�
public static final String 	Msg_Type_ListDev	=	"5"	 ;	//��ȡ�����豸�б�
public static final String 	Msg_Type_AddDev		=	"6"	 ;	//�豸�����Ϣ
public static final String 	Msg_Type_DelDEV		=	"7" ;	//�豸ɾ����Ϣ
public static final String 	Msg_Type_IR_Learn	=	"8" ;	//����ѧϰ��Ϣ
////////////////////�豸����////////////////////////////
public static final String 	Dev_Type_One_SW		=	"1"	;	//��������
public static final String 	Dev_Type_Four_SW	=	"3"	;	//�ļ�����
public static final String 	Dev_Type_Sixteen_SW	=	"3"	;	//ʮ��������
public static final String 	Dev_Type_TV			=	"4"	;	//����/������ң��
public static final String 	Dev_Type_Air		=	"5"	;	//�յ�ң��
public static final String 	Dev_Type_IR			=	"6"	;	//���������Ӧ
public static final String 	Dev_Type_Gas		=	"7"	;	//Σ��������
public static final String 	Dev_Type_CCD		=	"8"	;	//����ͷ
public static final String 	Dev_Type_STB		=	"9"	;	//����
public static final String 	Dev_Type_Oth		=	"a"	;	//����

////////////////////����״̬////////////////////////////
public static final byte 	FeadBack_Invalid	=	'0'	;	//��Ч
public static final byte 	FeadBack_Effective	=	'1'	;	//��Ч

////////////////////�豸����////////////////////////////
public static final byte 	Dev_Type_One_SW_byte		=	'1'	;	//��������
public static final byte 	Dev_Type_Four_SW_byte		=	'2'	;	//�ļ�����
public static final byte 	Dev_Type_Sixteen_SW_byte	=	'3'	;	//ʮ��������
public static final byte 	Dev_Type_TV_byte			=	'4'	;	//����ң��
public static final byte 	Dev_Type_Air_byte			=	'5'	;	//�յ�ң��
public static final byte 	Dev_Type_IR_byte			=	'6'	;	//���������Ӧ
public static final byte 	Dev_Type_Gas_byte			=	'7'	;	//Σ��������
public static final byte 	Dev_Type_CCD_byte			=	'8'	;	//����ͷ
public static final byte 	Dev_Type_STB_byte			=	'9'	;	//����
public static final byte 	Dev_Type_Oth_byte			=	'a'	;	//����
public static final String 	DEV_One_SW_ID	 			=	"00000001"	;		//���������豸ID
public static final String	DEV_Four_SW_ID	 			=	"00000002"	;		//�ļ������豸ID 
public static final String 	DEV_Sixteen_SW_ID			=	"00000003"	;		//ʮ��������ID 
public static final String	DEV_IRCtr_ID	 			=	"00000004"	;		//����ң���豸ID 
public static final String	DEV_Sensor_Human0_ID	 	=	"00000005"	;		//�����Ӧ�豸ID 
public static final String	DEV_Sensor_Human1_ID	 	=	"00000015"	;		//�����Ӧ�豸ID 
public static final String	DEV_Sensor_Human2_ID	 	=	"00000025"	;		//�����Ӧ�豸ID 
public static final String	DEV_Sensor_Human3_ID	 	=	"00000035"	;		//�����Ӧ�豸ID 
public static final String 	DEV_Sensor_Smoke0_ID	 	=	"00000006"	;		//�̸��豸ID 
public static final String 	DEV_Sensor_Smoke1_ID	 	=	"00000016"	;		//�̸��豸ID 
public static final String 	DEV_Sensor_Smoke2_ID	 	=	"00000026"	;		//�̸��豸ID 
public static final String 	DEV_Sensor_Smoke3_ID	 	=	"00000036"	;		//�̸��豸ID 
/////////////////////ָ������//////////////////////////
public static final String 	Cmd_Type_Close		=	"0"; 	//��
public static final String 	Cmd_Type_Open		=	"1" ;	//��
public static final String 	Cmd_Type_TEMP_I		=	"2"	;	//�¶�+
public static final String 	Cmd_Type_TEMP_R		=	"3"	;	//�¶�-
public static final String 	Cmd_Type_Air_Cool	=	"4"	;	//����ģʽ
public static final String 	Cmd_Type_Air_HEAT	=	"5"	;	//����ģʽ
public static final String 	Cmd_Type_WindVount	=	"6"	;	//ͨ��
public static final String 	Cmd_Type_Windmode	=	"7"	;	//�ڷ�
public static final String 	Cmd_Type_Time		=	"8"	;	//��ʱ

public static final String 	Cmd_Type_Tv_power	=	"0"; 	//���ӻ�����
public static final String 	Cmd_Typee_Tv_VOL_I	=	"1"	;	//���ӻ�����+
public static final String 	Cmd_Typee_Tv_VOL_R	=	"2"	;	//���ӻ�����-
public static final String	Cmd_Type_TV_AV		=		"3";	//av/tv

public static final String 	Cmd_Type_STB_power	=	"4"; 	//�����п���
public static final String 	Cmd_Type_Silent		=	"5"	;	//�����о���
public static final String 	Cmd_Type_STB_CH0	=	"C"	;	//������Ƶ��
public static final String 	Cmd_Type_STB_CH1	=	"6"	;	//������Ƶ��
public static final String 	Cmd_Type_STB_CH2	=	"7"	;	//������Ƶ��
public static final String 	Cmd_Type_STB_CH3	=	"8"	;	//������Ƶ��
public static final String 	Cmd_Type_STB_CH4	=	":"	;	//������Ƶ��
public static final String 	Cmd_Type_STB_CH5	=	";"	;	//������Ƶ��
public static final String 	Cmd_Type_STB_CH6	=	"<"	;	//������Ƶ��
public static final String 	Cmd_Type_STB_CH7	=	">"	;	//������Ƶ��
public static final String 	Cmd_Type_STB_CH8	=	"?"	;	//������Ƶ��
public static final String 	Cmd_Type_STB_CH9	=	"@"	;	//������Ƶ��
public static final String 	Cmd_Typee_STB_VOL_I	=	"A"	;	//����������+
public static final String 	Cmd_Typee_STB_VOL_R	=	"E"	;	//����������-
public static final String 	Cmd_Type_Programme_I=	"9"	;	//�����н�Ŀ+
public static final String 	Cmd_Type_Programme_R=	"="	;	//�����н�Ŀ-
public static final String 	Cmd_Type_TV			=	"F"	;	//����
public static final String 	Cmd_Type_INFO		=	"D"	;	//��Ϣ
public static final String 	Cmd_Type_ACK		=	"B"	;	//ȷ��
public static final String 	Cmd_Type_Back		=	"I"	;	//����
public static final String 	Cmd_Type_Custom1	=	"G"	;	//�Զ���1
public static final String 	Cmd_Type_Custom2	=	"H"	;	//�Զ���1

//public static final String 	Cmd_Type_TV		=		'b'	;	//tv
//public static final String 	Cmd_Type_AV		=		'c'	;	//av
public static final String 	Cmd_Type_VOL_I		=	"d"	;//����+
public static final String 	Cmd_Type_VOL_R		=	"e"	;	//����-

public static final String 	Cmd_Type_Menu		=	"g"	;	//�˵�
public static final String 	Cmd_Type_Quit		=	"h"	;	//�˳�
public static final String 	Cmd_Type_Dir_L		=	"i"	;	//������
public static final String 	Cmd_Type_Dir_R		=	"j"	;	//������
public static final String 	Cmd_Type_Dir_U		=	"k"	;	//������
public static final String 	Cmd_Type_Dir_D		=	"l"	;	//������

/////////////////////ָ�����//////////////////////////
public static final String 	Cmd_Ctr_Object_ALL	=	"0" ;	//���ж���
public static final String 	Cmd_Ctr_Object_N1	=	"1" ;	//����1
public static final String 	Cmd_Ctr_Object_N2	=	"2"	;	//����2
public static final String 	Cmd_Ctr_Object_N3	=	"3"	;	//����3
public static final String 	Cmd_Ctr_Object_N4	=	"4"	;	//����4
public static final String 	Cmd_Ctr_Object_N5	=	"5"	;	//����5
public static final String 	Cmd_Ctr_Object_N6	=	"6"	;	//����6
public static final String 	Cmd_Ctr_Object_N7	=	"7"	;	//����7
public static final String 	Cmd_Ctr_Object_N8	=	"8"	;	//����8
public static final String 	Cmd_Ctr_Object_N9	=	"9"	;	//����9
public static final String 	Cmd_Ctr_Object_N10	=	":"	;	//����10
public static final String 	Cmd_Ctr_Object_N11	=	";"	;	//����11
public static final String 	Cmd_Ctr_Object_N12	=	"<"	;	//����12
public static final String 	Cmd_Ctr_Object_N13	=	"="	;	//����13
public static final String 	Cmd_Ctr_Object_N14	=	">"	;	//����14
public static final String 	Cmd_Ctr_Object_N15	=	"?"	;	//����15
public static final String 	Cmd_Ctr_Object_N16	=	"@"	;	//����16

public static final byte 	Cmd_Ctr_Object_ALL_byte	=	0x30 ;	//���ж���
public static final byte 	Cmd_Ctr_Object_N1_byte	=	0x31 ;	//����1
public static final byte 	Cmd_Ctr_Object_N2_byte	=	0x32	;	//����2
public static final byte 	Cmd_Ctr_Object_N3_byte	=	0x33	;	//����3
public static final byte 	Cmd_Ctr_Object_N4_byte	=	0x34	;	//����4
public static final byte 	Cmd_Ctr_Object_N5_byte	=	0x35	;	//����5
public static final byte 	Cmd_Ctr_Object_N6_byte	=	0x36	;	//����6
public static final byte 	Cmd_Ctr_Object_N7_byte	=	0x37	;	//����7
public static final byte 	Cmd_Ctr_Object_N8_byte	=	0x38	;	//����8
public static final byte 	Cmd_Ctr_Object_N9_byte	=	0x39	;	//����9
public static final byte 	Cmd_Ctr_Object_N10_byte	=	0x3a	;	//����10
public static final byte 	Cmd_Ctr_Object_N11_byte	=	0x3b	;	//����11
public static final byte 	Cmd_Ctr_Object_N12_byte	=	0x3c	;	//����12
public static final byte 	Cmd_Ctr_Object_N13_byte	=	0x3d	;	//����13
public static final byte 	Cmd_Ctr_Object_N14_byte	=	0x3e	;	//����14
public static final byte 	Cmd_Ctr_Object_N15_byte	=	0x3f	;	//����15
public static final byte 	Cmd_Ctr_Object_N16_byte	=	0x40	;	//����16

public static final byte 	Sw_Act_Close		=	'0'; 	//��
public static final byte 	Sw_Act_Open		=	'1' ;	//��

public static final byte 	Tv_Act_Push		=	'1' ;	//��
public static final byte 	Tv_Act_Null		=	'0' ;	//��
//������
public static final String	Sensor_Human_Effective 		= "1"	;	//�����Ӧ�����������
public static final String	Sensor_Human_Invalid 		= 	"0"	  ;      //�����Ӧ�����������
public static final String	Sensor_Human_Effective_Msg	= "���ּ��-��������!"	;	//�����Ӧ�����������
public static final String	Sensor_Human_Invalid_Msg	= "���ּ��-��������!"	;


public static final String	Sensor_Smoke_Effective =	"1"	;	//�̸�
public static final String	Sensor_Smoke_Invalid =	"0" ;		//�̸�
public static final String	Sensor_Smoke_Effective_Msg =	"�̸м��-���Ż�!"	;	//�̸�
public static final String	Sensor_Smoke_Invalid_Msg =	"�̸м��-���Ż�!" ;		//�̸�

//���ӿ���ָ��

public static final String TV_Power_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Power_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;


public static final String TV_Mode_TV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_TV_AV+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Mode_AV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_TV_AV+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

public static final String TV_Programme_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Programme_I+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Programme_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Programme_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//�����Ӽ�
public static final String TV_VOL_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_VOL_I+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_VOL_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_VOL_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//�������˵�
public static final String TV_VOL_Silent = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Silent+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Menu = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Menu+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

public static final String TV_Type_Quit = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Quit+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//����
public static final String TV_Type_Dir_L = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Dir_L+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Dir_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_U = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Dir_U+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_D = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_Dir_D+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_OK = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.DEV_IRCtr_ID+Command.Cmd_Type_ACK+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

//���ɿ���ָ��
public static String Dev_Cmd_Creat (String Dev_Type,String DEV_ID,String DEV_Act,String DEV_Act_Obj){
	String Dev_Cmd= "" ;
	Dev_Cmd = Command.Msg_Head+Command.Msg_Type_Ctr+Dev_Type+DEV_ID+DEV_Act+DEV_Act_Obj+Command.Msg_Foot ;
	
	  return Dev_Cmd ;
	}

//�������ؿ��Ʒ�����ǰ״̬
public static byte Msg_Dev_switch_Decode (String _mInputMsg,int Dev_State_Pos){
	byte DevswitchState = 0x00 ;
	if(_mInputMsg.length()>Dev_State_Pos){
		DevswitchState = (_mInputMsg.getBytes())[Dev_State_Pos];			
	}
	  return DevswitchState ;
	}


//�������ؿ��Ʒ�����ǰλ��
public static byte Msg_Dev_switch_Obj_Decode (String _mInputMsg,int Dev_State_Pos){
	byte DevswitchObj = 0x00 ;
	if(_mInputMsg.length()>Dev_State_Pos	){
		DevswitchObj = (_mInputMsg.getBytes())[Dev_State_Pos-1];			
	}
	  return DevswitchObj ;
	}

//�������ؿ��Ʒ�����ǰ����
public static byte Msg_Dev_switch_act_Decode (String _mInputMsg,int Dev_State_Pos){
	byte Devswitchact = 0x00 ;
	if(_mInputMsg.length()>Dev_State_Pos	){
		Devswitchact = (_mInputMsg.getBytes())[Dev_State_Pos-2];			
	}
	  return Devswitchact ;
	}
/**
 * �����練����Ϣ����ȡ��Ч�Ŀյ�״̬��Ϣ����
 * 
 */	
public static  String Air_State_FeadBack(String mSendMsg){
	String Air_State="";
	if(mSendMsg !=""){
		Air_State = new String(mSendMsg.getBytes(),Command.Dev_Air_State_Pos,Command.Dev_Air_State_Size) ;//��ȡ�ַ���							
	}
	return Air_State;
}
}
