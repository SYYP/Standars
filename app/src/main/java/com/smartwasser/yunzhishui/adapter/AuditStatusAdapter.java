package com.smartwasser.yunzhishui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.bean.AduitStateResponse;

/**
 * Created by huangmengjie on 2016/7/20 0020.
 */
public class AuditStatusAdapter extends BaseAdapter{
    private Context mContext;
    private AduitStateResponse mAudit;
    public AuditStatusAdapter(Context mContext,AduitStateResponse mAudit){
          this.mContext=mContext;
          this.mAudit=mAudit;
    }
    @Override
    public int getCount() {
        return mAudit.getData().getPayOperationProcess().getRows().size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.item_audit_state,null);
            holder=new ViewHolder();
            holder.icon_state= (ImageView) convertView.findViewById(R.id.icon_state);
            holder.state_time= (TextView) convertView.findViewById(R.id.state_time);
            holder.state_sqr= (TextView) convertView.findViewById(R.id.state_sqr);
            holder.state_type= (TextView)convertView.findViewById(R.id.state_type);
            holder.audit_state= (TextView) convertView.findViewById(R.id.audit_state);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder)convertView.getTag();
        }
        String yj=mAudit.getData().getPayOperationProcess().getRows().get(position).getOperResult();
        if("同意".equals(yj)){
            holder.icon_state.setBackgroundResource(R.drawable.noaudit);
        }else{
            holder.icon_state.setBackgroundResource(R.drawable.audit);
        }
        holder.audit_state.setText(yj);
        holder.state_time.setText(mAudit.getData().getPayOperationProcess().getRows().get(position).getOperTime());
        holder.state_sqr.setText(mAudit.getData().getPayOperationProcess().getRows().get(position).getOperUsernamecn());
        holder.state_type.setText(mAudit.getData().getPayOperationProcess().getRows().get(position).getFlownode());
        return convertView;
    }
     class ViewHolder{
         ImageView icon_state;
         TextView state_time,state_sqr,state_type,audit_state;
     }
}
