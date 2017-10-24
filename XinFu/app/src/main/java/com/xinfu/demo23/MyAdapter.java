package com.xinfu.demo23;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinfu.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/10/19
 *     desc   :
 * </pre>
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;

    private String[] str;

    public MyAdapter(Context context, String[] str) {
        mContext = context;
        this.str = str;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_demo22, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        float screenHeight = getScreenWidth(mContext) / 7f;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.llParent.getLayoutParams();
        layoutParams.width = (int) screenHeight;
        holder.llParent.setLayoutParams(layoutParams);
        holder.tvNum.setText(str[position]);
    }

    @Override
    public int getItemCount() {
        return str.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.ll_parent)
        LinearLayout llParent;
        @InjectView(R.id.tv_num)
        TextView tvNum;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }


    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
