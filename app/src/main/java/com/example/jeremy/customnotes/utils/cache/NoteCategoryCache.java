package com.example.jeremy.customnotes.utils.cache;

import java.util.HashMap;
import java.util.Map;

public final class NoteCategoryCache {

    private static Map<String , String> noteCategoryMap = new HashMap<>();

    private NoteCategoryCache() {}

    public static void add(String key, String value) {
        noteCategoryMap.put(key , value);
    }

//    public static String get(String s) {
//        return noteCategoryMap.get(s);
//    }

//    public static void remove(String s) {
//        noteCategoryMap.remove(s);
//    }

    public static Map<String, String> getAll() {
        return noteCategoryMap;
    }

    public static void removeAll() {
        noteCategoryMap.clear();
    }

//    public static void sort() {
//    }

    public static int size() {
        return noteCategoryMap.size();
    }

    public static String[] getNoteCategoryArray() {
        return noteCategoryMap.values().toArray(new String[0]);
    }
}
