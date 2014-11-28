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

import android.annotation.SuppressLint;
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

@SuppressLint("SimpleDateFormat")
public class MonitorFragment extends Fragment {

	private static final String TAG = "MONITORFRAGMENT";
	private View monitorView;
	private SimpleAdapter infraredAdapter;
	private SimpleAdapter smokeAdapter;
	private List<HashMap<String, Object>> adapter_list;
	private HashMap<String, Object> adapter_map;
	private MyAdapter myAdapter;
	//infrared
	private String strInfraredName[] = { "�������̽�� 1", "�������̽�� 2", "�������̽�� 3", "�������̽�� 4" };
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
	private String strSmokeName[] = { "Σ�������� 1", "Σ�������� 2", "Σ�������� 3", "Σ�������� 4" };
	private int intSmokeImg[] = { R.drawable.monitor_smoke_ico,
			R.drawable.monitor_smoke_ico, R.drawable.monitor_smoke_ico,
			R.drawable.monitor_smoke_ico, };
	private int intSmokeImgRight[] = { R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right,
			R.drawable.monitor_infrared_ico_right };
	
	private String []mInfraredInputMessage={"","", "", "" };		
	private String []mSmokeInputMessage={"","", "", "" };
	private StringBuffer mOutputMessage ;
	private String mInputMessage2 ;
	private String messageString_user = "" ;
//	private String SensorStatus = "" ;
	private String[] SmokeSensorStatus = {"","","",""} ;
	private String[] InfraredSensorStatus = {"","","",""} ;
	private Date mDate ;
	private String dateString="";

	private static void showMonitorStatus (int _monitorId , char _monitorStatus){
		
	}
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		monitorView = layoutInflater.inflate(R.layout.fragment_monitor1, null);

		TabHost tabHost = (TabHost) monitorView
				.findViewById(R.id.monitor_tabhost);
		tabHost.setup(); // ʵ������tabWidget��tabContent
		//����ָʾ��
		

		
		TabSpec space_infrared = tabHost.newTabSpec("infrared")
				.setIndicator("���������" )
				.setContent(R.id.monitor_infrared_list);
		TabSpec space_smoke = tabHost.newTabSpec("smoke")
				.setIndicator("Σ��������")
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
		// ��ø����е�viewGroup�����Ѿ����ڵ�ʱ����Ҫ�Ƴ����������л�Fragment��ʱ�������쳣
		mDate = new Date() ;
		 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		 String formatTime = format1.format(mDate) ;
		 dateString = formatTime ;
//		Log.i(TAG, "moniforFragment onCreadView");
	
		//		Log.i(TAG, "this is in onCreatView mInputMessage2" + mInputMessage2.toString()) ;

		Log.i(TAG, "this is in onCreatView mInputMessage:" + mInfraredInputMessage[0]) ;
		
//		Log.i(TAG,"this is in onCreatView mInputMessage" + mInputMessage.toString()) ;
//		Log.i(TAG, "call moniforFragment onCreadView");

		
		
		ViewGroup p = (ViewGroup) monitorView.getParent();

		if (p != null) {
			p.removeAllViewsInLayout();
		}
	
		/**
		 * �����������ͷ
		 * 
		 */
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
		
		//�����������
		adapter_list = new ArrayList<HashMap<String, Object>>();
		//�̸���������
		smoke_adapter_list = new ArrayList<HashMap<String, Object>>();
		Sensor_InputMessage();
		Sensor_Refresh_State ();
		
		//����Adapter
		infraredAdapter = new SimpleAdapter(getActivity(), adapter_list,
				R.layout.monitor_listview_item, new String[] { "cameraImg",
						"cameraName", "cameraStatus" , "cameraImgRight" }, new int[] {
						R.id.monitor_listview_item_img,
						R.id.monitor_listview_item_tv,
						R.id.monitor_listview_item_tv_status ,
						R.id.monitor_listview_item_right_img });
		// myAdapter = new MyAdapter(getActivity(), strCameraName, intCameraImg)
		
		
	
		//��������̽��
		monitor_infrared_list.setAdapter(infraredAdapter);
		
		
		//�̸�
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

	
	/**
	 *�Ӻ�̨��ȡ�����Ϣ
	 * 
	 */
	private void Sensor_InputMessage(){
		Log.i(TAG, "this is Sensor_InputMessage:" + mInfraredInputMessage[0]) ;
		mInfraredInputMessage[0] = LoginActivity.getInputMessage(Command.Dev_Type_IR_byte,Command.Cmd_Ctr_Object_N1_byte,Command.DEV_Sensor_Human0_ID);
		mInfraredInputMessage[1] = LoginActivity.getInputMessage(Command.Dev_Type_IR_byte,Command.Cmd_Ctr_Object_N1_byte,Command.DEV_Sensor_Human1_ID);
		mInfraredInputMessage[2] = LoginActivity.getInputMessage(Command.Dev_Type_IR_byte,Command.Cmd_Ctr_Object_N1_byte,Command.DEV_Sensor_Human2_ID);
		mInfraredInputMessage[3] = LoginActivity.getInputMessage(Command.Dev_Type_IR_byte,Command.Cmd_Ctr_Object_N1_byte,Command.DEV_Sensor_Human3_ID);
		mSmokeInputMessage[0] = LoginActivity.getInputMessage(Command.Dev_Type_Gas_byte,Command.Cmd_Ctr_Object_N1_byte,Command.DEV_Sensor_Smoke0_ID);
		mSmokeInputMessage[1] = LoginActivity.getInputMessage(Command.Dev_Type_Gas_byte,Command.Cmd_Ctr_Object_N1_byte,Command.DEV_Sensor_Smoke1_ID);
		mSmokeInputMessage[2] = LoginActivity.getInputMessage(Command.Dev_Type_Gas_byte,Command.Cmd_Ctr_Object_N1_byte,Command.DEV_Sensor_Smoke2_ID);
		mSmokeInputMessage[3] = LoginActivity.getInputMessage(Command.Dev_Type_Gas_byte,Command.Cmd_Ctr_Object_N1_byte,Command.DEV_Sensor_Smoke3_ID);		
	}
	/**
	 *���ݴ�����״̬��Ϣˢ�¼�������ͼ
	 * 
	 */
	private void Sensor_Refresh_State (){
		Log.i(TAG, "Sensor_Refresh_State!!!") ;			
		for(int i = 0; i < mInfraredInputMessage.length; i++){
			InfraredSensorStatus[i]=Dev_SensorState_Refresh(Command.Dev_Type_IR_byte,mInfraredInputMessage[i]);	//������״̬ˢ��
		}
		for(int i = 0; i < mSmokeInputMessage.length; i++){
			SmokeSensorStatus[i]=Dev_SensorState_Refresh(Command.Dev_Type_Gas_byte,mSmokeInputMessage[i]);	//������״̬ˢ��
		}	
		for (int i = 0; i < intCameraImg.length; i++) {
			adapter_map = new HashMap<String, Object>();
			adapter_map.put("id", i) ;
			adapter_map.put("cameraImg", intCameraImg[i]);
			adapter_map.put("cameraName", strInfraredName[i]);
			adapter_map.put("cameraStatus", InfraredSensorStatus[i] +"\n\r"+ dateString) ;
			adapter_map.put("cameraImgRight", intCameraImgRight[i]);
			adapter_list.add(adapter_map);
		
			
			HashMap<String, Object> smoke_map = new HashMap<String, Object>();
			smoke_map.put("smoke_id", i) ;
			smoke_map.put("intSmokeImg", intSmokeImg[i]);
			smoke_map.put("strSmokeName", strSmokeName[i]);
			smoke_map.put("smokeStatus", SmokeSensorStatus[i] +"\n\r"+ dateString) ;
			smoke_map.put("intSmokeImgRight", intSmokeImgRight[i]);
			smoke_adapter_list.add(smoke_map);	
		}		

	}
	
	//������������ǰ״̬
	private static byte Msg_Dev_Sensor_Decode (String _mInputMsg){
		byte DevSensorState = 0x00 ;
		DevSensorState = (_mInputMsg.getBytes())[Command.Dev_Sensor_State_Pos];
		  return DevSensorState ;
		}
		
//		_mInputBuffer.toString().getBytes();
		
		
		
//	}
	
	//������״̬ˢ��
	private static String Dev_SensorState_Refresh (byte Dev_Type,String _mInputMsg){
		String SensorState = null  ;
		if(_mInputMsg.length()>Command.Dev_Sensor_State_Pos){
			byte DevState = Msg_Dev_Sensor_Decode(_mInputMsg); //������������ǰ״̬	
			if(DevState == Command.FeadBack_Invalid){
				Log.i(TAG, "Sensor_Human_Invalid_Msg") ;
				switch(Dev_Type){
					case Command.Dev_Type_IR_byte:
						SensorState = Command.Sensor_Human_Invalid_Msg ;					
						break;
					case Command.Dev_Type_Gas_byte:
						SensorState = Command.Sensor_Smoke_Invalid_Msg ;					
						break;
					default :
						break;
				}
			}
			else if(DevState == Command.FeadBack_Effective){
				switch(Dev_Type){
				case Command.Dev_Type_IR_byte:
					SensorState = Command.Sensor_Human_Effective_Msg ;					
					break;
				case Command.Dev_Type_Gas_byte:
					SensorState = Command.Sensor_Smoke_Effective_Msg ;					
					break;
				default :
					break;
				}
			}				
		}
		return SensorState ;
	}		

}
