package com.example.demo.leecode;

import io.netty.channel.ChannelInitializer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/30 上午11:13
 * @Version: 1.0
 */
public class KuoHaoSolution {

    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};

    public static void main(String[] args) {
        String str = "()[]";
        System.out.println(solution(str));
    }

    /**
     * 括号匹配
     * @param str
     */
    private static boolean solution(String str) {
        if(str.length()>0 && !map.containsKey(str.charAt(0))){
            return false;
        }
        LinkedList<Character> stack = new LinkedList<Character>(){
            @Override
            public boolean add(Character character) {
                return super.add(character);
            }
        };
        for (Character c : str.toCharArray()) {
            if(map.containsKey(c)){
                stack.addLast(c);
            }else if(!map.get(stack.removeLast()).equals(c)){
                return false;
            }
        }
        return stack.size() == 0;
    }
}
