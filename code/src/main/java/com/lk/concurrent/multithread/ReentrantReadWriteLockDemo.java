package com.lk.concurrent.multithread;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReentrantReadWriteLockDemo {

  public static void main(String[] args) {
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    WriteLock writeLock = readWriteLock.writeLock();
    ReadLock readLock = readWriteLock.readLock();
    HashMap map = new HashMap();

       new Thread(() -> {
         writeLock.lock();
         try {
           //to do something
           map.put("1","1");
         } finally {
           writeLock.unlock();
         }
           }, "thread write").start();

          new Thread(() -> {

              }, "thread name").start();


  }


}
