package com.xinfu.demo4;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xinfu.R;

import java.util.List;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/04/27
 *     desc   :
 * </pre>
 */
public class FilterAdapter extends BaseAdapter {
    private List<String> mLists;
    private int selectorPosition;

    public void setData(List<String> lists) {
        this.mLists = lists;
        selectorPosition = -1;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mLists != null) {
            return mLists.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), R.layout.item_filter, null);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        String s = mLists.get(position);
        holder.tvType.setText(s);

        if (selectorPosition == position) {
            holder.tvType.setSelected(true);
        } else {
            holder.tvType.setSelected(false);
        }
        return view;
    }

    public void changeState(int pos) {
        selectorPosition = pos;
        notifyDataSetChanged();
    }

    class MyViewHolder {
        TextView tvType;

        public MyViewHolder(View itemView) {
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
        }
    }
}
