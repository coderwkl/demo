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

    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        a.next = b;
        b.next = c;

        ListNode d = new ListNode(5);
        ListNode e = new ListNode(6);
        ListNode f = new ListNode(4);
        d.next = e;
        e.next = f;

        ListNode listNode = addTwoNumbers(a, d);
        while(null != listNode){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
