package fragment;

import java.util.zip.Inflater;

import com.young.frame.AddDeviceActivity;
import com.young.frame.AirControlActivity;
import com.young.frame.R;
import com.young.frame.Switch16Activity;
import com.young.frame.Switch1Activity;
import com.young.frame.Switch4Activity;
import com.young.frame.TVActivityUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;


public class EquipmentFragment extends Fragment {
	
	private View mainView ;
	private GridView gridView ;
	private MyAdapter myAdapter ;
	private int equipmentImgId[] ={R.drawable.equipment_air_control, R.drawable.equipment_tv_control,R.drawable.equipment_switch_one,R.drawable.equipment_switch_four,R.drawable.equipment_switch_sixteen,R.drawable.equipment_device_add} ;
	private String equipmentText[] ={"空调遥控器" , "电视遥控器" , "一键开关" , "四健开关" ,"十六健开关" ,"添加"} ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater() ;
		mainView = inflater.inflate(R.layout.fragment_equipment, null) ;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		ViewGroup p = (ViewGroup)mainView.getParent() ;
		if(p != null){
			p.removeAllViewsInLayout();
		}
		gridView = (GridView)mainView.findViewById(R.id.equipment_gv_device) ;
		myAdapter = new MyAdapter(getActivity(), equipmentText, equipmentImgId) ;
		gridView.setAdapter(myAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					getActivity().startActivity(new Intent(getActivity() , AirControlActivity.class));
					break;
					
				case 1 :
					getActivity().startActivity(new Intent(getActivity() , TVActivityUp.class));
					break ;
					
				case 2 :
					getActivity().startActivity(new Intent(getActivity() , Switch1Activity.class));
					break ;
				case 3 :
					getActivity().startActivity(new Intent(getActivity() , Switch4Activity.class));
					break ;
				case 4 :
					getActivity().startActivity(new Intent(getActivity() , Switch16Activity.class));
					break ;
				case 5 :
					getActivity().startActivity(new Intent(getActivity() , AddDeviceActivity.class));
					break ;
				default:
					break;
				}
				
				
				
			}
		});
		
		return mainView ;
	}
	

}
