package com.example.demo.heap;

import java.util.Arrays;

/**
 * @Description: 堆表示
 * 一种可能的方法是使用数组
 * 因为堆在形式上是一颗完全二叉树，用数组来存储它不会浪费任何空间
 * @Author: wukunlin
 * @CreateDate: 2019/9/29 下午2:53
 * @Version: 1.0
 */
public class HeapTest {

    private static int[] arr;
    static {
        arr = new int[]{3,5,2,7,4,1};
    }

    /**
     * 调整入口
     * 第k大的元素
     */
    private static void adjustEntrance(int k){
        if(arr == null){
            return;
        }
        //从第一个非叶子节点从下至上从右至左调整
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeap(i, arr.length);
        }
        for (int j = arr.length-1; j >= arr.length-k; j--) {
            int tmp = arr[0];
            arr[0] = arr[j];
            arr[j] = tmp;
            adjustHeap(0, j);
        }
    }

    /**
     * 调整大顶堆
     */
    private static void adjustHeap(int i, int length){
        int tmp = arr[i];
        for (int k = 2*i+1; k < length; k*=2+1) {
            if(k+1 < length && arr[k] < arr[k+1]){
                k++;
            }
            if(arr[k] > tmp){
                arr[i] = arr[k];
                i = k;
            }else {
                break;
            }
        }
        arr[i] = tmp;
    }

    private static void insertSort(){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]){
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                }
            }
        }
    }

    private static void printArr(){
        for (int x : arr){
            System.out.print(x + "\t");
        }
        System.out.println();
    }

    /**
     * 路径动态规划
     * 状态转移方程 -- dp[i][j]=dp[i-1][j]+dp[i][j-1]
     * @return
     */
    private static int pathDynamicPlanning(int m, int n){
        /*int [][]dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];*/
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j-1] + dp[j];
            }
        }
        return dp[m-1];
    }

    public static void main(String[] args) {
        //adjustEntrance(2);
        //insertSort();
        //printArr();
        System.out.println(pathDynamicPlanning(3,3));
    }

}
