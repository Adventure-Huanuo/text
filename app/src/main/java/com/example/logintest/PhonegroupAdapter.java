package com.example.logintest;

import android.content.Intent;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */

public class PhonegroupAdapter extends RecyclerView.Adapter<PhonegroupAdapter.ViewHolder> {
    private List<PhoneGroup> mPhoneGroup;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View phonegroupView;
        ImageView phonegroupImage;
        TextView phonegroupName;
        public ViewHolder(View view){
            super(view);
            phonegroupView=view;
            phonegroupImage=(ImageView)view.findViewById(R.id.phone_group_image);
            phonegroupName=(TextView)view.findViewById(R.id.phone_group_name);
        }
    }
    public  PhonegroupAdapter(List<PhoneGroup> phoneGroupList){
        mPhoneGroup=phoneGroupList ;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.phonegroup_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.phonegroupView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                PhoneGroup phoneGroup=mPhoneGroup.get(position);
                Toast.makeText(v.getContext(), "ok with "+phoneGroup.getGroupname(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        PhoneGroup phonegroup=mPhoneGroup.get(position);
        holder.phonegroupImage.setImageResource(phonegroup.getGroupId());
        holder.phonegroupName.setText(phonegroup.getGroupname());
    }
    @Override
    public int getItemCount(){
        return mPhoneGroup.size();
    }
}
