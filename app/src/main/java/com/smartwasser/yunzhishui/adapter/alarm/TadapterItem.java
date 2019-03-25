package com.smartwasser.yunzhishui.adapter.alarm;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.bean.RmonMenuResponse;

import java.util.List;

/**
 * Created by 15810 on 2019/2/22.
 */

public class TadapterItem extends BaseAdapter{
    private Context mContent;

    public TadapterItem(Context mContent, List<String> mList) {
        this.mContent = mContent;
        this.mList = mList;
    }

    private List<String> mList;
    @Override
    public int getCount() {
        return mList!=null?mList.size():0;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            view = View.inflate(mContent, R.layout.item_test,null);
            viewHolder = new ViewHolder();
            viewHolder.tv = view.findViewById(R.id.item_test_tv);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv.setText(mList.get(i));
        return view;
    }
    class ViewHolder{
        TextView tv;
    }
}
