package fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ipcamer.demo.AddCameraActivity;
import com.young.frame.AddScenesActivity;
import com.young.frame.BaseActivity;
import com.young.frame.R;
import com.young.frame.SettingScenesActivity;

public class Fragment2 extends Fragment implements OnItemClickListener, OnItemLongClickListener {

	private View mainView;
	private GridView gridView=null;
    private SimpleAdapter adapter1=null;
    private List<HashMap<String,Object>> list=null;
    private HashMap<String,Object> map=null;
    private String data[]={"sleeep","get up","go home","out door","场景5","场景6","场景7","场景8","场景9","场景10","添加场景"};
    private int   imgId[]={R.drawable.scenes_fragment_sleep,R.drawable.scenes_fragment_get_up,R.drawable.scenes_fragment_go_home,R.drawable.scenes_fragment_out_door,
    		R.drawable.scene_up, R.drawable.scene_up,R.drawable.scene_up,R.drawable.scene_up,R.drawable.scene_up,R.drawable.scene_up,R.drawable.add};
    private MyAdapter adapter=null;
    
    SimpleAdapter simple ;
//    private Dialog showDialog ;
    
    public void showDialog(String title, String msg,
			DialogInterface.OnClickListener mOkOnClickListener) {
		AlertDialog.Builder builder = new Builder(getActivity());
		builder.setMessage(msg);
		builder.setTitle(title);
		builder.setPositiveButton("确认", mOkOnClickListener);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
    
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
	    
	    for(int i=0; i<data.length; i++){
			HashMap< String, Object> map = new HashMap<String, Object>() ;
			map.put("imageId", imgId[i]);
			map.put("scenesName", data[i]);
			list.add(map);
		}
	    String[] from = {"imageId", "scenesName"} ;
	    int[] to = {R.id.scenes_gridview_img , R.id.scenes_gridview_text} ;
	    simple = new SimpleAdapter(getActivity(), list, R.layout.scenes_gridview_item, from, to) ;
	     
	    
//	    使用BaseAdapter添加数据
//	    adapter=new MyAdapter(getActivity(), data, imgId);
//	    gridView.setAdapter(adapter);
	    gridView.setAdapter(simple);
	    gridView.setOnItemClickListener(this);
	    gridView.setOnItemLongClickListener(this);
	    
	    
	    
		return mainView;
	}

	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			final int position, long id) {
		// TODO Auto-generated method stub
		
//		Intent intent = new Intent(getActivity() , SettingScenesActivity.class) ;
//		startActivity(intent);
		
//		Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show() ;
//		showDialog = new Dialog(getActivity()) ;
//		showDialog.setTitle("修改场景");
//		showDialog.set("Loging.....");
//		showDialog.show();
		
		
//		showDialog("修改场景", "fasdfasf", new OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
		String tempName = list.get(position).get("scenesName").toString() ;
		EditText editName = new EditText(getActivity()) ;
		editName.setText(tempName);
		AlertDialog dialog = new AlertDialog.Builder(getActivity()).create() ;
		dialog.setTitle("修改场景名称");
		dialog.setView(editName);
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "编辑", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity() , SettingScenesActivity.class) ;
				startActivity(intent);
			}
		});
		
		// 为对话框添加"取消"按钮
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0,
							int arg1) {

					}
				});
		// 为对话框添加"删除"按钮
		dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "删除",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0,
							int arg1) {
						list.remove(position);
						simple.notifyDataSetChanged();
					}
				});
		dialog.show();
		
		
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		if(list.get(position).get("scenesName").toString().equals("添加场景")){
			Intent intent = new Intent(getActivity() , AddScenesActivity.class) ;
			startActivity(intent);
		}else{
			Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show() ;
		}
		
		
	}

	


}
