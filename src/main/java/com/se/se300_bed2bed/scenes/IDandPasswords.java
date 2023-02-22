package com.se.se300_bed2bed.scenes;

import java.util.HashMap;

public class IDandPasswords {


    private String username;
    private String password;


    //hash map
    HashMap<String,String> loginInfo = new HashMap<String,String>();

    IDandPasswords(){
        loginInfo.put("test", "test");
        loginInfo.put("for", "for");
        loginInfo.put("idk", "idk");
//        loginInfo.get(username,password);
    }

    protected HashMap getLoginInfo() {
        return loginInfo;
    }
}
