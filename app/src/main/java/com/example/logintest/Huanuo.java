package com.example.logintest;

/**
 * Created by 蔡如男 on 2017/4/17.
 */

public class Huanuo {
    private String name;
    private int imageId;
    public Huanuo(String name,int imageId){
        this.name= name;
        this.imageId = imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
