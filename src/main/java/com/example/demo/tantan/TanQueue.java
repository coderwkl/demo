package com.example.demo.tantan;

import java.util.Stack;

/**
 * @Description: 探探笔试题
 * 设计一个先进先出的队列，可以插入正整数，并且提供一个方法返回最大值O(1)
 * @Author: wukunlin
 * @CreateDate: 2019/9/10 下午5:00
 * @Version: 1.0
 */
public class TanQueue<T> {

    //队首元素
    private T front;

    private TanStack<T> stack1 = new TanStack<>();
    private TanStack<T> stack2 = new TanStack<>();

    private void pushStack(T ele){
        if(stack1.isEmpty()){
            front = ele;
        }
        stack1.pushVal(ele);
    }

    private T popStack(){
        if(stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.pushVal(stack1.popVal());
            }
        }
        return stack2.popVal();
    }

    /***
     * 队首元素
     * @return
     */
    private T peekFront(){
        if(!stack2.isEmpty()){
            return stack2.peekVal();
        }
        return front;
    }

    private int maxVal(){
        int val1 = null == stack1.maxVal() ? 0 : (Integer) stack1.maxVal();
        int val2 = null == stack2.maxVal() ? 0 : (Integer) stack2.maxVal();
        return Math.max(val1, val2);
    }

    public static void main(String[] args) {

        TanQueue<Integer> queue = new TanQueue<>();
        queue.pushStack(3);
        queue.pushStack(5);

        queue.popStack();

        System.out.println(queue.maxVal());

        queue.pushStack(2);
        queue.pushStack(1);

        queue.popStack();

        System.out.println(queue.maxVal());

        queue.popStack();

        System.out.println(queue.maxVal());


    }
}
