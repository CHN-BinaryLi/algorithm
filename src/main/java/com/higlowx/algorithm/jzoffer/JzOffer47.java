package com.higlowx.algorithm.jzoffer;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * <p>
 * 示例:
 * 输入: n = 3
 * 输出: 6
 *
 * @author Dylan.Li
 * @date 2021/8/18
 */

public class JzOffer47 {

    /**
     * 逻辑运算符的短路效应 + 递归
     * 以逻辑运算符 && 为例，对于 A && B 这个表达式，如果 A 表达式返回 False ，那么 A && B 已经确定为 False ，此时不会去执行表达式 B。
     * 同理，对于逻辑运算符 ||， 对于 A || B 这个表达式，如果 A 表达式返回 True ，那么 A || B 已经确定为 True ，此时不会去执行表达式 B。
     *
     * 复杂度分析
     * 时间复杂度：O(n)。递归函数递归 n 次，每次递归中计算时间复杂度为 O(1)，因此总时间复杂度为 O(n)。
     * 空间复杂度：O(n)。递归函数的空间复杂度取决于递归调用栈的深度，这里递归函数调用栈深度为 O(n)，因此空间复杂度为 O(n)。
     *
     */
    public int sumNums(int n) {
        boolean flag = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    public static void main(String[] args) {
        JzOffer47 instance = new JzOffer47();
        int i = instance.sumNums(3);
        System.out.println(i);
    }
}
