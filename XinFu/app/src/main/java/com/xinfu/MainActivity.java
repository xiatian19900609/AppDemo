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
import com.xinfu.demo10.Demo10Activity;
import com.xinfu.demo11.Demo11Activity;
import com.xinfu.demo12.Demo12C;
import com.xinfu.demo13.Demo13Activity;
import com.xinfu.demo14.Demo14Activity;
import com.xinfu.demo15.Demo15Activity;
import com.xinfu.demo16.Demo16Activity;
import com.xinfu.demo17.Demo17Activity;
import com.xinfu.demo18.Demo18ActivityB;
import com.xinfu.demo19.Demo19Activity;
import com.xinfu.demo2.Demo2Activity;
import com.xinfu.demo20.Demo20Activity;
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
    @InjectView(R.id.btn10)
    Button btn10;
    @InjectView(R.id.btn11)
    Button btn11;
    @InjectView(R.id.btn12)
    Button btn12;
    @InjectView(R.id.btn13)
    Button btn13;
    @InjectView(R.id.btn14)
    Button btn14;
    @InjectView(R.id.btn15)
    Button btn15;
    @InjectView(R.id.btn16)
    Button btn16;
    @InjectView(R.id.btn17)
    Button btn17;
    @InjectView(R.id.btn18)
    Button btn18;
    @InjectView(R.id.btn19)
    Button btn19;
    @InjectView(R.id.btn20)
    Button btn20;

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
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Demo10Activity.class));
            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo11Activity.class);
                startActivity(intent);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo12C.class);
                startActivity(intent);
            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo13Activity.class);
                startActivity(intent);
            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo14Activity.class);
                startActivity(intent);
            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo15Activity.class);
                startActivity(intent);
            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo16Activity.class);
                startActivity(intent);
            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo17Activity.class);
                startActivity(intent);
            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo18ActivityB.class);
                startActivity(intent);
            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo19Activity.class);
                startActivity(intent);
            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Demo20Activity.class);
                startActivity(intent);
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
