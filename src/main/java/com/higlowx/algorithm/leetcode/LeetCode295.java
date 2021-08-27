package com.higlowx.algorithm.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 * [2,3,4]的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 示例：
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 * 进阶:
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * leetcode-cn：https://leetcode-cn.com/problems/find-median-from-data-stream
 *
 * @author Dylan.Li
 * @date 2021/8/27
 */

public class LeetCode295 {

    //本题的核心：
    // 1.使用大顶堆与小顶堆，且必须维护"大顶堆的top节点 <= 小顶堆的top节点"
    // 2.使用某种手段维护两堆的"节点数"满足："差最多不超过1 或相等"
    // 3.新插入的num要与大顶堆与小顶堆的top进行比较，并在需要交换时候进行交换，避免打破上述 核心1

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    private boolean intoMax = true;

    /**
     * initialize your data structure here.
     */
    public LeetCode295() {
        this.minHeap = new PriorityQueue<Integer>();
        this.maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
    }

    public void addNum(int num) {
        if (this.maxHeap.isEmpty()) {
            this.maxHeap.offer(num);
            this.intoMax = false;
            return;
        }
        if (!this.intoMax) {
            //add into min heap
            if (this.maxHeap.peek() > num) {
                Integer poll = this.maxHeap.poll();
                this.maxHeap.offer(num);
                this.minHeap.offer(poll);
            } else {
                this.minHeap.offer(num);
            }
            this.intoMax = true;
            return;
        }
        //add into max heap
        if (this.minHeap.peek() < num) {
            Integer poll = this.minHeap.poll();
            this.minHeap.offer(num);
            this.maxHeap.offer(poll);
        } else {
            this.maxHeap.offer(num);
        }
        this.intoMax = false;
    }

    public double findMedian() {
        int topOfMaxHeap = this.maxHeap.isEmpty() ? 0 : this.maxHeap.peek();
        if (this.minHeap.size() != this.maxHeap.size()) {
            return topOfMaxHeap;
        }
        int topOfMinHeap = this.minHeap.isEmpty() ? 0 : this.minHeap.peek();
        return (double) (topOfMaxHeap + topOfMinHeap) / 2;
    }

    public static void main(String[] args) {
        //["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
        //[[],[-1],[],[-2],[],[-3],[],[-4],[],[-5],[]]
        LeetCode295 ins = new LeetCode295();
        ins.addNum(-1);
        System.out.println(ins.findMedian());
        ins.addNum(-2);
        System.out.println(ins.findMedian());
        ins.addNum(-3);
        System.out.println(ins.findMedian());
        ins.addNum(-4);
        System.out.println(ins.findMedian());
        ins.addNum(-5);
        System.out.println(ins.findMedian());

    }
}
