package com.example.lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lee on 2017/5/14.
 */

public class FragmentPage3 extends Fragment implements View.OnClickListener {

    private MainActivity mActivity;
    private View mView;
    private Handler handler;
    private Handler handler1;
    private Handler handler2;
    final static int MESSAGE_OK = 1;
    final static int MESSAGE_OK1 = 1;
    final static int MESSAGE_OK2 = 1;
    private SharedPreferences pref;
    TextView tv_1;
    TextView tv_2;
    TextView tv_3;
    TextView tv_4;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv01;
    TextView tv02;
    TextView tv03;
    TextView tv04;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_page3, container,
                false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pref = PreferenceManager.getDefaultSharedPreferences(mActivity);
        TextView text02 = (TextView) mView.findViewById(R.id.text02);
        text02.setOnClickListener(this);
        TextView text2 = (TextView) mView.findViewById(R.id.text_2);
        text2.setOnClickListener(this);
        TextView text_02 = (TextView) mView.findViewById(R.id.text_02);
        text_02.setOnClickListener(this);
        tv_1 = (TextView) mView.findViewById(R.id.textView_1);
        tv_1.setOnClickListener(this);
        tv_2 = (TextView) mView.findViewById(R.id.textView_2);
        tv_2.setOnClickListener(this);
        tv_3 = (TextView) mView.findViewById(R.id.textView_3);
        tv_3.setOnClickListener(this);
        tv_4 = (TextView) mView.findViewById(R.id.textView_4);
        tv_4.setOnClickListener(this);
        tv1 = (TextView) mView.findViewById(R.id.textView1);
        tv1.setOnClickListener(this);
        tv2 = (TextView) mView.findViewById(R.id.textView2);
        tv2.setOnClickListener(this);
        tv3 = (TextView) mView.findViewById(R.id.textView3);
        tv3.setOnClickListener(this);
        tv4 = (TextView) mView.findViewById(R.id.textView4);
        tv4.setOnClickListener(this);
        tv01 = (TextView) mView.findViewById(R.id.textView01);
        tv01.setOnClickListener(this);
        tv02 = (TextView) mView.findViewById(R.id.textView02);
        tv02.setOnClickListener(this);
        tv03 = (TextView) mView.findViewById(R.id.textView03);
        tv03.setOnClickListener(this);
        tv04 = (TextView) mView.findViewById(R.id.textView04);
        tv04.setOnClickListener(this);
        sendRequestWithHttpURLConnection("工作在华诺");
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_OK:
                        String response = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(response);
                            tv_1.setText(json.getString("1"));
                            tv_2.setText(json.getString("2"));
                            tv_3.setText(json.getString("3"));
                            tv_4.setText(json.getString("4"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        sendRequestWithHttpURLConnection1("学习在华诺");
        handler1 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_OK1:
                        String response = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(response);
                            tv1.setText(json.getString("1"));
                            tv2.setText(json.getString("2"));
                            tv3.setText(json.getString("3"));
                            tv4.setText(json.getString("4"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        sendRequestWithHttpURLConnection2("生活在华诺");
        handler2 = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_OK2:
                        String response = (String) msg.obj;
                        try {
                            JSONObject json = new JSONObject(response);
                            tv01.setText(json.getString("1"));
                            tv02.setText(json.getString("2"));
                            tv03.setText(json.getString("3"));
                            tv04.setText(json.getString("4"));
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

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text02:
                Intent intent = new Intent(mActivity,document_1.class);
                intent.putExtra("str","工作在华诺");
                startActivity(intent);
                break;
            case R.id.text_2:
                Intent intent1 = new Intent(mActivity,document_1.class);
                intent1.putExtra("str","学习在华诺");
                startActivity(intent1);
                break;
            case R.id.text_02:
                Intent intent2 = new Intent(mActivity,document_1.class);
                intent2.putExtra("str","生活在华诺");
                startActivity(intent2);
                break;
            case R.id.textView_1:
                sendRequestWithHttpURLConnection3(tv_1.getText().toString());
                break;
        }
    }

    private void sendRequestWithHttpURLConnection(final String str) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("Doctype",str);
                    dataMap.put("id",pref.getString("id",""));
                    dataMap.put("token",pref.getString("token", ""));
                    HttpRequestor httpRequestor = new HttpRequestor();
                    String res = httpRequestor.doPost("http://172.16.201.17:8080/HuanuoServer/Document", dataMap);
                    Message message = new Message();
                    message.obj = res;
                    message.what = MESSAGE_OK;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(mActivity,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }

    private void sendRequestWithHttpURLConnection1(final String str) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("Doctype",str);
                    dataMap.put("id",pref.getString("id",""));
                    dataMap.put("token",pref.getString("token", ""));
                    HttpRequestor httpRequestor = new HttpRequestor();
                    String res = httpRequestor.doPost("http://172.16.201.17:8080/HuanuoServer/Document", dataMap);
                    Message message = new Message();
                    message.obj = res;
                    message.what = MESSAGE_OK1;
                    handler1.sendMessage(message);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(mActivity,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }

    private void sendRequestWithHttpURLConnection2(final String str) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("Doctype",str);
                    dataMap.put("id",pref.getString("id",""));
                    dataMap.put("token",pref.getString("token", ""));
                    HttpRequestor httpRequestor = new HttpRequestor();
                    String res = httpRequestor.doPost("http://172.16.201.17:8080/HuanuoServer/Document", dataMap);
                    Message message = new Message();
                    message.obj = res;
                    message.what = MESSAGE_OK2;
                    handler2.sendMessage(message);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(mActivity,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }

    private void sendRequestWithHttpURLConnection3(final String str) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("doctitle",str);
                    dataMap.put("id",pref.getString("id",""));
                    dataMap.put("token",pref.getString("token", ""));
                    HttpRequestor httpRequestor = new HttpRequestor();
                    String res = httpRequestor.doPost("http://172.16.201.17:8080/HuanuoServer/DocInfor", dataMap);
                    JSONObject json = new JSONObject(res);
                    if (json.getString("code").equals("1")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW); //为Intent设置Action属性
                        intent.setData(Uri.parse(json.getString("docurl"))); //为Intent设置DATA属性
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(mActivity,"请检查网络设置",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}
