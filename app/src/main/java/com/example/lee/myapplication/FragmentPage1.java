package com.example.lee.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lee on 2017/5/14.
 */

public class FragmentPage1 extends Fragment implements View.OnClickListener {

    private View mView;
    private MainActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();

    }

    @Override//构建视图
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_page1, container,
                false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button bt1 = (Button) mView.findViewById(R.id.img_1);//获取布局中定义的元素
        bt1.setOnClickListener(this);//为按钮注册监听器
        Button bt2 = (Button) mView.findViewById(R.id.img_2);
        bt2.setOnClickListener(this);
        Button bt3 = (Button) mView.findViewById(R.id.img_3);
        bt3.setOnClickListener(this);
        Button bt4 = (Button) mView.findViewById(R.id.img_4);
        bt4.setOnClickListener(this);
        Button bt5 = (Button) mView.findViewById(R.id.button_moy);
        bt5.setOnClickListener(this);
        Button bt6 = (Button) mView.findViewById(R.id.button_ad);
        bt6.setOnClickListener(this);
        Button bt7 = (Button) mView.findViewById(R.id.button_hr);
        bt7.setOnClickListener(this);
        Button bt8 = (Button) mView.findViewById(R.id.button_pb);
        bt8.setOnClickListener(this);
        Button bt9 = (Button) mView.findViewById(R.id.button_mef);
        bt9.setOnClickListener(this);
        Button bt10 = (Button) mView.findViewById(R.id.button_re);
        bt10.setOnClickListener(this);
        Button bt11 = (Button) mView.findViewById(R.id.button_cl);
        bt11.setOnClickListener(this);
        Button bt12 = (Button) mView.findViewById(R.id.button_hp);
        bt12.setOnClickListener(this);
        TextView tx1 = (TextView) mView.findViewById(R.id.text02);
        tx1.setOnClickListener(this);
    }

    @Override//回调方法，活动准备好和用户交互时调用
    public void onResume() {
        super.onResume();
    }

    @Override//将消耗的CPU的资源释放掉，以保存关键数据
    public void onPause() {
        super.onPause();
    }

    @Override//活动被销毁前调用，之后活动变为销毁状态
    public void onDestroy() {
        super.onDestroy();

    }
//使用toast实现按钮点击响应，弹出toast的功能要在onClick方法中编写
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_1:
                Intent intent1 = new Intent(mActivity, travelapply.class);
                startActivity(intent1);
                break;
            case R.id.img_2:
                Toast.makeText(mActivity,"HR系统", Toast.LENGTH_SHORT).show();//静态方法makeText创建toast对象
                break;
            case R.id.img_3:
                Toast.makeText(mActivity,"技术资源", Toast.LENGTH_SHORT).show();
                break;
            case R.id.img_4:
                Toast.makeText(mActivity,"培训系统", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_moy:
                Toast.makeText(mActivity,"财务管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_ad:
                Toast.makeText(mActivity,"行政管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_hr:
                Toast.makeText(mActivity,"人事管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_pb:
                Toast.makeText(mActivity,"公共信息", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_mef:
                Toast.makeText(mActivity,"个人工作台", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_re:
                Toast.makeText(mActivity,"内部招聘", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_cl:
                Toast.makeText(mActivity,"文化建设", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_hp:
                Toast.makeText(mActivity,"系统帮助", Toast.LENGTH_SHORT).show();
                break;
            case R.id.text02:
                startActivity(new Intent(mActivity,shouye_more_1.class));
                break;
        }
    }
}
