package com.example.lee.myapplication;

public class Apartment1 {
    private int item_imageId;
    private String item_name;
    public Apartment1(int item_imageId,String item_name)
    {
        this.item_imageId=item_imageId;
        this.item_name = item_name;
    }
    public int getItem_imageId(){
        return item_imageId;
    }
    public String getItem_name() {
        return item_name;
    }
}
