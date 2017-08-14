package com.example.lee.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BusinessListAdapter extends RecyclerView.Adapter<BusinessListAdapter.ViewHolder> {

    private List<BusinessList> mBusinessList;
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
    public BusinessListAdapter(List<BusinessList> businessList){
        mBusinessList=businessList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.businesslist_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.businessView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                BusinessList businessList = mBusinessList.get(position);
                Toast.makeText(v.getContext(),businessList.getBinderDocIDOS(),Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(v.getContext(), PhoneListdetial.class);
                //intent.putExtra("str", businessList.getBinderDocIDOS());
                //v.getContext().startActivity(intent);

            }
        });
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
