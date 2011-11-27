package com.exigen.jschool.setlocal;

import java.util.ArrayList;
import java.util.List;

class UniqueKeys {
    private static List<String> keys;

    public static boolean add(String key) {
        if (keys.contains(key)) return false;
        keys.add(key);
        return true;
    }

    public static void remove(String key) {
        keys.remove(key);
    }

    public static int count() {
        return keys.size();
    }

    public static boolean contains(String key) {
        return keys.contains(key);
    }

    public  static String getKey(int index){
        return keys.get(index);
    }

    public static void initUniqueKey(){
        keys = new ArrayList<String>() ;
    }
}
