package com.zvio.linkedList;

public class TestDemo {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addLast(3);
        list.addLast(4);
        list.addFirst(5);
        System.out.println(list);
        list.add(2,100);
        System.out.println(list);
        System.out.println(list.remove(new Integer(100)));
        System.out.println(list);
    }
}
