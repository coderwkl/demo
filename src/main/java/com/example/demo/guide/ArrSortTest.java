package com.example.demo.guide;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/27 上午9:31
 * @Version: 1.0
 */
public class ArrSortTest {

    private static void quickSort(int[] arr, int m, int n) {
        Assert.notNull(arr, "arr must not be null");
        int head = m, tail = n;
        //基准元素
        int tmp = 0;
        if (head <= tail) {
            tmp = arr[head];
            while (head != tail) {
                while (head < tail && arr[tail] >= tmp) {
                    tail--;
                }
                arr[head] = arr[tail];
                while (head < tail && arr[head] <= tmp) {
                    head++;
                }
                arr[tail] = arr[head];
            }
            //基准元素归位
            arr[tail] = tmp;
            quickSort(arr, m, head - 1);
            quickSort(arr, tail + 1, n);
        }
    }

    public static void heapSort(int[] arr){
        //从第一个非叶子节点从下至上从右至左调整
        for(int i = arr.length/2-1; i>=0; i--){
            adjustHeap(arr, i, arr.length);
        }
        //调整堆结构+交换堆顶与末尾元素
        for (int j = arr.length-1; j >= arr.length-2 ; j--) {
            int tmp = arr[0];
            arr[0] = arr[j];
            arr[j] = tmp;
            adjustHeap2(arr, 0, j);
        }
    }
    /**
     * 构造大顶堆(升序)
     * @param arr
     * @param i
     * @param length
     */
    private static void adjustHeap(int[] arr, int i, int length){
        //当前元素
        int tmp = arr[i];
        for(int k = i * 2 + 1; k < length; k*=2+1){
            //如果左节点小于右节点
            if(k+1 < length && arr[k] < arr[k+1]){
                k++;
            }
            //如果子节点大于父节点(不用交换)
            if(arr[k] > tmp){
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = tmp;
    }

    private static void adjustHeap2(int[] arr, int i, int length){
        int tmp = arr[i];
        for (int j = 2*i+1; j < length; j*=2+1) {
            //左节点小于右节点
            if(j+1<length && arr[j] < arr[j+1]){
                j++;
            }
            //子节点大于根节点
            if(arr[j] > tmp){
                arr[i] = arr[j];
                i = j;
            }else{
                break;
            }
        }
        arr[i] = tmp;
    }


    public static List<Integer> maxIntStr(int[] a, int[] b){
        List<Integer> list = new ArrayList<>();
        int i=0, j=0;
        while(i<a.length&&j<b.length){
            if(a[i]<b[j]){
                i++;
            }
            else if(a[i]>b[j]){
                j++;
            }
            else{
                list.add(a[i]);
                i++;
                j++;
            }
        }
        return list;
    }

    /**
     * 上n级台阶(斐波那契数列)
     * @param n
     * @return
     */
    private static int febnaqi(int n){
        int[] arr = new int[n+1];
        arr[1] = 1;
        arr[2] = 2;
        for(int i=3;i<n+1;i++){
            arr[i] = arr[i-1]+arr[i-2];
        }
        return arr[n];
    }

    /**
     * 计算除自身外的数的乘积
     * @param arr
     * @return
     */
    private static int[] solution(int[] arr){
        //分别生成左右两边的乘积
        int[] res = new int[arr.length];
        int m = 1, n = 1;
        for (int i = 0; i < arr.length; i++) {
            res[i] = m;
            m *= arr[i];
        }
        for (int j = arr.length-1; j > 0; j--) {
            n *= arr[j];
            res[j-1]*=n;
        }
        return res;
    }

    /**
     * 最短无序子数组
     * @return
     */
    private static int minSubArr(){
        int[] nums = new int[]{1,5,2,4};
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }

    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,2,4,6};
        //quickSort(arr,0,5);
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        //List<Integer> list = maxIntStr(new int[]{1,2,6,8,9}, new int[]{6,7,8});
        //list.forEach(System.out::println);
        System.out.println("===");
        int[] arr1 = new int[]{1,2,3,4};
        for (int j = arr1.length-1; j > 0; j--) {
            System.out.println(arr1[j]);
        }
        System.out.println(Arrays.toString(solution(arr1)));
        System.out.println("====");
        int x = 1 ^ 4;
        int count = 0;
        while (x != 0){
            x &= (x-1);
            count++;
        }
        System.out.println(count);
        System.out.println("====");
        System.out.println(uniquePaths(3,3));
    }

}
