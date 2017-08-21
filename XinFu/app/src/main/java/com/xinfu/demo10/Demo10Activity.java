package com.xinfu.demo10;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xinfu.R;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/07/07
 *     desc   :
 * </pre>
 */
public class Demo10Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo10);
        StatusBarUtil.transparencyBar(this);
//        StatusBarUtil.StatusBarLightMode(this, i);
//        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
    }
}
