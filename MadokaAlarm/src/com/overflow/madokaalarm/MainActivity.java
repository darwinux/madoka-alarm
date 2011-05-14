package com.overflow.madokaalarm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import com.overflow.madokaalarm.AlarmAdapter.ViewHolder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private Button btnConfig = null;
	private Button btnCancel = null;
	private Button btnExit = null;
	private ListView mainList = null;
	//private TextView tv = null;
	private Context context;
	
	private Calendar cal = null;
	AlarmAdapter adapter = null;
	private boolean firstRun = true;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        cal = Calendar.getInstance();
        
        mainList = (ListView) this.findViewById(R.id.mainList);
        btnConfig = (Button) this.findViewById(R.id.btnConfig);
        btnCancel = (Button) this.findViewById(R.id.btnCancel);
        btnExit = (Button) this.findViewById(R.id.btnExit);
        //tv = (TextView) this.findViewById(R.id.tv);
 
        
       // SharedPreferences alarmSet = getSharedPreferences("MadokaAlarm",Activity.MODE_PRIVATE);
        //tv.setText(alarmSet.getString("AlarmHour", null)+":"+alarmSet.getString("AlarmMinute", null));
       // firstRun = alarmSet.getBoolean("firstRun", true);
 /*       if(firstRun==true) {
        	try {
				firstPullXmlToData();
				
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.i("MainActivity", String.valueOf(firstRun));
        }
        else {
        	Log.i("MainActivity", String.valueOf(firstRun));
       	 
        } */
        
        
        //mainList
        try {
        	
			adapter = new AlarmAdapter(this);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			mainList.setAdapter(adapter);
			mainList.setItemsCanFocus(false);
			mainList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			mainList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					ViewHolder vHollder = (ViewHolder) view.getTag();
					vHollder.cBox.toggle(); 
					 try {
						// vHollder.cBox.toggle();
						testPullBuildXML(vHollder,position);
						
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//vHollder.cBox.toggle(); 
				    
					 
					}
			         
				
				
			});
			mainList.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Log.i("MainActivity", "setOnItemLongClickListener()");
					
					Intent i = new Intent();
					
					i.setClass(MainActivity.this, AlarmConfigAcitvity.class);
					startActivity(i);
					return true;
				}
				
			});
			
		}
		
			
		OnClickListener bPop = new OnClickListener() {    
	        @Override    
	        public void onClick(View v) {    
	            for(int i=0;i<mainList.getCount();i++){    
	                if(AlarmAdapter.alarmTime.get(i) != null){    
	                    ViewHolder vHollder = (ViewHolder) mainList.getChildAt(i).getTag();    
	                    Log.i("MainActivity", "--onClick --"+vHollder.title.getText());    
	                }    
	            }    
	        }    
	    };  

        
        
        btnExit.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//MainActivity.this.finish();
				//Intent i = new Intent();
				//i.setClass(MainActivity.this, ShowActivity.class);
				//i.setClass(MainActivity.this, AlarmConfigAcitvity.class);
				//startActivity(i);
				MainActivity.this.finish();
				
			}
        	
        });
        
        btnConfig.setOnClickListener(new Button.OnClickListener() {
        	//点击设置按钮
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				cal.setTimeInMillis(System.currentTimeMillis());
				
				int mHour = cal.get(Calendar.HOUR_OF_DAY);
				int mMinute = cal.get(Calendar.MINUTE);
				
				new TimePickerDialog(MainActivity.this,
						new TimePickerDialog.OnTimeSetListener() {
							
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								// TODO Auto-generated method stub
								cal.setTimeInMillis(System.currentTimeMillis());
								cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
								Log.i("MainActivity", String.valueOf(hourOfDay));
								cal.set(Calendar.MINUTE, minute);
								Log.i("MainActivity", String.valueOf(minute));
								cal.set(Calendar.SECOND, 0);
								cal.set(Calendar.MILLISECOND, 0);
								
								SharedPreferences wt = getSharedPreferences("MadokaAlarm",Activity.MODE_WORLD_READABLE);
								SharedPreferences.Editor et = wt.edit();
								//et.putString("AlarmHour",PullXml.format(hourOfDay));
								//et.putString("AlarmMinute", PullXml.format(minute));
								et.putBoolean("firstRun", false);
								et.commit();
								try {
									testPullBuildXML(hourOfDay,minute);
								} catch (Throwable e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Log.i("MainActivity",PullXml.format(hourOfDay)+":"+PullXml.format(minute));
								Log.i("MainActivity", String.valueOf(cal.getTimeInMillis()));
								String tmps = "设置闹钟时间为"+PullXml.format(hourOfDay)+":"+PullXml.format(minute);
								tv.setText(tmps);
								Intent iAlarmService = new Intent(MainActivity.this,AlarmService.class);
								startService(iAlarmService);
							}
						},mHour,mMinute,true).show();
				
				*/
				Intent iAlarmService = new Intent(MainActivity.this,AlarmService.class);
				startService(iAlarmService);
				
			}
        	
        });
        
        btnCancel.setOnClickListener(new Button.OnClickListener() {
        	//点击取消按钮
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
				PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
				
				AlarmManager am;
				am = (AlarmManager) getSystemService(ALARM_SERVICE);
				am.cancel(pendingIntent);
				tv.setText("闹钟已经取消");
				*/
				
				Intent iAlarmService = new Intent(MainActivity.this,AlarmService.class);
				stopService(iAlarmService);
			}
        	
        });
        
        
        
        
    }// onCreate结束
    
    public void testPullBuildXML(ViewHolder vHollder,int position) throws Throwable 
    {  
    	
    	PullXml px = new PullXml(this);
    	//第一次
		//InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("alarmtime.xml");
    	//FileInputStream inputStream = openFileInput("alarmtime.xml");
		ArrayList<AlarmTimeInfo> alarmTime = px.getAlarmTimeInfo();
		//vHollder.cBox.toggle();
		alarmTime.get(position).setEnable(String.valueOf(vHollder.cBox.isChecked()));
		px.setAlarmTimeInfo(alarmTime);
		px.saveConfig();
		//alarmTime.add(new AlarmTimeInfo(0,11,00));
		//alarmTime.remove(0);
		//alarmTime.get(0).setWeek(1);
		//alarmTime.get(0).setHour(hour);
		//alarmTime.get(0).setMinute(minute);
		//FileOutputStream outStream  = this.openFileOutput("alarmtime.xml", Context.MODE_PRIVATE);
		
		//px.bulidXml(alarmTime, PullXml.Writer(this));  
		
		
		
		for(AlarmTimeInfo alarmTimeInfo:alarmTime) {
			Log.i("MainActiviy", alarmTimeInfo.toString());
			//tv.setText(alarmTimeInfo.toString());
		}
		
		
		//File file = new File(Environment.getExternalStorageDirectory(),"alarmtime.xml");
		//FileOutputStream outStream  = new FileOutputStream(file);
		//FileOutputStream outStream  = this.openFileOutput("alarmtime.xml", Context.MODE_PRIVATE);
		//px.bulidXml(alarmTime, outStream);  
    }  
    
    public void firstPullXmlToData() throws Throwable {
    	//第一次运行程序拷贝src下的xml到私有目录下
    	PullXml px = new PullXml(context);
    	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("alarmtime.xml");
    	
    	ArrayList<AlarmTimeInfo> alarmTime = px.getAlarmTime(inputStream);
    	
    	px.setAlarmTimeInfo(alarmTime);
    	px.saveConfig();
    	//FileOutputStream outStream  = this.openFileOutput("alarmtime.xml", Context.MODE_PRIVATE);
		//px.bulidXml(alarmTime, outStream);
		SharedPreferences wt = getSharedPreferences("MadokaAlarm",Activity.MODE_WORLD_READABLE);
		SharedPreferences.Editor et = wt.edit();
		et.putBoolean("firstRun", false);
		et.commit();
    }
    
    

} 