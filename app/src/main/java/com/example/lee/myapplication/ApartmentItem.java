package com.example.lee.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.appcompat.R.styleable.View;

public class ApartmentItem extends AppCompatActivity {
    private List<Apartment> apartmentList=new ArrayList<>();
    private EditText searchET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apartment_item_activity);
        initApartment();
        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.apart_recyclerview);
        searchET = (EditText) findViewById(R.id.search_frame);//找到控件
        searchET.setOnKeyListener(new android.view.View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                // 修改回车键功能
                if (keyCode == KeyEvent.KEYCODE_ENTER  && event.getAction() == KeyEvent.ACTION_DOWN ) {
                    // 先隐藏键盘
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(ApartmentItem.this
                                            .getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    //接下来在这里做你自己想要做的事，实现自己的业务。
                    search();
                }

                return false;
            }
        });
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ApartmentAdapter2 adapter=new ApartmentAdapter2(apartmentList);
        recyclerView.setAdapter(adapter);
    }



    private void initApartment() {
        for (int i = 0; i <=100; i++) {
            Apartment runan = new Apartment(R.drawable.touxiang,"蔡如男","工程师","Radio1", "runan.cai@huanuo-nokia.com", "18573197193", "sh");
            apartmentList.add(runan);
        }
    }

    // 搜索功能
    private void search() {

        String searchContext = searchET.getText().toString().trim();
        if (TextUtils.isEmpty(searchContext)) {
            Toast.makeText(ApartmentItem.this, "搜索框为空，请输入", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(ApartmentItem.this,me_paper2.class);
            startActivity(intent);
        }
    }




}
