package com.lk.basic.equalsandhashcode;

import java.util.HashSet;

/**
 * 第二种 会创建“类对应的散列表”
 * <p>
 * 这里所说的“会创建类对应的散列表”是说：我们会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中，用到该类。
 * 例如，会创建该类的HashSet集合。
 * <p>
 * 在这种情况下，该类的“hashCode() 和 equals() ”是有关系的：
 * 1)、如果两个对象相等，那么它们的hashCode()值一定相同。
 * 这里的相等是指，通过equals()比较两个对象时返回true。
 * 2)、如果两个对象hashCode()相等，它们并不一定相等。
 * 因为在散列表中，hashCode()相等，即两个键值对的哈希值相等。然而哈希值相等，并不一定能得出键值对相等。
 * 补充说一句：“两个不同的键值对，哈希值相等”，这就是哈希冲突。
 * <p>
 * 此外，在这种情况下。若要判断两个对象是否相等，除了要覆盖equals()之外，也要覆盖hashCode()函数。否则，equals()无效。
 * 例如，创建Person类的HashSet集合，必须同时覆盖Person类的equals() 和 hashCode()方法。
 * 如果单单只是覆盖equals()方法。我们会发现，equals()方法没有达到我们想要的效果。
 */
public class ConflictHashCodeTest1 {

    public static void main(String[] args) {
        // 新建2个相同内容的Person对象，
        // 再用equals比较它们是否相等
        Person p1 = new Person("eee", 100);
        Person p2 = new Person("eee", 100);
        Person p3 = new Person("aaa", 200);
        HashSet set = new HashSet();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        System.out.printf("p1.equals(p3) : %s; p1(%d) p3(%d)\n", p1.equals(p3), p1.hashCode(), p3.hashCode());
        System.out.println("set:" + set);
        //我们重写了Person的equals()。但是，很奇怪的发现：HashSet中仍然有重复元素：p1 和 p2。为什么会出现这种情况呢？
        //这是因为虽然p1 和 p2的内容相等，但是它们的hashCode()不等；所以，HashSet在添加p1和p2的时候，认为它们不相等。
    }


    private static class Person {
        int age;
        String name;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return name + " - " + age;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (this == obj) {
                return true;
            }

            if (this.getClass() != obj.getClass()) {
                return false;
            }

            Person person = (Person) obj;
            return this.age == person.age && this.name.equals(person.name);
        }
    }
}
