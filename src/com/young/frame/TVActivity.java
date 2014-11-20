package com.young.frame;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;

import com.wayland.global.Command;

import android.app.Activity;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TVActivity extends Activity implements OnClickListener {
	public ImageButton tv_power , tv_program_increase , tv_program_reduce , tv_mode ,tv_voice_increase ,tv_voice_reduce ,tv_voice_silence ,tv_btn_quit ;
	public ImageButton	vt_menu_left ,vt_menu_right ,vt_menu_up ,vt_menu_down ,vt_menu_ok;
	
	private OutputStream mOutPut ;
	private Vibrator mVibrator ;
	private int vibratorTime = 200 ;
	
	Boolean mIsTVOpen =false ;
	
	void findView (){
		 tv_power = (ImageButton)findViewById(R.id.tv_btn_power) ;
		 tv_program_increase = (ImageButton)findViewById(R.id.tv_btn_program_increase) ;
		 tv_program_reduce = (ImageButton)findViewById(R.id.tv_btn_program_reduce) ;
		 tv_mode = (ImageButton)findViewById(R.id.tv_btn_mode) ;
		 tv_voice_increase = (ImageButton)findViewById(R.id.tv_voice_increase) ;
		 tv_voice_reduce = (ImageButton)findViewById(R.id.tv_voice_reduce) ;
		 tv_voice_silence = (ImageButton)findViewById(R.id.tv_voice_silence) ;
		 tv_btn_quit = (ImageButton)findViewById(R.id.tv_btn_quit) ;
		 vt_menu_left = (ImageButton)findViewById(R.id.tv_menu_left) ;
		 vt_menu_right = (ImageButton)findViewById(R.id.tv_menu_right) ;
		 vt_menu_up = (ImageButton)findViewById(R.id.tv_menu_up) ;
		 vt_menu_down = (ImageButton)findViewById(R.id.tv_menu_down) ;
		 vt_menu_ok = (ImageButton)findViewById(R.id.tv_menu_ok) ;
//		 tv_power = (ImageButton)findViewById(R.id.tv_btn_power) ;
		 
		 tv_power.setOnClickListener(this);
		 tv_program_increase.setOnClickListener(this);
		 tv_program_reduce.setOnClickListener(this);
		 tv_mode.setOnClickListener(this);
		 tv_voice_increase.setOnClickListener(this);
		 tv_voice_reduce.setOnClickListener(this);
		 tv_voice_silence.setOnClickListener(this);
		 tv_btn_quit.setOnClickListener(this);
		 vt_menu_left.setOnClickListener(this);
		 vt_menu_right.setOnClickListener(this);
		 vt_menu_up.setOnClickListener(this);
		 vt_menu_down.setOnClickListener(this);
		 vt_menu_ok.setOnClickListener(this);
		 
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tv_layout);
		findView();
		mVibrator = (Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE) ;
		
		LoginActivity mLoginActivity = new LoginActivity() ;
		WeakReference reference = new WeakReference(mLoginActivity) ;
		mOutPut = ((LoginActivity)reference.get()).getSocketOutPutStream() ;
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewId = v.getId() ;
		switch (viewId) {
		case R.id.tv_btn_power:
			mVibrator.vibrate(vibratorTime);
			if(!mIsTVOpen){
				try {
					mOutPut.write(Command.TV_Power_on.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mIsTVOpen = true ;
			}else{
				try {
					mOutPut.write(Command.TV_Power_off.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mIsTVOpen = false ;
			}
			
			

			break;
		case R.id.tv_btn_mode:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_Power_on.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_btn_program_increase:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_Programme_I.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_btn_program_reduce:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_Programme_R.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_voice_increase:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_VOL_I.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_voice_reduce:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_VOL_R.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_voice_silence:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_VOL_Silent.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_menu_left:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_Type_Dir_L.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_menu_right:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_Type_Dir_R.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_menu_up:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_Type_Dir_U.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_menu_down:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_Type_Dir_D.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_menu_ok:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_Type_Dir_OK.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.tv_btn_quit:
			mVibrator.vibrate(vibratorTime);

			try {
				mOutPut.write(Command.TV_Type_Quit.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
//		case R.id.tv_btn_power:
//
//			break;

		default:
			break;
		}
	}


}
