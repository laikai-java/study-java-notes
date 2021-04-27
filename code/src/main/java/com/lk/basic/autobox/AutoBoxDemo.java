package com.lk.basic.autobox;

/**
 * 自动装箱与拆箱
 *
 * Java为每种基本数据类型都提供了对应的包装器类型
 * byte（1字节）	Byte
 * short（2字节）	Short
 * int（4字节）	Integer
 * long（8字节）	Long
 * float（4字节）	Float
 * double（8字节）	Double
 * char（2字节）	Character
 * boolean（未定）	Boolean
 *
 * 装箱就是  自动将基本数据类型转换为包装器类型；
 * 拆箱就是  自动将包装器类型转换为基本数据类型。
 * 在装箱的时候自动调用的是Integer的valueOf(int)方法。
 * 而在拆箱的时候自动调用的是Integer的intValue方法。
 *
 * Integer、Short、Byte、Character、Long这几个类的valueOf方法的实现是类似的。
 *
 * Double、Float的valueOf方法的实现是类似的。直接new
 *
 *
 * 当 "=="运算符的两个操作数都是 包装器类型的引用，则是比较指向的是否是同一个对象，
 * 而如果其中有一个操作数是表达式（即包含算术运算）则比较的是数值（即会触发自动拆箱的过程）。
 * 如果数值是int类型的，装箱过程调用的是Integer.valueOf；如果是long类型的，装箱调用的Long.valueOf方法
 */
public class AutoBoxDemo {

  public static void main(String[] args) {
    // 装箱 相当于 Integer a = Integer.valueOf(5)
    Integer a11 = 5;//

    //拆箱 相当于 int b = a.intValue();
    int b111 = a11;

    Integer test = Integer.valueOf(200);

    Integer i1 = 127;
    Integer i2 = 127;
    Integer i3 = 128;
    Integer i4 = 128;

    //-XX:AutoBoxCacheMax=<size>  设置缓冲区大小 默认为 -128 ～ 127
    System.out.println(i1==i2);
    System.out.println(i3==i4);

    // 浮点类型无缓存
    System.out.println("###########################");
    Double d1 = 100.0;
    Double d2 = 100.0;
    Double d3 = 200.0;
    Double d4 = 200.0;
    System.out.println(d1==d2);
    System.out.println(d3==d4);

    System.out.println("###########################");
    //布尔
    Boolean b1 = true;
    Boolean b2 = true;
    Boolean b3 = false;
    Boolean b4 = false;
    System.out.println(b1 == b2);
    System.out.println(b3 == b4);


    System.out.println("#########测试##################");

    Integer a = 1;
    Integer b = 2;
    Integer c = 3;
    Integer d = 3;
    Integer e = 321;
    Integer f = 321;
    Long g = 3L;
    Long h = 2L;
    System.out.println(c==d);//true
    System.out.println(e==f);//false
    System.out.println(c==(a+b));//a+b 自动拆箱了 true
    System.out.println(c.equals(a+b));//a+b 自动拆箱了 结果又装箱了 true
    System.out.println(g==(a+b));//true
    System.out.println(g.equals(a+b));//a+b 自动拆箱了 结果又装箱为Integer false
    System.out.println(g.equals(a+h));//true

  }

}
