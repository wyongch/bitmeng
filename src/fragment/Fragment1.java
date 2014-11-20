package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.young.frame.MyImageView;
import com.young.frame.R;

public class Fragment1 extends Fragment implements OnClickListener {

	private static final String TAB = "Fragment1" ;
	private View mainView;
	
	private ImageButton index_info_btn_out, index_info_btn_fire, 
	index_info_btn_cloud, index_info_btn_alarm, index_info_btn_other ;
	private ImageButton index_scenes_btn_sleep, index_scenes_btn_getup,  index_scenes_btn_outdoor,  index_scenes_btn_gohome ;
	 
	//状态测试数据
	//获取状态
	private String index_status_info = "1" ;
	//比较状态
	private String index_status_info_normal = "1" ;
	private String index_status_info_warn = "2" ;
	
	//定义设备类型
	private String index_device_type ;
	//执行定时任务
//	private Handler mHandler ;
	Handler mHandler ;
	Runnable mRunnable ;
	void findView(){
		//提示信息按钮
		index_info_btn_out = (ImageButton)mainView.findViewById(R.id.index_btn_out) ;
		index_info_btn_fire = (ImageButton)mainView.findViewById(R.id.index_btn_fire) ;
		index_info_btn_cloud = (ImageButton)mainView.findViewById(R.id.index_btn_cloud) ;
		index_info_btn_alarm = (ImageButton)mainView.findViewById(R.id.index_btn_alarm) ;
		index_info_btn_other = (ImageButton)mainView.findViewById(R.id.index_btn_other) ;
		
		index_info_btn_out.setOnClickListener(this);
		index_info_btn_fire.setOnClickListener(this);
		index_info_btn_cloud.setOnClickListener(this);
		index_info_btn_alarm.setOnClickListener(this);
		index_info_btn_other.setOnClickListener(this);
		//场景按钮
		index_scenes_btn_sleep = (ImageButton)mainView.findViewById(R.id.index_btn_sleep) ;
		index_scenes_btn_getup = (ImageButton)mainView.findViewById(R.id.index_btn_getup) ;
		index_scenes_btn_outdoor = (ImageButton)mainView.findViewById(R.id.index_btn_outdoor) ;
		index_scenes_btn_gohome = (ImageButton)mainView.findViewById(R.id.index_btn_gohome) ;
		index_scenes_btn_sleep.setOnClickListener(this);
		index_scenes_btn_getup.setOnClickListener(this);
		index_scenes_btn_outdoor.setOnClickListener(this);
		index_scenes_btn_gohome.setOnClickListener(this);
	}
	
	//获取设备类型
	String getDeviceType(){
		
		return  index_device_type ;
	}
	
	void changeStatusImage(ImageButton _imageButton, int _resourceId ){
//		if(_statusCode.equals(object)){
//			
//		}
		_imageButton.setImageResource(_resourceId);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mainView = inflater.inflate(R.layout.fragment_index,null);
		findView() ;
		
		 mHandler = new Handler() ;
		 mRunnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				Log.i(TAB, "this is TimerTask") ;
				
				
				
				if(index_status_info.equals(index_status_info_normal)){
//					index_info_btn_out.setImageResource(R.drawable.index_ib_alarm);
					changeStatusImage(index_info_btn_out, R.drawable.index_ib_alarm);
				}else if(index_status_info.equals(index_status_info_warn)){
					changeStatusImage(index_info_btn_out, R.drawable.index_ib_fire);
				}
				
				mHandler.postDelayed(this, 2000) ;
//				boolean flag = true ;
//				while (flag) {
//					
//				}
								
			}
		};		
		
		//设置单击事件响应
		

	}
	
	
	
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
		super.onPause();
		mHandler.removeCallbacks(mRunnable);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup p = (ViewGroup)mainView.getParent();
		
		if(p != null)
		{ 
			p.removeAllViewsInLayout();
		}
		
		//启动定时任务
		mHandler.postDelayed(mRunnable, 2000) ;
		
		
		return mainView;
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int view_id = v.getId();
		switch (view_id) {
		case R.id.index_btn_sleep:
			Toast.makeText(getActivity(), "你选择了睡觉场景", Toast.LENGTH_SHORT).show();

			break;
		case R.id.index_btn_getup:
			Toast.makeText(getActivity(), "你选择了起床场景", Toast.LENGTH_SHORT).show();

			break;
		case R.id.index_btn_outdoor:
			Toast.makeText(getActivity(), "你选择了出门场景", Toast.LENGTH_SHORT).show();

			break;
		case R.id.index_btn_gohome:
			Toast.makeText(getActivity(), "你选择了回家场景", Toast.LENGTH_SHORT).show();

			break;
		case R.id.index_btn_out:

			break;
		case R.id.index_btn_fire:

			break;
		case R.id.index_btn_cloud:

			break;
		case R.id.index_btn_alarm:

			break;
		case R.id.index_btn_other:

			break;

		default:
			break;
		}
	}
	
	
	
	
	
}
