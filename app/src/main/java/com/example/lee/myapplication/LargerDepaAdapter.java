package com.example.lee.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LargerDepaAdapter extends RecyclerView.Adapter<LargerDepaAdapter.ViewHolder> {
    private Context context;
    private List<LargerDepartments> mLargerDepaList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View LargerDepaView;
        Button larger_depart;
        public ViewHolder(View view){
            super(view);
            LargerDepaView = view;
            larger_depart=(Button) view.findViewById(R.id.larger_depart);
        }
    }
    public LargerDepaAdapter(List<LargerDepartments> apartmentList){
        mLargerDepaList=apartmentList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.larger_departments,parent,false);
        final ViewHolder holder=new ViewHolder(view);
       /*holder.LargerDepaView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            int position = holder.getAdapterPosition();
            Apartment1 apartment1 = mLargerDepaList.get(position);
            Intent intent = new Intent(v.getContext(), PhoneListdetial.class);
        intent.putExtra("str", apartment1.getItem_name());
        v.getContext().startActivity(intent);
        }
        });*/
        return holder;
        }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        LargerDepartments largerDepartments=mLargerDepaList.get(position);
        holder.larger_depart.setText(largerDepartments.getItem_name());
        }
    @Override
    public int getItemCount(){
        return mLargerDepaList.size();
        }
}
