package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Chronometer timer;
    Button start, end;
    RadioGroup rGroup;
    RadioButton rbtn1, rbtn2;
    CalendarView cal;
    TimePicker timePicker;
    TextView[] textViews = new TextView[5];
    Integer[] textViewId = {R.id.t1, R.id.t2, R.id.t3, R.id.t4, R.id.t5};
    Integer t_year, t_month, t_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");

        timer = (Chronometer)findViewById(R.id.timer);
        start = (Button)findViewById(R.id.start);
        end = (Button)findViewById(R.id.end);
        rGroup = (RadioGroup)findViewById(R.id.rGroup);
        rbtn1 = (RadioButton)findViewById(R.id.rbtn1);
        rbtn2 = (RadioButton)findViewById(R.id.rbtn2);
        cal = (CalendarView)findViewById(R.id.cal);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        for(int i=0; i<textViewId.length; i++)
            textViews[i] = (TextView)findViewById(textViewId[i]);

        cal.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);
        rGroup.clearCheck();

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbtn1){
                    cal.setVisibility(View.VISIBLE);
                    timePicker.setVisibility(View.INVISIBLE);
                }
                else if(checkedId == R.id.rbtn2){
                    cal.setVisibility(View.INVISIBLE);
                    timePicker.setVisibility(View.VISIBLE);
                } else{
                    cal.setVisibility(View.INVISIBLE);
                    timePicker.setVisibility(View.INVISIBLE);
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
                timer.setTextColor(Color.RED);
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.stop();
                timer.setTextColor(Color.BLUE);
                textViews[0].setText(Integer.toString(t_year));
                textViews[1].setText(Integer.toString(t_month));
                textViews[2].setText(Integer.toString(t_day));
                textViews[3].setText(Integer.toString(timePicker.getCurrentHour()));
                textViews[4].setText(Integer.toString(timePicker.getCurrentMinute()));
            }
        });

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                t_year = year;
                t_month = month+1;
                t_day = dayOfMonth;
            }
        });

    }
}