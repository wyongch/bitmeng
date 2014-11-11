package com.young.frame;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AirControlActivity extends Activity implements OnClickListener {
	
	private ImageButton btn_Power ;
	private ImageButton btn_temp_up ;
	private ImageButton btn_temp_down ;
	private ImageView vi_curren_temp ;
	private Vibrator mVibrator ;
	private int[] image_temp= new int[]{R.drawable.img_temp_16 ,R.drawable.img_temp_17 ,R.drawable.img_temp_18 ,R.drawable.img_temp_19 ,R.drawable.img_temp_20 ,R.drawable.img_temp_21 ,
			R.drawable.img_temp_22 ,R.drawable.img_temp_23 ,R.drawable.img_temp_24 ,R.drawable.img_temp_25 ,R.drawable.img_temp_26 ,R.drawable.img_temp_27 ,R.drawable.img_temp_28 ,
			R.drawable.img_temp_29 ,R.drawable.img_temp_30 ,R.drawable.img_temp_31 } ;
	
	int currentid = 0 ;
	
	
	private void findView() {
		// TODO Auto-generated method stub
		btn_Power = (ImageButton)findViewById(R.id.btn_airpower);
		btn_Power.setOnClickListener(this);
		btn_temp_up = (ImageButton)findViewById(R.id.btn_temp_up);
		btn_temp_up.setOnClickListener(this);
		btn_temp_down = (ImageButton)findViewById(R.id.btn_temp_down);
		btn_temp_down.setOnClickListener(this);
		
		vi_curren_temp = (ImageView)findViewById(R.id.vi_current_temp) ;
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_air_control);
		findView();
		mVibrator = (Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE) ;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.btn_airpower :
			
			mVibrator.vibrate(200);
			
//			startActivity(new Intent(this , TVActivity.class));
			
			Log.i("wyongch", "11111111111") ;
			break ;
		case R.id.btn_temp_up :
			mVibrator.vibrate(200);
//			int currentid = vi_curren_temp.getId();
//			for(int i = 0 ; i<image_temp.length ;i++){
//				if(currentid == image_temp[i]){
//					vi_curren_temp.setImageResource(image_temp[++i]);
//					Log.i("wyongch", "wwwwwwwwww");
//				}
//			}
			
//			int currentid = 0 +currentid ;
//			if(currentid == 0){
//				vi_curren_temp.setImageResource(image_temp[0]);
//			}
			//边界值问题
			if(currentid == image_temp.length-1){
				vi_curren_temp.setImageResource(image_temp[0]);
				currentid = 0 ;
				Toast.makeText(this, "length"+image_temp.length +"currentid"+currentid , Toast.LENGTH_LONG).show();
			}
			
			else if(currentid <image_temp.length ){
				
			vi_curren_temp.setImageResource(image_temp[++currentid]);
			Toast.makeText(this, "length"+image_temp.length +"currentid"+currentid , Toast.LENGTH_LONG).show();
			}
//			else if(currentid == image_temp.length) {
//				vi_curren_temp.setImageResource(image_temp[0]);
//			}
			
			break ;
		case R.id.btn_temp_down :
			mVibrator.vibrate(200);
			if (currentid == 0){
				vi_curren_temp.setImageResource(image_temp[currentid]);
				currentid = currentid+1 ;
			}
			else if(currentid>0){
				if(currentid <image_temp.length ){
					
					vi_curren_temp.setImageResource(image_temp[--currentid]);
					
					}
			}
			
			Toast.makeText(this, "length"+image_temp.length +"currentid"+currentid , Toast.LENGTH_LONG).show();
			break ;
	
		}
		
		
	}

}
