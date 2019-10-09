package com.example.demo.collection.linked;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/29 上午10:08
 * @Version: 1.0
 */
public class Solution2 {

    /**
     * 多叉树求最大值路径
     * @param node
     * @return
     */
    private static void maxValue(Pair<ListNode,Integer> node){

        /*Stack<Pair<ListNode,Integer>> stack = new Stack<>();
        stack.push(node);
        //当前层
        int currentCeng = 0;
        //最大值
        while(!stack.isEmpty()){
            Pair<ListNode,Integer> currentNode = stack.pop();
            System.out.println(currentNode.getKey().val);
            if(null != currentNode.getKey().children){
                ListNode listNode = currentNode.getKey();
                for(Pair<ListNode,Integer> child : listNode.children){
                    currentPair = child;
                    if(child.getValue() == currentCeng && child.getKey().val > listNode.val){
                        currentMax = child.getKey().val;
                        currentNode = child;
                    }
                }

            }
            if()
            stack.push(currentNode);
        }*/
    }

    private static void digui(Pair<ListNode,Integer> node, int[] arr){
        if(node.getKey().children == null){
            return;
        }
        for(Pair<ListNode,Integer> child : node.getKey().children){
            //层级最大值
            int max = arr[child.getValue()-1];
            if(child.getKey().val > max){
                arr[child.getValue()-1] = child.getKey().val;
            }
            digui(child, arr);
        }
    }


    public static void main(String[] args) {
        Pair<ListNode,Integer> a = new Pair<ListNode,Integer>(new ListNode(8), 1);

        List<Pair<ListNode,Integer>> children = new ArrayList<>();
        Pair<ListNode,Integer> b = new Pair<ListNode,Integer>(new ListNode(6), 2);
        Pair<ListNode,Integer> c = new Pair<ListNode,Integer>(new ListNode(7), 2);
        Pair<ListNode,Integer> d = new Pair<ListNode,Integer>(new ListNode(5), 2);
        children.add(b);
        children.add(c);
        children.add(d);

        a.getKey().children = children;

        List<Pair<ListNode,Integer>> children1 = new ArrayList<>();
        Pair<ListNode,Integer> e = new Pair<ListNode,Integer>(new ListNode(10), 3);
        children1.add(e);
        b.getKey().children = children1;

        List<Pair<ListNode,Integer>> children2 = new ArrayList<>();
        Pair<ListNode,Integer> f = new Pair<ListNode,Integer>(new ListNode(4), 3);
        children2.add(f);
        c.getKey().children = children2;

        int[] arr = new int[16];
        arr[0] = a.getKey().val;
        digui(a, arr);
        System.out.println(Arrays.toString(arr));
    }
}
