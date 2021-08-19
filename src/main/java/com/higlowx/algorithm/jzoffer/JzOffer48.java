package com.higlowx.algorithm.jzoffer;

/**
 * 不用加减乘除做加法
 *
 * 示例:
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 * 提示：
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 * leetcode-cn：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
 * nowcoder：https://www.nowcoder.com/practice/59ac416b4b944300b617d4f7f111b215
 *
 * @author Dylan.Li
 * @date 2021/8/19
 */

public class JzOffer48 {


    /**
     *
     * a = 7，b = 3
     * 正确答案：
     * a + b =
     * 0000 0111 +
     * 0000 0011 =
     * 0000 1010 =
     * 10
     *
     * a ^ b =
     * 0000 0111 ^
     * 0000 0011 =
     * 0000 0100 //有进位情况，得到 结果1
     *
     * (a & b) << 1 =
     * (0000 0111 & 0000 0011) << 1 =
     * 0000 0011 << 1 =
     * 0000 0110 //没有进位情况，得到 结果2
     *
     * 理论上，如果允许使用 加法 的情况下，结果1（十进制为4） + 结果2（十进制为6） 便能得到 最终结果 10
     * 但是，不允许使用 加法 ！！！！
     *
     * 脑筋急转弯一下，我们的目的有原来的 a（十进制为7） + b（十进制为3），
     * 变成了现在的 结果1（十进制为4） + 结果2（十进制为6）！！！
     *
     * 我们完全可以 继续套用上面的计算方式，再来一遍。
     *
     * 那何时停止循环呢？即得到 两个新的结果 后，不需要再进行 加法运算。
     * 如果 1 + 0 = 1，那还需要相加吗？是的，不需要了，结果 直接就是其中的 非0数 嘛！！
     *
     */
    public int add(int a, int b) {
        //但凡 a、b中有一个及以上为0，这个循环就会结束
        while(a != 0 && b != 0){
            int c = (a & b) << 1;
            a = a ^ b;
            b = c;
        }
        //返回理论上的非0数（特殊情况：初次传入的a和b均为0，也可以正常返回0）
        return a == 0 ? b : a;
    }

    public static void main(String[] args) {
        JzOffer48 instance = new JzOffer48();
        int add = instance.add(7, 3);
        System.out.println(add);
    }
}
