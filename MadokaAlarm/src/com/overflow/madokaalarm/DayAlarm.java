package com.overflow.madokaalarm;

import java.util.ArrayList;
import java.util.Calendar;

import android.util.Log;

public class DayAlarm implements IAlarm {
	private ArrayList<DayAlarmItem> dayAlarmItem = null;
	@Override
	public boolean IsTick(Calendar cal) {
		// TODO Auto-generated method stub
		//cal.setTimeInMillis(System.currentTimeMillis());
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		
		for(DayAlarmItem daItem:dayAlarmItem) {
			if(daItem.getHour()==hour && daItem.getMinute()==minute) {
				Log.i("DayAlarm IsTick", String.valueOf(daItem.getHour())+":"+String.valueOf(daItem.getMinute()));
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void Load(ArrayList<AlarmTimeInfo> lstInfo) {
		// TODO Auto-generated method stub
		dayAlarmItem = new ArrayList<DayAlarmItem>();
		
		for(AlarmTimeInfo ati:lstInfo) {
			if(ati.getType().equals("day")) {
				DayAlarmItem daItem = new DayAlarmItem();
				daItem.setHour(ati.getHour());
				daItem.setMinute(ati.getMinute());
				daItem.setAlarmTimeInfo(ati);
				dayAlarmItem.add(daItem);
				Log.i("DayAlarm Load", String.valueOf(ati.getHour())+":"+String.valueOf(ati.getMinute()));
			}
		}
	}

}
