package com.example.data.structure;

/**
 * Created by Evain on 2018/12/12.
 * 数组实现队列
 */

public class ArrayQueue {
    private String items[];
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    //申请队列
    public ArrayQueue(int capacity){
        items = new String[capacity];
        n = capacity;
    }

    //入队

    /**
     * 队列满了才统一进行一次搬移，节约每次搬移O（n-1）的时间复杂度
     * @param item
     * @return
     */
    public boolean enqueue(String item){
        if (tail == n){
            if (head == 0) return false;
            for (int i = head;i < tail ; i++){
                items[i-head] = items[i];
            }
            head = 0;
            tail -= head;
        }
        items[tail] = item;
        tail ++;
        return true;
    }

    //出队
    public String dequeue(){
        if (head == tail) return null;//队列为空
        String result = items[head];

        head++;
        return result;
    }

    public static void main(String[] args) {

        System.out.println(f1(4));
    }

    public static int digui(int n){
        if (n == 1) return 1;
        return digui(n - 1) + 1;
    }

    public static int f1(int n ){
        if (n == 1) return 1;
        if (n == 2) return 2;
        return f1(n-1) + f1(n-2);
    }
}

