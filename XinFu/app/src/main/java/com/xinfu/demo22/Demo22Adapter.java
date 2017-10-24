package com.xinfu.demo22;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.xinfu.R;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/17
 *     desc   :
 * </pre>
 */
public class Demo22Adapter extends RecyclerView.Adapter<Demo22Adapter.MyViewHolder> {
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View.inflate(parent.getContext() , R.layout.item_demo22, null);
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
