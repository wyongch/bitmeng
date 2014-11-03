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
	//存放界面的view组
	private ArrayList<View> pageViews; 
	// 包裹小圆点的LinearLayout  
    private ViewGroup group;  
    //当前页码  
    private int currentIndex;
	
    private ImageView imageView;    
    private ImageView[] imageViews; 
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_activity);
		//将要显示的图片放到ArrayList当中，存到适配器中  
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
        
      //将小圆点放到imageView数组当中  
        for (int i = 0; i < pageViews.size(); i++) {    
            imageView = new ImageView(GuideActivity.this);   
            LayoutParams params = new LayoutParams(24, 24);
            params.setMargins(0, 0, 20, 0);
            imageView.setLayoutParams(params);    
            imageViews[i] = imageView;    
              
            if (i == 0) {    
             //默认选中第一张图片  
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);    
            } else {    
                imageViews[i].setBackgroundResource(R.drawable.page_indicator);    
            }    
              
            group.addView(imageViews[i]);    
        } 
		
		viewPager.setAdapter(new GuidePageAdapter());    
	    viewPager.setOnPageChangeListener(new GuidePageChangeListener());
	}
	
	// 指引页面数据适配器,实现适配器方法  
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
	     
	// 指引页面更改事件监听器,左右滑动图片时候，小圆点变换显示当前图片位置  
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

