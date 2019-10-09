package com.example.demo.collection.linked;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/7/29 下午4:04
 * @Version: 1.0
 */
public class Solution {

    public ListNode reverseList(ListNode head){

        //存储临时变量
        ListNode pre = null,next = null;

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

    private ListNode filterNode(ListNode node){
        if(node == null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        node = node.next;
        while (node != null){
            current.next = new ListNode(node.val);
            current = current.next;
            if(node.next == null){
                break;
            }
            node = node.next.next;
        }
        return dummy.next;
    }

    /**
     * 12345-21435
     * @param node
     */
    private static ListNode option(ListNode node){
        if(null == node){
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int flag = 0;
        Stack stack = new Stack();
        while (node != null){
            if(flag < 2){
                stack.push(node.val);
                flag++;
                node = node.next;
            }else {
                int pop = (int)stack.pop();
                current.next = new ListNode(pop);
                current = current.next;
                if(stack.isEmpty()){
                    flag = 0;
                }
            }
        }
        if(!stack.isEmpty()){
            current.next = new ListNode((int)stack.pop());
        }
        return dummy.next;
    }

    /**
     * 选择奇数节点并排序
     * @param node
     * @return
     */
    private static ListNode selectSort(ListNode node){
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        Stack<Integer> stack = new Stack<>();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        while (null != node){
            if(!stack.isEmpty()){
                Integer last = stack.lastElement();
                if(last < node.val){
                    Integer pop;
                    while (!stack.isEmpty() && (pop = stack.lastElement())<node.val){
                        stack.pop();
                        arrayDeque.push(pop);
                    }
                    stack.push(node.val);
                    while (!arrayDeque.isEmpty()){
                        stack.push(arrayDeque.pop());
                    }
                }else {
                    stack.push(node.val);
                }
            }else{
                stack.push(node.val);
            }
            node = node.next;
        }

        while (!stack.isEmpty()){
            ListNode listNode = new ListNode(stack.pop());
            current.next = listNode;
            current = current.next;
        }
        return dummy.next;
    }

    /**
     * 选择奇数节点并排序
     * @param node
     * @return
     */
    private static ListNode selectSort2(ListNode node){
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (null != node){
            if(node.val % 2 == 1){
                node = node.next;
                continue;
            }
            //查找插入位置
            while (null != current.next){
                if(node.val > current.val && node.val < current.next.val) {
                    break;
                }
                current = current.next;
            }
            ListNode next = current.next;
            ListNode listNode = new ListNode(node.val);
            current.next = listNode;
            listNode.next = next;
            current = dummy;
            node = node.next;
        }
        return dummy.next;
    }

    /**
     * 判断链表是否为回文
     * @param node
     * @return
     */
    private static boolean isHuiwen(ListNode node){
        if(node == null){
            return false;
        }
        ListNode slow = node;
        ListNode fast = node;

        while (null != fast && null != fast.next){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode next = null;
        ListNode pre = null;
        //翻转链表前半部分
        while (node != slow){
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        //奇数节点去掉，后移
        if(null != fast){
            slow = slow.next;
        }
        while (null != pre){
            if(pre.val != slow.val){
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }

        return true;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(1);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        e.next = a;

        //插入节点
        /*ListNode insert = new ListNode(5);
        insert.next = c.next;
        c.next = insert;*/

        /*new Solution().reverseList(a);
        while(null != e){
            System.out.println(e.val);
            e = e.next;
        }*/
        //new Solution().removeNthFromEnd(a, 2);
        //ListNode res = new Solution().filterNode(a);
        //ListNode res = selectSort2(a);
        ListNode current = a;
        while(current != null){
            System.out.println(current.val);
            current = current.next;
            if(current == a){
                return;
            }
        }
        //System.out.println(isHuiwen(a));
    }
}
