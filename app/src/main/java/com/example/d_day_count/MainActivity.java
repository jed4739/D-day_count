package com.example.d_day_count;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.d_day_count.databinding.Main;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * 구현 기능 목록
 * 1. 시작일과 종료일 입력시 날짜 차이를 출력 - 완
 * 2. 앱 실행 시 자신의 생년월일로 카운터 표시
 * 3. 양력 및 음력 변환 기능 추가
 * */
public class MainActivity extends AppCompatActivity {

    Main binding;

    String startDate;
    String endDate;

    Calendar calendar_start;
    Calendar calendar_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        calendar_start = Calendar.getInstance();
        calendar_end = Calendar.getInstance();

        binding.startBtn.setOnClickListener(v -> {
            Log.i("ButtonChecker", "ButtonCheck True");
            datePickerDialog(true);
        });
        binding.endBtn.setOnClickListener(v -> {
            Log.i("ButtonChecker", "ButtonCheck False");
            datePickerDialog(false);
        });
    }
    /*
    * btnChecker = 시작일과 마지막일을 구분하는 변수
    * year, month, day = 선택한 날짜의 값
    * startDate, endDate = 로깅 변수
    * */
    public void datePickerDialog(boolean btnChecker) {
        GregorianCalendar today = new GregorianCalendar();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE);

        DatePickerDialog dlg = new DatePickerDialog(this, (view, year_num, month_num, dayOfMonth) -> {
            if (btnChecker == true) {
                startDate = year_num + "년 " + (month_num + 1) + "월 " + dayOfMonth + "일";
                Log.i("Date","startDate - " + startDate);
                calendar_start.set(year_num, month_num + 1, dayOfMonth);
                Toast.makeText(getApplicationContext(), "시작일은 " + startDate + " 입니다.", Toast.LENGTH_LONG).show();
            }else{
                endDate = year_num + "년 " + (month_num + 1) + "월 " + dayOfMonth + "일";
                Log.i("Date","startDate - " + startDate);
                Log.i("Date","startDate - " + endDate);
                calendar_end.set(year, month + 1, dayOfMonth);
                long finalDate = TimeUnit.MILLISECONDS.toDays(
                        calendar_end.getTimeInMillis() - calendar_start.getTimeInMillis()
                );
                binding.finalDate.setText(String.valueOf(finalDate));
                Toast.makeText(getApplicationContext(), "종료일은 " + endDate + " 입니다.", Toast.LENGTH_LONG).show();
            }
        },year,month,day);
        dlg.show();
    }

}