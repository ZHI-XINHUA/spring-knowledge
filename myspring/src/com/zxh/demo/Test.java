package com.zxh.demo;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("a","a");
        map.put("b","b");

        for (String key : map.keySet()){
            System.out.println("key="+key);
            map.put("c+"+key,"q");
        }
        System.out.println("======");
        for (String key :map.keySet()){
            System.out.println(key);
        }


    }
}
