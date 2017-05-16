package com.example.lee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class document_1 extends AppCompatActivity {

    private List<Huanuo> Firstpaper = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_1);
        initdocument();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DocumentAdapter adapter = new DocumentAdapter(Firstpaper);
        recyclerView.setAdapter(adapter);
    }
    private void  initdocument(){
        for (int i=1;i<7;i++) {
            Huanuo A = new Huanuo("报销及差旅制度", R.mipmap.choose);
            Firstpaper.add(A);
            Huanuo B = new Huanuo("华诺邮件群组", R.mipmap.choose);
            Firstpaper.add(B);
            Huanuo C = new Huanuo("后勤员工座机表", R.mipmap.choose);
            Firstpaper.add(C);
            Huanuo D = new Huanuo("华诺邮件群组", R.mipmap.choose);
            Firstpaper.add(D);
            Huanuo E = new Huanuo("滴滴出行企业版说明", R.mipmap.choose);
            Firstpaper.add(E);
            Huanuo F = new Huanuo("职业健康与安全文档汇总指引", R.mipmap.choose);
            Firstpaper.add(F);
            Huanuo G = new Huanuo("工作职责描述", R.mipmap.choose);
            Firstpaper.add(G);
        }
    }
}