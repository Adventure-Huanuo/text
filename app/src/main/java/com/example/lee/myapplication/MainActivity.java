package com.example.lee.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentArrayList;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private ArrayList<Integer> mRadioButtonArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

    }


    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);

        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        mRadioButton1 = (RadioButton) findViewById(R.id.rb1);
        mRadioButton2 = (RadioButton) findViewById(R.id.rb2);
        mRadioButton3 = (RadioButton) findViewById(R.id.rb3);
        mRadioButton4 = (RadioButton) findViewById(R.id.rb4);

        mRadioButton1.setOnClickListener(this);
        mRadioButton2.setOnClickListener(this);
        mRadioButton3.setOnClickListener(this);
        mRadioButton4.setOnClickListener(this);
    }


    private void initData() {
        mRadioButtonArrayList = new ArrayList<>();
        mRadioButtonArrayList.add(R.id.rb1);
        mRadioButtonArrayList.add(R.id.rb2);
        mRadioButtonArrayList.add(R.id.rb3);
        mRadioButtonArrayList.add(R.id.rb4);

        mFragmentArrayList = new ArrayList<>();
        mFragmentArrayList.add(new FragmentPage1());
        mFragmentArrayList.add(new FragmentPage2());
        mFragmentArrayList.add(new FragmentPage3());
        mFragmentArrayList.add(new FragmentPage4());


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, mFragmentArrayList);
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mRadioGroup.check(mRadioButtonArrayList.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRadioGroup.check(R.id.rb1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb1:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.rb2:
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.rb3:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.rb4:
                mViewPager.setCurrentItem(3, false);
                break;

        }
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
