package com.example.demo.stack;

import java.util.Stack;

/**
 * @Description: 只使用push，pop逆置栈
 * @Author: wukunlin
 * @CreateDate: 2019/9/25 下午3:55
 * @Version: 1.0
 */
public class StackReversal {

    private static void reverseStack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int tmp = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, tmp);
    }

    private static void insertAtBottom(Stack<Integer> stack, int data) {
        if(stack.isEmpty()){
            stack.push(data);
            return;
        }
        int tmp = stack.pop();
        insertAtBottom(stack, data);
        stack.push(tmp);
    }

    private static void print(Stack stack){
        if(null == stack){
            return;
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop().toString());
        }
    }

    private static Stack<Integer> stack1 = new Stack<>();
    private static Stack<Integer> stack2 = new Stack<>();

    private void push(int data){
        stack1.push(data);
    }

    private int pop(){
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        //Stack<Integer> stack = new Stack<>();
        StackReversal stack = new StackReversal();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        //print(stack);
        System.out.println("===");
        //reverseStack(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
