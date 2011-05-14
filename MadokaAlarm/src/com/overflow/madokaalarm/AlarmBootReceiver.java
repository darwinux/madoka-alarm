package com.overflow.madokaalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmBootReceiver extends BroadcastReceiver {
	static final String ACTION = "android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getAction().equals(ACTION)) {
			Intent intent = new Intent(arg0,AlarmService.class);
			intent.setAction("com.overflow.AlarmService");
			arg0.startService(intent);
		}
	}

}
