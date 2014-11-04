package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;

import com.ipcamer.demo.StartActivity;
import com.young.frame.MyImageView;
import com.young.frame.MyImageView.OnViewClickListener;
import com.young.frame.R;

public class Fragment3 extends Fragment implements OnViewClickListener {

	private View mainView;
	
	private Button bt_ipcamera ;
	
	private MyImageView mv_jock ; 
	
	private static final String TAG = "Fragment3" ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mainView = inflater.inflate(R.layout.fragment3,null);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup p = (ViewGroup)mainView.getParent();
		
		if(p != null)
		{
			p.removeAllViewsInLayout();
		}
		
		
		mv_jock = (MyImageView)mainView.findViewById(R.id.c_joke) ;
		
		mv_jock.setOnClickIntent(new OnViewClickListener() {
			
			@Override
			public void onViewClick(MyImageView view) {
				// TODO Auto-generated method stub
				Log.i(TAG, "c_joke被按下") ;
			}
		});
		
//		this.onViewClick(mv_jock);
		
		bt_ipcamera = (Button)mainView.findViewById(R.id.bt_camera) ;
		
		
		
		bt_ipcamera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity() , StartActivity.class));
			}
		});
		
		
		return mainView;
	}

	@Override
	public void onViewClick(MyImageView view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.c_joke:
			Log.i(TAG, "c_joke被按下") ;
			break;

		default:
			break;
		}
		
		Log.i(TAG, "c_joke被按下") ;
	}
}
