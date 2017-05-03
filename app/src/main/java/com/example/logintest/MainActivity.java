package com.example.logintest;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.logintest.R;
import com.example.logintest.document;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.logintest.R.id.image;
import static com.example.logintest.R.id.image2;
import static com.example.logintest.R.id.image3;
import static com.example.logintest.R.id.text2;
import static com.example.logintest.R.mipmap.phone;

public class MainActivity extends AppCompatActivity {


    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView ImageView1 = (ImageView) findViewById(R.id.image3);
        ImageView ImageView2 = (ImageView) findViewById(R.id.image2);
        ImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, document.class);
                startActivity(intent);
            }
        });
        ImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, phone.class);
                startActivity(intent);
            }
        });
    }

    // 点击手机HOME键，使应用程序退到后台；当再次打开App时，当前显示页面还是刚才退出时的页面
    // 点击返回键，弹出提示窗口
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 监控返回键
            new AlertDialog.Builder(MainActivity.this).setTitle("提示")
                    .setIconAttribute(android.R.attr.alertDialogIcon)
                    .setMessage("确定要退出吗?")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.this.finish();
                        }})
                    .setNegativeButton("取消", null)
                    .create().show();
            return false;
        } else if(keyCode == KeyEvent.KEYCODE_MENU) {
            // 监控菜单键
            Toast.makeText(MainActivity.this, "Menu", Toast.LENGTH_SHORT).show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
