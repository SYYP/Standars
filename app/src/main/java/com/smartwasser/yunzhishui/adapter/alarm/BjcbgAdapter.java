package com.smartwasser.yunzhishui.adapter.alarm;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.bean.BzjcbgBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: BjcbgAdapter
 * Author: Administrator
 * Comment: //TODO
 * Date: 2019-03-01 15:42
 */
public class BjcbgAdapter extends RecyclerView.Adapter<BjcbgAdapter.MyViewHolder> {
    private Context mContext;
    private MyViewHolder myViewHolder;
    private List<BzjcbgBean> mBzjcbgBeans = new ArrayList<>();

    public BjcbgAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_bzjcbg, parent, false));
        return myViewHolder;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextBzbh.setText(mBzjcbgBeans.get(position).getBzbh());
        holder.mTextBzmc.setText(mBzjcbgBeans.get(position).getBzzmc());
        if ("1".equals(mBzjcbgBeans.get(position).getBzzty())) {
            holder.mImgY.setImageDrawable(mContext.getDrawable(R.drawable.shap_roal));
        } else if ("2".equals(mBzjcbgBeans.get(position).getBzzty())) {
            holder.mImgY.setImageDrawable(mContext.getDrawable(R.drawable.shap_green));
        } else if ("3".equals(mBzjcbgBeans.get(position).getBzzty())) {
            holder.mImgY.setImageDrawable(mContext.getDrawable(R.drawable.shap_blue));
        }
        if ("1".equals(mBzjcbgBeans.get(position).getBzzte())) {
            holder.mImgE.setImageDrawable(mContext.getDrawable(R.drawable.shap_roal));
        } else if ("2".equals(mBzjcbgBeans.get(position).getBzzte())) {
            holder.mImgE.setImageDrawable(mContext.getDrawable(R.drawable.shap_green));
        } else if ("3".equals(mBzjcbgBeans.get(position).getBzzte())) {
            holder.mImgE.setImageDrawable(mContext.getDrawable(R.drawable.shap_blue));
        }
        if ("1".equals(mBzjcbgBeans.get(position).getBzzts())) {
            holder.mImgS.setImageDrawable(mContext.getDrawable(R.drawable.shap_roal));
        } else if ("2".equals(mBzjcbgBeans.get(position).getBzzts())) {
            holder.mImgS.setImageDrawable(mContext.getDrawable(R.drawable.shap_green));
        } else if ("3".equals(mBzjcbgBeans.get(position).getBzzts())) {
            holder.mImgS.setImageDrawable(mContext.getDrawable(R.drawable.shap_blue));
        }
        if ("1".equals(mBzjcbgBeans.get(position).getBzztsi())) {
            holder.mImgSi.setImageDrawable(mContext.getDrawable(R.drawable.shap_roal));
        } else if ("2".equals(mBzjcbgBeans.get(position).getBzztsi())) {
            holder.mImgSi.setImageDrawable(mContext.getDrawable(R.drawable.shap_green));
        } else if ("3".equals(mBzjcbgBeans.get(position).getBzztsi())) {
            holder.mImgSi.setImageDrawable(mContext.getDrawable(R.drawable.shap_blue));
        }
        if ("1".equals(mBzjcbgBeans.get(position).getBzztw())) {
            holder.mImgW.setImageDrawable(mContext.getDrawable(R.drawable.shap_roal));
        } else if ("2".equals(mBzjcbgBeans.get(position).getBzztw())) {
            holder.mImgW.setImageDrawable(mContext.getDrawable(R.drawable.shap_green));
        } else if ("3".equals(mBzjcbgBeans.get(position).getBzztw())) {
            holder.mImgW.setImageDrawable(mContext.getDrawable(R.drawable.shap_blue));
        }
        if ("1".equals(mBzjcbgBeans.get(position).getBzztl())) {
            holder.mImgL.setImageDrawable(mContext.getDrawable(R.drawable.shap_roal));
        } else if ("2".equals(mBzjcbgBeans.get(position).getBzztl())) {
            holder.mImgL.setImageDrawable(mContext.getDrawable(R.drawable.shap_green));
        } else if ("3".equals(mBzjcbgBeans.get(position).getBzztl())) {
            holder.mImgL.setImageDrawable(mContext.getDrawable(R.drawable.shap_blue));
        }
        holder.mTextYsl.setText(mBzjcbgBeans.get(position).getYsl());
        holder.mTextYl.setText(mBzjcbgBeans.get(position).getYsl());

    }


    @Override
    public int getItemCount() {
        for (int i = 0; i < 10; i++) {
            BzjcbgBean bzjcbgBean = new BzjcbgBean();
            bzjcbgBean.setBzbh(100 + i + "");
            bzjcbgBean.setBzzmc("西二旗");
            bzjcbgBean.setBzzty("1");
            bzjcbgBean.setBzzte("2");
            bzjcbgBean.setBzzts("1");
            bzjcbgBean.setBzztsi("1");
            bzjcbgBean.setBzztw("3");
            bzjcbgBean.setBzztl("2");
            bzjcbgBean.setYl(25.0 + i + "");
            bzjcbgBean.setYsl(1 + i + "");
            mBzjcbgBeans.add(bzjcbgBean);
        }
        return mBzjcbgBeans.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        /*
         **
         * 泵站编号
         */
        private TextView mTextBzbh;
        /**
         * 泵站名称
         */
        private TextView mTextBzmc;
        private ImageView mImgY;
        private ImageView mImgE;
        private ImageView mImgS;
        private ImageView mImgSi;
        private ImageView mImgW;
        private ImageView mImgL;
        /**
         * 油升量m²/s
         */
        private TextView mTextYsl;
        /**
         * 雨量mm
         */
        private TextView mTextYl;

        public MyViewHolder(View view) {
            super(view);
            mTextBzbh = (TextView) view.findViewById(R.id.text_bzbh);
            mTextBzmc = (TextView) view.findViewById(R.id.text_bzmc);
            mImgY = (ImageView) view.findViewById(R.id.img_y);
            mImgE = (ImageView) view.findViewById(R.id.img_e);
            mImgS = (ImageView) view.findViewById(R.id.img_s);
            mImgSi = (ImageView) view.findViewById(R.id.img_si);
            mImgW = (ImageView) view.findViewById(R.id.img_w);
            mImgL = (ImageView) view.findViewById(R.id.img_l);
            mTextYsl = (TextView) view.findViewById(R.id.text_ysl);
            mTextYl = (TextView) view.findViewById(R.id.text_yl);
        }
    }
}
