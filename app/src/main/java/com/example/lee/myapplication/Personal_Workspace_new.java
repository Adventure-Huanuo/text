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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Personal_Workspace_new extends AppCompatActivity {
    private List<BusinessList> businessList=new ArrayList<>();
    private SharedPreferences pref;
    private Handler handler;
    final static int MESSAGE_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.personal__workspace_new);
        TextView tv=(TextView)findViewById(R.id.wait);
        TextView tv1=(TextView)findViewById(R.id.done);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Personal_Workspace_new.this,Work_Todo.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Personal_Workspace_new.this,Work_Done.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        sendRequestWithHttpURLConnection("Ongoing");
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
                            initBusinessLists(count,json);
                            RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
                            LinearLayoutManager layoutManager=new LinearLayoutManager(Personal_Workspace_new.this);
                            recyclerView.setLayoutManager(layoutManager);
                            BusinessListAdapter adapter=new BusinessListAdapter(businessList);
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

    private void initBusinessLists(int count,JSONObject json){
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
            BusinessList b = new BusinessList(type,name,segment,time,dealer,binderDocIDOS);
            businessList.add(b);
        }
    }

    private void sendRequestWithHttpURLConnection(final String str) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
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
                    Toast.makeText(Personal_Workspace_new.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}

