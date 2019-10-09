package com.example.demo.queue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: 队列操作
 * @Author: wukunlin
 * @CreateDate: 2019/9/26 上午11:01
 * @Version: 1.0
 */
public class QueueSolution {

    //交叉队列实现
    //1,2,3  4,5,6 --  1,4,2,5,3,6
    //arrayDeque 默认尾进头出
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        QueueSolution solution = new QueueSolution();
        //solution.interLeavingQueue(queue);
        solution.reverseQueueFirstKElements(3, queue);

        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }

    /**
     * 合并操作
     * @param queue
     */
    public void interLeavingQueue(Queue<Integer> queue){
        if(queue.size() % 2 != 0){
            throw new IllegalArgumentException();
        }
        Stack<Integer> stack = new Stack<>();
        int halfSize = queue.size()/2;
        for (int i = 0; i < halfSize; i++) {
            stack.push(queue.poll());
        }
        while (!stack.isEmpty()){
            queue.offer(stack.pop());
        }
        //3 2 1 4 5 6
        for (int i = 0; i < halfSize; i++) {
            queue.offer(queue.poll());
        }
        for (int i = 0; i < halfSize; i++) {
            stack.push(queue.poll());
        }
        while (!stack.isEmpty()){
            queue.offer(stack.pop());
            queue.offer(queue.poll());
        }
    }

    /**
     * 逆置前k个元素
     * @param k
     * @param queue
     */
    public void reverseQueueFirstKElements(int k, Queue<Integer> queue){
        if(queue == null || k > queue.size()){
            throw new IllegalArgumentException();
        }else if(k > 0){
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < k; i++) {
                stack.push(queue.poll());
            }
            while (!stack.isEmpty()){
                queue.offer(stack.pop());
            }
            //逆置剩余元素
            for (int i = 0; i < queue.size() - k; i++) {
                queue.offer(queue.poll());
            }
        }

    }
}
