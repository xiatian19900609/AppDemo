package com.xinfu.demo6;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/05/10
 *     desc   :
 * </pre>
 */
public class Demo6Activity extends AppCompatActivity {
    @InjectView(R.id.username)
    ClearEditText username;
    @InjectView(R.id.password)
    ClearEditText password;
    @InjectView(R.id.login)
    Button login;
    @InjectView(R.id.main)
    LinearLayout main;


    private String passWord;
    private String userName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo6);
        ButterKnife.inject(this);
        addTextChanged(username, 1);
        addTextChanged(password, 2);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeKeybord(username,Demo6Activity.this);
                closeKeybord(password,Demo6Activity.this);
            }
        });
    }

    private void addTextChanged(EditText editText, final int type) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (type == 1) {
                    userName = charSequence.toString();
                } else if (type == 2) {
                    passWord = charSequence.toString();
                }

                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
                    login.setSelected(true);
                } else {
                    login.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText
     *            输入框
     * @param mContext
     *            上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }
}
