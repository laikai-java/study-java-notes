package com.lk.basic.simplethread;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

  public static void second(int second){
    try {
      TimeUnit.SECONDS.sleep(second);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
