package com.example.lee.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_page1, container,
                false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button bt1 = (Button) mView.findViewById(R.id.button_moy);//获取布局中定义的元素
        bt1.setOnClickListener(this);
        Button bt2 = (Button) mView.findViewById(R.id.button_ad);
        bt2.setOnClickListener(this);//为按钮注册监听器
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
//使用toast实现按钮点击响应，弹出toast的功能要在onClick方法中编写
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_moy:
                Toast.makeText(mActivity,"财务管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_ad:
                Toast.makeText(mActivity,"行政管理", Toast.LENGTH_SHORT).show();//静态方法makeText创建toast对象
                break;
        }
    }
}
