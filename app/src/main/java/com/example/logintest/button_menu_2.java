package com.example.logintest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 蔡如男 on 2017/5/3.
 */

public class button_menu_2 extends LinearLayout {
        private ImageView iv;
        private TextView tv;
        public button_menu_2(Context context) {
            this(context, null);
        }

        public button_menu_2(Context context, AttributeSet attrs) {
            super(context, attrs);
            // 导入布局
            LayoutInflater.from(context).inflate(R.layout.bottom_menu_2, this, true);
            iv = (ImageView) findViewById(R.id.image2);
            tv = (TextView) findViewById(R.id.text2);

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
