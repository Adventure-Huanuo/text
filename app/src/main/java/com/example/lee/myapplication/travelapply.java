package com.example.lee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class travelapply extends AppCompatActivity {
    private static final String[] m={"HomeTrip","海外差旅","商务差旅","项目差旅"};
    private Spinner spinner;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travelapply);
        spinner =(Spinner) findViewById(R.id.spinner_traveltype);
        //将可选内容与ArrayAdapter连接起来
        adapter =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,m);
        //设置下拉列表风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter添加到spinner中
        spinner.setAdapter(adapter);
        //设置默认值
        spinner.setVisibility(View.VISIBLE);
    }

    private void initApartment() {
    }
}
