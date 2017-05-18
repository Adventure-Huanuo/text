package com.example.lee.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;

public class PhoneListdetial extends AppCompatActivity {
        final static int MESSAGE_SHOW_IMG = 0;
        final static int MESSAGE_RESULT_ERR = 1;
        Handler handler;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.me_paper2_layout);
            Intent intent = getIntent();
            String searchText=intent.getStringExtra("str");
            ImageView iv = (ImageView)this.findViewById(R.id.search_image);
            TextView tv4 = (TextView)this.findViewById(R.id.textView4);
            TextView tv7 = (TextView)this.findViewById(R.id.textView7);
            TextView tv9 = (TextView)this.findViewById(R.id.textView9);
            TextView tv11 = (TextView)this.findViewById(R.id.textView11);
            TextView tv13 = (TextView)this.findViewById(R.id.textView13);
            TextView tv15 = (TextView)this.findViewById(R.id.textView15);

            String head = null ;
            //String name ;
            //String phone;
            //String emil;
            //String localtion;
            if(searchText.equals("蔡如男")|searchText.equals("801186")) {
                head = "http://img.woyaogexing.com/2016/11/16/d7db063bbd3c0996!200x200.jpg";
                tv4.setText("蔡如男");
                tv11.setText("18573197193");
                tv13.setText("runan.cai@huanuo-nokia.com");
                tv15.setText("上海联通");
            } else if (searchText.equals("刘丽")|searchText.equals("801244")) {
                head = "http://img.woyaogexing.com/2016/11/16/724d3cce0ad9624e!200x200.jpg";
                tv4.setText("刘丽");
                tv11.setText("18673132027");
                tv13.setText("li.liu@huanuo-nokia.com");
                tv15.setText("上海联通");
            } else if (searchText.equals("杜晓涵")|searchText.equals("801254")) {
                head = "http://img.woyaogexing.com/2016/11/16/fda1a73244f18c02!200x200.jpg";
                tv4.setText("杜晓涵");
                tv11.setText("18673132559");
                tv13.setText("xiaohan.du@huanuo-nokia.com");
                tv15.setText("宁波移动");
            } else {
                tv4.setText(null);
                tv7.setText(null);
                tv9.setText(null);
                tv11.setText(null);
                tv13.setText(null);
                tv15.setText(null);
            }
            requestNetByThread(head);
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    // super.handleMessage(msg);
                    switch (msg.what){
                        case MESSAGE_SHOW_IMG:
                            ((ImageView)findViewById(R.id.image_head)).setImageBitmap((Bitmap)msg.obj);
                            break;
                        case  MESSAGE_RESULT_ERR:
                            Toast.makeText(PhoneListdetial.this,"获取失败",Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            };
        }
    private void requestNetByThread(final String path) {
        new Thread(){
            @Override
            public void run() {
                //super.run();
                requestNet(path);
            }
        }.start();
    }

    private void requestNet(String path) {
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            int code = connection.getResponseCode();
            if (code == 200){
                Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                Message message = new Message();
                message.obj = bitmap;
                message.what = MESSAGE_SHOW_IMG;
                handler.sendMessage(message);
            } else{
                Message message = new Message();
                message.what = MESSAGE_RESULT_ERR;
                handler.sendMessage(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Message message = new Message();
            message.what = MESSAGE_RESULT_ERR;
            handler.sendMessage(message);
        }
    }
}
