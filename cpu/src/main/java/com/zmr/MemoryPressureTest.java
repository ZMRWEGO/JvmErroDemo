package com.zmr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaochunlin (946599275@qq.com)
 * @since 2021/5/22
 * JVM参数设置(JDK1.8)
 * -XX:InitialHeapSize=20971520
 * -XX:MaxHeapSize=20971520
 * -XX:NewSize=10485760
 * -XX:MaxNewSize=10485760
 * -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=15
 * -XX:PretenureSizeThreshold=3145728/5242880
 * -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails
 * -XX:+PrintGCTimeStamps
 * -Xloggc:gc.log
 */
public class MemoryPressureTest {
    /**
     * 内部类，用于创建对象，存于堆内存中
     */
    static class TestOOM{

    }

    static void test(){
        //用于存放模拟的对象，防止GC回收，用List做对象引用
        List<TestOOM> list = new ArrayList<>();
            for (; ; ) {
                list.add(new TestOOM());
            }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            System.out.println("内存飙升 导致了oom");
        }
    }
}
