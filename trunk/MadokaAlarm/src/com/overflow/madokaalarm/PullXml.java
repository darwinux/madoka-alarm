package com.overflow.madokaalarm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

public class PullXml {
	private static Context context;
	ArrayList<AlarmTimeInfo> lstAlarmTimeInfo;
	
	public PullXml(Context context) {
		this.context = context;
		initConfig();
	}
	
	public ArrayList<AlarmTimeInfo> getAlarmTimeInfo() {
		return lstAlarmTimeInfo;
	}
	
	public void setAlarmTimeInfo(ArrayList<AlarmTimeInfo> lstAlarmTimeInfo) {
		this.lstAlarmTimeInfo = lstAlarmTimeInfo;
	}
	
	public void initConfig() {
		FileInputStream inStream = getReader();
		try {
			lstAlarmTimeInfo = getAlarmTime(inStream);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				inStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void saveConfig() {
		FileOutputStream outStream = getWriter();
		try {
			bulidXml(outStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				outStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	public ArrayList<AlarmTimeInfo> getAlarmTime(InputStream inStream) throws Throwable {
		ArrayList<AlarmTimeInfo> alarmTime = null;
		AlarmTimeInfo alarmTimeInfo  = null;
		
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(inStream,"utf-8");
		int event = parser.getEventType();
		
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				alarmTime = new ArrayList<AlarmTimeInfo>();
				break;
			case XmlPullParser.START_TAG:
				if("AlarmTime".equals(parser.getName())) {
					alarmTimeInfo = new AlarmTimeInfo();
					//int id = new Integer(parser.getAttributeValue(0));
					int id = new Integer(parser.getAttributeValue(null,"id"));
					alarmTimeInfo.setId(id);
					String enable = parser.getAttributeValue(null,"enable");
					alarmTimeInfo.setEnable(enable);
					String type = parser.getAttributeValue(null, "type");
					alarmTimeInfo.setType(type);
					String theme = parser.getAttributeValue(null,"theme");
					alarmTimeInfo.setTheme(theme);
				}
				if(alarmTimeInfo!=null) {
					if("week".equals(parser.getName())) {
						String week = parser.nextText().toString();
						alarmTimeInfo.setWeek(week);
					}
					else if("hour".equals(parser.getName())) {
						int hour = new Integer(parser.nextText().toString());
						alarmTimeInfo.setHour(hour);
					}
					else if("minute".equals(parser.getName())) {
						int minute = new Integer(parser.nextText().toString());
						alarmTimeInfo.setMinute(minute);
					}
				}
				break;
			case XmlPullParser.END_TAG:
				if("AlarmTime".equals(parser.getName())) {
					alarmTime.add(alarmTimeInfo);
					alarmTimeInfo = null;
				}
				break;
			}
			event = parser.next();
		}
		return alarmTime;
	} //结束读
	
	public void bulidXml(OutputStream outStream) throws Exception {
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(outStream,"utf-8");
		
		serializer.startDocument("utf-8", true);
		serializer.startTag(null, "MadokaAlarm");
		
		for(AlarmTimeInfo alarmTimeInfo:lstAlarmTimeInfo) {
			serializer.startTag(null, "AlarmTime");
			serializer.attribute(null, "id", String.valueOf(alarmTimeInfo.getId()));
			serializer.attribute(null, "enable", alarmTimeInfo.getEnable());
			serializer.attribute(null, "type", alarmTimeInfo.getType());
			serializer.attribute(null, "theme", alarmTimeInfo.getTheme());
			

			
			serializer.startTag(null, "week");
			serializer.text(alarmTimeInfo.getWeek());
			serializer.endTag(null, "week");
			

			
			serializer.startTag(null, "hour");
			//serializer.text(String.valueOf(alarmTimeInfo.getHour()));
			serializer.text(format(alarmTimeInfo.getHour()));
			serializer.endTag(null, "hour");
			
			serializer.startTag(null, "minute");
			//serializer.text(String.valueOf(alarmTimeInfo.getMinute()));
			serializer.text(format(alarmTimeInfo.getMinute()));
			serializer.endTag(null, "minute");
			
			serializer.endTag(null, "AlarmTime");
		}
		
		serializer.endTag(null, "MadokaAlarm");
		serializer.endDocument();
		outStream.close();
	}
	
    public static String format(int x) {
    	//格式化字符串
    	String s = "" + x;
    	if(s.length() == 1) {
    		s="0"+s;
    	}
    	return s;
    }
    
    @SuppressWarnings("finally")
	public FileInputStream getReader() {
    	
    	FileInputStream inputStream = null;
    	File alarmtime = new File("/data/data/com.overflow.madokaalarm/files/alarmtime.xml");
    	try {
    		if(!alarmtime.exists()){
    			//PullXml px = new PullXml(context);
    			//inputStream = (FileInputStream) this.getClass().getClassLoader().getResourceAsStream("alarmtime.xml");
    			//lstAlarmTimeInfo = getAlarmTime(inputStream);
    			lstAlarmTimeInfo = createDefaultAlarmClock();
    			
    			saveConfig();
    			Log.i("PullXml", "if(!alarmtime.exists())");
    		}
    		
    		Log.i("PullXml", "try");
			inputStream = context.openFileInput("alarmtime.xml");
    		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			return inputStream;
		}
    	
    }
    
    @SuppressWarnings("finally")
	public FileOutputStream getWriter() {
    	FileOutputStream outputStream = null;
    	try {
			outputStream = context.openFileOutput("alarmtime.xml", Context.MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			return outputStream;
		}
    	
		
    	
    }
    
    private ArrayList<AlarmTimeInfo> createDefaultAlarmClock(){
    	ArrayList<AlarmTimeInfo> alarmTime = new ArrayList<AlarmTimeInfo>();
    	AlarmTimeInfo ati = new AlarmTimeInfo();
    	ati.setId(0);
    	ati.setType("day");
    	ati.setEnable("true");
    	ati.setTheme("/sdcard/");
    	ati.setWeek("1,2,3");
    	ati.setHour(8);
    	ati.setMinute(0);
    	alarmTime.add(ati);
    	
    	
		return alarmTime;
    	
    }
}
