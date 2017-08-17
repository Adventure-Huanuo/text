package com.example.lee.myapplication;

public class BusinessList01 {
    private String type;
    private String name;
    private String segment;
    private String time;
    private String dealer;
    public BusinessList01(String type,String name,String segment,String time,String dealer) {
        this.type = type;
        this.name = name;
        this.segment = segment;
        this.time = time;
        this.dealer = dealer;
    }
    public String getType(){
        return type;
    }
    public String getName(){
        return name;
    }
    public String getSegment(){
        return segment;
    }
    public String getTime(){
        return time;
    }
    public String getDealer(){
        return dealer;
    }
}