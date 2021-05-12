package com.lk.basic.comparableandcomparator;

/**
 *
 */
public class ComparatorClient {

  public static void main(String[] args) {
    Domain domain1 = new Domain("a");
    Domain domain2 = new Domain("a");

    DomainComparator domainComparator = new DomainComparator();
    domainComparator.compare(domain1,domain2);
  }
}
