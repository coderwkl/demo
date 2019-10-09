package com.example.demo.queue;

import java.util.ArrayDeque;

/**
 * @Description: 双端队列 滑动窗口最大值问题
 * @Author: wukunlin
 * @CreateDate: 2019/9/26 上午9:56
 * @Version: 1.0
 *
 *
 * 算法非常直截了当：
 *
 * 处理前 k 个元素，初始化双向队列。
 *
 * 遍历整个数组。在每一步 :
 *
 * 清理双向队列 :
 *
 *   - 只保留当前滑动窗口中有的元素的索引。
 *
 *   - 移除比当前元素小的所有元素，它们不可能是最大的。
 * 将当前元素添加到双向队列中。
 * 将 deque[0] 添加到输出中。
 * 返回输出数组。
 *
 */
class Solution {
    private ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
    private int[] nums;

    private void cleanDeque(int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k){
            deq.removeFirst();
        }

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        // init deque and output
        this.nums = nums;
        int maxIdx = 0;
        for (int i = 0; i < k; i++) {
            cleanDeque(i, k);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        int[] output = new int[n - k + 1];
        output[0] = nums[maxIdx];

        // build output
        for (int i = k; i < n; i++) {
            cleanDeque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }
}