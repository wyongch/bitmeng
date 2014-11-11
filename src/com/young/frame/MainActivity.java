package com.young.frame;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import fragment.EquipmentFragment;
import fragment.Fragment1;
import fragment.Fragment2;
import fragment.Fragment3;
import fragment.Fragment4;
import fragment.MonitorFragment;

public class MainActivity extends FragmentActivity{

	//可滑动窗口
	private ViewPager mTabPager;
	//4个tab页分别使用一个Fragment
    private Fragment1 mfragment1;
    private Fragment2 mfragment2;
    private EquipmentFragment mEquipmentFragment ;
//    private Fragment3 mfragment3;
//    private Fragment4 mfragment4;
    private MonitorFragment mMonitorFragment ;
    //存放fragment的Arraylist
    private ArrayList<Fragment> fragmentList;
    //tab标签图片 
    private ImageView mTab1,mTab2,mTab3,mTab4;
    private LinearLayout tab1,tab2,tab3,tab4;
    private int currIndex = 0;// 当前页卡编号
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		setTheme(0);
		
		mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
        //初始化标签栏中的图片
        mTab1 = (ImageView) findViewById(R.id.img_home);
        mTab2 = (ImageView) findViewById(R.id.img_scene);
        mTab3 = (ImageView) findViewById(R.id.img_equipment);
        mTab4 = (ImageView) findViewById(R.id.img_monitor);
        //初始化标签栏
        tab1 = (LinearLayout) findViewById(R.id.home);
        tab2 = (LinearLayout) findViewById(R.id.scene);
        tab3 = (LinearLayout) findViewById(R.id.equipment);
        tab4 = (LinearLayout) findViewById(R.id.monitor);
        //为标签设置事件监听
        tab1.setOnClickListener(new MyOnClickListener(0));
        tab2.setOnClickListener(new MyOnClickListener(1));
        tab3.setOnClickListener(new MyOnClickListener(2));
        tab4.setOnClickListener(new MyOnClickListener(3));
        /**
         * 实例化各个Fragment对象
         */
        mfragment1 = new Fragment1();
		mfragment2 = new Fragment2();
		
//		mfragment3 = new Fragment3();
		mEquipmentFragment = new EquipmentFragment() ;
//		mfragment4 = new Fragment4();
		mMonitorFragment = new MonitorFragment() ;
		
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(mfragment1);
		fragmentList.add(mfragment2);
//		fragmentList.add(mfragment3);
//		fragmentList.add(mfragment4);
		fragmentList.add(mEquipmentFragment) ;
		fragmentList.add(mMonitorFragment);
		
		mTabPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(),fragmentList));	
	}
	/**
	 * 
	 * @author Administrator
	 * 自定义视图适配器，继承FragmentPagerAdapter
	 * 构造函数形参为FragmentManager和泛型为Fragment的数组链表ArraList
	 *
	 */
	

	public class MyViewPagerAdapter extends FragmentPagerAdapter{
		
		private ArrayList<Fragment> fragmentList;
		
		@SuppressWarnings("unchecked")
		public MyViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
			// TODO 自动生成的构造函数存根
			super(fm);
			this.fragmentList = fragmentList;
		}


		@Override
		public Fragment getItem(int arg0) {
			// TODO 自动生成的方法存根
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return fragmentList.size();
		}
		
		
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};
	
	 /* 页卡切换监听
	  * 根据当前标签是否被选中修改其图片的状态
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(R.drawable.home_press));
				if (currIndex == 1) {
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.scene_unpress));
				} else if (currIndex == 2) {
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.monitor_unpress));
				}
				else if (currIndex == 3) {
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.equipment_unpress));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(R.drawable.scene_press));
				if (currIndex == 0) {
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.home_unpress));
				} else if (currIndex == 2) {
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.monitor_unpress));
				}
				else if (currIndex == 3) {
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.equipment_unpress));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(R.drawable.monitor_press));
				if (currIndex == 0) {
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.home_unpress));
				} else if (currIndex == 1) {
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.scene_unpress));
				}
				else if (currIndex == 3) {
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.equipment_unpress));
				}
				break;
			case 3:
				mTab4.setImageDrawable(getResources().getDrawable(R.drawable.equipment_press));
				if (currIndex == 0) {
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.home_unpress));
				} else if (currIndex == 1) {
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.scene_unpress));
				}
				else if (currIndex == 2) {
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.monitor_unpress));
				}
				break;
			}
			currIndex = arg0;
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	
	
}

