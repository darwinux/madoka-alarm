package com.overflow.madokaalarm;

import java.util.ArrayList;
import java.util.Calendar;

public interface IAlarm {
	void Load(ArrayList<AlarmTimeInfo> lstInfo);
	boolean IsTick(Calendar cal);
}
