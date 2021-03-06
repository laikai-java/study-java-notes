/**
 * 进程是操作系统进行资源分配的基本单位，
 * 而线程是操作系统进行调度的基本单位，即CPU分配时间的单位
 *
 * 上下文切换（有时也称做进程切换或任务切换）是指 CPU 从一个进程（或线程）切换到另一个进程（或线程）。
 * 上下文是指某一时间点 CPU 寄存器和程序计数器的内容。
 *
 * 但是，在切换前会保存上一个任务的状态，以便下次切换回这个任务时，可以再加载这个任务的状态。
 * 所以任务从保存到再加载的过程就是一次上下文切换。
 *
 * 上下文切换通常是计算密集型的，意味着此操作会消耗大量的 CPU 时间，
 * 故线程也不是越多越好。如何减少系统中上下文切换次数，是提升多线程性能的一个重点课题。
 */
package com.lk.concurrent.thread;