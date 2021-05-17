package com.lk.algorithms.limit;


/**
 * 计数器
 * 计数器是一种比较简单的限流算法，用途比较广泛，在接口层面，很多地方使用这种方式限流。
 * 在一段时间内，进行计数，与阀值进行比较，到了时间临界点，将计数器清0。
 */
public class CounterDemo {

  /**
   * 时间戳
   */
  public static long timestamp = System.currentTimeMillis();

  /**
   * 限流的请求阈值
   */
  public static long limitCount;

  /**
   * 时间间隔
   */
  public static long interval;

  /**
   * 请求数
   */
  public static long reqCount;

  public boolean grant(){
    long now = System.currentTimeMillis();

    if (now < timestamp + interval){
      if (reqCount < limitCount){
        reqCount++;
        return true;
      }else{
        return false;
      }
    }else{
      timestamp = System.currentTimeMillis();
      reqCount = 0;
      return false;
    }


  }



}
