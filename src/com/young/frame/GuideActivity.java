package com.young.frame;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class GuideActivity extends Activity{

	private ViewPager viewPager; 
	//��Ž����view��
	private ArrayList<View> pageViews; 
	// ����СԲ���LinearLayout  
    private ViewGroup group;  
    //��ǰҳ��  
    private int currentIndex;
	
    private ImageView imageView;    
    private ImageView[] imageViews; 
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_activity);
		//��Ҫ��ʾ��ͼƬ�ŵ�ArrayList���У��浽��������  
        LayoutInflater inflater = getLayoutInflater();    
        pageViews = new ArrayList<View>();    
        pageViews.add(inflater.inflate(R.layout.guidepage1, null));  
        pageViews.add(inflater.inflate(R.layout.guidepage2, null));
        pageViews.add(inflater.inflate(R.layout.guidepage3, null));
        pageViews.add(inflater.inflate(R.layout.guidepage4, null));
        pageViews.add(inflater.inflate(R.layout.guidepage5, null));
        
        imageViews = new ImageView[pageViews.size()];
        
        group = (ViewGroup)findViewById(R.id.viewgroup);    
        viewPager = (ViewPager)findViewById(R.id.viewpager);  
        
      //��СԲ��ŵ�imageView���鵱��  
        for (int i = 0; i < pageViews.size(); i++) {    
            imageView = new ImageView(GuideActivity.this);   
            LayoutParams params = new LayoutParams(24, 24);
            params.setMargins(0, 0, 20, 0);
            imageView.setLayoutParams(params);    
            imageViews[i] = imageView;    
              
            if (i == 0) {    
             //Ĭ��ѡ�е�һ��ͼƬ  
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);    
            } else {    
                imageViews[i].setBackgroundResource(R.drawable.page_indicator);    
            }    
              
            group.addView(imageViews[i]);    
        } 
		
		viewPager.setAdapter(new GuidePageAdapter());    
	    viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	}
	
	// ָ��ҳ������������,ʵ������������  
	   class GuidePageAdapter extends PagerAdapter {    
	        
	       @Override    
	       public int getCount() {    
	           return pageViews.size();    
	       }    
	   
	       @Override    
	       public boolean isViewFromObject(View arg0, Object arg1) {    
	           return arg0 == arg1;    
	       }    
	   
	       @Override    
	       public int getItemPosition(Object object) {    
	           // TODO Auto-generated method stub    
	           return super.getItemPosition(object);    
	       }    
	   
	       @Override    
	       public void destroyItem(View arg0, int arg1, Object arg2) {    
	           // TODO Auto-generated method stub    
	           ((ViewPager) arg0).removeView(pageViews.get(arg1));    
	       }    
	   
	       @Override    
	       public Object instantiateItem(View arg0, int arg1) {    
	           // TODO Auto-generated method stub    
	           ((ViewPager) arg0).addView(pageViews.get(arg1));    
	           return pageViews.get(arg1);    
	       }    
	   
	       @Override    
	       public void restoreState(Parcelable arg0, ClassLoader arg1) {    
	           // TODO Auto-generated method stub    
	   
	       }    
	   
	       @Override    
	       public Parcelable saveState() {    
	           // TODO Auto-generated method stub    
	           return null;    
	       }    
	   
	       @Override    
	       public void startUpdate(View arg0) {    
	           // TODO Auto-generated method stub    
	   
	       }    
	   
	       @Override    
	       public void finishUpdate(View arg0) {    
	           // TODO Auto-generated method stub    
	   
	       }    
	   }   
	     
	// ָ��ҳ������¼�������,���һ���ͼƬʱ��СԲ��任��ʾ��ǰͼƬλ��  
	   class GuidePageChangeListener implements OnPageChangeListener {    
	        
	       @Override    
	       public void onPageScrollStateChanged(int arg0) {    
	           // TODO Auto-generated method stub    
	   
	       }    
	   
	       @Override    
	       public void onPageScrolled(int arg0, float arg1, int arg2) {    
	           // TODO Auto-generated method stub    
	   
	       }    
	   
	       @Override    
	       public void onPageSelected(int arg0) {    
	        currentIndex = arg0;  
	           for (int i = 0; i < imageViews.length; i++) {    
	               imageViews[arg0].setBackgroundResource(R.drawable.page_indicator_focused);  
	                 
	               if (arg0 != i) {    
	                   imageViews[i].setBackgroundResource(R.drawable.page_indicator);    
	               }    
	           }  
	       }    
	   }
	   
	   public void startbutton(View v) {  
	      	Intent intent = new Intent();
			intent.setClass(GuideActivity.this,LoginActivity.class);
			startActivity(intent);
			this.finish();
	      }  
}

