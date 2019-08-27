package com.example.demo.tree;

import java.util.ArrayDeque;

/**
* @Description:    二叉树操作
* @Author:         wukunlin
* @CreateDate:     2019/8/26 16:13
* @Version:        1.0
*/
public class BinaryTreeOpt {

    static Node root;
    static {
        root = initTree();
    }

    public static Node initTree(){
        Node a = new Node(3);
        Node b = new Node(2);
        Node c = new Node(5);
        Node d = new Node(1);
        Node e = new Node(4);
        Node f = new Node(6);

        a.leftNode = b;
        a.rightNode = c;
        b.leftNode = d;
        b.rightNode = e;
        c.rightNode = f;

        return a;
    }

    /**
     * 广度遍历
     * @param root
     */
    public static void breadOrder(Node root){
        if(null == root){
            return;
        }
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node node = queue.remove();
            System.out.print(node.val + "\t");
            if(null != node.leftNode){
                queue.add(node.leftNode);
            }
            if(null != node.rightNode){
                queue.add(node.rightNode);
            }
        }
    }

    /**
     * 先序遍历
     * @param root
     */
    public static void deepOrder(Node root){
        if(null == root){
            return;
        }
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        System.out.println();
        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.val + "\t");
            if(null != node.rightNode){
                stack.push(node.rightNode);
            }
            if(null != node.leftNode){
                stack.push(node.leftNode);
            }
        }
    }


    public static void main(String[] args) {
        breadOrder(root);
        deepOrder(root);
    }

}
