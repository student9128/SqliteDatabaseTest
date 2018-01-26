package com.kevin.sqlitedatabasetest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.sqlitedatabasetest.database.DataEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> on 2018/1/25.
 * <h3>Description:</h3>
 * <div>
 * </div>
 */


public class RecyclerGreenAdapter extends RecyclerView.Adapter<RecyclerGreenAdapter.MyViewHolder> {

    private Context context;
    private List<DataEntity> dataEntityList;

    public RecyclerGreenAdapter(Context context, List<DataEntity> dataEntityList) {
        this.context = context;
        this.dataEntityList = dataEntityList;
    }

    public void showData(List<DataEntity> data) {
        dataEntityList.clear();
        dataEntityList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycler_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataEntity dataEntity = dataEntityList.get(position);
        holder.tvName.setText(dataEntity.getName());
        holder.tvAge.setText(dataEntity.getAge());
    }

    @Override
    public int getItemCount() {
        return dataEntityList == null ? 0 : dataEntityList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_age)
        TextView tvAge;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
