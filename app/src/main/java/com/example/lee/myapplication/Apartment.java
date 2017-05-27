package com.example.lee.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Apartment {
    private int item_imageId;
    private String item_name;
    private String item_duty;
    private String item_apar;
    private String item_number;
    private String item_email;
    private String item_base;

    public Apartment(int item_imageId,String item_name, String item_duty,String item_apar,String item_number,String item_email,String item_base)
    {
        this.item_imageId=item_imageId;
        this.item_name = item_name;
        this.item_duty = item_duty;
        this.item_apar = item_apar;
        this.item_number = item_number;
        this.item_email = item_email;
        this.item_base = item_base;
    }
    public int getItem_imageId(){
        return item_imageId;
    }
    public String getItem_name() {
        return item_name;
    }

    public String getItem_duty() {
        return item_duty;
    }

    public String getItem_apar() {
        return item_apar;
    }

    public String getItem_number() {
        return item_number;
    }

    public String getItem_email() {
        return item_email;
    }

    public String getItem_base() {
        return  item_base;
    }
}
