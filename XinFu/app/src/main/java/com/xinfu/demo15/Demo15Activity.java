package com.xinfu.demo15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.xinfu.R;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/20
 *     desc   :
 * </pre>
 */
public class Demo15Activity extends AppCompatActivity {
    @InjectView(R.id.btn)
    Button btn;
    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo15);
        ButterKnife.inject(this);
        DbManger.getIntance(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int nextInt = random.nextInt(50);
                DbManger.add("summer" + nextInt, String.valueOf(nextInt));
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbManger.searchAllData();
            }
        });
    }
}
