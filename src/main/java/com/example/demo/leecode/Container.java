package com.example.demo.leecode;

/**
 * @Description: 盛水最多的容器
 * @Author: wukunlin
 * @CreateDate: 2019/8/29 下午4:43
 * @Version: 1.0
 */
public class Container {

    private static int maxContain(int[] arr){
        int max = 0, l = 0, r = arr.length - 1;
        while(l<r){
            max = Math.max(max, (Math.min(arr[l],arr[r])) * (r-l));
            if(arr[l] < arr[r]){
                l++;
            }else {
                r--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,8,5,7,4,6};
        System.out.println(maxContain(arr));
    }
}
