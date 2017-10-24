package com.xinfu.demo21;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/16
 *     desc   :
 * </pre>
 */
public class Demo21Activity extends AppCompatActivity {
    @InjectView(R.id.tcp)
    ThreeColorProgress tcp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo21);
        ButterKnife.inject(this);
        tcp.setData(3, 2, 4);
    }
}
