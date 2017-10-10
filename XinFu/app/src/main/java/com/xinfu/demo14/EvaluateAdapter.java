package com.xinfu.demo14;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinfu.R;

/**
 * <pre>
 *     author : xiatian
 *     time   : 2017/09/14
 *     desc   :
 * </pre>
 */
public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.EvaluateHolder> {
    @Override
    public EvaluateAdapter.EvaluateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_evaluate, null);
        return new EvaluateHolder(view);
    }

    @Override
    public void onBindViewHolder(EvaluateAdapter.EvaluateHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class EvaluateHolder extends RecyclerView.ViewHolder {
        Button btnLookEvaluate, btnAgainEvaluate;
        ImageView iv_icon;
        TextView tvTitle, tvPoint;

        public EvaluateHolder(View view) {
            super(view);
            btnLookEvaluate = (Button) view.findViewById(R.id.btn_look_evaluate);
            btnAgainEvaluate = (Button) view.findViewById(R.id.btn_again_evaluate);
            tvPoint = (TextView) view.findViewById(R.id.tv_point);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
        }
    }
}
