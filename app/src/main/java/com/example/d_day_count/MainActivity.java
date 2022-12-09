package com.example.d_day_count;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.d_day_count.databinding.Main;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * 구현 기능 목록
 * 1. 시작일과 종료일 입력시 날짜 차이를 출력
 * 2. 앱 실행 시 자신의 생년월일로 카운터 표시
 * 3. 양력 및 음력 변환 기능 추가
 * */
public class MainActivity extends AppCompatActivity {

    Main binding;
    String startDate = "";
    String endDate = "";
    Calendar calendar_start;
    Calendar calendar_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        calendar_start = Calendar.getInstance();
        calendar_end = Calendar.getInstance();

        binding.startBtn.setOnClickListener(v -> {
            GregorianCalendar today = new GregorianCalendar();
            int year = today.get(Calendar.YEAR);
            int month = today.get(Calendar.MONTH);
            int day = today.get(Calendar.DATE);

            DatePickerDialog dlg = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    startDate = String.valueOf(year + (month + 1) + dayOfMonth);
                    calendar_start.set(year, month + 1, dayOfMonth);
                }
            }, year, month, day);
            dlg.show();
        });
        binding.endBtn.setOnClickListener(v -> {
            GregorianCalendar today = new GregorianCalendar();
            int year = today.get(Calendar.YEAR);
            int month = today.get(Calendar.MONTH);
            int day = today.get(Calendar.DATE);

            DatePickerDialog dlg = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    endDate = String.valueOf(year + (month + 1) + dayOfMonth);
                    calendar_end.set(year, month + 1, dayOfMonth);

                    long finalDate = TimeUnit.MILLISECONDS.toDays(
                            calendar_end.getTimeInMillis() - calendar_start.getTimeInMillis()
                    );
                    binding.finalDate.setText(String.valueOf(finalDate));
                }
            }, year, month, day);
            dlg.show();
        });
    }

}