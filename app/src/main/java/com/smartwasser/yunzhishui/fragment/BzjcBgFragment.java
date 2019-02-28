package com.smartwasser.yunzhishui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartwasser.yunzhishui.R;
import com.smartwasser.yunzhishui.adapter.alarm.BjcbgAdapter;

/**
 * Name: BzjcDtFragment
 * Author: Administrator
 * Comment: //TODO
 * Date: 2019-02-28 10:30
 */
public class BzjcBgFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bzjcbg, null);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BjcbgAdapter bjcbgAdapter = new BjcbgAdapter(getActivity());
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        //    divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.custom_divider));
        mRecyclerview.addItemDecoration(divider);
        mRecyclerview.setAdapter(bjcbgAdapter);



    }

}
