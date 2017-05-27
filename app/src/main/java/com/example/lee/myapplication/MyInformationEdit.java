package com.example.lee.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyInformationEdit extends AppCompatActivity {
    private Button edit;
    private Button confirm;
    private Button cancel;
    private Button exit;
    private EditText textView4;
    private EditText textView7;
    private EditText textView9;
    private EditText textView11;
    private EditText textView13;
    private EditText textView15;
    private EditText textView17;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Handler handler;
    final static int MESSAGE_OK = 0;
    final static int MESSAGE_ERR = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_paper_edit);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        edit = (Button) findViewById(R.id.edit);
        confirm = (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);
        exit = (Button) findViewById(R.id.exit);
        textView4 = (EditText) findViewById(R.id.textView4);
        textView7 = (EditText) findViewById(R.id.textView7);
        textView9 = (EditText) findViewById(R.id.textView9);
        textView11 = (EditText) findViewById(R.id.textView11);
        textView13 = (EditText) findViewById(R.id.textView13);
        textView15 = (EditText) findViewById(R.id.textView15);
        textView17 = (EditText) findViewById(R.id.textView17);
        final Intent intent = getIntent();
        HttpRequestor httpRequestor = new HttpRequestor();
        Bitmap bitmap = httpRequestor.convertStringToIcon(intent.getStringExtra("strMap"));
        ((ImageView)findViewById(R.id.image_head)).setImageBitmap(bitmap);
        textView4.setText(pref.getString("name",""));
        textView7.setText(pref.getString("gender",""));
        textView9.setText(pref.getString("depart",""));
        textView11.setText(pref.getString("tel",""));
        textView13.setText(pref.getString("email",""));
        textView15.setText(pref.getString("location",""));
        textView17.setText(pref.getString("id",""));
        edit.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                setEnabled(textView4);
                setEnabled(textView7);
                setEnabled(textView9);
                setEnabled(textView11);
                setEnabled(textView13);
                setEnabled(textView15);
                setEnabled(textView17);
                confirm.setVisibility(confirm.VISIBLE);
                cancel.setVisibility(cancel.VISIBLE);
                edit.setVisibility(cancel.GONE);
            }
        });
        confirm.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                JSONObject object = new JSONObject();
                try {
                    object.put("gender",textView7.getText().toString());
                    //object.put("iconurl","");
                    object.put("name",textView4.getText().toString());
                    object.put("tel",textView11.getText().toString());
                    object.put("location",textView15.getText().toString());
                    object.put("id",textView17.getText().toString());
                    object.put("depart",textView9.getText().toString());
                    object.put("email",textView13.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendRequestWithHttpURLConnection(object);
                handler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        // super.handleMessage(msg);
                        switch (msg.what){
                            case MESSAGE_OK:
                                String response = msg.obj.toString();
                                String code=null;
                                try {
                                    JSONObject object1 = new JSONObject(response);
                                    code = object1.getString("code");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                if(code.equals("3")) {
                                    setEdit(textView4,"name");
                                    setEdit(textView7,"gender");
                                    setEdit(textView9,"depart");
                                    setEdit(textView11,"tel");
                                    setEdit(textView13,"email");
                                    setEdit(textView15,"location");
                                    setEdit(textView17,"id");
                                    setDisabled(textView4);
                                    setDisabled(textView7);
                                    setDisabled(textView9);
                                    setDisabled(textView11);
                                    setDisabled(textView13);
                                    setDisabled(textView15);
                                    setDisabled(textView17);
                                    confirm.setVisibility(confirm.INVISIBLE);
                                    cancel.setVisibility(cancel.INVISIBLE);
                                    edit.setVisibility(cancel.VISIBLE);
                                } else {
                                    getEdit(textView4,"name");
                                    getEdit(textView7,"gender");
                                    getEdit(textView9,"depart");
                                    getEdit(textView11,"tel");
                                    getEdit(textView13,"email");
                                    getEdit(textView15,"location");
                                    getEdit(textView17,"id");
                                    setDisabled(textView4);
                                    setDisabled(textView7);
                                    setDisabled(textView9);
                                    setDisabled(textView11);
                                    setDisabled(textView13);
                                    setDisabled(textView15);
                                    setDisabled(textView17);
                                    confirm.setVisibility(confirm.INVISIBLE);
                                    cancel.setVisibility(cancel.INVISIBLE);
                                    edit.setVisibility(cancel.VISIBLE);
                                    Toast.makeText(MyInformationEdit.this,"资料更新失败",Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case MESSAGE_ERR:
                                Toast.makeText(MyInformationEdit.this,"服务器未响应，请检查网络",Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                };
            }
        });
        cancel.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                getEdit(textView4,"name");
                getEdit(textView7,"gender");
                getEdit(textView9,"depart");
                getEdit(textView11,"tel");
                getEdit(textView13,"email");
                getEdit(textView15,"location");
                getEdit(textView17,"id");
                setDisabled(textView4);
                setDisabled(textView7);
                setDisabled(textView9);
                setDisabled(textView11);
                setDisabled(textView13);
                setDisabled(textView15);
                setDisabled(textView17);
                confirm.setVisibility(confirm.INVISIBLE);
                cancel.setVisibility(cancel.INVISIBLE);
                edit.setVisibility(cancel.VISIBLE);
            }
        });
    }

    public void setEnabled (EditText editText) {
        editText.setEnabled(true);
        editText.setFocusableInTouchMode(true);
        editText.setFocusable(true);
        editText.requestFocus();
    }

    public void setDisabled (EditText editText) {
        editText.setEnabled(false);
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }
    public void setEdit (EditText editText,String par) {
        String str = editText.getText().toString();
        editText.setText(str);
        editor = pref.edit();
        editor.putString(par,str);
        editor.apply();
    }

    public void getEdit (EditText editText,String par) {
        editText.setText(pref.getString(par,""));
    }

    private void sendRequestWithHttpURLConnection (final JSONObject json) {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("id",pref.getString("id",""));
                    dataMap.put("token",pref.getString("token",""));
                    dataMap.put("data",json.toString());
                    HttpRequestor httpRequestor = new HttpRequestor();
                    httpRequestor.doPost_2("http://172.16.201.17:8080/HuanuoServer/DataUpdate",dataMap,handler);
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(MyInformationEdit.this,"服务器未响应,请检查网络",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}