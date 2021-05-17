package com.lk.algorithms.limit.single;


/**
 * 计数器
 * 计数器是一种比较简单的限流算法，用途比较广泛，在接口层面，很多地方使用这种方式限流。
 * 在一段时间内，进行计数，与阀值进行比较，到了时间临界点，将计数器清0。
 *
 *
 * 单机版
 *
 * 这里需要注意的是，存在一个时间临界点的问题。举个栗子，在 12:01:00 到 12:01:58 这段时间内没有用户请求，
 * 然后在 12:01:59 这一瞬时发出 100 个请求，OK，
 * 然后在 12:02:00 这一瞬时又发出了 100 个请求。
 * 这里你应该能感受到，在这个临界点可能会承受恶意用户的大量请求，甚至超出系统预期的承受。
 */
public class CounterDemo {

    /**
     * 时间戳
     */
    public static long timestamp = System.currentTimeMillis();

    /**
     * 限流的请求阈值
     */
    public static long limitCount = 100;

    /**
     * 时间间隔 单位毫秒
     */
    public static long interval = 1000;

    /**
     * 请求数
     */
    public static long reqCount;

    public static boolean grant() {

        long now = System.currentTimeMillis();
        if (now < timestamp + interval) {
            if (reqCount < limitCount) {
                reqCount++;
                return true;
            } else {
                return false;
            }
        } else {
            timestamp = now;
            reqCount = 0;
            return false;
        }
    }

  public static void main(String[] args) {
    for (int i = 0; i < 500; i++) {
      new Thread(() -> {
          if (grant()){
            System.out.println("执行业务逻辑");
          }else {
            System.out.println("限流");
          }
      }, String.valueOf(i)).start();
    }
  }


}
