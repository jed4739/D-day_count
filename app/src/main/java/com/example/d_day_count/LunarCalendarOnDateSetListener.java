package com.example.d_day_count;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class LunarCalendarOnDateSetListener implements DatePickerDialog.OnDateSetListener {
    private TextView textView;

    public LunarCalendarOnDateSetListener(TextView textView) {
        this. textView = textView;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar lunarCalendar = Calendar.getInstance();
        lunarCalendar.set(Calendar.YEAR, year);
        lunarCalendar.set(Calendar.MONTH, month);
        lunarCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        int lunarYear = lunarCalendar.get(Calendar.YEAR);
        int lunarMonth = lunarCalendar.get(Calendar.MONTH);
        int lunarDay = lunarCalendar.get(Calendar.DAY_OF_MONTH);
        textView.setText(lunarYear + "-" + lunarMonth + "-" + lunarDay);
    }
}
