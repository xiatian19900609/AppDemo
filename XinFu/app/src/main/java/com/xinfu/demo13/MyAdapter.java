package com.xinfu.demo13;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xinfu.R;

import java.util.List;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/08/21
 *     desc   :
 * </pre>
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<BeanInfo> lists;

    public MyAdapter(List<BeanInfo> lists) {
        this.lists = lists;
    }

    public void setData(List<BeanInfo> lists) {
        this.lists = lists;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_test, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BeanInfo obj = lists.get(position);
        holder.payName.setText(obj.name);
    }

    @Override
    public int getItemCount() {
        if (lists != null) {
            return lists.size();
        }
        return 0;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView payName;

        public MyViewHolder(View view) {
            super(view);
            payName = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
