package com.lk.algorithms.lru;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * map + 双向链表
 */
public class LRUCache2 {

  int capacity;

  private HashMap<Integer, Node<Integer, Integer>> map;

  DoubleLinkedNode<Integer, Integer> doubleLinkedNode = new DoubleLinkedNode<>();

  public LRUCache2(int capacity) {
    this.capacity = capacity;
    map = new HashMap<>(capacity);
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }
    //获取当前节点
    Node<Integer, Integer> node = map.get(key);
    //将节点从双向链表删除
    doubleLinkedNode.remove(node);
    //添加到头节点
    doubleLinkedNode.addHead(node);
    return node.value;


  }

  public void put(int key, int value) {

    if (map.containsKey(key)) {
      //存在key  覆盖原值
      //获取当前节点
      Node<Integer, Integer> node = map.get(key);
      node.value = value;

      //将节点从双向链表删除
      doubleLinkedNode.remove(node);
      //添加到头节点
      doubleLinkedNode.addHead(node);
    } else {
      //不存在key

      //判断容量是否达到最大值
      if (map.size() == capacity) {
        //腾出空间
        Node<Integer, Integer> last = doubleLinkedNode.getLast();
        map.remove(last.key);
        doubleLinkedNode.remove(last);
      }

      //添加节点
      Node<Integer, Integer> node = new Node<>(key, value);
      map.put(key, node);
      doubleLinkedNode.addHead(node);
    }

  }

  private static class Node<K, V> {

    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;

    public Node() {
      this.prev = this.next = null;
    }

    public Node(K key, V value) {
      this();
      this.key = key;
      this.value = value;
    }
  }

  private static class DoubleLinkedNode<K, V> {

    Node<K, V> head;
    Node<K, V> tail;

    public DoubleLinkedNode() {
      this.head = new Node<>();
      this.tail = new Node<>();

      this.head.next = this.tail;
      this.tail.prev = this.head;
    }

    public void addHead(Node<K, V> node) {
      node.next = this.head.next;
      node.prev = this.head;
      this.head.next.prev = node;
      this.head.next = node;
    }

    public void remove(Node<K, V> node) {
      node.prev.next = node.next;
      node.next.prev = node.prev;
      node.next = null;
      node.prev = null;
    }

    public Node<K, V> getLast() {
      if (this.tail.prev == this.head) {
        return null;
      }
      return this.tail.prev;
    }


  }

  public static void main(String[] args) {
    LRUCache2 lRUCache = new LRUCache2(2);
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
