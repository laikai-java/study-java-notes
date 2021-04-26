package com.lk.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

  public static int[] twoSum(int[] nums, int target) {

    Map<Integer,Integer> data = new HashMap<>(nums.length);
    //遍历
    for (int i = 0; i < nums.length; i++) {

      int result = target - nums[i];
      if (data.containsKey(result)){
        return new int[]{data.get(result),i};
      }
      data.put(nums[i],i);
    }
    return null;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{2,7,11,15};
    int target = 9;

    final int[] ints = twoSum(nums, target);
    for (int anInt : ints) {
      System.out.println(anInt);
    }
  }
}
