package com.example.demo.leecode;

import com.example.demo.leecode.domain.ListNode;

/**
 * @Description: 链表
 * @Author: wukunlin
 * @CreateDate: 2019/8/29 下午5:00
 * @Version: 1.0
 */
public class ListNodeTest {

    /**
     * 合并两个链表
     */
    private static ListNode reverse(ListNode l1, ListNode l2){
        ListNode current = new ListNode(0);
        ListNode dummy = current;

        ListNode m = l1, n = l2;

        while (m != null || n!=null){
            if(m == null){
                current.next = n;
                n = n.next;
            }
            else if(n == null){
                current.next = m;
                m = m.next;
            }else{
                if(m.val <= n.val){
                    current.next = m;
                    m = m.next;
                }else {
                    current.next = n;
                    n = n.next;
                }
            }

            current = current.next;
        }
        return dummy.next;
    }

    /**
     * 判断链表是否有环
     * @param head
     * @return
     */
    private static boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        if(head == null || head.next == null){
            return false;
        }
        while (slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;

        }
        return true;
    }



    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(4);
        a.next = b;
        b.next = c;

        ListNode d = new ListNode(1);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);
        d.next = e;
        e.next = f;

        ListNode reverse = reverse(a, d);
        while (reverse!=null){
            System.out.println(reverse.val);
            reverse = reverse.next;
        }


        ListNode listNode1 = new ListNode(1);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode4;
        listNode4.next = listNode5;

        System.out.println(hasCycle(a));

    }
}
