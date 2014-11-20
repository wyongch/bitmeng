package com.wayland.global;

import org.apache.http.util.ByteArrayBuffer;

public class Command {

//	public static final String myname = "nihao" ;
	
	
	public static final String 	Dev_ID_SIZE		=	"00000003" ; //�豸ID����
	public static final String 	Dev_Smoke_ID	=	"00000006" ; //�豸ID����	
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
////////////////////��Ϣ����////////////////////////////
public static final String 	Msg_Type_Register	=	"1" ;	//��֤��Ϣ
public static final String		Msg_Type_Ctr	=	"2" ;	//������Ϣ
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
public static final String 	Dev_Type_TV			=	"4"	;	//����ң��
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
public static final String 	Cmd_Type_Windspeed	=	"6"	;	//����
public static final String 	Cmd_Type_Windshifty	=	"7"	;	//�ڷ�
public static final String 	Cmd_Type_Time		=	"8"	;	//��ʱ
public static final String 	Cmd_Type_Programme_I=	"9"	;	//��Ŀ+
public static final String 	Cmd_Type_Programme_R=	"a"	;	//��Ŀ-
public static final String		Cmd_Type_TV_AV	=		"b";		//av/tv
//public static final String 	Cmd_Type_TV		=		'b'	;	//tv
//public static final String 	Cmd_Type_AV		=		'c'	;	//av
public static final String 	Cmd_Type_VOL_I		=	"d"		;//����+
public static final String 	Cmd_Type_VOL_R		=	"e"	;	//����-
public static final String 	Cmd_Type_Silent		=	"f"	;	//����
public static final String 	Cmd_Type_Menu		=	"g"	;	//�˵�
public static final String 	Cmd_Type_Quit		=	"h"	;	//�˳�
public static final String 	Cmd_Type_Dir_L		=	"i"	;	//������
public static final String 	Cmd_Type_Dir_R		=	"j"	;	//������
public static final String 	Cmd_Type_Dir_U		=	"k"	;	//������
public static final String 	Cmd_Type_Dir_D		=	"l"	;	//������
public static final String 	Cmd_Type_ACK		=	"m"	;	//ȷ��
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
public static final String 	Cmd_Ctr_Object_N10	=	"a"	;	//����10
public static final String 	Cmd_Ctr_Object_N11	=	"b"	;	//����11
public static final String 	Cmd_Ctr_Object_N12	=	"c"	;	//����12
public static final String 	Cmd_Ctr_Object_N13	=	"d"	;	//����13
public static final String 	Cmd_Ctr_Object_N14	=	"e"	;	//����14
public static final String 	Cmd_Ctr_Object_N15	=	"f"	;	//����15
public static final String 	Cmd_Ctr_Object_N16	=	"g"	;	//����16



//������
public static final String	Sensor_Human_Effective = "1"	;	//�����Ӧ�����������
public static final String	Sensor_Human_Invalid = 	"0"	  ;      //�����Ӧ�����������
public static final String	Sensor_Human_Effective_Msg = "�����Ӧ���--��������"	;	//�����Ӧ�����������
public static final String	Sensor_Human_Invalid_Msg = "�����Ӧ���--��������"	;


public static final String	Sensor_Smoke_Effective =	"1"	;	//�̸�
public static final String	Sensor_Smoke_Invalid =	"0" ;		//�̸�
public static final String	Sensor_Smoke_Effective_Msg =	"�̸м�����Ż�"	;	//�̸�
public static final String	Sensor_Smoke_Invalid_Msg =	"�̸м�����Ż�" ;		//�̸�


	
	
//�յ�����ָ�� 
//���ƶ���N1��Ӧ���ǵ�Դ
public static final String Air_Command_Power_Open = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Open+ Cmd_Ctr_Object_N1+ Msg_Foot ;
public static final String Air_Command_Power_Close = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Open+ Cmd_Ctr_Object_N1+ Msg_Foot ;
//���ƶ���N2��Ӧ����ģʽ
public static final String Air_Command_Mode_Cool = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Air_Cool+ Cmd_Ctr_Object_N2+ Msg_Foot ;
public static final String Air_Command_Mode_Heat = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Air_HEAT+ Cmd_Ctr_Object_N2+ Msg_Foot ;
//���ƶ���N3��Ӧ�����¶�
public static final String Air_Command_Temp_Increase = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_TEMP_I+ Cmd_Ctr_Object_N3+ Msg_Foot ;
public static final String Air_Command_Temp_Reduce = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_TEMP_R+ Cmd_Ctr_Object_N3+ Msg_Foot ;
//���ƶ���N4��Ӧ���Ƿ���
public static final String Air_Command_Windspeed = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Open+ Cmd_Ctr_Object_N4+ Msg_Foot ;
public static final String Air_Command_Power_Windshifty = Msg_Head+ Msg_Type_Ctr+ Dev_Type_Air+ Dev_ID_SIZE+ Cmd_Type_Open+ Cmd_Ctr_Object_N4+ Msg_Foot ;


//���ӿ���ָ��

public static final String TV_Power_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Power_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;


public static final String TV_Mode_TV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_TV_AV+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Mode_AV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_TV_AV+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

public static final String TV_Programme_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Programme_I+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Programme_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Programme_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//�����Ӽ�
public static final String TV_VOL_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_VOL_I+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_VOL_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_VOL_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//�������˵�
public static final String TV_VOL_Silent = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Silent+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Menu = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Menu+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

public static final String TV_Type_Quit = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Quit+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
//����
public static final String TV_Type_Dir_L = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Dir_L+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Dir_R+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_U = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Dir_U+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_D = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Dir_D+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
public static final String TV_Type_Dir_OK = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_ACK+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;


	
}
