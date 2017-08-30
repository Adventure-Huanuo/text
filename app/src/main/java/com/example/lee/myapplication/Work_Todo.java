package com.example.lee.myapplication;

import android.app.Activity;
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
import android.text.method.CharacterPickerDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.util.ArrayList;
import java.util.List;

public class Work_Todo extends AppCompatActivity {
    private List<BusinessList01> businessList01=new ArrayList<>();
    private TextView tv;
    private TextView tv1;
    private Handler handler;
    final static int MESSAGE_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work__todo);
        TextView tv=(TextView)findViewById(R.id.doing);
        TextView tv1=(TextView)findViewById(R.id.done);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Work_Todo.this,Personal_Workspace_new.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Work_Todo.this,Work_Done.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        sendRequestWithHttpURLConnection("ToDo");
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
                            initBusinessList01s(count,json);
                            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                            LinearLayoutManager layoutManager=new LinearLayoutManager(Work_Todo.this);
                            recyclerView.setLayoutManager(layoutManager);
                            BusinessListAdapter01 adapter=new BusinessListAdapter01(businessList01);
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

    private void initBusinessList01s(int count,JSONObject json){
        for (int i = 1; i <= count; i++){
            String subStr=null;
            String type=null;
            String name=null;
            String segment=null;
            String time=null;
            String dealer=null;
            String binderDocIDOS=null;
            try {
                subStr = json.getString(""+i);
                JSONObject subJson = new JSONObject(subStr);
                type = subJson.getString("PROCESSOS");
                name = subJson.getString("sqr");
                segment = subJson.getString("ACTIVITYOS");
                time = subJson.getString("JobStartedOS");
                dealer = subJson.getString("ry_dis");
                binderDocIDOS = subJson.getString("BinderDocIDOS");
            }catch (Exception e){
                e.printStackTrace();
            }
            BusinessList01 b = new BusinessList01(type,name,segment,time,dealer,binderDocIDOS);
            businessList01.add(b);
        }
    }

    private void sendRequestWithHttpURLConnection(final String str) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(Work_Todo.this);
                    dataMap.put("worktype",str);
                    dataMap.put("token",pref.getString("token", ""));
                    dataMap.put("id",pref.getString("id",""));

                    HttpRequestor httpRequestor = new HttpRequestor();
                    String res = httpRequestor.doPost("http://172.16.201.17:8080/HuanuoServer/WorkList", dataMap);
                    Message message = new Message();
                    message.obj = res;
                    message.what = MESSAGE_OK;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(Work_Todo.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}
