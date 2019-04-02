package com.example.data.structure.binarytree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Evain on 2019/1/8.
 * 功能：把一个数组得值存入二叉树，用三种方式进行遍历
 */
public class BinTreeTraverse {
    private int[] array = {1,2,3,4,5,6,7,8,9};
    private static List<Node> nodeList = null;

    private static class Node   {
        Node leftChild;
        Node rightChild;
        int date;

        public Node(int date) {
            leftChild = null;
            rightChild = null;
            this.date = date;
        }
    }

    public void createBinTree(){
        nodeList = new LinkedList<Node>();
        //数组数据依次转换为node节点
        for (int i = 0; i < array.length; i++) {
            nodeList.add(new Node(array[i]));
        }
        //对前lastParentIndex-1个父节点按照父节点与孩子节点的数字关系建立二叉树
        for (int i = 0; i < array.length / 2 - 1; i++) {
            //leftChild
            nodeList.get(i).leftChild = nodeList.get(i * 2 + 1);
            //right
            nodeList.get(i).rightChild = nodeList.get(i * 2 + 2);
        }

        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = array.length / 2 - 1;
        // 左孩子
        nodeList.get(lastParentIndex).leftChild = nodeList.get(lastParentIndex * 2 + 1);

        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (array.length % 2 == 1) {
            nodeList.get(lastParentIndex).rightChild = nodeList
                    .get(lastParentIndex * 2 + 2);
        }


    }

    /**
     * 先序遍历
     * @param node
     */
    public static void preOrderTraverse(Node node){
        if (node == null) return;
        System.out.println(node.date);
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    public static void main(String[] args) {
        BinTreeTraverse binTreeTraverse = new BinTreeTraverse();
        binTreeTraverse.createBinTree();

        Node root = nodeList.get(0);

        preOrderTraverse(root);
    }
}
