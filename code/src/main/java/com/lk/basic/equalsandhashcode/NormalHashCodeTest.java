package com.lk.basic.equalsandhashcode;

/**
 * 第一种 不会创建“类对应的散列表”
 * <p>
 * 这里所说的“不会创建类对应的散列表”是说：我们不会在HashSet, Hashtable, HashMap等等这些本质是散列表的数据结构中，用到该类。
 * 例如，不会创建该类的HashSet集合。
 * <p>
 * 在这种情况下，该类的“hashCode() 和 equals() ”没有半毛钱关系的！
 * 这种情况下，equals() 用来比较该类的两个对象是否相等。而hashCode() 则根本没有任何作用，所以，不用理会hashCode()。
 * <p>
 * 下面，我们通过示例查看类的两个对象相等 以及 不等时hashCode()的取值。
 */
public class NormalHashCodeTest {

    public static void main(String[] args) {
        // 新建2个相同内容的Person对象，
        // 再用equals比较它们是否相等
        Person p1 = new Person("eee", 100);
        Person p2 = new Person("eee", 100);
        Person p3 = new Person("aaa", 200);
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        System.out.printf("p1.equals(p3) : %s; p1(%d) p3(%d)\n", p1.equals(p3), p1.hashCode(), p3.hashCode());
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
