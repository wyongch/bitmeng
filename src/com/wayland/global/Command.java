package com.wayland.global;

import org.apache.http.util.ByteArrayBuffer;

public class Command {

//	public static final String myname = "nihao" ;
	
	
	public static final String 	Dev_ID_SIZE		=	"00000000" ; //�豸ID����
//	byte[]  BYT_DEV_ID_SIZE = new Integer(Dev_ID_SIZE).
//	public static final String BYT_DEV_ID_SIZE = new Integer(Dev_ID_SIZE).toString() ;
	public static final String Msg_Head = "bt" ;
	public static final String Msg_Foot = "m" ;
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
public static final String 	Dev_Type_Four_SW	=	"2"	;	//�ļ�����
public static final String 	Dev_Type_Sixteen_SW	=	"3"	;	//ʮ��������
public static final String 	Dev_Type_TV			=	"4"	;	//����ң��
public static final String 	Dev_Type_Air		=	"5"	;	//�յ�ң��
public static final String 	Dev_Type_IR			=	"6"	;	//���������Ӧ
public static final String 	Dev_Type_Gas		=	"7"	;	//Σ��������
public static final String 	Dev_Type_CCD		=	"8"	;	//����ͷ
public static final String 	Dev_Type_STB		=	"9"	;	//����
public static final String 	Dev_Type_Oth		=	"a"	;	//����
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
	
	
	
}
