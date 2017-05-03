package com.example.logintest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 蔡如男 on 2017/5/2.
 */

public class buttom_menu_1 extends LinearLayout {
    private ImageView iv;
    private TextView tv;
    public buttom_menu_1(Context context) {
        this(context, null);
    }

    public buttom_menu_1(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 导入布局
        LayoutInflater.from(context).inflate(R.layout.bottom_menu_1, this, true);
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
