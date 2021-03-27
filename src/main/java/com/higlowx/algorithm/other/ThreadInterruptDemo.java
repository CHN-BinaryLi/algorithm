package com.higlowx.algorithm.other;

/**
 * @author Dylan.Li
 * @date 2021/3/27
 * @since
 */

public class ThreadInterruptDemo {

    public String doing() {
        synchronized (this) {
            try {
                int i = 1;
                while (i != 10) {
                    Thread.sleep(1000);
                    System.out.println(i);
                    i++;
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() +
                        " 持有锁后竟然收到了其他线程发来的中断信号");
                //ignore
            }
        }
        System.out.println(Thread.currentThread().getName() + " 在阻塞中被终端唤醒，立即返回");
        return "success";
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadInterruptDemo ins = new ThreadInterruptDemo();
        System.out.println("创建任务线程...");
        Thread thread = ins.newThread();
        thread.start();
        System.out.println("任务线程已运行...");
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + " 等待5秒后调用任务线程的中断方法，定向向任务线程发送中断信号");
        thread.interrupt();
    }

    public Thread newThread() {
        return new Thread(new Runnable() {
            public void run() {
                String doing = doing();
                System.out.println(doing);
            }
        });
    }
}
