package com.example.logintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit=(EditText)findViewById(R.id.account);
        passwordEdit=(EditText)findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        responseText = (TextView) findViewById(R.id.response_text);
        login.setOnClickListener(this);
        checkBox1=(CheckBox) findViewById(R.id.checkBox1);
        checkBox2=(CheckBox) findViewById(R.id.checkBox2);
        login.setOnClickListener(this);
        checkBox1=(CheckBox) findViewById(R.id.checkBox1);
        checkBox2=(CheckBox) findViewById(R.id.checkBox2);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    passwordEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    passwordEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void  onClick(View v) {
        if (v.getId() == R.id.login){
            sendRequestWithHttpURLConnection();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                String use = accountEdit.getText().toString();
                String pas = passwordEdit.getText().toString();
                String response = "请检查网络设置";
                String code;

                try {
                    Map dataMap = new HashMap();
                    dataMap.put("username", use);
                    dataMap.put("Password", pas);
                    code = new HttpRequestor().doPost("http://172.16.201.17:8080/HuanuoServer/login", dataMap);
                    //code = new HttpRequestor().doPost("http://www.12306.cn/mormhweb/", dataMap);
                    System.out.println(code);
                    //showResponse(code);
                    if (code.equals("1")) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (code.equals("-1")) {
                        //Toast.makeText(LoginActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                        //showResponse("密码不正确");
                        response = "密码不正确";
                    } else if (code.equals("-2")) {
                        //Toast.makeText(LoginActivity.this, "帐号不存在", Toast.LENGTH_SHORT).show();
                        //showResponse("帐号不存在");
                        response = "帐号不存在";
                    } else {
                        //Toast.makeText(LoginActivity.this, "请检查网络设置", Toast.LENGTH_SHORT).show();
                        //showResponse("请检查网络设置");
                        response = "请检查网络设置";
                    }
                    showResponse(response);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }).start();
    }
    private  void showResponse(final String response){
        runOnUiThread(new Runnable(){
            @Override
            public void run(){
                //在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }
}