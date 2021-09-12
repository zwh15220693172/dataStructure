package com.zvio.linkedList;

import com.zvio.MyList;

/**
 * 链表内部为节点类型
 * 优点：增删相对ArrayList较快
 * 缺点：绝对不要访问他，访问要遍历，超级慢的
 * 数据域记录第一个节点以及最后一个节点
 * 因此他在最前面或者最后面添加数据是超级快的
 * 有队列的意思在里面
 */
public class MyLinkedList<E> implements MyList<E> {
    // 第一个节点
    private MyNode<E> firstNode;
    // 最后一个节点
    private MyNode<E> lastNode;
    // 长度大小
    private int size;

    /**
     * LinkedList的特殊方法添加到第一个
     */
    public void addFirst(E e){
        MyNode<E> myNode = new MyNode<>(e);
        // 如果第一个为空
        if (this.firstNode == null) {
            // 那么第一个是自己
            this.firstNode = myNode;
            // 最后一个也是自己
            this.lastNode = myNode;
            this.firstNode.setNextNode(this.lastNode);
            this.lastNode.setLastNode(this.firstNode);
        } else {
            this.firstNode.setLastNode(myNode);
            myNode.setNextNode(this.firstNode);
            this.firstNode = myNode;
        }
        size++;
    }

    /**
     * LinkedList的特殊方法添加到最后一个
     */
    public void addLast(E e){
        if (this.firstNode == null) {
            this.addFirst(e);
        } else {
            MyNode<E> node = new MyNode<>(e);
            this.lastNode.setNextNode(node);
            node.setLastNode(this.lastNode);
            this.lastNode = node;
            size++;
        }
    }

    /**
     * 直接加到第一个就对了
     */
    @Override
    public void add(E e) {
        this.addFirst(e);
    }

    /**
     * 在索引的下表处
     */
    @Override
    public void add(int index, E e) {
        MyNode<E> node = new MyNode<>(e);
        MyNode<E> currentNode = this.firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextNode;
        }
        // 把上一个节点的下一个节点设置成新加入的节点
        MyNode<E> currentLastNode = currentNode.getLastNode();
        currentLastNode.nextNode = node;
        // 把当前节点的上一个节点设置成新加入的节点
        currentNode.lastNode = node;
        // 设置新加入的节点
        node.setLastNode(currentLastNode);
        node.setNextNode(currentNode);
        size++;
    }

    /**
     * 使用索引获取数据需要遍历，因此非常慢
     */
    @Override
    public E get(int index) {
        MyNode<E> currentNode = this.firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextNode;
        }
        return currentNode.value;
    }

    /**
     * 删除效率非常的高，因为遍历到元素只需要改变前后两个节点的位置即可
     */
    @Override
    public E remove(int index) {
        MyNode<E> currentNode = this.firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.nextNode;
        }
        // 上一个节点
        MyNode<E> lastNode = currentNode.lastNode;
        // 下一个节点
        MyNode<E> nextNode = currentNode.nextNode;
        lastNode.nextNode = nextNode;
        nextNode.lastNode = lastNode;
        size--;
        return currentNode.value;
    }

    /**
     * 根据元素删除的思路也很简单，找到元素的下标删掉就好了
     */
    @Override
    public E remove(E e) {
        int index = -1;
        MyNode<E> currentNode = this.firstNode;
        for (int i = 0; i < size; i++) {
            E curValue = currentNode.value;
            if (curValue.equals(e)) {
                index = i;
            }
            currentNode = currentNode.nextNode;
        }
        return index == -1?null:remove(index);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        MyNode<E> currentNode = this.firstNode;
        String result = "元素的个数为: " + this.size + "\n";
        for (int i = 0; i < size; i++) {
            if (currentNode != null) {
                result += currentNode.value + " ";
                currentNode = currentNode.nextNode;
            }
        }
        return result;
    }

    // 节点类：记录当前值，上一个节点以及下一个节点
    // 这里的数据模式就是套娃
    class MyNode<E>{
        // 上一个节点
        private MyNode<E> lastNode;
        // 下一个节点
        private MyNode<E> nextNode;
        // 当前值
        private E value;

        public MyNode(E value){
            this.value = value;
        }

        public MyNode(MyNode<E> lastNode, MyNode<E> nextNode, E value){
            this.lastNode = lastNode;
            this.nextNode = nextNode;
            this.value = value;
        }

        public MyNode<E> getLastNode() {
            return lastNode;
        }

        public void setLastNode(MyNode<E> lastNode) {
            this.lastNode = lastNode;
        }

        public MyNode<E> getNextNode() {
            return nextNode;
        }

        public void setNextNode(MyNode<E> nextNode) {
            this.nextNode = nextNode;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }
}
