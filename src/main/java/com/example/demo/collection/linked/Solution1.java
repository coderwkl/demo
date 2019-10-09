package com.example.demo.collection.linked;


public class Solution1 {

    /**
     * 两个链表数值相加
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        ListNode m = l1;
        ListNode n = l2;
        //进位标识符
        int carry = 0;
        while(m != null || n != null){
            int x = m == null ? 0 : m.val;
            int y = n == null ? 0 : n.val;
            int sum = x + y + carry;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if(m != null){
                m = m.next;
            }
            if(n != null){
                n = n.next;
            }
        }
        if(carry > 0){
            curr.next = new ListNode(carry);
        }
        return dummy.next;
    }

    /**
     * 求链表的交点
     * @param l1
     * @param l2
     * @return
     */
    static int intersectLinked(ListNode l1, ListNode l2){
        ListNode m = l1;
        ListNode n = l2;
        int counter = 0;
        int i = l1.length() + l2.length();
        while(m.val != n.val){
            counter++;
            //当counter等于i时执行下面逻辑形成环
            //此时val!=说明找不到交点
            if(counter == i){
                return -1;
            }
            if(m.next == null){
                m.next = l2;
            }
            if(n.next == null){
                n.next = l1;
            }
            m = m.next;
            n = n.next;

        }
        return m.val;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        a.next = b;

        ListNode d = new ListNode(5);
        ListNode e = new ListNode(6);
        ListNode f = new ListNode(1);
        ListNode g = new ListNode(4);
        d.next = e;
        e.next = f;
        f.next = g;

        ListNode listNode = addTwoNumbers(a, d);
        while(null != listNode){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
        /*int i = intersectLinked(a, d);
        System.out.println(i);*/
    }
}
