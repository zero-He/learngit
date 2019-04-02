package com.example.data.structure.binarytree;

import java.util.LinkedList;

/**
 * Created by Evain on 2019/1/8.
 */
public class PreBinaryTree<T> {

    /*
     * 先序创建二叉树
     * 返回：根节点
     */
    public TreeNode<T> creatBinaryPre(LinkedList<T> treeData)
    {
        TreeNode<T> root=null;
        T data=treeData.removeFirst();
        if (data!=null)
        {
            root=new TreeNode<T>(data, null, null);
            root.left=creatBinaryPre(treeData);
            root.right=creatBinaryPre(treeData);
        }
        return root;
    }
    /*
     * 先序遍历二叉树（递归）
     */
    public void PrintBinaryTreePreRecur(TreeNode<T> root)
    {
        if (root!=null)
        {
            System.out.print(root.data);
            PrintBinaryTreePreRecur(root.left);
            PrintBinaryTreePreRecur(root.right);
        }
    }

    static class TreeNode<T> {
        public T data;
        public TreeNode<T> left;
        public TreeNode<T> right;

        public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * * 测试二叉树的  创建、遍历
     *  * 二叉树结构如下
     *  *            A
     *  *       B         C
     *  *    D     E   F     G
     *  *  H   I        J
     * @param args
     */
    public static void main(String[] args) {
        PreBinaryTree<Character> binaryTree=new PreBinaryTree<>();

        //输入ABDH##I##E##CF#J##G##（#用null代替）
        LinkedList<Character> tree=new LinkedList<>();
        tree.add('A');tree.add('B');tree.add('D');
        tree.add('H');tree.add(null);tree.add(null);
        tree.add('I');tree.add(null);tree.add(null);
        tree.add('E');tree.add(null);tree.add(null);
        tree.add('C');tree.add('F');tree.add(null);
        tree.add('J');tree.add(null);tree.add(null);
        tree.add('G');tree.add(null);tree.add(null);

        TreeNode<Character> root=binaryTree.creatBinaryPre(tree);

        //先序遍历（递归）
        binaryTree.PrintBinaryTreePreRecur(root);System.out.println();

    }
}
