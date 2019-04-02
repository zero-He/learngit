package com.example.data.structure.binarytree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的深度优先遍历和广度优先遍历
 * @author  * Created by Evain on 2019/1/8.
 */
public class BinaryTreeTraverseTest {
    public static void main(String[] args) {
        BinarySortTree<Integer> tree = new BinarySortTree<Integer>();

        tree.insertNode(35);
        tree.insertNode(20);
        tree.insertNode(15);
        tree.insertNode(16);
        tree.insertNode(29);
        tree.insertNode(28);
        tree.insertNode(30);
        tree.insertNode(40);
        tree.insertNode(50);
        tree.insertNode(45);
        tree.insertNode(55);

        System.out.print("先序遍历（递归）：");
        tree.preOrderTraverse(tree.getRoot());
        System.out.println();
        System.out.print("中序遍历（递归）：");
        tree.inOrderTraverse(tree.getRoot());
        System.out.println();
        System.out.print("后序遍历（递归）：");
        tree.postOrderTraverse(tree.getRoot());
        System.out.println();

        System.out.print("先序遍历（非递归）：");
        tree.preOrderTraverseNoRecursion(tree.getRoot());
        System.out.println();
        System.out.print("中序遍历（非递归）：");
        tree.inOrderTraverseNoRecursion(tree.getRoot());
        System.out.println();
        System.out.print("后序遍历（非递归）：");
        tree.postOrderTraverseNoRecursion(tree.getRoot());
        System.out.println();

        System.out.print("广度优先遍历：");
        tree.breadthFirstTraverse(tree.getRoot());
        System.out.println();
        System.out.print("二叉树查找");
        tree.binSearch(tree.getRoot(),15);
        System.out.println();
        tree.delete(tree.getRoot(),29);
        System.out.print("先序遍历（递归）：");
        tree.preOrderTraverse(tree.getRoot());
    }
}

/**
 * 结点
 */
class Node<E extends Comparable<E>> {

    E value;
    Node<E> left;
    Node<E> right;

    Node(E value) {
        this.value = value;
        left = null;
        right = null;
    }

}

/**
 * 使用一个先序序列构建一棵二叉排序树（又称二叉查找树）
 */
class BinarySortTree<E extends Comparable<E>> {

    private Node<E> root;

    BinarySortTree() {
        root = null;
    }

    public void insertNode(E value) {
        if (root == null) {
            root = new Node<E>(value);
            return;
        }
        Node<E> currentNode = root;
        while (true) {
            if (value.compareTo(currentNode.value) > 0) {
                if (currentNode.right == null) {
                    currentNode.right = new Node<E>(value);
                    break;
                }
                currentNode = currentNode.right;
            } else {
                if (currentNode.left == null) {
                    currentNode.left = new Node<E>(value);
                    break;
                }
                currentNode = currentNode.left;
            }
        }
    }

    public Node<E> getRoot(){
        return root;
    }

    /**
     * 先序遍历二叉树（递归）
     * @param node
     */
    public void preOrderTraverse(Node<E> node) {
        System.out.print(node.value + " ");
        if (node.left != null)
            preOrderTraverse(node.left);
        if (node.right != null)
            preOrderTraverse(node.right);
    }

    /**
     * 中序遍历二叉树（递归）
     * @param node
     */
    public void inOrderTraverse(Node<E> node) {
        if (node.left != null)
            inOrderTraverse(node.left);
        System.out.print(node.value + " ");
        if (node.right != null)
            inOrderTraverse(node.right);
    }

    /**
     * 后序遍历二叉树（递归）
     * @param node
     */
    public void postOrderTraverse(Node<E> node) {
        if (node.left != null)
            postOrderTraverse(node.left);
        if (node.right != null)
            postOrderTraverse(node.right);
        System.out.print(node.value + " ");
    }

    /**
     * 先序遍历二叉树（非递归）
     * @param root
     */
    public void preOrderTraverseNoRecursion(Node<E> root) {
        LinkedList<Node<E>> stack = new LinkedList<Node<E>>();
        Node<E> currentNode = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            currentNode = stack.pop();
            System.out.print(currentNode.value + " ");
            if (currentNode.right != null)
                stack.push(currentNode.right);
            if (currentNode.left != null)
                stack.push(currentNode.left);
        }
    }

    /**
     * 中序遍历二叉树（非递归）
     * @param root
     */
    public void inOrderTraverseNoRecursion(Node<E> root) {
        LinkedList<Node<E>> stack = new LinkedList<Node<E>>();
        Node<E> currentNode = root;
        while (currentNode != null || !stack.isEmpty()) {
            // 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.right;
        }
    }

    /**
     * 后序遍历二叉树（非递归）
     * @param root
     */
    public void postOrderTraverseNoRecursion(Node<E> root) {
        LinkedList<Node<E>> stack = new LinkedList<Node<E>>();
        Node<E> currentNode = root;
        Node<E> rightNode = null;
        while (currentNode != null || !stack.isEmpty()) {
            // 一直循环到二叉排序树最左端的叶子结点（currentNode是null）
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            currentNode = stack.pop();
            // 当前结点没有右结点或上一个结点（已经输出的结点）是当前结点的右结点，则输出当前结点
            while (currentNode.right == null || currentNode.right == rightNode) {
                System.out.print(currentNode.value + " ");
                rightNode = currentNode;
                if (stack.isEmpty()) {
                    return; //root以输出，则遍历结束
                }
                currentNode = stack.pop();
            }
            stack.push(currentNode); //还有右结点没有遍历
            currentNode = currentNode.right;
        }
    }

    /**
     * 广度优先遍历二叉树，又称层次遍历二叉树
     * @param root
     */
    public void breadthFirstTraverse(Node<E> root) {
        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        Node<E> currentNode = null;
        queue.offer(root);
        while (!queue.isEmpty()) {
            currentNode = queue.poll();
            System.out.print(currentNode.value + " ");
            if (currentNode.left != null)
                queue.offer(currentNode.left);
            if (currentNode.right != null)
                queue.offer(currentNode.right);
        }
    }


    /**
     * 二叉树查找
     * @param root
     * @param e
     * @return
     */
    public Node<E> binSearch(Node<E> root,E e){
        if (root == null) return null;
        if (root.value == e) {
            System.out.println(root.value);
            return root;
        }else if (root.value.compareTo(e) == 1){
            binSearch(root.left,e);
        }else {
            binSearch(root.right,e);
        }
        return null;

    }

    public void delete(Node<E> root,E e){
        if (root == null) return;
        Node p = root;
        Node pp = null;
        while(p != null && p.value != e){
            pp = p;
            if (p.value.compareTo(e) > 0 ){
                p = p.left;
            }else {
                p = p.right;
            }
        }

        if (p == null)return;//没有找到

        if (p.left != null && p.right != null){
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null){
                minPP = minP;
                minP = minP.left;
            }
            p.value = minP.value;
            p = minP ;
            pp = minPP;

        }

        Node child ;
        if (p.left != null) child = p.left;
        if (p.right != null) child = p.right;
        else child = null;

        if (pp ==null) root = child;
        else if (pp.right == p) pp.right = child;
        else  pp.left = child;


    }

}
