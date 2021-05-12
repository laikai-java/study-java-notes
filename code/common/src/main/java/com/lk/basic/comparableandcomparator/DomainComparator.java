package com.lk.basic.comparableandcomparator;

import java.util.Comparator;

/**
 * 泛型指定死了，所以实现Comparator接口的实现类只能是两个相同的对象（不能一个Domain、一个String）进行比较了，
 * 实现Comparator接口的实现类一般都会以"待比较的实体类+Comparator"来命名
 */
public class DomainComparator implements Comparator<Domain> {

  @Override
  public int compare(Domain domain1, Domain domain2) {
    if (domain1.getStr().compareTo(domain2.getStr()) > 0) {
      return 1;
    } else if (domain1.getStr().compareTo(domain2.getStr()) == 0) {
      return 0;
    } else {
      return -1;
    }
  }
}