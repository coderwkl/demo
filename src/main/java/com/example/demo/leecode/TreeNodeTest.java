package com.example.demo.leecode;

import com.example.demo.leecode.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/30 下午4:19
 * @Version: 1.0
 */
public class TreeNodeTest {

    public static void main(String[] args) {
        /*TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(4);
        a.leftNode = b;
        TreeNode c = new TreeNode(2);
        b.leftNode = c;
        TreeNode d = new TreeNode(5);
        a.rightNode = d;*/

        //TreeNode root = mergeTree(a, c);
        //lastOrder(root);

        System.out.println("======");
        //digui(a);
        //minDepth(a);
        //maxDepth(a);

        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(1);
        TreeNode e = new TreeNode(1);
        TreeNode f = new TreeNode(1);
        a.leftNode = b;
        a.rightNode = c;
        b.leftNode = d;
        d.leftNode = e;
        e.rightNode = f;

        int i = maxSamePath(a, 0);
        System.out.println("samePath="+i);

    }

    /**
     * 合并树
     * @param a
     * @param c
     */
    private static TreeNode mergeTree(TreeNode a, TreeNode c) {
        if(a == null){
            return c;
        }
        if(c == null){
            return a;
        }
        TreeNode newNode = new TreeNode(a.val+c.val);
        newNode.leftNode = mergeTree(a.leftNode, c.leftNode);
        newNode.rightNode = mergeTree(a.rightNode, c.rightNode);
        return newNode;
    }

    /**
     * 后序遍历
     * @param root
     */
    private static void lastOrder(TreeNode root){
        if(root == null){
            return;
        }
        Stack<TreeNode> order = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node!= null || !stack.isEmpty()){
            if (node != null) {
                stack.push(node);
                order.push(node);
                node = node.rightNode;
            }else{
                TreeNode pop = stack.pop();
                node = pop.leftNode;
            }
        }

        while (!order.isEmpty()){
            System.out.print(order.pop().val+"\t");
        }
    }

    private static void minDepth(TreeNode root){
        if(null == root){
            System.out.println("depth="+0);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()){
            for (int i = 0; i < queue.size(); i++) {
                TreeNode current = queue.poll();
                if(null != current){
                    if(null == current.leftNode && null == current.rightNode){
                        System.out.println("depth="+level);
                        return;
                    }
                    if(null != current.leftNode){
                        queue.offer(current.leftNode);
                    }
                    if(null != current.rightNode){
                        queue.offer(current.rightNode);
                    }
                }
            }
            level++;
        }
    }

    private static void maxDepth(TreeNode root){
        if(null == root){
            System.out.println("depth="+0);
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()){
            for (int i = 0; i < queue.size(); i++) {
                TreeNode current = queue.poll();
                if(null != current){
                    if(null != current.leftNode || null != current.rightNode){
                        level++;
                        if(null != current.leftNode){
                            queue.offer(current.leftNode);
                        }
                        if(null != current.rightNode){
                            queue.offer(current.rightNode);
                        }
                    }
                }
            }
        }
        System.out.println("depth="+level);
    }

    private static void digui(TreeNode node){
        if(node == null){
            return;
        }
        System.out.println(node.val);
        digui(node.leftNode);
        digui(node.rightNode);
    }

    /**
     * 最长同值路径
     * @param node
     * @param path
     */
    private static int maxSamePath(TreeNode node, int path){
        if(null == node.leftNode && null == node.rightNode){
            return path;
        }
        if(null != node.leftNode && node.leftNode.val == node.val){
            path++;
            path = maxSamePath(node.leftNode, path);
        }
        if(null != node.rightNode && node.rightNode.val == node.val){
            path++;
            path = maxSamePath(node.rightNode, path);
        }
        return path;
    }
}
