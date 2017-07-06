package com.xinfu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinfu.demo1.Demo1Activity;
import com.xinfu.demo2.Demo2Activity;
import com.xinfu.demo3.Demo3Activity;
import com.xinfu.demo4.Demo4Activity;
import com.xinfu.demo5.Demo5Activity;
import com.xinfu.demo6.Demo6Activity;
import com.xinfu.demo7.Demo7Activity;
import com.xinfu.demo8.Demo8Activity;
import com.xinfu.demo9.Demo9Activity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.btn3)
    Button btn3;
    @InjectView(R.id.btn5)
    Button btn5;
    @InjectView(R.id.btn4)
    Button btn4;
    @InjectView(R.id.btn6)
    Button btn6;
    @InjectView(R.id.btn7)
    Button btn7;
    @InjectView(R.id.btn8)
    Button btn8;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.btn9)
    Button btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (getIntent().hasExtra("demo8")) {
            String demo8 = getIntent().getStringExtra("demo8");
            tv.setText(demo8);
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo1Activity.class));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo2Activity.class));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo3Activity.class));
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo4Activity.class));
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo5Activity.class));
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo6Activity.class));
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo7Activity.class));
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo8Activity.class));
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo9Activity.class));
            }
        });
        requestLocalPermission();
    }

    /**
     * android 6.0 申请权限
     */
    private void requestLocalPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_PHONE_STATE,
                    },
                    0);
        }
    }
}
