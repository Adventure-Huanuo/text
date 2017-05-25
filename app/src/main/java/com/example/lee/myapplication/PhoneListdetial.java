package com.example.lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class PhoneListdetial extends AppCompatActivity {
    final static int MESSAGE_OK = 0;
    final static int MESSAGE_ERR = 1;
    final static int MESSAGE_SHOW_IMG = 0;
    final static int MESSAGE_RESULT_ERR = 1;
    Handler handler;
    Handler handler1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_paper2_layout);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String id = pref.getString("account", "");
        String token = pref.getString("token", "");
        final Intent intent = getIntent();
        String searchText = intent.getStringExtra("str");
        final TextView tv4 = (TextView) this.findViewById(R.id.textView4);
        final TextView tv7 = (TextView) this.findViewById(R.id.textView7);
        final TextView tv9 = (TextView) this.findViewById(R.id.textView9);
        final TextView tv11 = (TextView) this.findViewById(R.id.textView11);
        final TextView tv13 = (TextView) this.findViewById(R.id.textView13);
        final TextView tv15 = (TextView) this.findViewById(R.id.textView15);
        final TextView tv17 = (TextView) this.findViewById(R.id.textView17);
        sendRequestWithHttpURLConnection(id,token,searchText);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // super.handleMessage(msg);
                switch (msg.what) {
                    case MESSAGE_OK:
                        String response = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(response);
                            String code = json.getString("code");
                            if (code.equals("1")){
                                String gender = json.getString("gender");
                                String name = json.getString("name");
                                String tel = json.getString("tel");
                                String location = json.getString("location");
                                String job_id = json.getString("id");
                                String depart = json.getString("depart");
                                String email = json.getString("email");
                                tv4.setText(name);
                                tv7.setText(gender);
                                tv9.setText(depart);
                                tv11.setText(tel);
                                tv13.setText(email);
                                tv15.setText(location);
                                tv17.setText(job_id);
                            } else {
                                Intent intent = new Intent(PhoneListdetial.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case MESSAGE_ERR:
                        Toast.makeText(PhoneListdetial.this, "请检查网络", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
        handler1 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                // super.handleMessage(msg);
                switch (msg.what){
                    case MESSAGE_SHOW_IMG:
                        ((ImageView)findViewById(R.id.image_head)).setImageBitmap((Bitmap)msg.obj);
                        break;
                    case  MESSAGE_RESULT_ERR:
                        Toast.makeText(PhoneListdetial.this,"照片获取失败",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
    }
    private void sendRequestWithHttpURLConnection(final String id,final String token,final String searchText) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("sname",searchText);
                    dataMap.put("id",id);
                    dataMap.put("token",token);
                    HttpRequestor httpRequestor = new HttpRequestor();
                    httpRequestor.doPost_1("http://172.16.201.17:8080/HuanuoServer/getTel", dataMap,handler);
                    httpRequestor.requestNet(httpRequestor.getPath(),handler1);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(PhoneListdetial.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}