package com.overflow.madokaalarm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class AlarmAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	static ArrayList<AlarmTimeInfo> alarmTime;
	PullXml px = null;
	
	
	
	
	
	public AlarmAdapter(Context context) throws Throwable {
		mInflater = LayoutInflater.from(context);
		//FileInputStream inputStream = context.openFileInput("alarmtime.xml");
		init(context);
	}
	
	public void init(Context context) throws Throwable {
		//alarmTime = new ArrayList<AlarmTimeInfo>();
		px = new PullXml(context);
		alarmTime = px.getAlarmTimeInfo();
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return alarmTime.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list, null);
			holder.img = (ImageView) convertView.findViewById(R.id.img);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.cBox = (CheckBox) convertView.findViewById(R.id.cb);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.img.setBackgroundResource(R.drawable.img1);
		holder.title.setText(alarmTime.get(position).getHour()+":"+alarmTime.get(position).getMinute());
		holder.cBox.setChecked(Boolean.parseBoolean(alarmTime.get(position).getEnable()));
		holder.cBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				alarmTime.get(position).setEnable(String.valueOf(isChecked));
				
				Log.i("AlarmAdapter", alarmTime.get(position).getId()+":"+alarmTime.get(position).getEnable());
				
			}
			
		});
		return convertView;
	}
	
	public final class ViewHolder {
		public ImageView img;
		public TextView title;
		public CheckBox cBox;
	}
	
	
	
}
