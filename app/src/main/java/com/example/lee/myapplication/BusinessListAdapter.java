package com.example.lee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.ViewHolder> {

    private List<BusinessList> mBusinessList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView businesstype;
        TextView businessname;
        TextView businesssegment;
        TextView businesstime;
        TextView businessdealer;
        public ViewHolder(View view){
            super(view);
            businesstype=(TextView)view.findViewById(R.id.type);
            businessname=(TextView)view.findViewById(R.id.name);
            businesssegment=(TextView)view.findViewById(R.id.segment);
            businesstime=(TextView)view.findViewById(R.id.time);
            businessdealer=(TextView)view.findViewById(R.id.dealer);
        }
    }
    public BusinessListAdapter(List<BusinessList> businessList){
        mBusinessList=businessList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.businesslist_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        BusinessList businessList=mBusinessList.get(position);
        holder.businesstype.setText(businessList.getType());
        holder.businessname.setText(businessList.getName());
        holder.businesssegment.setText(businessList.getSegment());
        holder.businesstime.setText(businessList.getTime());
        holder.businessdealer.setText(businessList.getDealer());
    }
    @Override
    public int getItemCount(){
        return mBusinessList.size();
    }
}
