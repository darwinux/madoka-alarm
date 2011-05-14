package com.overflow.madokaalarm;

import java.io.IOException;





import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;

public class AlertMusic {
	public MediaPlayer mediaPlayer = null;
	private Context context = null;
	
	private AudioManager mAudioManager = null;
	private int maxVolume;
	private int changeVolume = 1 ;
	boolean isOk = false;
	
	public AlertMusic(Context context) {
		this.context = context;
		mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	}
	
	public void PlayMusic(boolean isFadein) {
		mediaPlayer = MediaPlayer.create(context, R.raw.op);
		mediaPlayer.setLooping(true);
		
		
			
			

		
		
			try {
				mediaPlayer.prepare();
			}
			catch (IllegalStateException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			if(isFadein) {
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume*changeVolume/10, 0);
				Thread td = new Thread(new loop());
				td.start();
			}
			
			mediaPlayer.start();
	
		
		
		
		
		//td.start();
	}
	
	public void FreeMusic() {
		if (mediaPlayer!=null) {
			mediaPlayer.stop();
			mediaPlayer.release();
			isOk = true;
		}
	}
	
	public class loop implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i("ShowActivity", String.valueOf(changeVolume));
			while(!isOk) {
				changeVolume+=1;
				mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume*changeVolume/10, 0);
				if(changeVolume==10){
					isOk = true;
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
}
