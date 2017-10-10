package com.xinfu.demo15;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xinfu.R;

import java.util.ArrayList;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/20
 *     desc   :
 * </pre>
 */
public class Demo15Adapter extends BaseAdapter {
    private ArrayList<ProductInfo> productInfos;

    @Override
    public int getCount() {
        if (productInfos != null) {
            return productInfos.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View.inflate(viewGroup.getContext(), R.layout.item_demo15, null);
        return null;
    }
}
