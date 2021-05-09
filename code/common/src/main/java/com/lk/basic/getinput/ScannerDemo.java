package com.lk.basic.getinput;

import java.util.Scanner;

/**
 * 用键盘获取输入的两种方式
 */
public class ScannerDemo {

  public static void main(String[] args) {
    Scanner  scanner = new Scanner(System.in);
    System.out.println("请输入：");
    final String s = scanner.nextLine();
    System.out.println(s);
    scanner.close();
  }
}
