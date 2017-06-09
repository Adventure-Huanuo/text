package com.example.lee.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lee on 2017/5/14.
 */

public class FragmentPage3 extends Fragment implements View.OnClickListener {

    private MainActivity mActivity;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_page3, container,
                false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView bt3 = (TextView) mView.findViewById(R.id.text02);
        bt3.setOnClickListener(this);
        TextView bt4 = (TextView) mView.findViewById(R.id.text01);
        bt4.setOnClickListener(this);
        TextView bt5 = (TextView) mView.findViewById(R.id.textView_1);
        bt5.setOnClickListener(this);
        TextView bt6 = (TextView) mView.findViewById(R.id.textView_2);
        bt6.setOnClickListener(this);
        TextView bt7 = (TextView) mView.findViewById(R.id.textView1);
        bt7.setOnClickListener(this);
        TextView bt8 = (TextView) mView.findViewById(R.id.textView01);
        bt8.setOnClickListener(this);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text02:
                startActivity(new Intent(mActivity,document_1.class));
                break;
        }
        switch (v.getId()) {
            case R.id.text01:
                Toast.makeText(mActivity,"文档", Toast.LENGTH_SHORT).show();
                break;
        }
        switch (v.getId()) {
            case R.id.textView_1:
                Intent intent = new Intent(mActivity,PhotoShow.class);
                intent.putExtra("url","http://172.16.201.17:8080/img/docs/business2.png");
                startActivity(intent);
                break;
        }
        switch (v.getId()) {
            case R.id.textView_1:
                Intent intent = new Intent(mActivity,PhotoShow.class);
                intent.putExtra("url","http://172.16.201.17:8080/img/docs/business2.png");
                startActivity(intent);
                break;
        }
        switch (v.getId()) {
            case R.id.textView_2:
                Intent intent = new Intent(mActivity,PhotoShow.class);
                intent.putExtra("url","http://172.16.201.17:8080/img/docs/didi.png");
                startActivity(intent);
                break;
        }
        switch (v.getId()) {
            case R.id.textView1:
                Intent intent = new Intent(mActivity,PhotoShow.class);
                intent.putExtra("url","http://172.16.201.17:8080/img/docs/PM1.jpg");
                startActivity(intent);
                break;
        }
        switch (v.getId()) {
            case R.id.textView01:
                Intent intent = new Intent(mActivity,PhotoShow.class);
                intent.putExtra("url","http://172.16.201.17:8080/img/docs/lovefund.png");
                startActivity(intent);
                break;
        }
    }
}
