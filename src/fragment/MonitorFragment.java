package fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ipcamer.demo.StartActivity;
import com.young.frame.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Space;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

public class MonitorFragment extends Fragment {

	private static final String TAG = "MONITORFRAGMENT";
	private View monitorView;
	private SimpleAdapter monitorAdapter;
	private List<HashMap<String, Object>> adapter_list;
	private HashMap<String, Object> adapter_map;
	private MyAdapter myAdapter;
	private String strCameraName[] = { "人体红外探测1", "人体红外探测1", "人体红外探测1", "人体红外探测1" };
	private int intCameraImg[] = { R.drawable.monitor_infrared_ico,
			R.drawable.monitor_infrared_ico, R.drawable.monitor_infrared_ico,
			R.drawable.monitor_infrared_ico, };
	private int intCameraImgRight[] = { R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right };
	private ListView camera_list;
	private ImageButton monitor_IpCamera;
	
	//smoke
	private ListView smoke_list ;
	private MyAdapter mySmokeAdapter;
	private String strSmokeName[] = { "烟感探测1", "烟感探测1", "烟感探测1", "烟感探测1" };
	private int intSmokeImg[] = { R.drawable.monitor_smoke_ico,
			R.drawable.monitor_smoke_ico, R.drawable.monitor_smoke_ico,
			R.drawable.monitor_smoke_ico, };
	private int intSmokeImgRight[] = { R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right };
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		monitorView = layoutInflater.inflate(R.layout.fragment_monitor1, null);

		TabHost tabHost = (TabHost) monitorView
				.findViewById(R.id.monitor_tabhost);
		tabHost.setup(); // 实例化了tabWidget和tabContent
		//定义指示器
		
		
		

//		TabSpec space_camera = tabHost.newTabSpec("infrared")
//				.setIndicator("人体红外检测")
//				.setContent(R.id.monitor_camera_list);
		TabSpec space_infrared = tabHost.newTabSpec("infrared")
				.setIndicator("人体红外检测")
				.setContent(R.id.monitor_infrared_list);
		TabSpec space_smoke = tabHost.newTabSpec("smoke")
				.setIndicator("烟感探测器")
				.setContent(R.id.monitor_smoke_list);
//		tabHost.addTab(space_camera);
		tabHost.addTab(space_infrared);
		tabHost.addTab(space_smoke);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// return super.onCreateView(inflater, container, savedInstanceState);
		// 获得父类中的viewGroup，当已经存在的时候需要移除，否在在切换Fragment的时候会出现异常
		Log.i(TAG, "call moniforFragment onCreadView");

		ViewGroup p = (ViewGroup) monitorView.getParent();

		if (p != null) {
			p.removeAllViewsInLayout();
		}

		// tabDetect = new Space(context)
//		ListView view1 = (ListView) monitorView
//				.findViewById(R.id.monitor_camera_list);
//		ListView view2 = (ListView) monitorView
//				.findViewById(R.id.monitor_infrared_list);
//		ListView view3 = (ListView) monitorView
//				.findViewById(R.id.monitor_smoke_list);

		// tabHost.addTab(tabHost.newTabSpec("monitor_detect")
		// .setIndicator("人体探测")
		// .setContent((TabContentFactory)
		// getActivity().findViewById(R.id.monitor_camera_list)));
		// tabHost.addTab(tabHost.newTabSpec("monitor_infrared")
		// .setIndicator("红外感应")
		// .setContent((TabContentFactory)
		// getActivity().findViewById(R.id.monitor_camera_list)));
		// tabHost.addTab(tabHost.newTabSpec("monitor_infrared")
		// .setIndicator("烟感")
		// .setContent((TabContentFactory)
		// getActivity().findViewById(R.id.monitor_camera_list)));
		// tabHost.setCurrentTab(0);

		monitor_IpCamera = (ImageButton) monitorView
				.findViewById(R.id.monitor_btn_camera);
		monitor_IpCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), StartActivity.class));
			}
		});

		camera_list = (ListView) monitorView
				.findViewById(R.id.monitor_infrared_list);
		adapter_list = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < intCameraImg.length; i++) {
			adapter_map = new HashMap<String, Object>();
			adapter_map.put("cameraImg", intCameraImg[i]);
			adapter_map.put("cameraName", strCameraName[i]);
			adapter_map.put("cameraImgRight", intCameraImgRight[i]);
			adapter_list.add(adapter_map);
		}

		monitorAdapter = new SimpleAdapter(getActivity(), adapter_list,
				R.layout.monitor_listview_item, new String[] { "cameraImg",
						"cameraName", "cameraImgRight" }, new int[] {
						R.id.monitor_listview_item_img,
						R.id.monitor_listview_item_tv,
						R.id.monitor_listview_item_right_img });
		// myAdapter = new MyAdapter(getActivity(), strCameraName, intCameraImg)
		// ;
		camera_list.setAdapter(monitorAdapter);
		
		smoke_list = (ListView)monitorView.findViewById(R.id.monitor_smoke_list) ;
		mySmokeAdapter = new MyAdapter(getActivity(), strSmokeName, intSmokeImg) ;
		smoke_list.setAdapter(monitorAdapter);
		
		
		return monitorView;
	}

}
