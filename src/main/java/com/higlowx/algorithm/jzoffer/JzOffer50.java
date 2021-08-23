package com.higlowx.algorithm.jzoffer;

import java.util.HashSet;

/**
 * 找出数组中重复的数字。
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * <p>
 * 限制：
 * 2 <= n <= 100000
 * <p>
 * leetcode-cn：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * nowcoder：https://www.nowcoder.com/practice/6fe361ede7e54db1b84adc81d09d8524
 *
 * @author Dylan.Li
 * @date 2021/8/23
 */

public class JzOffer50 {

    /**
     * 本题如果要优化的话，时间复杂度基本没有优化的可能，始终为O(n)。
     * 但是可以通过直接在原数组上操作，避免额外的空间占用，将空间复杂度从O(n)提升到O(1)。
     * 详情可参考：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/solution/mian-shi-ti-03-shu-zu-zhong-zhong-fu-de-shu-zi-yua/
     */
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i : nums) {
            if(!set.add(i)){
                return i;
            }
        }
        return -1;
    }

}
