package com.example.lee.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.CharacterPickerDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.ArrayList;
import java.util.List;

public class Work_Todo extends AppCompatActivity {
    private List<BusinessList01> businessList01=new ArrayList<>();
    private TextView tv;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work__todo);
        initBusinessList01s();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        TextView tv=(TextView)findViewById(R.id.doing);
        TextView tv1=(TextView)findViewById(R.id.done);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Work_Todo.this,Personal_Workspace_new.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Work_Todo.this,Work_Done.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BusinessListAdapter01 adapter01=new BusinessListAdapter01(businessList01);
        recyclerView.setAdapter(adapter01);
    }

    private void initBusinessList01s(){
        for (int i=0;i<1;i++){
            BusinessList01 a=new BusinessList01("无","无","无","无","无");
            businessList01.add(a);
        }
    }
}
