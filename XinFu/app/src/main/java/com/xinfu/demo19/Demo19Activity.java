package com.xinfu.demo19;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinfu.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/09
 *     desc   :
 * </pre>
 */
public class Demo19Activity extends AppCompatActivity {
    @InjectView(R.id.historylineview)
    HistoryLineView historylineview;
    ArrayList<HistoryInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo19);
        ButterKnife.inject(this);
        init();
        historylineview.upDateData(list);
    }

    private void init() {
        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    HistoryInfo info = new HistoryInfo();
                    info.date = "10-7";
                    info.money = 15;
                    list.add(info);
                    break;
                case 1:
                    HistoryInfo info1 = new HistoryInfo();
                    info1.date = "10-8";
                    info1.money = 60;
                    list.add(info1);
                    break;
                case 2:
                    HistoryInfo info2 = new HistoryInfo();
                    info2.date = "10-9";
                    info2.money = 87;
                    list.add(info2);
                    break;
                case 3:
                    HistoryInfo info3 = new HistoryInfo();
                    info3.date = "10-10";
                    info3.money = 39;
                    list.add(info3);
                    break;
                case 4:
                    HistoryInfo info4 = new HistoryInfo();
                    info4.date = "10-11";
                    info4.money = 5;
                    list.add(info4);
                    break;
                case 5:
                    HistoryInfo info5 = new HistoryInfo();
                    info5.date = "10-12";
                    info5.money = 15;
                    list.add(info5);
                    break;
                case 6:
                    HistoryInfo info6 = new HistoryInfo();
                    info6.date = "10-13";
                    info6.money = 99;
                    list.add(info6);
                    break;

            }
        }
    }
}
