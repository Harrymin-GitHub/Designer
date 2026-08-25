package com.minhr.design.module_discover.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minhr.design.module_discover.R;

/**
 * author : Harrymin
 * e-mail : zhoujuan26@jd.com
 * date   : 2026/8/25 7:38 PM
 * desc   : 布局（视图）、布局上的子元素、将数据绑定到视图上
 */
class ADA_Test extends RecyclerView.Adapter<ADA_Test.TestViewHolder> {
    final Context context;
    ADA_Test(Context context){
        this.context = context;
    }

    static class TestViewHolder extends RecyclerView.ViewHolder{
        View tvDes;
        public TestViewHolder(View itemView) {
            super(itemView);
            tvDes = itemView.findViewById(R.id.tv_des);
        }
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.act_category_detail, parent,
                false);
        return new TestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        holder.tvDes.setAlpha(1);
        if(holder.getItemViewType() == 1){
            //....
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2 ==1 ){
            return 1;
        }else{
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}