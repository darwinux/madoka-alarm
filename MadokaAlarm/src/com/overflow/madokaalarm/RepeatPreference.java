package com.overflow.madokaalarm;

import java.text.DateFormatSymbols;
import java.util.Calendar;


import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.ListPreference;
import android.util.AttributeSet;

public class RepeatPreference extends ListPreference {
	private DayOfWeek mDayOfWeek = new DayOfWeek(0);
	private DayOfWeek mNewDayOfWeek = new DayOfWeek(0);
	
	public RepeatPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		String[] weekdays = new DateFormatSymbols().getWeekdays();
        String[] values = new String[] {
            weekdays[Calendar.MONDAY],
            weekdays[Calendar.TUESDAY],
            weekdays[Calendar.WEDNESDAY],
            weekdays[Calendar.THURSDAY],
            weekdays[Calendar.FRIDAY],
            weekdays[Calendar.SATURDAY],
            weekdays[Calendar.SUNDAY],
        };
        setEntries(values);
        setEntryValues(values);
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		// TODO Auto-generated method stub
		if (positiveResult) {
            mDayOfWeek.set(mNewDayOfWeek);
            setSummary(mDayOfWeek.toString(getContext(), true));
            callChangeListener(mDayOfWeek);
        }
	}

	@Override
	protected void onPrepareDialogBuilder(Builder builder) {
		// TODO Auto-generated method stub
		CharSequence[] entries = getEntries();
        @SuppressWarnings("unused")
		CharSequence[] entryValues = getEntryValues();
        
        
        builder.setMultiChoiceItems(
                entries, mDayOfWeek.getBooleanArray(),
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which,
                            boolean isChecked) {
                        mNewDayOfWeek.set(which, isChecked);
                    }
                });
	}
	
	public void setDaysOfWeek(DayOfWeek dow) {
        mDayOfWeek.set(dow);
        mNewDayOfWeek.set(dow);
        setSummary(dow.toString(getContext(), true));
    }

    public DayOfWeek getDaysOfWeek() {
        return mDayOfWeek;
    }

}
