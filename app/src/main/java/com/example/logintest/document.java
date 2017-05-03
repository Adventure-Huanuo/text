package com.example.logintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class document extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_layout);
        TextView TextView_2 = (TextView) findViewById(R.id.text2);
        TextView TextView_1 = (TextView) findViewById(R.id.text1);
        ImageView ImageView_2 = (ImageView) findViewById(R.id.image2);
        ImageView ImageView_1= (ImageView) findViewById(R.id.image1);
        TextView TextView1 = (TextView) findViewById(R.id.text02);
        TextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(document.this, document_1.class);
                startActivity(intent);
            }
        });
                TextView_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(document.this, phone.class);
                        startActivity(intent);
                    }
                });
                ImageView_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(document.this, phone.class);
                        startActivity(intent);
                    }
                });
        ImageView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(document.this, MainActivity.class);
                startActivity(intent);
            }
        });
        TextView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(document.this, phone.class);
                startActivity(intent);
            }
        });
            }
        }
