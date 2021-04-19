> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [joonwhee.blog.csdn.net](https://joonwhee.blog.csdn.net/article/details/106324537)

某日，囧辉和同事二狗决定就谁是 “&#￥* 大厦 11 楼 11 室（只有囧辉和二狗两人）HashMap 最强者” 展开一番较量。画面过于血腥，**成年人**请在未成年人陪同下观看。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X2pwZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxYVd5V3ZrWTViRXJGV1pvSzByZjFjUGJNTlF4amljMkJsbUQxY0ZGbHg3ak5Cb0QyYWpRSHF5QS8?x-oss-process=image/format,png)

好消息，本人最新**集合框架**面试题文章新鲜出炉，详细解析了当前 Java 面试中集合框架的高频面试题：[问遍了身边的大厂面试官朋友，我整理出这份 Java 集合高频面试题（带解析）](https://joonwhee.blog.csdn.net/article/details/115712641)

> 二狗：天天听你憨逼吹牛，是时候让你知道什么叫残忍了。

囧辉：二狗子，这屎可以乱吃，这话不能乱说哦。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X2dpZi9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxTHVBaWFvcGFhVHFvZTNyTndncXlJcGRzRnBsN3pIRWtjUjlQdDE2Y3pwd3dLWFdnMFJBbFBvUS8?x-oss-process=image/format,png)

> 二狗：先来点简单的，介绍下 HashMap 的底层数据结构吧。

囧辉：我们现在用的都是 JDK 1.8，底层是由 “数组 + 链表 + 红黑树” 组成，如下图，而在 JDK 1.8 之前是由 “数组 + 链表” 组成。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxemxxQlBuaklncUNoRjU1M1dDRDVldGM2a2QwTndpYndPN3A3aWNDdG9iNUY3YzJZNGdKZ3ZHelEv?x-oss-process=image/format,png)

> 二狗：为什么要改成 “数组 + 链表 + 红黑树”？

囧辉：主要是为了提升在 hash 冲突严重时（链表过长）的查找性能，使用链表的查找性能是 O(n)，而使用红黑树是 O(logn)。

> 二狗：那在什么时候用链表？什么时候用红黑树？

囧辉：对于插入，默认情况下是使用链表节点。当同一个索引位置的节点在新增后达到 9 个（阈值 8）：如果此时数组长度大于等于 64，则会触发链表节点转红黑树节点（treeifyBin）；而如果数组长度小于 64，则不会触发链表转红黑树，而是会进行扩容，因为此时的数据量还比较小。

对于移除，当同一个索引位置的节点在移除后达到 6 个，并且该索引位置的节点为红黑树节点，会触发红黑树节点转链表节点（untreeify）。

> 二狗：为什么链表转红黑树的阈值是 8？

囧辉：我们平时在进行方案设计时，必须考虑的两个很重要的因素是：时间和空间。对于 HashMap 也是同样的道理，简单来说，阈值为 8 是在时间和空间上权衡的结果（这 B 我装定了）。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X2pwZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxY0JCazdNNFM2RHEyR3NTcGlhbWljaWM2WWRpYVJVQXN1OXpYdkw0aWNxalRUT3BpYVNEWmV1bkRqOXpRLw?x-oss-process=image/format,png)

红黑树节点大小约为链表节点的 2 倍，在节点太少时，红黑树的查找性能优势并不明显，付出 2 倍空间的代价作者觉得不值得。

理想情况下，使用随机的哈希码，节点分布在 hash 桶中的频率遵循泊松分布，按照泊松分布的公式计算，链表中节点个数为 8 时的概率为 0.00000006（跟大乐透一等奖差不多，中大乐透？不存在的），这个概率足够低了，并且到 8 个节点时，红黑树的性能优势也会开始展现出来，因此 8 是一个较合理的数字。

> 二狗：（呦呦呦，时间和空间上权衡的结果，还装起 B 来了）那为什么转回链表节点是用的 6 而不是复用 8？

囧辉：如果我们设置节点多于 8 个转红黑树，少于 8 个就马上转链表，当节点个数在 8 徘徊时，就会频繁进行红黑树和链表的转换，造成性能的损耗。

> 二狗：（小菜鸡，懂得还不少）那 HashMap 有哪些重要属性？分别用于做什么的？

囧辉：除了用来存储我们的节点 table 数组外，HashMap 还有以下几个重要属性：1）size：HashMap 已经存储的节点个数；2）threshold：扩容阈值，当 HashMap 的个数达到该值，触发扩容。3）loadFactor：负载因子，扩容阈值 = 容量 * 负载因子。

> 二狗：threshold 除了用于存放扩容阈值还有其他作用吗？

囧辉：在我们新建 HashMap 对象时， threshold 还会被用来存初始化时的容量。HashMap 直到我们第一次插入节点时，才会对 table 进行初始化，避免不必要的空间浪费。

> 二狗：HashMap 的默认初始容量是多少？HashMap 的容量有什么限制吗？

囧辉：默认初始容量是 16。HashMap 的容量必须是 2 的 N 次方，HashMap 会根据我们传入的容量计算一个大于等于该容量的最小的 2 的 N 次方，例如传 9，容量为 16。

> 二狗：（你他娘的是在绕口令吧）你这个 *@%￥#& 的 N 次方是怎么算的？

囧辉：Talk is cheap. Show you the code。

```
static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
```

> 二狗：卧槽，还彪英文，来来来，这代码你给我解释下。

囧辉：我们先不看第一行 “int n = cap - 1”，先看下面的 5 行计算。

|=（或等于）：这个符号比较少见，但是 “+=” 应该都见过，看到这你应该明白了。例如：a |= b，可以转成：a = a | b。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZHMG9JMW5aWXBpYWNXc3cwQjRxVDlFWEZXaWJDNE5TZ2dNTnR0RldXUEJaaWNjUHBYeGdTTUE1aFpnOHVKdHpBU2hJVHdCVGlhS2RBMmpBLw?x-oss-process=image/format,png)

>>>（无符号右移）：例如 a >>> b 指的是将 a 向右移动 b 指定的位数，右移后左边空出的位用零来填充，移出右边的位被丢弃。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZHMG9JMW5aWXBpYWNXc3cwQjRxVDlFb1prYlppY3JzbklNcjFDN1VRbklZN2lhOWJTNndqMVhBdHh5UnVEN0RrOXh2NUtzaExwSEEzWWcv?x-oss-process=image/format,png)

假设 n 的值为 0010 0001，则该计算如下图：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxZGRSeW52YUpNc2pGWk1aVDFERE03UGliVGtpYnRQQ1hXS1VnM2Q5TVVKc010b1RRc2ljZjlua3dnLw?x-oss-process=image/format,png)

相信你应该看出来，这 5 个公式会通过最高位的 1，拿到 2 个 1、4 个 1、8 个 1、16 个 1、32 个 1。当然，有多少个 1，取决于我们的入参有多大，但我们肯定的是经过这 5 个计算，得到的值是一个低位全是 1 的值，最后返回的时候 +1，则会得到 1 个比 n 大的 2 的 N 次方。

这时再看开头的 cap - 1 就很简单了，这是为了处理 cap 本身就是 2 的 N 次方的情况。

计算机底层是二进制的，移位和或运算是非常快的，所以这个方法的效率很高。

PS：这是 HashMap 中我个人最喜欢的设计，非常巧妙，真想给作者一个么么哒（不小心暴露了）。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X2pwZy9LUlJ4dnFHY2ljWkZHMG9JMW5aWXBpYWNXc3cwQjRxVDlFc2d6UGoxaWE4SmRvZE5XODhaa21Wc0paUjlEOGVzWk5NaGljVVlPd1l3VmRxd2JNZHBQSUh5VWcv?x-oss-process=image/format,png)

> 二狗：（这叼毛讲的还凑合啊，连我都听懂了）你说 HashMap 的容量必须是 2 的 N 次方，这是为什么？

囧辉：计算索引位置的公式为：(n - 1) & hash，当 n 为 2 的 N 次方时，n - 1 为低位全是 1 的值，此时任何值跟 n - 1 进行 & 运算的结果为该值的低 N 位，达到了和取模同样的效果，实现了均匀分布。实际上，这个设计就是基于公式：x mod 2^n = x & (2^n - 1)，因为 & 运算比 mod 具有更高的效率。

如下图，当 n 不为 2 的 N 次方时，hash 冲突的概率明显增大。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxQWRrTTJ4TTBRZFZ5UXpKNkdaTmc5T3ZGVVJyVXpPdVBRYnVDWDhIbDBVdFoyU1AxVExNeHFRLw?x-oss-process=image/format,png)

> 二狗：你说 HashMap 的默认初始容量是 16，为什么是 16 而不是其他的？

囧辉：（这是什么煞笔问题）我认为是 16 的原因主要是：16 是 2 的 N 次方，并且是一个较合理的大小。如果用 8 或 32，我觉得也是 OK 的。实际上，我们在新建 HashMap 时，最好是根据自己使用情况设置初始容量，这才是最合理的方案。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxUkF3NUZ0aDRVYzgzeWJjdVRGazhVY1JpYTNFcmZKcU9Tc0VoWnpyYVQyM0s5MllRM21uemZJZy8?x-oss-process=image/format,png)

二狗：刚才说的负载因子默认初始值又是多少？

囧辉：负载因子默认值是 0.75。

> 二狗：为什么是 0.75 而不是其他的？

囧辉：（又问这种憨逼问题）这个也是在时间和空间上权衡的结果。如果值较高，例如 1，此时会减少空间开销，但是 hash 冲突的概率会增大，增加查找成本；而如果值较低，例如 0.5，此时 hash 冲突会降低，但是有一半的空间会被浪费，所以折衷考虑 0.75 似乎是一个合理的值。

> 二狗：为什么不是 0.74 或 0.76？

囧辉：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X2pwZy9LUlJ4dnFHY2ljWkZHMG9JMW5aWXBpYWNXc3cwQjRxVDlFMWx2WnRHSHNHZXpRUTExeUdEWWxJOVg2OWljRjY4eUJIOHV4VWV6aFR2ZU1tMHZpY0g5cjBaZmcv?x-oss-process=image/format,png)

> 二狗：那我们换个问题问吧，HashMap 的插入流程是怎么样的？

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkZHMG9JMW5aWXBpYWNXc3cwQjRxVDlFZ0huREYyN05ZekFQS0FiR1MwR2ljcFMyOExXRzJXd09VRDluM21nZWFhM0pnRWtVcURiRXFLZy8?x-oss-process=image/format,png)

囧辉：Talk is cheap. Show you the picture。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxVEh1RWdFR2lhVkV3N0JoazBvVWRDVnNnN2dObG5yYUtuemljUEo2M3JiNDlvNFhRTTJWak5pY2ljdy8?x-oss-process=image/format,png)

> 二狗：图里刚开始有个计算 key 的 hash 值，是怎么设计的？

囧辉：拿到 key 的 hashCode，并将 hashCode 的高 16 位和 hashCode 进行异或（XOR）运算，得到最终的 hash 值。

```
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

> 二狗：为什么要将 hashCode 的高 16 位参与运算？

囧辉：主要是为了在 table 的长度较小的时候，让高位也参与运算，并且不会有太大的开销。

例如下图，如果不加入高位运算，由于 n - 1 是 0000 0111，所以结果只取决于 hash 值的低 3 位，无论高位怎么变化，结果都是一样的。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxZUgxUnJYWXdKVlhqb3FPRk9WUnpJamxuZE1yQjVaaWNpYmliMENTbnphR2N1M0RDbWlhMGliOUtYelEv?x-oss-process=image/format,png)

如果我们将高位参与运算，则索引计算结果就不会仅取决于低位。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxVTlTRmVBdkIwVzBHV2JjTjlLZUFnbXlxZWhPWDZuTWliVGFEMjQ3TzVkSXZOS0loZUpvazNDZy8?x-oss-process=image/format,png)

> 二狗：扩容（resize）流程介绍下？

囧辉：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxb0Y0a0FsQU91c0s4SmlhS0Q3VVR5VzR2dE1CTWNCOTVZTXpKODZTdERaNDdkWmpsZXdiSVJFdy8?x-oss-process=image/format,png)

​

> 二狗：红黑树和链表都是通过 e.hash & oldCap == 0 来定位在新表的索引位置，这是为什么？

囧辉：请看对下面的例子。

扩容前 table 的容量为 16，a 节点和 b 节点在扩容前处于同一索引位置。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxZTlNVFV6bXYzbGEzR01YOWhQRWFTZElkNWljSExRZVdkQVF4Q3dSQTVEaWFvRHJpYTh0Z3BEMTV3Lw?x-oss-process=image/format,png)

扩容后，table 长度为 32，新表的 n - 1 只比老表的 n - 1 在高位多了一个 1（图中标红）。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxa05XbGljc3VVQ0Yzc2VVSjJkOVNpYld4N0RHRGpQYlIwb0V1NnVyUFpFbGJUNVhPNFFaY2ljNjR3Lw?x-oss-process=image/format,png)

因为 2 个节点在老表是同一个索引位置，因此计算新表的索引位置时，只取决于新表在高位多出来的这一位（图中标红），而这一位的值刚好等于 oldCap。

因为只取决于这一位，所以只会存在两种情况：1）  (e.hash & oldCap) == 0 ，则新表索引位置为 “原索引位置” ；2）(e.hash & oldCap) != 0，则新表索引位置为 “原索引 + oldCap 位置”。

二狗：HashMap 是线程安全的吗？

囧辉：不是。HashMap 在并发下存在数据覆盖、遍历的同时进行修改会抛出 ConcurrentModificationException 异常等问题，JDK 1.8 之前还存在死循环问题。

> 二狗：介绍一下死循环问题？

囧辉：导致死循环的根本原因是 JDK 1.7 扩容采用的是 “头插法”，会导致同一索引位置的节点在扩容后顺序反掉。而 JDK 1.8 之后采用的是 “尾插法”，扩容后节点顺序不会反掉，不存在死循环问题。

JDK 1.7.0 的扩容代码如下，用例子来看会好理解点。

```
void transfer(Entry[] newTable) {
    Entry[] src = table;
    int newCapacity = newTable.length;
    for (int j = 0; j < src.length; j++) {
        Entry<K,V> e = src[j];
        if (e != null) {
            src[j] = null;
            do {
                Entry<K,V> next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            } while (e != null);
        }
    }
}
```

PS：这个流程较难理解，建议对着代码自己模拟走一遍。

例子：我们有 1 个容量为 2 的 HashMap，loadFactor=0.75，此时线程 1 和线程 2 同时往该 HashMap 插入一个数据，都触发了扩容流程，接着有以下流程。

1）在 2 个线程都插入节点，触发扩容流程之前，此时的结构如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxMjBpY1BVU2haSGliTXpTTnBzZjZ2U0tmZENJYk0zRDhyZ0dVRDZpYmljaWN1Z1dUZ3pyV0YxMmxVekEv?x-oss-process=image/format,png)

2）线程 1 进行扩容，执行到代码：Entry<K,V> next = e.next 后被调度挂起，此时的结构如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxbnh2UWFrMUpBSkR4WFZhYlVFV2liUU1hRDRKcmg4MXZrTDJNdW9Pc29zRkFpYmRKeHpNZmVPRlEv?x-oss-process=image/format,png)

3）线程 1 被挂起后，线程 2 进入扩容流程，并走完整个扩容流程，此时的结构如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxamlhODNPSjAwMURvZGljVkZhTnEyQ1Ayakg5T2JMU2Exc0FlMmR0dXo4SnFGVTJxajVwM0t4M1Ev?x-oss-process=image/format,png)

由于两个线程操作的是同一个 table，所以该图又可以画成如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxZFJtTnFTRmljSGFzN2Y2eEM5OFEwU2d4RHpyQmJ5QlRxaWJidVY5Wk1IVjJUQjdpYldjRk9XU1FBLw?x-oss-process=image/format,png)

4）线程 1 恢复后，继续走完第一次的循环流程，此时的结构如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxSEhkY3ZFNkUwRGFaR21PeTdFaWFDaWFiblZxc2dXNVllWGVvNDhmQUNIcGpPaWNncHdOZFVyMWpBLw?x-oss-process=image/format,png)

5）线程 1 继续走完第二次循环，此时的结构如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxUGlhaWJtcndwUjIzSmhpY1prMTYwOFJpY2s4dTVQb2h3ZVppYXppY1NBZGQxRGJmRnl0QWpoRldydEZ3Lw?x-oss-process=image/format,png)

6）线程 1 继续执行第三次循环，执行到 e.next = newTable[i] 时形成环，执行完第三次循环的结构如下图。

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxM2lhTHlnV01pYlVneHF5NUViaWJQaWJJYmM1eHBqT2VyVUV6SjcxMzNwdjhEMDd1dEVZaWNTNXhzUGcv?x-oss-process=image/format,png)

如果此时线程 1 调用 map.get(11)，悲剧就出现了——Infinite Loop。

> 二狗：（尼玛，没听懂，尴尬了）那总结下 JDK 1.8 主要进行了哪些优化？

囧辉：JDK 1.8 的主要优化刚才我们都聊过了，主要有以下几点：

1）底层数据结构从 “数组 + 链表” 改成 “数组 + 链表 + 红黑树”，主要是优化了 hash 冲突较严重时，链表过长的查找性能：O(n) -> O(logn)。

2）计算 table 初始容量的方式发生了改变，老的方式是从 1 开始不断向左进行移位运算，直到找到大于等于入参容量的值；新的方式则是通过 “5 个移位 + 或等于运算” 来计算。

```
// JDK 1.7.0
public HashMap(int initialCapacity, float loadFactor) {
    // 省略
    // Find a power of 2 >= initialCapacity
    int capacity = 1;
    while (capacity < initialCapacity)
        capacity <<= 1;
    // ... 省略
}
// JDK 1.8.0_191
static final int tableSizeFor(int cap) {
    int n = cap - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}
```

3）优化了 hash 值的计算方式，老的通过一顿瞎 JB 操作，新的只是简单的让高 16 位参与了运算。

```
// JDK 1.7.0
static int hash(int h) {
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
// JDK 1.8.0_191
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

4）扩容时插入方式从 “头插法” 改成 “尾插法”，避免了并发下的死循环。

5）扩容时计算节点在新表的索引位置方式从 “h & (length-1)” 改成 “hash & oldCap”，性能可能提升不大，但设计更巧妙、更优雅。

> 二狗：除了 HashMap，还用过哪些 Map，在使用时怎么选择？

囧辉：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X3BuZy9LUlJ4dnFHY2ljWkdMVGljYUxZa3BiTldUZTBkVlRMRncxdHJmYkhpYUtPZDY0S09vZ1g2bmZIeXo3b0VoZ0c4R2NpYWliem1wN01neEJESE5CUzBqTFNyaWFlZy8?x-oss-process=image/format,png)

> 二狗：（不妙，这个 B HashMap 懂得比我还多，得赶紧溜）到时间和女朋友吃饭了，我们之后再分胜负。

囧辉：

![](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9tbWJpei5xcGljLmNuL3N6X21tYml6X2pwZy9LUlJ4dnFHY2ljWkZHMG9JMW5aWXBpYWNXc3cwQjRxVDlFYkxmODdiaWNKaWNaeWtqU3BUaFdxaWJYeFhWQmtiYUkyNXIxTFcxbWhpYkVlTmRDNHJ4MlJWZWZhZy8?x-oss-process=image/format,png)

越努力，越幸运。转发【**朋友圈**】【**收藏**】【**点赞**】，是对囧辉最大的支持。

[Java 基础高频面试题（2021 年最新版）](https://blog.csdn.net/v123411739/article/details/115364158)

[921 天，咸鱼到阿里的修仙之路](https://joonwhee.blog.csdn.net/article/details/106182747)

[两年 Java 开发工作经验面试总结](https://joonwhee.blog.csdn.net/article/details/71437307)

[4 年 Java 经验，阿里网易拼多多面试总结、心得体会](https://joonwhee.blog.csdn.net/article/details/99708892)

[5 年 Java 经验，字节、美团、快手核心部门面试总结（真题解析）](https://joonwhee.blog.csdn.net/article/details/109588711)

[复习 2 个月拿下美团 offer，我都做了些啥](https://joonwhee.blog.csdn.net/article/details/106463578)

[如何写一份让 HR 眼前一亮的简历（附模板）](https://joonwhee.blog.csdn.net/article/details/109709619)

[面试阿里，HashMap 这一篇就够了](https://joonwhee.blog.csdn.net/article/details/106324537)

[面试必问的 MySQL，你懂了吗？](https://joonwhee.blog.csdn.net/article/details/106893197)

[面试必问的线程池，你懂了吗？](https://joonwhee.blog.csdn.net/article/details/106609583)

[跳槽，如何选择一家公司](https://joonwhee.blog.csdn.net/article/details/109171171)

[如何准备好一场大厂面试](https://joonwhee.blog.csdn.net/article/details/108702592)

[面试必问的分布式锁，你懂了吗？](https://blog.csdn.net/v123411739/article/details/114501792)