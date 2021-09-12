package com.zvio;

public interface MyList<E> {
    void add(E e);
    void add(int index, E e);
    E get(int index);
    E remove(int index);
    E remove(E e);
    int size();
}
