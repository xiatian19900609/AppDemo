package com.xinfu.demo20;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/13
 *     desc   :
 * </pre>
 */
public class Demo20Activity extends AppCompatActivity {
    //各维度标题
    private String[] titles = {"场均助攻", "场均篮板", "场均抢断", "场均盖帽", "场均得分"};
    //各维度分值
    private float[] data = {50.0f, 30.0f, 100.0f, 80.0f, 10.0f};
    private float[] data1 = {70.0f, 50.0f, 20.0f, 100.0f, 50.0f};

    @InjectView(R.id.creditScoreView)
    PolygonView creditScoreView;
    @InjectView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo20);
        ButterKnife.inject(this);
        creditScoreView.setData(titles, data);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creditScoreView.setData(data1);
            }
        });
    }
}
