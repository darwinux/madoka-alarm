package com.overflow.madokaalarm;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ShowActivity extends Activity {
	private PowerManager pm = null;
	private PowerManager.WakeLock wl = null;
	
	private AlertMusic am = null;
	
	private Vibrator vibrator = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.show);
		
		
		long[] pattern = {1000,1000,1000,1000};
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		vibrator.vibrate(pattern, 2);
		final Window win = getWindow();
		  win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
		  | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
		  win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
		  | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
		am = new AlertMusic(this);
		
		wl = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "ShowActivity");
		
		wl.acquire();
		
		
		
		//mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume*changeVolume/10, 0);
		//Thread td = new Thread(new loop());
		
		am.PlayMusic(false);
		//td.start();
		new AlertDialog.Builder(ShowActivity.this)
		.setIcon(R.drawable.temp)
		.setTitle("闹钟")
		.setMessage("赶快起床了！")
		.setPositiveButton("关掉它", 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						am.FreeMusic();
						//Intent iAlarmService = new Intent(ShowActivity.this,AlarmService.class);
						//stopService(iAlarmService);
						
						ShowActivity.this.finish();
					}
				}).show();
		
	}
	/*
	public class loop implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i("ShowActivity", String.valueOf(changeVolume));
			while(!isOK) {
				changeVolume+=1;
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume*changeVolume/10, 0);
				if(changeVolume==10){
					isOK = true;
				}
				try {
					Thread.sleep(3*400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
		}
		
	}
	*/
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("ShowActivity", "---------OK");
		super.onDestroy();
		vibrator.cancel();
		wl.release();
		
	}
	

}
