package com.nhnacademy;

import java.util.ArrayList;

public class BlackList {
    private ArrayList<String> blackList = new ArrayList<>();

    public BlackList(){
        //blackList.add("/127.0.0.1");
    } 

    public void add(String ipAddress) {
        blackList.add(ipAddress);
    }

    public void delete(String ipAddress) {
        blackList.remove(ipAddress);
    }

    public void print() {
        for (String blockedIP : blackList) {
            System.out.println(blockedIP);
        }
    }

    public ArrayList<String> getBlackList() {
        return blackList;
    }

    public boolean checkBlackList(String ipAddress){
        for (String string : blackList) {
            if(string.equals(ipAddress)){
                return true;
            }
        }
        return false;
    }
}