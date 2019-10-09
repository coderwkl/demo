package com.example.demo.tree;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
* @Description:    二叉树操作
* @Author:         wukunlin
* @CreateDate:     2019/8/26 16:13
* @Version:        1.0
*/
public class BinaryTreeOpt {

    static Node root;
    static Node tree;
    static {
        root = initTree();
        tree = initTree2();
    }

    /**
     * 普通二叉树节点
     * @return
     */
    private static Node initTree2() {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        a.leftNode = b;
        a.rightNode = c;
        b.leftNode = d;
        b.rightNode = e;
        c.leftNode = f;
        c.rightNode = g;
        return a;
    }

    /**
     * 二叉搜索树节点(BST)
     * @return
     */
    private static Node initTree(){
        Node a = new Node(4);
        Node b = new Node(2);
        Node c = new Node(5);
        Node d = new Node(1);
        Node e = new Node(3);
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
        Node node = root;
        while (node != null || !stack.isEmpty()){
            if(node != null){
                System.out.println(node.val);
                stack.push(node);
                node = node.leftNode;
            }else{
                node = stack.pop();
                node = node.rightNode;
            }
        }


        /*stack.push(root);
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
        }*/
    }

    /**
     * 之字遍历二叉树
     * @param root
     */
    private static void zhiOrder(Node root){
        if(null == root){
            return;
        }
        boolean isLeft = true;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            if(isLeft){
                while (!stack1.isEmpty()){
                    Node pop = stack1.pop();
                    System.out.println(pop.val);
                    if(pop.rightNode != null){
                        stack2.push(pop.rightNode);
                    }
                    if(pop.leftNode != null){
                        stack2.push(pop.leftNode);
                    }
                }
            }else{
                while (!stack2.isEmpty()){
                    Node pop = stack2.pop();
                    System.out.println(pop.val);
                    if(pop.leftNode != null){
                        stack1.push(pop.leftNode);
                    }
                    if(pop.rightNode != null){
                        stack1.push(pop.rightNode);
                    }
                }
            }
            isLeft = !isLeft;
        }
    }

    private static void digui(Node node){
        if(node == null){
            return;
        }
        digui(node.leftNode);
        digui(node.rightNode);
        System.out.println(node.val);
    }

    /**
     * 计算二叉树的深度
     */
    private static void deepth(){
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        int deepth = 0;
        while (!queue.isEmpty()){
            for (int i = 0; i < queue.size(); i++) {
                Node node = queue.poll();
                if(node != null){
                    System.out.println(node.val);
                    if(node.leftNode != null){
                        queue.offer(node.leftNode);
                    }
                    if(node.rightNode != null){
                        queue.offer(node.rightNode);
                    }
                }
            }
            deepth++;
        }
        System.out.println("deepth="+(deepth-1));
    }

    /**
     * 计算二叉树的深度 O(n)
     */
    private static void deepthSingle(){
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        queue.offer(new Node(666));
        int deepth = 0;
        while (!queue.isEmpty()){
            Node node = queue.poll();
            if(node != null){
                if(node.val == 666){
                    deepth++;
                    //null 元素为层标记
                    if(!queue.isEmpty()){
                        queue.offer(new Node(666));
                    }
                }
                System.out.println(node.val);
                if(node.leftNode != null){
                    queue.offer(node.leftNode);
                }
                if(node.rightNode != null){
                    queue.offer(node.rightNode);
                }
            }
        }
        System.out.println("deepth="+(deepth-1));
    }

    /**
     * 递归查找二叉树的最大值
     * @return
     */
    private static int findMax(Node node){
        int rootVal, left, right, max = Integer.MIN_VALUE;
        if(null != node){
            rootVal = node.val;
            left = findMax(node.leftNode);
            right = findMax(node.rightNode);
            if(left > right){
                max = left;
            }
            else {
                max = right;
            }
            if(rootVal > max){
                max = rootVal;
            }
        }
        return max;

    }

    /**
     * 递归查找元素
     * @param node
     * @param data
     * @return
     */
    private static boolean findElement(Node node, int data){
        if(node == null){
            return false;
        }
        if(node.val == data){
            return true;
        }else {
            if(data < node.val){
                return findElement(node.leftNode, data);
            }else{
                return findElement(node.rightNode, data);
            }
        }
    }

    /**
     * 打印所有子节点到根节点的路径
     */
    private static void printPaths(){
        int[] path = new int[256];
        printPaths(root, path, 0);
    }

    private static void printPaths(Node node, int[] path, int pathLen) {
        if(null == node){
            return;
        }
        path[pathLen++] = node.val;
        if(null == node.leftNode && null == node.rightNode){
            printArray(path, pathLen);
        }else{
            printPaths(node.leftNode, path, pathLen);
            printPaths(node.rightNode, path, pathLen);
        }
    }

    private static void printArray(int[] path, int pathLen) {
        for (int i = 0; i < pathLen; i++) {
            System.out.print(path[i]+"\t");
        }
        System.out.println();
    }

    private static Node mirrorOfBinaryTree(Node node){
        if(node == null){
            return null;
        }else{
            mirrorOfBinaryTree(node.leftNode);
            mirrorOfBinaryTree(node.rightNode);
            Node tmp = node.leftNode;
            node.leftNode = node.rightNode;
            node.rightNode = tmp;
        }
        return node;
    }

    /**
     * 打印某结点的所有祖先节点
     * @param root
     * @param node
     * @return
     */
    private static boolean printAllAncestors(Node root, Node node){
        if(root == null){
            return false;
        }
        if(node.equals(root.leftNode) || node.val == root.rightNode.val ||
                printAllAncestors(root.leftNode, node) || printAllAncestors(root.rightNode, node)){
            System.out.println(root.val);
            return true;
        }
        return false;
    }

    /**
     * 统计子二叉树的个数
     * @param node
     * @return
     */
    private static int subTreeNum(Node node, int sum){
        if(node == null){
            return sum;
        }
        if(node.leftNode != null || node.rightNode != null){
            int i = 0,j = 0;
            sum++;
            if(node.leftNode != null){
                i = subTreeNum(node.leftNode, sum);
            }
            if(node.rightNode != null){
                j = subTreeNum(node.rightNode, sum);
            }
            return i+j;
        }
        return sum;
    }

    /**
     * 将有序数组转换为BST二叉搜索树
     * @param a
     * @param left
     * @param right
     * @return
     */
    private static Node buildBST(int[] a, int left, int right){
        if(left > right){
            return null;
        }
        Node node = new Node();
        if(left == right){
            node.val = a[left];
            node.leftNode = null;
            node.rightNode = null;
        }else{
            int mid = left + (left + right) / 2;
            node.val = a[mid];
            node.leftNode = buildBST(a, left, mid-1);
            node.rightNode = buildBST(a, mid + 1, right);
        }
        return node;
    }

    /**
     * 找到第k小的元素
     * @return
     */
    private static Node kthSmallestInBST(Node node, int k, int count){
        if(node == null){
            return null;
        }
        Node left = kthSmallestInBST(node.leftNode, k, count);
        if(left != null){
            return left;
        }
        if(++count == k){
            return node;
        }
        return kthSmallestInBST(node.rightNode, k ,count);
    }

    /**
     * 递归删除所有叶子节点
     * @param node
     * @return
     */
    private static Node deleteLeafNodes(Node node){
        if(node != null){
            if(node.leftNode == null && node.rightNode == null){
                node = null;
            }else{
                node.leftNode = deleteLeafNodes(node.leftNode);
                node.rightNode = deleteLeafNodes(node.rightNode);
            }
        }
        return node;
    }

    /**
     * 递归删除所有节点
     * @param node
     * @return
     */
    private static Node deleteAllNodes(Node node){
        if(node != null){
            if(node.leftNode == null && node.rightNode == null){
                node = null;
            }else{
                node.leftNode = deleteAllNodes(node.leftNode);
                node.rightNode = deleteAllNodes(node.rightNode);
                if(node.leftNode == null && node.rightNode == null){
                    node = null;
                }
            }
        }
        return node;
    }

    public static void main(String[] args) {
        //breadOrder(root);
        //deepOrder(root);
        //zhiOrder(root);
        //digui(root);
        //deepthSingle();
        //System.out.println(findMax(root));
        //System.out.println(findElement(root, 7));
        //printPaths();
        //mirrorOfBinaryTree(root);
        //deepOrder(root);
        //printAllAncestors(tree, new Node(5));
        //System.out.println(subTreeNum(root, 0));
        //System.out.println(kthSmallestInBST(root, 1, 0).val);
        Node node = deleteLeafNodes(root);
        breadOrder(node);
    }

}
