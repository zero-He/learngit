package com.example.data.structure.binarytree;

/**
 * Created by Evain on 2019/1/15.
 * 堆：一种特殊的树，完全二叉树
 */
public class Heap {
    private int n;
    private int count;
    private int[] a;//数组从小标1开始存储数据

    public Heap(int capacity){
        a = new int[capacity +1];
        count = 0;
        n = capacity;
    }

    public void insert(int data){
        if (count > n)return;
        ++count;
        a[count] = data;

        int i = count  ;

        while (i/2 > 0 && a[i] > a[i/2]){
            swap(a,i,i/2);
            i = i/2;
        }
    }
    void swap(int[] a,int i,int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public void removeMax(){
        if (count == 0 )return;
        a[1] = a[count];
        count--;

        heapify(a,count,1);
    }

    private void heapify(int[] a,int n,int i){//自上而下堆化
        while (true){
            int maxPos = i;
            if (i * 2 < n && a[i] < a[2*i]) maxPos = 2* i ;
            if (i*2 +1 < n && a[i] < a[2*i + 1]) maxPos = 2* i +1;
            if (maxPos == i )break;
            swap(a,i,maxPos);
            i = maxPos;
        }
    }


    private  void buildHeap(int[] a, int n) {
        for (int i = n/2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    // n 表示数据的个数，数组 a 中的数据从下标 1 到 n 的位置。
    public  void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }


    public static void main(String[] args) {
        Heap heap = new Heap(4);
        heap.insert(15);
        heap.insert(10);
        heap.insert(3);
        heap.insert(12);
        heap.removeMax();
        for (int i = 0; i < heap.count +1  ; i++) {
            System.out.println(heap.a[i]);
        }
    }
}
