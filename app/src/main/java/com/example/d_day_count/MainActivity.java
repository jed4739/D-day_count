package com.example.d_day_count;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.d_day_count.databinding.Main;

import org.joda.time.LocalDate;

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

    String startDate, endDate;

    Calendar calendar_start, calendar_end, birth;


    boolean dateChecker;
    boolean gregorianChecker = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        calendar_start = Calendar.getInstance();
        calendar_end = Calendar.getInstance();
        birth = Calendar.getInstance();


        birth.set(2004,5, 5);
        String birthCount = "+" + TimeUnit.MILLISECONDS.toDays(calendar_start.getTimeInMillis()-birth.getTimeInMillis());
        binding.birthCount.setText(birthCount);

        binding.startBtn.setOnClickListener(v -> {
            Log.i("ButtonChecker", "ButtonCheck StartBtn");
            if (gregorianChecker) {
                datePickerDialog(true);
            } else {
                lunarDatePickerDialog(true);
            }
        });
        binding.endBtn.setOnClickListener(v -> {
            Log.i("ButtonChecker", "ButtonCheck EndBtn");
            if (gregorianChecker) {
                datePickerDialog(false);
            } else {
                lunarDatePickerDialog(false);
            }
        });
        binding.calendarBtn.setOnClickListener(v -> {
            Log.i("ButtonChecker", "ButtonCheck CalendarBtn");
            if (dateChecker && binding.calendarBtn.getText() == "양력 달력") {
                Toast("현재 양력달력이 설정되어 있습니다.\n초기화를 진행해주세요.");
            } else if (dateChecker && binding.calendarBtn.getText() == "음력 달력") {
                Toast("현재 음력달력이 설정되어 있습니다.\n초기화를 진행해주세요.");
            } else {
                changeCalendar();
            }
        });
        binding.resetBtn.setOnClickListener(v -> {
            calendar_start = Calendar.getInstance();
            dateChecker = false;
            Toast("초기화 완료");
        });
    }

    private void changeCalendar() {
        Button calendarBtn = binding.calendarBtn;
        if (calendarBtn.getText() == "양력 달력") {
            calendarBtn.setText("음력 달력");
            Toast("음력 달력으로 변경되었습니다.");
            gregorianChecker = false;
        } else {
            calendarBtn.setText("양력 달력");
            Toast("양력 달력으로 변경되었습니다.");
            gregorianChecker = true;
        }
    }
    /*
    * btnChecker = 시작일과 마지막일을 구분하는 변수
    * year, month, day = 선택한 날짜의 값
    * startDate, endDate = 로깅 변수
    * */
    private void datePickerDialog(boolean btnChecker) {
        GregorianCalendar today = new GregorianCalendar();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE);
        DatePickerDialog dlg = new DatePickerDialog(this, (view, year_num, month_num, dayOfMonth) -> {
            if (btnChecker) {
                startDate = year_num + "년 " + (month_num + 1) + "월 " + dayOfMonth + "일";
                Toast("시작일은 " + startDate + " 입니다.");

                calendar_start.set(year_num, month_num + 1, dayOfMonth);
                dateChecker = true;
            } else {
                if (dateChecker) {
                    endDate = year_num + "년 " + (month_num + 1) + "월 " + dayOfMonth + "일";
                    Toast("종료일은 " + endDate + " 입니다.");

                    calendar_end.set(year_num, month_num + 1, dayOfMonth);
                    long finalDate = TimeUnit.MILLISECONDS.toDays((calendar_end.getTimeInMillis() - calendar_start.getTimeInMillis()));
                    if (finalDate < 0) {
                        long i = finalDate * -1;
                        binding.finalDate.setText(String.valueOf(i));
                    } else {
                        binding.finalDate.setText(String.valueOf(finalDate));
                    }
                } else {
                    Toast("시작일을 설정해주세요!");
                }
            }
        },year,month,day);
        dlg.show();
    }

    private void lunarDatePickerDialog(boolean lunar) {
        GregorianCalendar today = new GregorianCalendar();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DATE);
        DatePickerDialog dlg = new DatePickerDialog(this, (view, year_num, month_num, dayOfMonth) -> {
            if (lunar) {
                startDate = year_num + "년 " + (month_num + 1) + "월 " + dayOfMonth + "일";
                Toast("시작일은 " + startDate + " 입니다.");

                calendar_start.set(year_num, month_num + 1, dayOfMonth);
                dateChecker = true;
            } else {
                if (dateChecker) {
                    endDate = year_num + "년 " + (month_num + 1) + "월 " + dayOfMonth + "일";
                    Toast("종료일은 " + endDate + " 입니다.");

                    calendar_end.set(year_num, month_num + 1, dayOfMonth);
                    long finalDate = TimeUnit.MILLISECONDS.toDays((calendar_end.getTimeInMillis() - calendar_start.getTimeInMillis()));
                    if (finalDate < 0) {
                        long i = finalDate * -1;
                        binding.finalDate.setText(String.valueOf(i));
                    } else {
                        binding.finalDate.setText(String.valueOf(finalDate));
                    }
                } else {
                    Toast("시작일을 설정해주세요!");
                }
            }
        },year,month,day);
        dlg.show();
    }

    private void Toast(String toast) {
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }

}