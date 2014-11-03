package fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.young.frame.R;

public class Fragment2 extends Fragment {

	private View mainView;
	private GridView gridView=null;
    private SimpleAdapter adapter1=null;
    private List<HashMap<String,Object>> list=null;
    private HashMap<String,Object> map=null;
    private String data[]={"场景1","场景2","场景3","场景4","场景5","场景6","场景7","场景8","场景9","场景10","添加"};
    private int   imgId[]={R.drawable.scene,R.drawable.scene,R.drawable.scene,R.drawable.scene,R.drawable.scene,
            R.drawable.scene,R.drawable.scene,R.drawable.scene,R.drawable.scene,R.drawable.scene,R.drawable.add};
    private MyAdapter adapter=null;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mainView = inflater.inflate(R.layout.fragment2,null);
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
	    
		return mainView;
	}
}
