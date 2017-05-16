package com.example.lee.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 蔡如男 on 2017/4/17.
 */

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    private List<Huanuo> mFirstPaper;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView paperImage;
        TextView paperName;
        public ViewHolder(View view){
            super(view);
            paperImage = (ImageView) view.findViewById(R.id.paper_image);
            paperName = (TextView) view.findViewById(R.id.paper_name);
        }
    }
    public DocumentAdapter(List<Huanuo>FirstPaper){
        mFirstPaper =FirstPaper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Huanuo frist = mFirstPaper.get(position);
        holder.paperImage.setImageResource(frist.getImageId());
        holder.paperName.setText(frist.getName());
    }
    @Override
    public int getItemCount() {
        return mFirstPaper.size();
    }
}

