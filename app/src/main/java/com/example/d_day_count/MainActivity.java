package com.example.d_day_count;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.d_day_count.databinding.Main;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 구현 기능 목록
 * 1. 시작일과 종료일 입력시 날짜 차이를 출력
 * 2. 앱 실행 시 자신의 생년월일로 카운터 표시
 * 3. 양력 및 음력 변환 기능 추가
 * */
public class MainActivity extends AppCompatActivity {

    Main binding;
    int year, month, day;
    String startDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.startBtn.setOnClickListener(v -> dateDialog());
    }

    public void dateDialog() {
        GregorianCalendar today = new GregorianCalendar();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DATE);
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
        };


    }
}