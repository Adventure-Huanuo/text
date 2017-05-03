package com.example.logintest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 蔡如男 on 2017/4/28.
 */

public class moy_bt extends LinearLayout {
    private ImageView iv;
    private TextView  tv;

    public moy_bt(Context context) {
        this(context, null);
    }

    public moy_bt(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 导入布局
        LayoutInflater.from(context).inflate(R.layout.button_layout_1, this, true);
        iv = (ImageView) findViewById(R.id.image1);
        tv = (TextView) findViewById(R.id.text1);

    }
    /**
     * 设置图片资源
     */
    public void setImageResource(int resId) {
        iv.setImageResource(resId);
    }

    /**
     * 设置显示的文字
     */
    public void setTextViewText(String text) {
        tv.setText(text);
    }
}

