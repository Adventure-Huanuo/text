package com.example.logintest;

/**
 * Created by Administrator on 2017/4/21.
 */

public class PhoneGroup {
    private String groupname;
    private int groupId;
    public PhoneGroup(String groupname,int groupId){
        this.groupname=groupname;
        this.groupId=groupId;
    }
    public String getGroupname(){
        return groupname;
    }
    public int getGroupId(){
        return groupId;
    }
}
