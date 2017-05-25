package com.example.lee.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ApartmentAdapter extends ArrayAdapter<Apartment> {
    private int resourceId;
    public ApartmentAdapter(Context context, int textViewResourceId, List<Apartment> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Apartment apartment=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView itemName=(TextView)view.findViewById(R.id.item_name);
        TextView itemDuty=(TextView)view.findViewById(R.id.item_duty);
        TextView itemNumber=(TextView)view.findViewById(R.id.item_number);
        TextView itemEmail=(TextView)view.findViewById(R.id.item_email);
        TextView itemBase=(TextView)view.findViewById(R.id.item_base);
        itemName.setText(apartment.getItem_name());
        itemDuty.setText(apartment.getItem_duty());
        itemNumber.setText(apartment.getItem_number());
        itemEmail.setText(apartment.getItem_email());
        itemBase.setText(apartment.getItem_base());
        return view;
    }
   }
