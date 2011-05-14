package com.overflow.madokaalarm;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.util.Log;


public class AlertManager {
	ArrayList<IAlarm> lstAlarm = null;
	
	public void initAlarm(FileInputStream inStream,Context context) {
		lstAlarm = new ArrayList<IAlarm>();
		PullXml px = new PullXml(context);
		WeekAlarm wa = new WeekAlarm();
		DayAlarm da = new DayAlarm();
	
		ArrayList<AlarmTimeInfo> alarmTime;
		try {
			alarmTime = px.getAlarmTime(inStream);
			wa.Load(alarmTime);
			lstAlarm.add(wa);
			da.Load(alarmTime);
			lstAlarm.add(da);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public boolean IsTick(Calendar cal) {
		
		for(IAlarm alarm:lstAlarm) {
			if(alarm.IsTick(cal)) {
				Log.i("AlartManagerTRUE",cal.getTime().toString() );
				return true;
			}
			else {
				Log.i("AlartManagerFALSE",cal.getTime().toString() );
			}
		}
		return false;
	}
	
}
