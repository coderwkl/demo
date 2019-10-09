package com.example.demo.leecode;

import java.util.*;

/**
 * @Description: 两数之和
 * @Author: wukunlin
 * @CreateDate: 2019/8/29 下午3:32
 * @Version: 1.0
 */
public class SumArr {

    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};


    private static Map<Integer,Integer> sumArr(int[] arr, int sum){
        int[] res  = new int[16];
        Map<Integer,Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if(map.containsKey(sum-arr[i])){
                map.put(sum-arr[i],arr[i]);
            }
            else{
                map.put(arr[i], -1);
            }
        }
        return map;
    }

    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 三数相加为0
     * @param nums
     * @return
     */
    private static List<List<Integer>> threeSum(int[] nums){
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if(null == nums || length < 3){
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            if(nums[i] > 0){
                return res;
            }
            //去重
            if(i > 0 && nums[i-1] == nums[i]){
                continue;
            }
            int L = i + 1;
            int R = length - 1;
            while (L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    //去重
                    while (L<R && nums[L] == nums[L+1]){ L++;}
                    while (L<R && nums[R] == nums[R-1]){ R--;}
                    L++;
                    R--;
                }else if(sum < 0){
                    L++;
                }else {
                    R--;
                }
            }
        }

        return res;
    }

    private static void removeElement(int[] arr){
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[arr.length - 1]);
        for(int i = arr.length - 2; i >=0; i--){
            Integer integer = stack.lastElement();
            if(arr[i] != integer){
                stack.push(arr[i]);
            }
        }
        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    /**
     * 前一元素减后一元素最大值
     * @param arr
     * @return
     */
    private static int subtract(int[] arr){
        int max = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            max = Math.max(max, arr[i] - arr[i+1]);
        }
        return max;
    }

    /**
     * 移动0到末尾
     * @param arr
     * @return
     */
    private static int[] moveZero(int[] arr){
        //0,1,0,3,12
        int i=0;
        int j=arr.length-1;
        while (i<j){
            if(arr[i]==0 && arr[j]!=0){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }else {
                if(arr[i] != 0){
                    i++;
                }
                if(arr[j] == 0){
                    j--;
                }
            }
        }
        return arr;
    }

    /**
     * 移动零到末尾(有序)
     * @param arr
     * @return
     */
    private static int[] moveZero2(int[] arr){
        //0,1,0,3,12
        //0的个数
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 0){
                count++;
            }else{
                arr[i-count] = arr[i];
            }
        }
        for (int i = arr.length - 1; i > arr.length - 1 - count ; i--) {
            arr[i] = 0;
        }

        return arr;
    }

    /**
     * 移除最小值，使总和最大
     * @return
     */
    private static int removeMin(){
        int[] arr = new int[]{1,-3,5,2,4,6};
        int min = arr[0];
        int sum = 0;
        for (int anArr : arr) {
            min = Math.min(anArr, min);
            sum += anArr;
        }
        return sum-min;
    }

    /**
     * 奇数位放奇数
     */
    private static void exchange(){
        int[] arr = new int[]{1,3,2,4};
        //偶数指针
        //int i = 0;
        //奇数指针
        //int j = 1;
        for(int i=0,j=1; i < arr.length && j<arr.length; ){
            if(arr[i]%2 != 0 && arr[j]%2 ==0 ){
                int k = arr[i];
                arr[i] = arr[j];
                arr[j] = k;
                i+=2;
                j+=2;
            }else if(arr[i]%2 == 0 && arr[j]%2 !=0){
                i+=2;
                j+=2;
            }else if(arr[i]%2 == 0 && arr[j]%2 ==0){
                i+=2;
            }else {
                j+=2;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 寻找插入位置
     */
    private static void insertPosition(){
        //输入: [1,3,5,6], 5
        //输出: 2
        int[] arr = new int[]{1,3,5,6};
        int val = 5;
        for (int i = 0; i < arr.length-1; ) {
            if(arr[i] <= val && arr[i+1] > val){
                System.out.println(i);
                break;
            }
            i++;
        }
    }

    /**
     * 去除重复元素
     */
    private static void removeEle(){
        //nums = [0,1,2,2,3,0,4,2], val = 2,
        //函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int ele = 2;
        int numbers = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == ele){
                numbers++;
                continue;
            }
            int k = nums[i];
            nums[i] = nums[i-numbers];
            nums[i-numbers] = k;
        }
        int[] newArr = Arrays.copyOf(nums, nums.length-numbers);
        System.out.println(Arrays.toString(newArr));
    }

    public static void main(String[] args) {
        /*int[] arr = new int[]{2,7,3,6,5};
        Map<Integer,Integer> map = sumArr(arr, 9);
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
        System.out.println("=======");
        int[] ints = twoSum(arr, 9);
        System.out.println(Arrays.toString(ints));
        System.out.println("=======");
        int[] arr1 = new int[]{-1,0,1,1,-2,1};
        List<List<Integer>> lists = threeSum(arr1);
        for(List<Integer> list : lists){
            list.forEach(str -> System.out.print(str+"\t"));
            System.out.println();
        }

        System.out.println("========");*/
        //int[] arr2 = new int[]{0,1,0,3,12};
        //System.out.println(Arrays.toString(moveZero2(arr2)));
        exchange();
        insertPosition();
        removeEle();
    }
}
