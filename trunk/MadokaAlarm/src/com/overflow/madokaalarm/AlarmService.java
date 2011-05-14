package com.overflow.madokaalarm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class AlarmService extends Service {
	//private int hourOfDay;
	//private int minute;
	private AlertManager alertManager = null;
	private Context context;
	private boolean isRunning = true;
	Calendar cl = Calendar.getInstance();
	
	//cl.setTimeInMillis(System.currentTimeMillis());
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		//
		Log.i("AlarmService", "----------AlarmService start");
		//SharedPreferences alarmSet = getSharedPreferences("MadokaAlarm",Activity.MODE_PRIVATE);
		
		//hourOfDay = Integer.parseInt(alarmSet.getString("AlarmHour", null));
		//minute = Integer.parseInt(alarmSet.getString("AlarmMinute", null));
		//PullXml px = new PullXml();
		
		//Calendar cl = Calendar.getInstance();
		
		//cl.setTimeInMillis(System.currentTimeMillis());
		//cl.set(Calendar.SECOND, 0);
		//cl.set(Calendar.MILLISECOND, 0);
		//Log.i("AlarmService", String.valueOf(Calendar.DAY_OF_WEEK)+":"+String.valueOf(Calendar.HOUR_OF_DAY)+":"+String.valueOf(Calendar.MINUTE));
		
		
		Thread td = new Thread(new loop());
		td.start();
		
		
		
		//cl.set(Calendar.HOUR_OF_DAY, hourOfDay);
		//cl.set(Calendar.MINUTE, minute);
		//cl.set(Calendar.SECOND, 0);
		//cl.set(Calendar.MILLISECOND, 0);
		
	}
	
	public class loop implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i("AlarmService Thread", "Running");
			FileInputStream inputStream;
			
			try {
				inputStream = openFileInput("alarmtime.xml");
				alertManager = new AlertManager();
				//ArrayList<AlarmTimeInfo> alarmTime = px.getAlarmTime(inputStream);
				//hourOfDay = alarmTime.get(0).getHour();
				//minute = alarmTime.get(0).getMinute();
				alertManager.initAlarm(inputStream,context);
				
				while(isRunning)
				{
					cl.setTimeInMillis(System.currentTimeMillis());
					if(alertManager.IsTick(cl)) {
					Log.i("AlarmService", "OK,It's the Time! ");
					Intent arIntent = new Intent();
					arIntent.setClass(AlarmService.this, ShowActivity.class);
					arIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(arIntent);
					
					//Intent arIntent = new Intent(this,AlarmReceiver.class);
					//PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, arIntent, 0);
						
					//AlarmManager am = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
					//am.set(AlarmManager.RTC_WAKEUP, cl.getTimeInMillis(), pendingIntent);
					
					//am.setRepeating(AlarmManager.RTC_WAKEUP, cl.getTimeInMillis(), (60*1000), pendingIntent);
					}
					Thread.sleep(60*1000);
				}
				
				
				
				
				//Log.i("AlarmService", String.valueOf(hourOfDay)+":"+String.valueOf(minute));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
	}
	

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("AlarmService", "----------AlarmService stop");
		//AlarmManager am = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
		//Intent intent = new Intent(this,MainActivity.class);
		//PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
		//am.cancel(pi);
		isRunning = false;
		//Handler.removeCallbacks(Thread ddd);
		super.onDestroy();
	}
	
	
	
}
