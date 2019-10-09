package com.example.demo.leecode.domain;

import java.util.List;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/29 下午4:58
 * @Version: 1.0
 */
public class ListNode {

    public int val;
    public ListNode next = null;
    public ListNode(int val) {
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
