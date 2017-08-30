package com.example.lee.myapplication;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BusinessListAdapter01 extends RecyclerView.Adapter<BusinessListAdapter01.ViewHolder> {

    private List<BusinessList01> mBusinessList01;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView businesstype;
        TextView businessname;
        TextView businesssegment;
        TextView businesstime;
        TextView businessdealer;
        View businessView;
        public ViewHolder(View view){
            super(view);
            businessView = view;
            businesstype=(TextView)view.findViewById(R.id.type);
            businessname=(TextView)view.findViewById(R.id.name);
            businesssegment=(TextView)view.findViewById(R.id.segment);
            businesstime=(TextView)view.findViewById(R.id.time);
            businessdealer=(TextView)view.findViewById(R.id.dealer);
        }
    }
    public BusinessListAdapter01(List<BusinessList01> businessList01){
        mBusinessList01=businessList01;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.businesslist_item01,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.businessView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                BusinessList01 businessList01 = mBusinessList01.get(position);
                //Toast.makeText(v.getContext(),businessList01.getBinderDocIDOS(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), TravelList.class);
                intent.putExtra("str1","ToDo");
                intent.putExtra("str2", businessList01.getBinderDocIDOS());
                v.getContext().startActivity(intent);

            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        BusinessList01 businessList01=mBusinessList01.get(position);
        holder.businesstype.setText(businessList01.getType());
        holder.businessname.setText(businessList01.getName());
        holder.businesssegment.setText(businessList01.getSegment());
        holder.businesstime.setText(businessList01.getTime());
        holder.businessdealer.setText(businessList01.getDealer());
    }
    @Override
    public int getItemCount(){
        return mBusinessList01.size();
    }
}
