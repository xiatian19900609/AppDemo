package com.xinfu.demo5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xinfu.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/08
 *     desc   :
 * </pre>
 */
public class Demo5Activity extends AppCompatActivity {


    @InjectView(R.id.tvCurrentMonth)
    TextView tvCurrentMonth;
    @InjectView(R.id.btn)
    Button btn;
    @InjectView(R.id.ll_calendar)
    FrameLayout llCalendar;
    private CalendarCard mCalendarCard;
    private ArrayList<Long> mLongs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5);
        ButterKnife.inject(this);
        mLongs = new ArrayList<Long>();
        mLongs.add(1493568000000L);
        mLongs.add(1493654400000L);
        mLongs.add(1493740800000L);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvCurrentMonth.setText(str);

        mCalendarCard = new CalendarCard(this, mLongs, null);
        llCalendar.addView(mCalendarCard);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLongs.add(System.currentTimeMillis());
                mCalendarCard.update(mLongs);
            }
        });
    }
}
