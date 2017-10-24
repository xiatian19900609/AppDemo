package com.xinfu.demo24;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinfu.R;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/23
 *     desc   :
 * </pre>
 */
public class MtachFragment extends Fragment {

    private String mText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mText = arguments.getString("text");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setTextSize(24f);
        textView.setTextColor(getResources().getColor(R.color.color_e82c2c));
        textView.setText("测试" + mText);
        return textView;
    }
}
