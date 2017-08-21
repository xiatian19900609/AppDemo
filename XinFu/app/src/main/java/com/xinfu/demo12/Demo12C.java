package com.xinfu.demo12;

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
 *     time   : 2017/07/27
 *     desc   :
 * </pre>
 */
public class Demo12C extends AppCompatActivity {

    @InjectView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo12);
        ButterKnife.inject(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Demo12B b = new Demo12B();
                b.a(getBaseContext());
            }
        });
    }
}
