package com.zmr;

/**
 * @author zhaochunlin (946599275@qq.com)
 * @since 2021/5/22
 */
public class CpuPressureTest {

    public static void main(String[] args) {
        // cpu核心数
        int num = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Thread(new PressureRunner());
            threads[i].start();
        }
    }

    public static class PressureRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                System.out.println("我哩个去 我正在使用cpu--thread-"+Thread.currentThread().getId());
            }
        }
    }
}
