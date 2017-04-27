package com.example.logintest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
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
        pref = PreferenceManager.getDefaultSharedPreferences(this);
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
        boolean isRemember=pref.getBoolean("checkBox2",false);
        if (isRemember){
            String account =pref.getString("account","");
            String password=pref.getString("password","");
            accountEdit.setText(account);
            checkBox2.setChecked(true);

        }
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
                String code;
                try {
                    Map dataMap = new HashMap();
                    dataMap.put("username", use);
                    dataMap.put("Password", pas);
                    code = new HttpRequestor().doPost("http://172.16.201.17:8080/HuanuoServer/login", dataMap);
                    if (code.equals("1")) {
                        editor=pref.edit();
                        if(checkBox2.isChecked()){
                            editor.putBoolean("checkBox2",true);
                            editor.putString("account",use);
                            editor.putString("password",pas);
                        }else {
                            editor.clear();
                        }editor.apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (code.equals("-1")) {
                        Looper.prepare();
                        Toast.makeText(LoginActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    } else {
                        Looper.prepare();
                        Toast.makeText(LoginActivity.this, "帐号不存在", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                } catch (Exception e) {
                    e.getMessage();
                    Looper.prepare();
                    Toast.makeText(LoginActivity.this, "请检查网络设置", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }
}