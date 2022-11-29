package com.comarch.szkolenia.jdbc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(4);
        list.add(7);
        list.add(10);
        list.add(4325);
        list.add(23);
        list.add(345);
        list.add(34);
        list.add(77);
        list.add(456);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int element = iterator.next();
            System.out.println(element);
        }
    }
}
