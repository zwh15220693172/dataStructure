package com.zvio.arrayList;

import com.zvio.MyList;

/**
 *  ArrayList的底层数据结构为动态的数组
 *  优点：查询跟LinkedList比较快
 *  缺点：增删跟LinkedList比较慢
 */
public class MyArrayList<E> implements MyList<E> {
    // 使用数组初始化语法对数组进行实例化
    private Object[] elements = {};
    // 记录元素的个数，不用赋予默认值
    // 对象数据域数值类型的默认值为0，char的默认值为\u000f，boolean为false, Object为null
    private int size;

    /**
     * 默认的无参构造
     */
    public MyArrayList(){
        this(16);
    }

    /**
     * 有参构造设置容量
     */
    public MyArrayList(int capacity){
        elements = new Object[capacity];
    }

    /**
     * ArrayList的add方法是重点
     */
    @Override
    public void add(E e) {
        // 如果记录的size与elements的长度相同，添加之前就要先扩容
        // 扩容的机制为原来数组长度的两倍,因此有可能会造成资源空间的浪费
        if (size >= elements.length) {
            Object[] newElements = new Object[this.elements.length * 2];
            // 使用System.arrayCopy进行复制数组
            System.arraycopy(this.elements,0,newElements,0,this.elements.length);
            elements = newElements;
        }
        elements[size++] = e;
    }

    // 根据索引添加元素
    @Override
    public void add(int index, E e) {
        if (index >= this.size) {
            return;
        }
        if (size >= elements.length) {
            // 容量不够只能扩容再添加了
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(this.elements,0,newElements,0,index);
            System.arraycopy(this.elements,index,newElements,index+1,this.elements.length - index);
            newElements[index] = e;
            this.elements = newElements;
        } else {
            // 反向遍历赋值
            for (int i = size; i > index; i--) {
                int j = i - 1;
                elements[i] = elements[j];
            }
            elements[index] = e;
        }
        size++;
    }

    /**
     * ArrayList的好处就是可以根据数组的索引查询数据
     */
    @Override
    public E get(int index) {
        return (E)elements[index];
    }

    /**
     * 根据元素删除元素
     */
    @Override
    public E remove(E e) {
        // 先检查size
        if (size == 0) {
            return null;
        }
        int index = -1;
        // 遍历查询相等元素的下标
        for (int i = 0; i < size; i++) {
            Object element = elements[i];
            if (element.equals(e)) {
                //  找到下标直接中断即可
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        }
        // 然后调用下面根据索引删除的方法
        return this.remove(index);
    }

    /**
     * 根据索引删除元素
     */
    @Override
    public E remove(int index) {
        Object currentElement = elements[index];
        for (int i = index; i < size -1; i++) {
            // 下一个交换的下标
            int j = i + 1;
            elements[i] = elements[j];
        }
        size--;
        return (E)currentElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * 重写toString, 方便看看里面有啥东西
     */
    @Override
    public String toString() {
        String result = "元素的为" + size + "\n";
        for (int i = 0; i < size; i++) {
            result += elements[i].toString() + " ";
        }
        return result;
    }
}
