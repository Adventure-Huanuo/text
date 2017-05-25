package com.example.lee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ApartmentItem extends AppCompatActivity {
    private List<Apartment> apartmentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_item_activity);
        initApartment();
        ApartmentAdapter adapter = new ApartmentAdapter(ApartmentItem.this, R.layout.apartment_item_activity, apartmentList);
        ListView listView = (ListView) findViewById(R.id.AparListView);
        listView.setAdapter(adapter);
    }
    private void initApartment(){
        for (int i=0;i<101;i++){
            Apartment runan=new Apartment("runan","Radio1","runan.cai@huanuo-nokia.com",19274329933,"sh");
            apartmentList.add(runan);
        }
    }
}
