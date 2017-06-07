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

//主程序入口类

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //初始化数据
    private ViewPager mViewPager;//定义一个Viewpager容器
    private ArrayList<Fragment> mFragmentArrayList;
    private RadioGroup mRadioGroup;//定义radiobutton对象
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private ArrayList<Integer> mRadioButtonArrayList;//定义Arraylist存放RadioButton

    @Override//新建两个函数
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//初始化组件
        initView();
//初始化数据
        initData();

    }

    //定义函数，实例化
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);//获取布局中定义的元素

        mRadioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        mRadioButton1 = (RadioButton) findViewById(R.id.rb1);
        mRadioButton2 = (RadioButton) findViewById(R.id.rb2);
        mRadioButton3 = (RadioButton) findViewById(R.id.rb3);
        mRadioButton4 = (RadioButton) findViewById(R.id.rb4);

        mRadioButton1.setOnClickListener(this);//按钮监听
        mRadioButton2.setOnClickListener(this);
        mRadioButton3.setOnClickListener(this);
        mRadioButton4.setOnClickListener(this);
    }

    //初始化数据
    private void initData() {
        mRadioButtonArrayList = new ArrayList<>();//实例化ArrayList
        mRadioButtonArrayList.add(R.id.rb1);//添加radiobutton
        mRadioButtonArrayList.add(R.id.rb2);
        mRadioButtonArrayList.add(R.id.rb3);
        mRadioButtonArrayList.add(R.id.rb4);

        mFragmentArrayList = new ArrayList<>();//实例化四个Fragment对象后,把他们放到View集合中
        mFragmentArrayList.add(new FragmentPage1());//添加fragmentpage
        mFragmentArrayList.add(new FragmentPage2());
        mFragmentArrayList.add(new FragmentPage3());
        mFragmentArrayList.add(new FragmentPage4());//

        //定义适配器
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, mFragmentArrayList);
        //设置Viewpager的适配器
        mViewPager.setAdapter(fragmentAdapter);
        //设置页面滑动的监听事件
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override//滚动时position为显示的页面位置，Offset相对于与滚动页的下个页面间的的偏移量，OffsetPixels偏移像素，滑动时调用
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //获取RadioButton按钮的位置，采用数组
            @Override
            public void onPageSelected(int position) {
                mRadioGroup.check(mRadioButtonArrayList.get(position));
            }
            //state有三个状态，0:什么也没做，1：正在滑动，2：滑动完毕
            @Override//页面滚动发生或停止时，状态更新调用
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRadioGroup.check(R.id.rb1);

    }

    @Override//4个button的点击事件，把对应的fragment的位置传入到viewPager中，对点击的按钮的id进行判断,判断点击的是第几个,调用viewpager.setCurrentItem(index)，显示页数
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
