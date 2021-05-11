/**
 * 事务:一系列的操作要么统一提交 要么统一回滚 防止脏数据
 * 事务的特性:ACID
 * A:Atomicity原子性 一个事务中的所有操作要么全部完成 要么全部不完成  事务不可分割
 * C:Consistency一致性 在事务开始之前和事务结束以后，数据库的完整性没有被破坏。这表示写入的资料必须完全符合所有的预设约束、触发器)、级联回滚等。
 * I:Isolation 隔离性 数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。
 *  事务隔离分为不同级别，包括读未提交（Read uncommitted）、读提交（read committed）、可重复读（repeatable read）和串行化（Serializable）。
 * D:Durability 持久性 事务处理结束后，对数据的修改就是永久的，即便系统故障也不会丢失。
 */
package com.lk.spring.transaction;