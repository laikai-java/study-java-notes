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
public class ConflictHashCodeTest2 {

    public static void main(String[] args) {
        // 新建2个相同内容的Person对象，
        // 再用equals比较它们是否相等
        Person p1 = new Person("eee", 100);
        Person p2 = new Person("eee", 100);
        Person p3 = new Person("aaa", 200);
        Person p4 = new Person("AAA", 200);
        HashSet set = new HashSet();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        System.out.printf("p3.equals(p4) : %s; p3(%d) p4(%d)\n", p3.equals(p4), p3.hashCode(), p4.hashCode());
        System.out.println("set:" + set);
        //这下，equals()生效了，HashSet中没有重复元素。
        // 比较p1和p2，我们发现：它们的hashCode()相等，通过equals()比较它们也返回true。所以，p1和p2被视为相等。
        //比较p1和p4，我们发现：虽然它们的hashCode()相等；但是，通过equals()比较它们返回false。所以，p1和p4被视为不相等。
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
        public int hashCode() {
            int nameHash = name.toLowerCase().hashCode();
            return nameHash ^ age;
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
