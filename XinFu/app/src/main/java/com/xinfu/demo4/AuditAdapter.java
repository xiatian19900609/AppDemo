package com.xinfu.demo4;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
public class AuditAdapter extends RecyclerView.Adapter<AuditAdapter.MyViewHolder> {
    private List<DataBean> mLists;

    public void setData(List<DataBean> lists) {
        this.mLists = lists;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_test, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataBean bean = mLists.get(position);
        holder.tvName.setText(bean.T_name);

    }

    @Override
    public int getItemCount() {
        if (mLists != null) {
            return mLists.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
