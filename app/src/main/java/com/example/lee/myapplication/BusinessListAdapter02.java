package com.example.lee.myapplication;

import android.content.Intent;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.jar.Attributes;

public class BusinessListAdapter02 extends RecyclerView.Adapter<BusinessListAdapter02.ViewHolder> {

    private List<BusinessList02> mBusinessList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView businesstype;
        TextView businessname;
        TextView businesssegment;
        TextView businesstime;
        View businessView;
        public ViewHolder(View view){
            super(view);
            businessView = view;
            businesstype=(TextView)view.findViewById(R.id.type);
            businessname=(TextView)view.findViewById(R.id.name);
            businesssegment=(TextView)view.findViewById(R.id.segment);
            businesstime=(TextView)view.findViewById(R.id.time);
        }
    }
    public BusinessListAdapter02(List<BusinessList02> businessList){
        mBusinessList=businessList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        final View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.business_item02,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.businessView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                BusinessList02 businessList = mBusinessList.get(position);

                //Toast.makeText(v.getContext(),businessList.getBinderDocIDOS(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), TravelList.class);
                intent.putExtra("str1","Finished");
                intent.putExtra("str2", businessList.getBinderDocIDOS());
                v.getContext().startActivity(intent);

            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        BusinessList02 businessList=mBusinessList.get(position);
        holder.businesstype.setText(businessList.getType());
        holder.businessname.setText(businessList.getName());
        holder.businesssegment.setText(businessList.getSegment());
        holder.businesstime.setText(businessList.getTime());
    }
    @Override
    public int getItemCount(){
        return mBusinessList.size();
    }
}
