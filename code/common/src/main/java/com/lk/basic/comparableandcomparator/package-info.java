/**
 * Java 中为我们提供了两种比较机制：Comparable 和 Comparator，二者都是用来实现对象的比较、排序。
 *
 * Comparable可以认为是一个内比较器，实现了Comparable接口的类有一个特点，就是这些类是可以和自己比较的，
 * 至于具体和另一个实现了Comparable接口的类如何比较，则依赖compareTo方法的实现。
 *
 * 如果add进入一个Collection的对象想要Collections的sort方法帮你自动进行排序的话，
 * 那么这个对象必须实现Comparable接口。compareTo方法的返回值是int，有三种情况：
 *    比较者大于被比较者，返回正整数
 *    比较者等于被比较者，返回0
 *    比较者小于被比较者，返回负整数
 *
 *
 *
 * 如果实现类没有实现Comparable接口，又想对两个类进行比较（或者实现类实现了Comparable接口，但是对compareTo方法内的比较算法不满意），那么可以实现Comparator接口，自定义一个比较器，写比较算法。
 *
 * 实现Comparable接口的方式比实现Comparator接口的耦合性要强一些，如果要修改比较算法，要修改Comparable接口的实现类，而实现Comparator的类是在外部进行比较的，不需要对实现类有任何修改。
 * 因此：
 * 对于一些普通的数据类型（比如 String, Integer, Double…），它们默认实现了Comparable 接口，实现了 compareTo 方法，我们可以直接使用。
 *
 * 而对于一些自定义类，它们可能在不同情况下需要实现不同的比较策略，我们可以新创建 Comparator 接口，然后使用特定的 Comparator 实现进行比较。
 *
 *
 * Comparable  实现compareTo(T t)方法 相当于一个内部比较器 比较的对象是泛型 可以和自己比较 但是比较方法定死了类型
 * Comparator 实现compare(T t,T t)方法 同类型的比较
 * */
package com.lk.basic.comparableandcomparator;