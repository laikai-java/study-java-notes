package com.lk.algorithms.lru;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 巧用LinkedHashMap
 */
public class LRUCache {


  LinkedHashMap<Integer, Integer> map;

  public LRUCache(int capacity) {
    map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
      @Override
      protected boolean removeEldestEntry(Entry<Integer, Integer> eldest) {
        return map.size() > capacity;
      }
    };
  }

  public int get(int key) {
    return map.getOrDefault(key, -1);
  }

  public void put(int key, int value) {
    map.put(key, value);
  }

  public static void main(String[] args) {
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // 缓存是 {1=1}
    lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
    lRUCache.get(1);    // 返回 1
    lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    lRUCache.get(2);    // 返回 -1 (未找到)
    lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    lRUCache.get(1);    // 返回 -1 (未找到)
    lRUCache.get(3);    // 返回 3
    lRUCache.get(4);    // 返回 4
  }

}
