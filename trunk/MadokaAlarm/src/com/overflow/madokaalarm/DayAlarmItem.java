package com.overflow.madokaalarm;

public class DayAlarmItem {
	private int hour;
	private int minute;
	private AlarmTimeInfo ati;
	
	public int getHour() {
		return hour;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public AlarmTimeInfo getAlarmTimeInfo() {
		return ati;
	}
	
	public void setAlarmTimeInfo(AlarmTimeInfo ati) {
		this.ati = ati;
	}
}
