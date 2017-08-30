package com.example.lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class TravelList extends AppCompatActivity {
    private Handler handler;
    final static int MESSAGE_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_list);
        final TextView textView28 = (TextView) findViewById(R.id.textView28);
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("str1");
        String str2 = intent.getStringExtra("str2");
        sendRequestWithHttpURLConnection(str1,str2);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // super.handleMessage(msg);
                switch (msg.what) {
                    case MESSAGE_OK:
                        String response = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(response);
                            textView28.setText(json.getString("申请人"));

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

    private void sendRequestWithHttpURLConnection(final String str1,final String str2) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(TravelList.this);
                    Map dataMap = new HashMap();
                    dataMap.put("worktype",str1);
                    dataMap.put("docid",str2);
                    dataMap.put("token",pref.getString("token", ""));
                    dataMap.put("id",pref.getString("id",""));

                    HttpRequestor httpRequestor = new HttpRequestor();
                    String res = httpRequestor.doPost("http://172.16.201.17:8080/HuanuoServer/GetApplicationForm", dataMap);
                    Message message = new Message();
                    message.obj = res;
                    message.what = MESSAGE_OK;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(TravelList.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}
