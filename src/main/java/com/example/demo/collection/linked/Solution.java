package com.example.demo.collection.linked;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/7/29 下午4:04
 * @Version: 1.0
 */
public class Solution {

    public ListNode reverseList(ListNode head){

        //存储临时变量
        ListNode pre = null;
        ListNode next = null;

        while(null != head){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public ListNode removeNthFromEnd(ListNode head, int n){

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode node1 = dummy, node2 = dummy;
        // node1 节点先跑，node1节点 跑到第 n 个节点的时候,node2 节点开始跑
        // 当node1 节点跑到最后一个节点时，node2 节点所在的位置就是第 （L-n ） 个节点，也就是倒数第 n+1（L代表总链表长度）
        while (node1 != null) {
            node1 = node1.next;
            if(n < 1 && node1 != null){
                node2 = node2.next;
            }
            n--;
        }
        node2.next = node2.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        /*new Solution().reverseList(a);
        while(null != e){
            System.out.println(e.val);
            e = e.next;
        }*/
        new Solution().removeNthFromEnd(a, 2);
        while(null != a){
            System.out.println(a.val);
            a = a.next;
        }
    }
}
