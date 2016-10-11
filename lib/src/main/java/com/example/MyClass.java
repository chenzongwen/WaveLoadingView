package com.example;

import java.util.ArrayList;
import java.util.TreeMap;

public class MyClass {
    public static void main(String[] argc) {
        TreeMap<Integer, String> urlMap = new TreeMap<>();
        urlMap.put(1, "one");
        urlMap.put(5, "five");
        urlMap.put(3, "three");
        urlMap.put(2, "two");

        for (String valus : urlMap.values()) {
            System.out.println(valus);
        }

       // ArrayList
    }
}
