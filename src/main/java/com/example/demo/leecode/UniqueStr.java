package com.example.demo.leecode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 最长不重复子串
 * @Author: wukunlin
 * @CreateDate: 2019/8/29 下午4:16
 * @Version: 1.0
 */
public class UniqueStr {

    private static int strLength(String str){
        int length = str.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < length && j < length){
            if(!set.contains(str.charAt(j))){
                set.add(str.charAt(j++));
                ans = Math.max(ans, j - i);
            }else {
                set.remove(str.charAt(i++));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "abcdaef";
        System.out.println(strLength(str));
    }

}
