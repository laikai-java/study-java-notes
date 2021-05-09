/**
 * == : 它的作用是判断两个对象的地址是不是相等。
 * 即，判断两个对象是不是同一个对象(基本数据类型==比的是值，引用数据类型==比的是内存地址)。
 * equals() : 它的作用也是判断两个对象是否相等。但它一般有两种使用情况:
 * 情况 1:类没有覆盖 equals() 方法。则通过 equals() 比􏰀该类的两个对象时，等价于通过 “==”比较这两个对象。
 * 情况 2:类覆盖了 equals() 方法。一般，我们都覆盖 equals() 方法来比较两个对象的内容是否相等;
 *        若它们的内容相等，则返回 true (即，认为这两个对象相等)。
 *
 * equals()
 * 1. 对称性：如果x.equals(y)返回是"true"，那么y.equals(x)也应该返回是"true"。
 * 2. 反射性：x.equals(x)必须返回是"true"。
 * 3. 类推性：如果x.equals(y)返回是"true"，而且y.equals(z)返回是"true"，那么z.equals(x)也应该返回是"true"。
 * 4. 一致性：如果x.equals(y)返回是"true"，只要x和y内容一直不变，不管你重复x.equals(y)多少次，返回都是"true"。
 * 5. 非空性，x.equals(null)，永远返回是"false"；x.equals(和x不同类型的对象)永远返回是"false"。
 *
 *
 * hashCode()
 * 作用是获取哈希码，也称为散列码；它实际上是返回一个int整数。这个哈希码的作用是确定该对象在哈希表中的索引位置。
 *
 * hashCode() 定义在JDK的Object.java中，这就意味着Java中的任何类都包含有hashCode() 函数。
 * 虽然，每个Java类都包含hashCode() 函数。
 * 但是，仅仅当创建并某个“类的散列表”时，该类的hashCode() 才有用(作用是：确定该类的每一个对象在散列表中的位置；
 * 其它情况下(例如，创建类的单个对象，或者创建类的对象数组等等)，类的hashCode() 没有作用。
 *
 * 上面的散列表，指的是：Java集合中本质是散列表的类，如HashMap，Hashtable，HashSet。
 *
 * 也就是说：hashCode() 在散列表中才有用，在其它情况下没用。
 * 在散列表中hashCode() 的作用是获取对象的散列码，进而确定该对象在散列表中的位置。
 */
package com.lk.basic.equalsandhashcode;