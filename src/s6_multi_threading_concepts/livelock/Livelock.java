package s6_multi_threading_concepts.livelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livelock {
    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        Livelock livelock = new Livelock();
        //new way to create thread in java-8  :)
        new Thread(livelock::worker1, "worker-1").start();
        new Thread(livelock::worker2, "worker-2").start();
    }

    public void worker1() {

        while (true) {
            try {
                lock1.tryLock(50, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {}

            System.out.println("Worker-1 acquires the lock1...");

            System.out.println("Worker-1 tries to get lock-2...");
            if (lock2.tryLock()) {
                System.out.println("Worker-1 acquires the lock2...");
                lock2.unlock();
            } else {
                System.out.println("Worker-1 could not acquire the lock2...");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    public void worker2() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MICROSECONDS);
            } catch (InterruptedException e) {}
            System.out.println("Worker-2 acquires the lock2...");
            System.out.println("Worker-2 tries to get lock-1...");
            if (lock1.tryLock()) {
                System.out.println("Worker-2 acquires the lock1...");
                lock1.unlock();
            } else {
                System.out.println("Worker-2 could not acquire the lock1...");
                continue;
            }
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }
}
