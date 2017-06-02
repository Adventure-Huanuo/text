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
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApartmentItem1 extends AppCompatActivity {
    final static int MESSAGE_OK = 0;
    final static int MESSAGE_ERR = 1;
    Handler handler;
    private List<Apartment1> apartmentList=new ArrayList<>();
    private EditText searchET;
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
                                String rowcount = json.getString("rowcount");
                                int count=0;
                                try {
                                    count = Integer.parseInt(rowcount);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                initApartment(count,json);
                                RecyclerView recyclerView=(RecyclerView) findViewById(R.id.apart_recyclerview);
                                LinearLayoutManager layoutManager=new LinearLayoutManager(ApartmentItem1.this);
                                recyclerView.setLayoutManager(layoutManager);
                                ApartmentAdapter3 adapter=new ApartmentAdapter3(apartmentList);
                                recyclerView.setAdapter(adapter);
                            } else {
                                Toast.makeText(ApartmentItem1.this, "获取失败", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case MESSAGE_ERR:
                        Toast.makeText(ApartmentItem1.this, "请检查网络", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
        searchET = (EditText) findViewById(R.id.search_frame);//找到控件
        searchET.setOnKeyListener(new android.view.View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                // 修改回车键功能
                if (keyCode == KeyEvent.KEYCODE_ENTER  && event.getAction() == KeyEvent.ACTION_DOWN ) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(ApartmentItem1.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    //接下来在这里做你自己想要做的事，实现自己的业务。
                    search();
                }

                return false;
            }
        });
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
                    Toast.makeText(ApartmentItem1.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
    // 搜索功能
    private void search() {

        String searchContext = searchET.getText().toString().trim();
        if (TextUtils.isEmpty(searchContext)) {
            Toast.makeText(ApartmentItem1.this, "搜索框为空，请输入", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(ApartmentItem1.this,me_paper2.class);
            startActivity(intent);
        }
    }
}
