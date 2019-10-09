package com.example.demo.tantan;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/9/11 下午2:00
 * @Version: 1.0
 */
public class TanStack<T> extends Stack<T>{

    private Stack<T> maxStack = new Stack<>();

    public void pushVal(T ele){
        super.push(ele);
        if(maxStack.isEmpty()){
            maxStack.push(ele);
        }else{
            T peek = maxStack.peek();
            if(((Integer)peek).compareTo((Integer)ele) <= 0){
                maxStack.push(ele);
            }
        }
    }

    public T popVal(){
        T pop = super.pop();
        if(maxStack.peek().equals((Integer)pop)){
            maxStack.pop();
        }
        return pop;
    }

    public T peekVal(){
        return super.peek();
    }

    public T maxVal(){
        try{
            return maxStack.peek();
        }catch (EmptyStackException e){
            return null;
        }
    }

}
