package com.example.demo.huiwen;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/25 下午12:07
 * @Version: 1.0
 */
public class TestHuiWen {

    public static void main(String[] args) {

        String str = "abcba";
        System.out.println(longestPalindrome2(str));
    }

    public static String longestPalindrome2(String T){
        String result=null;
        //存放最大回文字符串的长度
        int max=0;
        //遍历每一个字符，以每一个字符为中心判断奇偶扩展子串
        for(int i=0;i<T.length();i++){
            //定义两个数组下标指针，以i，i+1为中心的偶子序列
            int pStart=i;
            int pEnd=i+1;
            while(pStart>=0&&pEnd<=(T.length()-1)&&T.charAt(pStart)==T.charAt(pEnd)){
                pStart--;
                pEnd++;
            }
            //如果子字符串的长度>max，则暂存为最长子回文串  子回文串的长度=(pEnd-1)-(pStart+1)-1=pEnd-pStart-1
            if(pEnd-pStart-1>max){
                max=pEnd-pStart-1;
                result=T.substring( pStart+1, pEnd-1+1);
            }

            //以i为中心，判断扩展奇序列是否为回文串
            pStart=i-1;
            pEnd=i+1;
            while(pStart>=0&&pEnd<=(T.length()-1)&&T.charAt(pStart)==T.charAt(pEnd)){
                pStart--;
                pEnd++;
            }
            if (pEnd-pStart-1>max) {
                max=pEnd-pStart-1;
                result=T.substring(pStart+1, pEnd-1+1);
            }
        }
        return result;
    }
}
