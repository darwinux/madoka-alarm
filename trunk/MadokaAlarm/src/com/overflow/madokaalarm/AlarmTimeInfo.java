package com.overflow.madokaalarm;

public class AlarmTimeInfo {
	private int id;
	private String week;
	private int hour;
	private int minute;
	private String enable;
	private String type;
	private String theme;
	
	public AlarmTimeInfo() {
		
	}
	
	public AlarmTimeInfo(int id,String week,int hour,int minute,String enable,String type,String theme) {
		super();
		this.id = id;
		this.week = week;
		this.hour = hour;
		this.minute = minute;
		this.enable = enable;
		this.type = type;
		this.theme = theme;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getWeek() {
		return week;
	}
	
	public void setWeek(String week) {
		this.week = week;
	}
	

	
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
	
	public String getEnable() {
		return enable;
	}
	
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTheme() {
		return theme;
	}
	
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	@Override  
    public String toString()  
    {  
        return "alarmTimeInfo [id=" + id +", week"+ week +", hour=" + hour + ", minute=" + minute + ", enable"+ enable +", type"+type+", theme"+theme +"]";  
    }  
}
