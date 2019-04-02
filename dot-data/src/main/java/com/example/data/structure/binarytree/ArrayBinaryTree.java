package com.example.data.structure.binarytree;

/**
 * Created by Evain on 2019/1/7.
 */




public class ArrayBinaryTree<T> {

    private int deep;

    private int len;

    T root;

    Object[] array;



    public ArrayBinaryTree() {

//设置一个默认深度

        deep = 10;

        len = (int) (Math.pow(2, deep) - 1);

        //创建一个数组来存储这个

        array = new Object[len - 1];

        array[0] = 1;

        root = (T) array[0];

    }



    public ArrayBinaryTree(int deep, T root) {

        array[0] = root;

        this.root = root;

        this.deep = deep;

        len = (int) (Math.pow(2, deep) - 1);

        //创建一个数组来存储这个

        array = new Object[len - 1];

    }



    public void setChild(int index, T value, boolean isLeft) {

        if (array[index] == null) {

            System.out.println("该结点为空，不能建立其子树");

        } else {

            if (isLeft && 2 * index + 1 <= len + 1) {

                array[2 * index + 1] = value;

                int i = 2 * index + 1;

            } else if (!isLeft && 2 * index + 1 <= len + 2) {

                array[2 * index + 2] = value;

                int i = 2 * index + 2;

            } else {

                System.out.println("您的下标不在合法范围内");

            }

        }



    }



    public Object getTree(int index) {

        return array[index];

    }



    public Object getRight(int index) {

        if (index < 0 || (int) Math.pow(2, deep - 1) > len - 1) {

            System.out.println("位置越界了或者该节点是root结点");

            return -1;

        } else {

            return array[index * 2 + 2];

        }

    }



    public Object getLeft(int index) {

        if (index < 0 || (int) Math.pow(2, deep - 1) > len - 1) {

            System.out.println("位置越界了或者该节点是root结点");

            return -1;

        } else {

            return array[index * 2 + 1];

        }

    }



    public int getDeep() {

        return deep;

    }



    public int getLen() {

        return (int) Math.pow(2, deep);

    }



    public T getRoot() {

        return root;

    }

}

