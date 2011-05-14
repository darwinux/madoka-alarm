package com.overflow.madokaalarm;

import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;

public class WeekAlarm implements IAlarm {
	private ArrayList<WeekAlarmItem> weekAlarmItem = null;
	
	public void Load(ArrayList<AlarmTimeInfo> lstInfo) {
		weekAlarmItem = new ArrayList<WeekAlarmItem>();
		
		for(AlarmTimeInfo ati:lstInfo) {
			
			if(ati.getType().equals("week")) {
				//Log.i("WeekAlarm", "if(ati.getType().equals week");
				WeekAlarmItem waItem = null;
				//waItem.setWeek(ati.getWeek());
				String[] weekArray = ati.getWeek().split(",");
				for(String i:weekArray) {
					waItem = new WeekAlarmItem();
					waItem.setWeek(i);
					waItem.setHour(ati.getHour());
					waItem.setMinute(ati.getMinute());
					waItem.setAlarmTimeInfo(ati);
					
					weekAlarmItem.add(waItem);
				}
					
					//Log.i("WeekAlarm Load", waItem.getWeek()+":"+String.valueOf(waItem.getHour())+":"+String.valueOf(waItem.getMinute()));
				
				
				
			}
		}
		
	}
	
	public boolean IsTick(Calendar cal) {
		
		//cal = Calendar.getInstance();
		//cal.setTimeInMillis(System.currentTimeMillis());
		String week = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));
		Log.i("WeekAlarm IsTick", String.valueOf(week));
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		
		for(WeekAlarmItem waItem:weekAlarmItem) {
			if(waItem.getWeek().equals(week) && waItem.getHour()==hour && waItem.getMinute() == minute) {
				Log.i("WeekAlarm IsTick", String.valueOf(waItem.getWeek())+":"+String.valueOf(waItem.getHour())+":"+String.valueOf(waItem.getMinute()));
				return true;
			}
		}
		Log.i("WeekAlarm IsTick", "no track here");
		return false;
		
	}
	

	
}
