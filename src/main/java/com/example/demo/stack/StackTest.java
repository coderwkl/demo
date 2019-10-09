package com.example.demo.stack;

import java.util.Stack;

public class StackTest {

    /**
     * 判断pop是否为push的弹出子序列
     * @param push
     * @param pop
     * @return
     */
    public static boolean isPopSequence(int[] push, int[] pop){
        if(push.length == 0 || pop.length == 0){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        //记录子序列的弹出匹配位置
        int popIndex = 0;
        for(int i = 0; i < push.length; i++){
            stack.push(push[i]);
            //如果栈不为空，且栈顶元素等于弹出序列
            while(!stack.isEmpty() && stack.peek() == pop[popIndex]){
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    private static void invertStack(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        Integer tmp = null;
        tmp = stack.pop();

    }

    public static void main(String[] args) {
        System.out.println(isPopSequence(new int[]{1,2,3,4,5}, new int[]{2,1,5,4,3}));
    }
}
