package com.example.lee.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ApartmentAdapter3 extends RecyclerView.Adapter<ApartmentAdapter3.ViewHolder> {
    private Context context;
    private List<Apartment1> mapartmentList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View ApartmentView;
        ImageView itemImage;
        TextView itemName;
        public ViewHolder(View view){
            super(view);
            ApartmentView = view;
            itemImage=(ImageView)view.findViewById(R.id.item_image);
            itemName=(TextView)view.findViewById(R.id.item_name);
        }
    }
    public ApartmentAdapter3(List<Apartment1> apartmentList){
        mapartmentList=apartmentList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.apartmentlist_item_1,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.ApartmentView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            int position = holder.getAdapterPosition();
            Apartment1 apartment1 = mapartmentList.get(position);
            Intent intent = new Intent(v.getContext(), PhoneListdetial.class);
            intent.putExtra("str", apartment1.getItem_name());
            v.getContext().startActivity(intent);
        }
    });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Apartment1 apartmen1=mapartmentList.get(position);
        holder.itemImage.setImageResource(apartmen1.getItem_imageId());
        holder.itemName.setText(apartmen1.getItem_name());
    }
    @Override
    public int getItemCount(){
        return mapartmentList.size();
    }
}
