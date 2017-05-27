package com.example.lee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ApartmentAdapter2 extends RecyclerView.Adapter<ApartmentAdapter2.ViewHolder> {
    private List<Apartment> mapartmentList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName;
        TextView itemDuty;
        TextView itemApar;
        TextView itemNumber;
        TextView itemEmail;
        TextView itemBase;
        public ViewHolder(View view){
            super(view);
            itemImage=(ImageView)view.findViewById(R.id.item_image);
            itemName=(TextView)view.findViewById(R.id.item_name);
            itemDuty=(TextView)view.findViewById(R.id.item_duty);
            itemApar=(TextView)view.findViewById(R.id.item_apar);
            itemNumber=(TextView)view.findViewById(R.id.item_number);
            itemEmail=(TextView)view.findViewById(R.id.item_email);
            itemBase=(TextView)view.findViewById(R.id.item_base);
        }
    }
    public ApartmentAdapter2(List<Apartment> apartmentList){
        mapartmentList=apartmentList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.apartmentlist_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Apartment apartment=mapartmentList.get(position);
        holder.itemImage.setImageResource(apartment.getItem_imageId());
        holder.itemName.setText(apartment.getItem_name());
        holder.itemDuty.setText(apartment.getItem_duty());
        holder.itemApar.setText(apartment.getItem_apar());
        holder.itemNumber.setText(apartment.getItem_number());
        holder.itemEmail.setText(apartment.getItem_email());
        holder.itemBase.setText(apartment.getItem_base());
    }
    @Override
    public int getItemCount(){
        return mapartmentList.size();
    }
}
