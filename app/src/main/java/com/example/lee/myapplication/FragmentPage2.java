package com.example.lee.myapplication;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by lee on 2017/5/14.
 */

public class FragmentPage2 extends Fragment implements View.OnClickListener {
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
        mView = inflater.inflate(R.layout.fragment_page2, container,
                false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button bt2 = (Button) mView.findViewById(R.id.COM);
        Button bt3 = (Button) mView.findViewById(R.id.RSO);
        ImageView serchImage = (ImageView) mView.findViewById(R.id.search_image);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        serchImage.setOnClickListener(this);
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
            /*case R.id.COM:
                Intent intent1 = new Intent(mActivity, ApartmentItem.class);
                startActivity(intent1);
                break;*/
            case R.id.RSO:
                Intent intent2 = new Intent(mActivity, ApartmentItem1.class);
                intent2.putExtra("str","RSO");
                startActivity(intent2);
                break;
            case R.id.search_image:
                Intent intent = new Intent(mActivity,PhoneListdetial.class);
                String searchText = ((EditText) mView.findViewById(R.id.search_text)).getText().toString();
                intent.putExtra("str",searchText);
                startActivity(intent);
                break;
        }
    }
}
