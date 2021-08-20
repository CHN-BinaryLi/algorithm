package com.higlowx.algorithm.jzoffer;

/**
 * 把字符串转换成整数
 * <p>
 * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * <p>
 * 注意：
 * 假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * <p>
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为[−231, 231− 1]。如果数值超过这个范围，请返回 INT_MAX (2^31− 1) 或INT_MIN (−2^31) 。
 * <p>
 * 示例1:
 * 输入: "42"
 * 输出: 42
 * 示例2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。因此无法执行有效的转换。
 * 示例5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。因此返回 INT_MIN (−2^31) 。
 * <p>
 * <p>
 * leetCode-cn：https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
 * nowcoder：https://www.nowcoder.com/practice/1277c681251b4372bdef344468e4f26e
 * leetCode：https://leetcode-cn.com/problems/string-to-integer-atoi
 *
 * @author Dylan.Li
 * @date 2021/8/20
 */

public class JzOffer49 {

    /**
     * 本题的核心：
     * 1. ('0' ～ '9' 的 ASCII码) - ('0' 的 ASCII码) = int 中的 0 ～ 9
     * 2. 输入字符串的格式是否合法，对应该如何处理
     * 3. 最大数与最小数的边界处理问题
     *
     * 时间复杂度与空间复杂度均为 O(n)
     */
    public int strToInt(String str) {
        if (str == null) {
            return 0;
        }
        //去掉两头空格
        str = str.trim();
        if ("".equals(str)) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int res = 0;
        //默认符号位 1，表示正数；为 -1 时表示负数，最终结果返回 sign * res；
        int sign = 1;
        //首个字符 相关
        char first;
        if ((first = chars[0]) == '+' || first == '-') {
            sign = first == '+' ? sign : -1;
        } else if (first < '0' || first > '9') {
            return res;
        } else {
            res = first - '0';
        }
        //拼接逻辑
        char p;
        for (int i = 1; i < chars.length; i++) {
            //出现不连续数字时
            if ((p = chars[i]) < '0' || p > '9') {
                break;
            }
            //逆推比较，防止res越界
            if (res > (Integer.MAX_VALUE - (p - '0')) / 10) {
                //越界时返回
                //为什么不是 return sign * Integer.MAX_VALUE？
                //因为Integer.MAX_VALUE=2147483647，在sign=-1时，不能返回-2147483648
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            //拼接
            res = res * 10 + (p - '0');
        }
        return sign * res;
    }

    public static void main(String[] args) {

        System.out.println('1' - '0');
        System.out.println('0' - '0');
        System.out.println('7' - '0');

        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);

        JzOffer49 instance = new JzOffer49();
        System.out.println(instance.strToInt("-91283472332"));

    }
}
