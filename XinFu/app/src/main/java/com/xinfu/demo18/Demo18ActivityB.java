package com.xinfu.demo18;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/10
 *     desc   :
 * </pre>
 */
public class Demo18ActivityB extends AppCompatActivity {
    @InjectView(R.id.btn_explode)
    Button btnExplode;
    @InjectView(R.id.btn_slide)
    Button btnSlide;
    @InjectView(R.id.btn_fade)
    Button btnFade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo18_b);
        ButterKnife.inject(this);
        setupWindowAnimations();
        btnExplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Demo18ActivityB.this, Demo18ActivityA.class);
                startActivity(intent);
            }
        });
        btnSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Demo18ActivityB.this, Demo18ActivityA.class);
                startActivity(intent);
            }
        });
        btnFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Demo18ActivityB.this, Demo18ActivityA.class);
                startActivity(intent);
            }
        });
    }

    private void setupWindowAnimations() {
        Slide slide = (Slide) TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
        getWindow().setExitTransition(slide);
    }


}
