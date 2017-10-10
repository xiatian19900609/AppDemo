package com.xinfu.demo17;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>定时
 *     author : xiatian
 *     time   : 2017/09/21
 *     desc   :
 * </pre>
 */
public class Demo17Activity extends AppCompatActivity {
    private static final String TAG = "Demo17Activity";
    @InjectView(R.id.timeview)
    TimingView timeview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo17);
        ButterKnife.inject(this);
        timeview.setListener(new TimingView.OnTimeListener() {
            @Override
            public void onTimeStateChange(int status) {
                switch (status) {
                    case 0:
                        Toast.makeText(Demo17Activity.this, "10秒", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(Demo17Activity.this, "30秒", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(Demo17Activity.this, "60秒", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(Demo17Activity.this, "300秒", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

}
