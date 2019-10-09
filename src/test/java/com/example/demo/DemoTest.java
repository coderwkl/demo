package com.example.demo;

import com.example.demo.leecode.domain.ListNode;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/7/30 上午11:33
 * @Version: 1.0
 */
public class DemoTest {

    @Test
    public void test1(){
        int update = 0;
        do{
            System.out.println("aaa");
        }while(update != 1);
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<>(1);
        for (int j = 0; j < 3; j++) {
            new Thread(() -> {
                //UUID.randomUUID().toString().substring(1,8)
                //elementData[size++] = e;
                //elementData[size] = e;
                //size++;
                if(System.currentTimeMillis()%2L == 0){
                    list.add(String.valueOf(1));
                }else{
                    list.add(String.valueOf(2));
                }
                System.out.println(list);
            }).start();
        }
        while(Thread.activeCount() > 2){}
    }

    @Test
    public void test3(){
        /*int i = 0;
        for(;;i++){
            System.out.println(i);
        }*/
        int h;
        String str = "wkl";
        //List<Character> chars = str.toCharArray();
        int hash = (h = str.hashCode()) ^ (h >>> 16);
        System.out.println(hash);
    }

    @Test
    public void test4(){

        int i = 123;
        StringBuilder sb = new StringBuilder();
        while(i > 0){

            int x = i % 10;
            sb.append(x);

            i /= 10;
        }
        System.out.println(sb);

        System.out.println(null != null);

    }

    @Test
    public void test5(){
        System.out.println(test7(25));
    }

    private int popcount(int x) {
        int count;
        for (count = 0; x != 0; ++count) {
            //zeroing out the least significant nonzero bit
            x &= x - 1;
        }
        return count;
    }

    private float test6(int i){
        float max = i;
        float min = 0;
        float mid = (max+min)/2;
        while (true){
            if(Math.abs(i-mid*mid)<0.002){
                return mid;
            }
            if(mid * mid < i){
                min = mid;
            }
            if(mid * mid > i){
                max = mid;
            }
            mid = (max+min)/2;
        }
    }

    private int test7(int i){
        int max = i/2+1;
        int min = 0;
        while (min<max){
            int mid = (max+min+1)>>>1;
            int square = mid*mid;
            if(square > i){
                max = mid - 1;
            }else{
                min = mid;
            }
        }
        return min;
    }

    @Test
    public void test7(){
        Stack<Integer> stack = new Stack<>();
        /*stack.push(1);
        stack.push(2);*/
        System.out.println(stack.pop());
    }

    @Test
    public void test8(){
        //求最大公共前缀
        String[] strings = new String[]{"floor", "flaa", "flbbb"};
        String maxStr = strings[0];
        for (int i = 1; i < strings.length; i++) {
            while(strings[i].indexOf(maxStr) != 0){
                maxStr = maxStr.substring(0, maxStr.length()-1);
                //if(maxStr.isEmpty()) {return "";}
            }
        }
        System.out.println(maxStr);
        System.out.println("flaa".indexOf("floor"));
        System.out.println("===");
        int[][] arr = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        //之字遍历二维数组
        for (int i = 0; i < arr.length; i++) {
            //flag==0 左到右
            int flag = i % 2;
            for (int j = 0; j < arr[0].length; j++) {
                int val;
                if(flag == 0){
                    val = arr[i][j];
                }else{
                    val = arr[i][arr[0].length-j-1];
                }
                System.out.print(val+"\t");
            }
        }
    }

    @Test
    public void test9(){
        //插入排序
        int[] arr = new int[]{3,2,5,4,7};
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 ; j--) {
                if(arr[j] < arr[j-1]){
                    arr[j] = arr[j] ^ arr[j-1];
                    arr[j-1] = arr[j] ^ arr[j-1];
                    arr[j] = arr[j-1] ^ arr[j];
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test10(){
        //整数反转
        int i = 1234;
        int print = 0;
        while (i != 0){
            print = print * 10 + i % 10;
            i/=10;
        }
        System.out.println(print);
    }

    @Test
    public void test11(){
        //移除重复元素
        int[] arr = new int[]{3,3,5,4,6,4};
        HashSet<Integer> hashSet = new HashSet<>();
        for (Integer i : arr) {
            if(!hashSet.add(i)){
                hashSet.remove(i);
            }
        }
        System.out.println(Arrays.toString(hashSet.toArray()));
    }

    @Test
    public void test12(){
        //最长重复子串
        String str = "cccaaaabbbbb";
        int i = 0, j = 1;
        int max = 0;
        int x = 0,y = 0;
        char[] chars = str.toCharArray();
        for (int k = 1; k < chars.length; k++) {
            if(chars[k] == chars[k-1]){
                j++;
                if(k == chars.length-1){
                    if(j-i>max){
                        max = j-i;
                        x = i;
                        y = j;
                    }
                }
            }else{
                //map.put(chars[k], j-i);
                if(j-i>max){
                    max = j-i;
                    x = i;
                    y = j;
                }
                i = j;
                j++;
            }
        }
        System.out.println(str.substring(x,y));
    }

    @Test
    public void test13(){
        //init
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;

        //删除倒数第二个元素
        int n = 2;
        ListNode fast = a;
        ListNode slow = a;
        while (null != fast && null != fast.next){
            fast = fast.next.next;
            n--;
            if(n < 1){
                slow = slow.next;
            }
        }
        slow.next = slow.next.next;
        while (a!=null){
            System.out.println(a.val);
            a = a.next;
        }
    }

    @Test
    public void test14(){
        int[] arr = new int[]{3,2,5,4,7};
        quickSort(arr, 0, 4);
        System.out.println(Arrays.toString(arr));
    }
    private void quickSort(int[] arr, int m, int n) {
        Assert.notNull(arr, "arr must not be null");
        int head = m, tail = n;
        //基准元素
        int tmp = 0;
        if (head <= tail) {
            tmp = arr[head];
            while (head != tail) {
                while (head < tail && arr[tail] > tmp) {
                    tail--;
                }
                arr[head] = arr[tail];
                while (head < tail && arr[head] < tmp) {
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

    @Test
    public void test15(){
        //合并有序数组
        int[] m = new int[]{1,4,5};
        int[] n = new int[]{2,3,6};
        int i = m.length-1;
        int j = n.length-1;
        int k = i+j+1;

        while (i>=0 && j>=0){
            m[k--] = m[i] > n[j] ? m[i--] : n[j--];
        }
        System.arraycopy(n, 0, m, 0, j+1);
        System.out.println(Arrays.toString(m));
    }

    @Test
    public void test16(){
        //二分查找
        int res = 12;
        int[] arr = new int[]{1,4,7,9,10,11};
        int i = 0, j = arr.length-1;
        while (i <= j){
            int mid = (i+j)/2;
            if(arr[mid] == res){
                System.out.println(res);
                return;
            }else if(arr[mid] > res){
                j = mid-1;
            }else{
                i = mid+1;
            }
        }
        System.out.println(-1);
    }

    @Test
    public void testDigui(){
        int[] arr = new int[]{1,4,7,9,10,11};
        System.out.println(digui(arr, 0, 5));
    }
    private int digui(int[] arr, int i, int j){
        if(i > j)return -1;
        //二分查找
        int res = 11;
        int mid = (i+j)/2;
        if(arr[mid] == res){
            return res;
        }else if(arr[mid] > res){
            j = mid-1;
        }else{
            i = mid+1;
        }
        return digui(arr, i, j);
    }
    //买卖股票最佳时机
    @Test
    public void test17(){
        //在每一天时候，向前查看最低的股票价格
        int[] arr = new int[]{7,1,6,4,9};
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < min){
                min = arr[i];
            }else if(arr[i]-min > max){
                max = arr[i]-min;
            }
        }
        System.out.println(max);
    }
    //全排列
    @Test
    public void test18(){
        //function(16);
        new Thread(() -> {
            System.out.println("thread1");
            new Thread(() -> {
                System.out.println("thread2");
            }).start();
        }).start();
    }
    private void function(int n){
        if(n <= 1) return;
        if(n > 1){
            //System.out.println(n);
            function(n/2);
            //function(n/2);
            System.out.println(n);
        }
    }
    @Test
    public void test19(){
        int[] arr = {1,1,2,2,2,2,3,3,3};
        //统计有序数组出现次数最多的数，空间复杂度O(1)
        int number = 0, max = 0;
        for (int i = 0; i < arr.length; i++) {
            //短路，不会下标越界
            if(i == arr.length - 1 || arr[i] != arr[i+1]){
                int j = i;
                //找到上一个不相等的位置
                while (j >= 1 && arr[j] == arr[j-1]){
                    j--;
                }
                if(i-j+1 > max){
                    max = i-j+1;
                    number = arr[i];
                }
                if(i == arr.length - 1){
                    break;
                }
            }
        }
        System.out.println(number + "：" + max);
    }

    @Test
    public void test20(){
        int[] arr = {1,1,2,2,2,3,4,0,8,9,6,7,9};
        //0-9数 统计每个数出现次数
        //计数排序
        int[] res = new int[10];
        for (int i = 0; i < arr.length; i++) {
            res[arr[i]]++;
        }
        /*for (int i = 0; i < res.length; i++) {
            System.out.println(i+"出现了"+res[i]+"次");
        }*/
        for (int i = 0; i < res.length; i++) {
            if(res[i]>1){
                for (int j = 1; j < res[i]; j++) {
                    System.out.print(i+"\t");
                }
            }
            System.out.print(i+"\t");
        }
    }

    @Test
    public void test21(){
        int[] arr = {3,2,1,2,2,3};
        Set<Integer> integers = new HashSet<Integer>(){
            @Override
            public int hashCode() {
                int h = 0;
                Iterator i = iterator();
                while (i.hasNext()) {
                    Object obj = i.next();
                    if (obj != null)
                        h += obj.hashCode();
                }
                return h < 0 ? h : h;
            }
        };
        for (int i = 0; i < arr.length; i++) {
            if(!integers.add(arr[i])){
                integers.remove(arr[i]);
                integers.add(-arr[i]);
            }
        }
        integers.forEach(System.out::println);
    }

    @Test
    public void test22(){
        int[] T = new int[]{1,2,3};
        int[] P = new int[]{2,4};
        //P是否为T的子串
        int n = T.length;
        int m = P.length;
        //可选位置有n-m+1个
        for (int i = 0; i < n - m + 1; i++) {
            int j = 0;
            while (j < m && P[j] == T[i + j]){
                j++;
            }
            if(j == m){
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }
}
