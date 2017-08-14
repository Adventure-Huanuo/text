package com.example.lee.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LargerDepaAdapter extends RecyclerView.Adapter<LargerDepaAdapter.ViewHolder> {
    private List<LargerDepartments> mLargerDepaList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View LargerDepaView;
        TextView larger_depart;
        public ViewHolder(View view){
            super(view);
            LargerDepaView = view;
            larger_depart=(TextView) view.findViewById(R.id.larger_depart);
        }
    }
    public LargerDepaAdapter(List<LargerDepartments> apartmentList){
        mLargerDepaList=apartmentList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.larger_departments, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.LargerDepaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                LargerDepartments largerDepartments = mLargerDepaList.get(position);
                Toast.makeText(v.getContext(),largerDepartments.getDepart_name(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        LargerDepartments largerDepartments=mLargerDepaList.get(position);
        holder.larger_depart.setText(largerDepartments.getDepart_name());
        }
    @Override
    public int getItemCount(){
        return mLargerDepaList.size();
        }
}
