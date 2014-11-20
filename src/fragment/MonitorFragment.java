package fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import com.ipcamer.demo.StartActivity;
import com.wayland.global.Command;
import com.young.frame.LoginActivity;
import com.young.frame.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
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
	private SimpleAdapter infraredAdapter;
	private SimpleAdapter smokeAdapter;
	private List<HashMap<String, Object>> adapter_list;
	private HashMap<String, Object> adapter_map;
	private MyAdapter myAdapter;
	//infrared
	private String strInfraredName[] = { "人体红外探测1", "人体红外探测1", "人体红外探测1", "人体红外探测1" };
	private int intCameraImg[] = { R.drawable.monitor_infrared_ico,
			R.drawable.monitor_infrared_ico, R.drawable.monitor_infrared_ico,
			R.drawable.monitor_infrared_ico, };
	private int intCameraImgRight[] = { R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right };
	private ListView camera_list;
	private ListView monitor_infrared_list;
	private ListView monitor_smock_list;
	
	private ImageButton monitor_IpCamera;
	
	//smoke
	private ListView smoke_list ;
	private MyAdapter mySmokeAdapter;
	private List<HashMap<String, Object>> smoke_adapter_list ;
	private String strSmokeName[] = { "烟感探测1", "烟感探测1", "烟感探测1", "烟感探测1" };
	private int intSmokeImg[] = { R.drawable.monitor_smoke_ico,
			R.drawable.monitor_smoke_ico, R.drawable.monitor_smoke_ico,
			R.drawable.monitor_smoke_ico, };
	private int intSmokeImgRight[] = { R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right };
	
	private String []mInputMessage={"","", "", "" };		
	private StringBuffer mOutputMessage ;
	private String mInputMessage2 ;
//	private BufferedReader mBufferedReader ;
//	private InputStreamReader inputReader ;
//	private String str = null;

//	private InputStream mInputStream ;
//	private OutputStream mOutputStream ;
	private String messageString_user = "" ;
//	private String SensorStatus = "" ;
	private String[] SensorStatus = {"","","",""} ;
	private Date mDate ;

	
	//解析传感器当前状态
	private static byte Msg_Dev_Sensor_Decode (String _mInputMsg){
		byte DevSensorState = 0x00 ;
		DevSensorState = (_mInputMsg.getBytes())[Command.Dev_Sensor_State_Pos];
		  return DevSensorState ;
		}
		
//		_mInputBuffer.toString().getBytes();
		
		
		
//	}
	
	//传感器状态刷新
	private static String Dev_SensorState_Refresh (String _mInputMsg){
		String SensorState = null  ;
		if(_mInputMsg.length()>Command.Dev_Sensor_State_Pos){
		byte DevState = Msg_Dev_Sensor_Decode(_mInputMsg); //解析传感器当前状态	
		if(DevState == Command.FeadBack_Invalid){
			Log.i(TAG, "Sensor_Human_Invalid_Msg") ;
			SensorState = Command.Sensor_Human_Invalid_Msg ;
			}
			else if(DevState == Command.FeadBack_Effective){
				Log.i(TAG, "Sensor_Human_Effective_Msg") ;
				SensorState = Command.Sensor_Human_Effective_Msg ;
			}				

		}
		  return SensorState ;
		}	
	private static void showMonitorStatus (int _monitorId , char _monitorStatus){
		
		
		
		
//		switch (key) {
//		case value:
//			
//			break;
//
//		default:
//			break;
//		}
//		
//		if(_monitorStatus == '0'){
//			Log.i(TAG, "Sensor_Human_Invalid_Msg") ;
//			SensorStatus = Command.Sensor_Human_Invalid_Msg ;
//		}
//		else if(_monitorStatus == '1'){
//			Log.i(TAG, "Sensor_Human_Effective_Msg") ;
//			SensorStatus = Command.Sensor_Human_Effective_Msg ;
//		}
		
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		monitorView = layoutInflater.inflate(R.layout.fragment_monitor1, null);

		LoginActivity loginActivity = new LoginActivity() ;
//		WeakReference refer = new WeakReference(loginActivity) ;
		WeakReference  refer = new WeakReference(loginActivity) ;
		
		TabHost tabHost = (TabHost) monitorView
				.findViewById(R.id.monitor_tabhost);
		tabHost.setup(); // 实例化了tabWidget和tabContent
		//定义指示器
		

		
		TabSpec space_infrared = tabHost.newTabSpec("infrared")
				.setIndicator("人体红外检测" )
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
		mDate = new Date() ;
		 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");  
		 String formatTime = format1.format(mDate) ;
		String dateString = formatTime ;
		Log.i(TAG, "moniforFragment onCreadView");
//		LoginActivity loginActivity = new LoginActivity() ;
//		WeakReference  refer = new WeakReference(loginActivity) ;
//		mInputMessage = ((LoginActivity)refer.get()).getInputMessage(Command.Dev_Type_IR_byte,Command.DEV_Sensor_Human0_ID);
		LoginActivity loginActivity = new LoginActivity() ;
		WeakReference<LoginActivity>  refer = new WeakReference<LoginActivity>(loginActivity) ;
		mInputMessage[0] = LoginActivity.getInputMessage(Command.Dev_Type_IR_byte,Command.DEV_Sensor_Human0_ID);
		mInputMessage[1] = LoginActivity.getInputMessage(Command.Dev_Type_IR_byte,Command.DEV_Sensor_Human1_ID);
		mInputMessage[2] = LoginActivity.getInputMessage(Command.Dev_Type_IR_byte,Command.DEV_Sensor_Human2_ID);
		mInputMessage[3] = LoginActivity.getInputMessage(Command.Dev_Type_IR_byte,Command.DEV_Sensor_Human3_ID);	
		//		Log.i(TAG, "this is in onCreatView mInputMessage2" + mInputMessage2.toString()) ;

		Log.i(TAG, "this is in onCreatView mInputMessage:" + mInputMessage[0]) ;
		
//		Log.i(TAG,"this is in onCreatView mInputMessage" + mInputMessage.toString()) ;
		Log.i(TAG, "call moniforFragment onCreadView");

		
		
		ViewGroup p = (ViewGroup) monitorView.getParent();

		if (p != null) {
			p.removeAllViewsInLayout();
		}
		
		for(int i = 0; i < mInputMessage.length; i++){
			SensorStatus[i]=Dev_SensorState_Refresh(mInputMessage[i]);	//传感器状态刷新
		}
		
		monitor_IpCamera = (ImageButton) monitorView
				.findViewById(R.id.monitor_btn_camera);
		monitor_IpCamera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getActivity(), StartActivity.class));
			}
		});
		
		

		
		monitor_infrared_list = (ListView) monitorView
				.findViewById(R.id.monitor_infrared_list);
		
		//红外测试数据
		adapter_list = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < intCameraImg.length; i++) {
			adapter_map = new HashMap<String, Object>();
			adapter_map.put("id", i) ;
			adapter_map.put("cameraImg", intCameraImg[i]);
			adapter_map.put("cameraName", strInfraredName[i]);
			adapter_map.put("cameraStatus", SensorStatus[i] + dateString) ;
			adapter_map.put("cameraImgRight", intCameraImgRight[i]);
			adapter_list.add(adapter_map);
		
		}
		
		//烟感数据链表
		smoke_adapter_list = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < intCameraImg.length; i++) {
			HashMap<String, Object> smoke_map = new HashMap<String, Object>();
			smoke_map.put("smoke_id", i) ;
			smoke_map.put("intSmokeImg", intSmokeImg[i]);
			smoke_map.put("strSmokeName", strSmokeName[i]);
			smoke_map.put("smokeStatus", SensorStatus + dateString) ;
			smoke_map.put("intSmokeImgRight", intSmokeImgRight[i]);
			smoke_adapter_list.add(smoke_map);
		
		}
			

		//红外Adapter
		infraredAdapter = new SimpleAdapter(getActivity(), adapter_list,
				R.layout.monitor_listview_item, new String[] { "cameraImg",
						"cameraName", "cameraStatus" , "cameraImgRight" }, new int[] {
						R.id.monitor_listview_item_img,
						R.id.monitor_listview_item_tv,
						R.id.monitor_listview_item_tv_status ,
						R.id.monitor_listview_item_right_img });
		// myAdapter = new MyAdapter(getActivity(), strCameraName, intCameraImg)
		
		
	
		//设置人体探测
		monitor_infrared_list.setAdapter(infraredAdapter);
		
		
		//烟感
		smoke_list = (ListView)monitorView.findViewById(R.id.monitor_smoke_list) ;
		
		//
		smokeAdapter = new SimpleAdapter(getActivity(), smoke_adapter_list,
				R.layout.monitor_listview_smoke_item, new String[] { "intSmokeImg",
			"strSmokeName", "smokeStatus" , "intSmokeImgRight" }, new int[] {
			R.id.monitor_listview_item_img,
			R.id.monitor_listview_item_tv,
			R.id.monitor_listview_item_tv_status ,
			R.id.monitor_listview_item_right_img });
		smoke_list.setAdapter(smokeAdapter);
		
		return monitorView;
	}

}
