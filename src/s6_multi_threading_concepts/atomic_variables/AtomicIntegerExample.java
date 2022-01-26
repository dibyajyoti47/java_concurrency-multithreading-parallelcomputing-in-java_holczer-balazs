package s6_multi_threading_concepts.atomic_variables;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    private AtomicInteger counter = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerExample atomicInteger = new AtomicIntegerExample();
        Thread t1 = new Thread(atomicInteger::increment,"thread1");
        Thread t2 = new Thread(atomicInteger::increment,"thread2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicInteger.counter.get());
    }

    public void increment() {
        for (int i=0; i<10000;i++) {
            counter.getAndIncrement();
        }
    }
}
