package s6_multi_threading_concepts.deadlock.newway;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        Deadlock deadlock = new Deadlock();
        //new way to create thread in java-8  :)
        new Thread(deadlock::worker1, "worker-1").start();
        new Thread(deadlock::worker2, "worker-2").start();
    }

    public void worker1() {
        lock1.lock();
        System.out.println("Worker 1 acquires lock-1...");
        try { Thread.sleep(300); } catch (InterruptedException e) {}
        lock2.lock();
        System.out.println("Worker 1 acquires lock-2...");
        lock1.unlock();
        lock2.unlock();
    }

    public void worker2()  {
        lock2.lock();
        System.out.println("Worker 2 acquires lock-2...");
        try {Thread.sleep(300);} catch (InterruptedException e) {}
        lock1.lock();
        System.out.println("Worker 2 acquires lock-1...");
        lock2.unlock();
        lock1.unlock();
    }

}
