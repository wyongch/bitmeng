package fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.young.frame.AirControl;
import com.young.frame.R;
import com.young.frame.Switch4Activity;
import com.young.frame.TVActivity;

public class Fragment4 extends Fragment {

	private View mainView;
	private GridView gridView=null;
    private SimpleAdapter adapter1=null;
    private List<HashMap<String,Object>> list=null;
    private HashMap<String,Object> map=null;
    private String data[]={"空调遥控","电视遥控","四键开关遥控","设备4","设备5","设备6","设备7","设备8","设备9","设备10","添加"};
    private int   imgId[]={R.drawable.equipment,R.drawable.equipment,R.drawable.equipment,R.drawable.equipment,R.drawable.equipment,
            R.drawable.equipment,R.drawable.equipment,R.drawable.equipment,R.drawable.equipment,R.drawable.equipment,R.drawable.add};
    private MyAdapter adapter=null;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mainView = inflater.inflate(R.layout.fragment4,null);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ViewGroup p = (ViewGroup)mainView.getParent();
		
		if(p != null)
		{
			p.removeAllViewsInLayout();
		}
		
		gridView=(GridView) mainView.findViewById(R.id.grid_view);
	    list=new ArrayList<HashMap<String,Object>>();
//	    使用BaseAdapter添加数据
	    adapter=new MyAdapter(getActivity(), data, imgId);
	    gridView.setAdapter(adapter);
	    
	   gridView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			switch (position) {
			case 0:
				getActivity().startActivity(new Intent(getActivity() , AirControl.class));
				break;
				
			case 1 :
				getActivity().startActivity(new Intent(getActivity() , TVActivity.class));
				
			case 2 :
				getActivity().startActivity(new Intent(getActivity() , Switch4Activity.class));
			default:
				break;
			}
			
		}
		   
		   
	});
	    
		return mainView;
	}
}
