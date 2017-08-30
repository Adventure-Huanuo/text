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

public class document_1 extends AppCompatActivity {

    private List<Huanuo> Firstpaper = new ArrayList<>();
    private SharedPreferences pref;
    private Handler handler;
    final static int MESSAGE_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_1);
        final TextView tv_1 = (TextView) findViewById(R.id.tv_1);
        pref = PreferenceManager.getDefaultSharedPreferences(document_1.this);
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        tv_1.setText(str);
        sendRequestWithHttpURLConnection(str);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // super.handleMessage(msg);
                switch (msg.what) {
                    case MESSAGE_OK:

                        String response = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(response);
                            String rowcount = json.getString("rowCount");
                            int count=0;
                            try {
                                count = Integer.parseInt(rowcount);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            initdocument(count,json);
                            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(document_1.this);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            DocumentAdapter adapter = new DocumentAdapter(Firstpaper);
                            recyclerView.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        };

    }
    private void initdocument(int count,JSONObject json) {
        for (int i = 1; i <= count; i++){
            String name=null;
            try {
                name = json.getString(""+i);
            }catch (Exception e){
                e.printStackTrace();
            }
            Huanuo docs = new Huanuo("   "+name,R.mipmap.choose);
            Firstpaper.add(docs);
        }
    }

    private void sendRequestWithHttpURLConnection(final String str) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("category",str);
                    dataMap.put("id",pref.getString("id",""));
                    dataMap.put("token",pref.getString("token", ""));
                    HttpRequestor httpRequestor = new HttpRequestor();
                    String res = httpRequestor.doPost("http://172.16.201.17:8080/HuanuoServer/DocumentList", dataMap);
                    Message message = new Message();
                    message.obj = res;
                    message.what = MESSAGE_OK;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(document_1.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }

}