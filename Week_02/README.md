#学习笔记
对于不同 GC 的总结：

1.在 GCLogAnalysis 的场景：

| GC              | 512M  | 1024M | 2048M | 4096M |
| --------------- | ----- | ----- | ----- | ----- |
| SerialGC        | 14443 | 18456 | 17333 | 15572 |
| ParallelGC      | 13023 | 20263 | 23220 | 21463 |
| ConcMarkSweepGC | 14397 | 20392 | 16485 | 16333 |
| G1GC            | 16865 | 25792 | 25189 | 24394 |

SerialGC 在小内存的场景下表现不错，ParallelGC 在多线程场景下表现很好，G1GC 在大内存的场景下表现优异。CMS呃，已经被标记为过时了。台式机主频较高，内存小于 512m 直接报错。

2.在 gateway-server 场景：
由于 gateway-server 业务叫简单，而且测试主机 CPU 较高，测试出来 Serial/Parallel/ConcMarkSweep/G1 差别并不大。

3.在更复杂的业务场景：
G1 明显让响应延时更低，Parallel 明显让整体吞吐量更大，CMS 更像是 Parallel 这种注重吞吐量的垃圾收集器向 G1 这种注重低延迟的垃圾收集器的过渡，整体来讲 CMS 并没有太大的优势。