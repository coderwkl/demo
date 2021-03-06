package com.example.demo.collection.linked;

import javafx.util.Pair;

import java.util.List;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/7/29 下午4:02
 * @Version: 1.0
 */
public class ListNode {

    int val;
    ListNode next = null;

    //多叉树
    List<Pair<ListNode,Integer>> children;


    ListNode(int val) {
        this.val = val;
    }

    public int length(){
        int i = 0;
        ListNode listNode = this;
        while(listNode != null){
            listNode = listNode.next;
            i++;
        }
        return i;
    }
}
