package com.young.frame;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

//Logo闪屏界面
public class SplashActivity extends Activity { 
 
	//最短闪屏时间
	private static final int SHOW_TIME_MIN = 1300; 
	private SharedPreferences sPreferences;  
    private SharedPreferences.Editor editor;  
    private String log = "manual-debug"; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.splash_activity); 

        //此处可执行异步任务
        new AsyncTask<Void, Void, Integer>() { 

            @Override
            protected Integer doInBackground(Void... params) { 
            	 int result; 
                 long startTime = System.currentTimeMillis(); 
                 //载入后台资源等
                 result = loadingCache(); 
                 long loadingTime = System.currentTimeMillis() - startTime; 
                 if (loadingTime < SHOW_TIME_MIN) { 
                     try { 
                         Thread.sleep(SHOW_TIME_MIN - loadingTime); 
                     } catch (InterruptedException e) { 
                         e.printStackTrace(); 
                     } 
                 } 
                 return result;  
            }  
            
            @Override
            protected void onPostExecute(Integer result) { 
                // ... ... 
            	sPreferences = getSharedPreferences("mlife", MODE_PRIVATE);  
                editor = sPreferences.edit();  
                String runRecord = sPreferences.getString("runRecord", null);  
                if (runRecord == null) {  
                    Log.d(log,"初次启动应用，进入导航界面！");  
                    new Handler().postDelayed(new Runnable() {  
                          
                        @Override  
                        public void run() {  
                            // TODO Auto-generated method stub  
                            Intent intent = new Intent(SplashActivity.this,GuideActivity.class);  
                            startActivity(intent);  
                            finish();  
                          //两个参数分别表示进入的动画,退出的动画 
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out); 
                        }  
                    }, 1500);  
                    editor.putString("runRecord", "already");  
                    editor.commit();  
                }else {  
                    Log.d(log, "不是初次启动，直接进入主界面！");  
                    new Handler().postDelayed(new Runnable() {  
                          
                        @Override  
                        public void run() {  
                            // TODO Auto-generated method stub  
                            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);  
                            startActivity(intent);  
                            finish();  
                          //两个参数分别表示进入的动画,退出的动画 
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out); 
                        }  
                    }, 1500);   
                }
            };
        }.execute(new Void[]{}); 
    } 

    private int loadingCache() { 
    	return 0;
    } 

}