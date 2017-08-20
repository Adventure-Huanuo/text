package com.example.lee.myapplication;

public class BusinessList02 {
    private String type;
    private String name;
    private String segment;
    private String time;
    private String binderDocIDOS;
    public BusinessList02(String type,String name,String segment,String time,String binderDocIDOS) {
        this.type = type;
        this.name = name;
        this.segment = segment;
        this.time = time;
        this.binderDocIDOS = binderDocIDOS;
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
    public String getBinderDocIDOS(){
        return binderDocIDOS;
    }
}