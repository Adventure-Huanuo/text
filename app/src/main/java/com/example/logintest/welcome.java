package com.example.logintest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * 开场欢迎动画
 */
public class welcome extends Activity

{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 显示欢迎界面
        setContentView(R.layout.entermovie);
        // 初始化handler
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent=new Intent(welcome.this,LoginActivity.class);
                startActivity(intent);
                welcome.this.finish();
            }
        },3000);
    }
}
