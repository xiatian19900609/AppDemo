package com.xinfu.demo11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/07/12
 *     desc   :
 * </pre>
 */
public class Demo11ChildFragment extends Fragment {
    private static final String TAG = "Demo11ChildFragment";
    private static final String TYPE = "type";
    @InjectView(R.id.tv)
    TextView tv;
    private String mS;
    private boolean isOnCreate;

    public static Demo11ChildFragment newInstance(String s) {
        Bundle args = new Bundle();
        args.putString(TYPE, s);
        Demo11ChildFragment fragment = new Demo11ChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mS = bundle.getString(TYPE);
        isOnCreate = true;
        Log.d(TAG, "onCreate: " + mS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo7, container, false);
        ButterKnife.inject(this, view);
        Log.d(TAG, "onCreateView: " + mS);
        setUserVisibleHint(getUserVisibleHint());
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isOnCreate) {
            Log.d(TAG, "setUserVisibleHint: " + mS);
            tv.setText(mS);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
