# JvmErroDemo
java程序运行时异常模拟和总结

#### 1 cpu过高模拟
- cpu空转
- 频繁进行full gc 
由于我们设置了最大堆得大小，所以垃圾回收器会在达到阈值时进行
依次full gc，对新生代和老年代进行一次统一回收

- 排查步骤
1 top查看长期占用cpu的进程，从进程查看对应的线程
top 查看进程
top -p pid -H  
10进制转化为16进制  printf %0x tid
jstat pid | grep tid

#### 2 内存占用率过高
内存占用率过高
- 查看gc情况
jstat -gc pid 5000 
每5s刷新一次
- 打印案发现场
`jmap -dump:format=b,file=myHeap.hprof <pid>`
**为什么我内存没有变高，而cpu却变高了呢？**

因为你设置了堆的最大阈值，我们设置的一般是2G，所以只会导致
频繁的full gc


> 总结  cpu占用过高，可能是一些线程一直占用cpu导致的，一般看来
>就是线程长期阻塞；内存彪高的话，可能是我们的Xmx设置的比较大，同时
>一些存在一些大对象，直接进入老年代

