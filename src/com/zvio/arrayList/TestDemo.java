package com.zvio.arrayList;

public class TestDemo {
    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 11; i++) {
            list.add(i);
        }
        System.out.println(list);
        list.remove(12);
        System.out.println(list);
    }
}
