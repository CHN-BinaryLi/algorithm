package com.higlowx.algorithm.jzoffer;

/**
 * 构建乘积数组
 * <p>
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
 * 不能使用除法。
 * <p>
 * 示例:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 * <p>
 * 提示：
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 * <p>
 * leetcode-cn：https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof
 * nowcoder：https://www.nowcoder.com/practice/94a4d381a68b47b7a8bed86f2975db46
 *
 * @author Dylan.Li
 * @date 2021/8/24
 */

public class JzOffer51 {

    /**
     * 方法一：暴力循环
     * <p>
     * 时间复杂度O(n^2)，空间复杂度O(1)，可以得到正确结果，但是会超时。
     * 本题的优化只能在时间复杂度上进行
     */
    public int[] constructArr1(int[] a) {
        if (a.length == 0) {
            return a;
        }
        int[] b = new int[a.length];
        int temp = 1;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i == j) {
                    continue;
                }
                temp *= a[j];
            }
            b[i] = temp;
            temp = 1;
        }
        return b;
    }

    /**
     * 方法二 较容易理解
     * 思路见图片 jzOffer51.png
     * 时间复杂度O(n)，空间复杂度O(n)。
     */
    public int[] constructArr2(int[] a) {
        //bad case
        if (a.length == 0) {
            return a;
        }
        //上三角
        int[] bu = new int[a.length];
        bu[0] = 1;
        for (int i = 1; i < a.length; i++) {
            bu[i] = bu[i - 1] * a[i - 1];
        }
        //下三角
        int[] bd = new int[a.length];
        bd[a.length - 1] = 1;
        for (int i = a.length - 2; i >= 0; i--) {
            bd[i] = bd[i + 1] * a[i + 1];
        }
        //汇总组装
        for (int i = 0; i < a.length; i++) {
            bu[i] = bu[i] * bd[i];
        }
        return bu;
    }

    /**
     * 方法三 基于方法二的算法基础，加入动态规划，在空间复杂度方面优化
     * 时间复杂度O(n)，空间复杂度O(1)。
     */
    public int[] constructArr(int[] a) {
        //bad case
        if (a.length == 0) {
            return a;
        }
        //上三角
        int[] b = new int[a.length];
        b[0] = 1;
        for (int i = 1; i < a.length; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        //下三角
        int temp = 1;
        for (int i = a.length - 2; i >= 0; i--) {
            //因为每次循环时，只会使用bd中的一个值，所以可以使用中间值保存，避免了额外创建数组的空间消耗
            temp = temp * a[i + 1];
            b[i] = b[i] * temp;
        }
        return b;
    }
}