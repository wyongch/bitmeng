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
    private String data[]={"sleeep","get up","go home","out door","����5","����6","����7","����8","����9","����10","��ӳ���"};
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
		builder.setPositiveButton("ȷ��", mOkOnClickListener);
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
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
	     
	    
//	    ʹ��BaseAdapter�������
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
//		showDialog.setTitle("�޸ĳ���");
//		showDialog.set("Loging.....");
//		showDialog.show();
		
		
//		showDialog("�޸ĳ���", "fasdfasf", new OnClickListener() {
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
		dialog.setTitle("�޸ĳ�������");
		dialog.setView(editName);
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "�༭", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity() , SettingScenesActivity.class) ;
				startActivity(intent);
			}
		});
		
		// Ϊ�Ի������"ȡ��"��ť
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "ȡ��",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0,
							int arg1) {

					}
				});
		// Ϊ�Ի������"ɾ��"��ť
		dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "ɾ��",
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
		
		if(list.get(position).get("scenesName").toString().equals("��ӳ���")){
			Intent intent = new Intent(getActivity() , AddScenesActivity.class) ;
			startActivity(intent);
		}else{
			Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show() ;
		}
		
		
	}

	


}
