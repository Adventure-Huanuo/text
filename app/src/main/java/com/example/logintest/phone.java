package com.example.logintest;

import android.os.OperationCanceledException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class phone extends AppCompatActivity {
    private List<PhoneGroup> phoneGroupList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_layout);
        initPhoneGroups();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.phone_recycler_view);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        PhonegroupAdapter adapter=new PhonegroupAdapter(phoneGroupList);
        recyclerView.setAdapter(adapter);
    }
    private void initPhoneGroups(){
        for (int i=0;i<1;i++){
            PhoneGroup CO=new PhoneGroup("Communication-Operation",R.drawable.phonelist);
            phoneGroupList.add(CO);
            PhoneGroup EPC=new PhoneGroup("EPC-Product Support",R.drawable.phonelist);
            phoneGroupList.add(EPC);
            PhoneGroup FC=new PhoneGroup("Finance-Control",R.drawable.phonelist);
            phoneGroupList.add(FC);
            PhoneGroup GDC=new PhoneGroup("GDC",R.drawable.phonelist);
            phoneGroupList.add(GDC);
            PhoneGroup GMO=new PhoneGroup("GM-Office",R.drawable.phonelist);
            phoneGroupList.add(GMO);
            PhoneGroup HR=new PhoneGroup("Human-Resource",R.drawable.phonelist);
            phoneGroupList.add(HR);
            PhoneGroup NO=new PhoneGroup("Nokia",R.drawable.phonelist);
            phoneGroupList.add(NO);
            PhoneGroup NMS=new PhoneGroup("NM&SON",R.drawable.phonelist);
            phoneGroupList.add(NMS);
            PhoneGroup PM1=new PhoneGroup("Project Management",R.drawable.phonelist);
            phoneGroupList.add(PM1);
            PhoneGroup PM  =new PhoneGroup("PM",R.drawable.phonelist);
            phoneGroupList.add(PM);
            PhoneGroup RSO=new PhoneGroup("RSO",R.drawable.phonelist);
            phoneGroupList.add(RSO);
            PhoneGroup SISD=new PhoneGroup("SISD",R.drawable.phonelist);
            phoneGroupList.add(SISD);

        }
    }
}
