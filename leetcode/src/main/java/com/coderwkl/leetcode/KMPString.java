package com.coderwkl.leetcode;

/**
 * @Description: KMP字符串匹配
 * @Author: wukunlin
 * @CreateDate: 2019/10/9 上午10:49
 * @Version: 1.0
 */
public class KMPString {

    /**
     * 根据检索串计算前缀表
     * @param index
     * @return
     */
    private int[] pregfixTab(char[] index){
        int[] prefix = new int[index.length];
        int i = 1, j = 0;
        prefix[0] = 0;
        prefix[1] = 0;
        while (i < index.length){
            if(index[i] == index[j]){
                prefix[i] = j + 1;
                i++;
                j++;
            }else if(j > 0){
                j = prefix[j-1];
            }else{
                prefix[i] = 0;
                i++;
            }
        }
        return prefix;
    }

    /**
     * KMP匹配算法
     * @return
     */
    private int kmpAlgorithm(char[] text, char[] index){
        int i = 0, j = 0;
        //前缀表
        int[] prefix = pregfixTab(index);
        while (i < text.length){
            if(text[i] == index[j]){
                if(j == index.length-1){
                    return i-j;
                }else{
                    i++;
                    j++;
                }
            }else if(j > 0){
                j = prefix[j-1];
            }else {
                i++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        KMPString kmp = new KMPString();
        int index = kmp.kmpAlgorithm(new char[]{'b','a','c','b','a','b','a','b','a','b','a','c','a','c','a'},
                new char[]{'a','b','a','b','a','c','a'});
        System.out.println(index);
    }
}
