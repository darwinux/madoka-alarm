package com.overflow.madokaalarm;



import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.ListPreference;
import android.preference.CheckBoxPreference;
import android.preference.RingtonePreference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class AlarmConfigAcitvity extends PreferenceActivity implements TimePickerDialog.OnTimeSetListener,
Preference.OnPreferenceChangeListener{
	private EditTextPreference times;
	
	private Preference mTimePref;
	private RingtonePreference mRingtonePref;
	private CheckBoxPreference mVibratePref;
	
	private RepeatPreference mRepeatPref;
	
	private int mHour;
	private int mMinute;
	private int mId;
	public static final String ALARM_ID = "alarm_id";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		addPreferencesFromResource(R.xml.preference);
		
		times = (EditTextPreference) this.findPreference("times");
		times.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			@Override
			public boolean onPreferenceChange(Preference preference,
					Object newValue) {
				// TODO Auto-generated method stub
				String val = (String) newValue;
                // Set the summary based on the new label.
                preference.setSummary(val);
                if (val != null && !val.equals(times.getText())) {
                    // Call through to the generic listener.
                    return AlarmConfigAcitvity.this.onPreferenceChange(preference,
                        newValue);
                }
				return true;
			}
			
		});
		
		mTimePref = this.findPreference("time");
		mRingtonePref = (RingtonePreference) this.findPreference("alarm");
		mRingtonePref.setOnPreferenceChangeListener(this);
		mVibratePref = (CheckBoxPreference) this.findPreference("vibrate");
		mVibratePref.setOnPreferenceChangeListener(this);
		mRepeatPref = (RepeatPreference) this.findPreference("setRepeat");
		mRepeatPref.setOnPreferenceChangeListener(this);
		
		Intent i = getIntent();
		mId = i.getIntExtra(ALARM_ID, -1);
		
		if(mId==-1) {
			//新建闹钟
		}
		else {
			//打开当前点击的这个闹钟属性
		}
		
		getListView().setItemsCanFocus(true);
		
		Button mFinish = (Button) this.findViewById(R.id.btnFinish);
		mFinish.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}//onCreate
	
	
	
	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		// TODO Auto-generated method stub
		if(preference == mTimePref) {
			showTimePicker();
		}
		return super.onPreferenceTreeClick(preferenceScreen, preference);
	}



	private void showTimePicker() {
		new TimePickerDialog(this, this, mHour, mMinute, DateFormat.is24HourFormat(this)).show();
	}
}
