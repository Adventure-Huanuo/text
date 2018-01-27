package com.example.lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApartmentItem2 extends AppCompatActivity {
    final static int MESSAGE_OK = 0;
    final static int MESSAGE_ERR = 1;
    private TextView dpt;
    Handler handler;
    private List<Apartment1> apartmentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_item_activity);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String id = pref.getString("account", "");
        String token = pref.getString("token", "");
        final Intent intent = getIntent();
        String depart = intent.getStringExtra("str");
        sendRequestWithHttpURLConnection(id,token,depart);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // super.handleMessage(msg);
                switch (msg.what) {
                    case MESSAGE_OK:
                        String response = (String) msg.obj;
                        try {
                            if(response.length()>15){
                                JSONObject json = new JSONObject(response);
                                String rowcount = json.getString("rowCount");
                                int count=0;
                                try {
                                    count = Integer.parseInt(rowcount);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                initApartment(count,json);
                                RecyclerView recyclerView=(RecyclerView) findViewById(R.id.man_recyclerview);
                                LinearLayoutManager layoutManager=new LinearLayoutManager(ApartmentItem2.this);
                                recyclerView.setLayoutManager(layoutManager);
                                ApartmentAdapter3 adapter=new ApartmentAdapter3(apartmentList);
                                recyclerView.setAdapter(adapter);
                            } else {
                                Toast.makeText(ApartmentItem2.this, "获取失败", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case MESSAGE_ERR:
                        Toast.makeText(ApartmentItem2.this, "请检查网络", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
        dpt = (TextView) findViewById(R.id.phonepage);
        dpt.setText(depart);
    }

    private void initApartment(int count,JSONObject json) {
        for (int i = 1; i <=count; i++) {
            String name=null;
            try {
                name = json.getString(""+i);
            }catch (Exception e){
                e.printStackTrace();
            }
            Apartment1 employee = new Apartment1(R.drawable.boy,name);
            apartmentList.add(employee);
        }
    }
    private void sendRequestWithHttpURLConnection(final String id,final String token,final String searchText) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("depart",searchText);
                    dataMap.put("id",id);
                    dataMap.put("token",token);
                    HttpRequestor httpRequestor = new HttpRequestor();
                    httpRequestor.doPost_2("http://172.16.201.17:8080/HuanuoServer/addressList", dataMap,handler);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(ApartmentItem2.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }

}