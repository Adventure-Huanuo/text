package com.example.lee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.apart_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ApartmentAdapter2 adapter=new ApartmentAdapter2(apartmentList);
        recyclerView.setAdapter(adapter);
    }
    private void initApartment() {
        for (int i = 0; i <101; i++) {
            Apartment runan = new Apartment(R.drawable.touxiang,"runan","工程师","Radio1", "runan.cai@huanuo-nokia.com", "18573197193", "sh");
            apartmentList.add(runan);
        }
    }
}
