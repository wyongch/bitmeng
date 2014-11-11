package com.young.frame;

import com.wayland.global.Command;

import android.app.Activity;
import android.os.Bundle;

public class TVActivity extends Activity {
	
	private static final String TV_Power_on = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String TV_Power_off = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;


	private static final String TV_Mode_TV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String TV_Mode_AV = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

	private static final String TV_Programme_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String TV_Programme_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

	private static final String TV_VOL_I = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String TV_VOL_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

	private static final String TV_VOL_Silent = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String TV_Type_Menu = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;

	private static final String TV_Type_Quit = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	
	private static final String TV_Type_Dir_L = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String TV_Type_Dir_R = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Open+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String TV_Type_Dir_U = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String TV_Type_Dir_d = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;
	private static final String TV_Type_OK = Command.Msg_Head+Command.Msg_Type_Ctr+Command.Dev_Type_TV+Command.Dev_ID_SIZE+Command.Cmd_Type_Close+Command.Cmd_Ctr_Object_N1+Command.Msg_Foot ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tv_layout);
	}


}
