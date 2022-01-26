package s4_inter_thread_communication.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {public void run() {increment();}});
        Thread t2 = new Thread(new Runnable() {public void run() {increment();}});
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter+ " is counter value.");
    }

    private static int counter = 0;
    private static Lock lock =new ReentrantLock();

    private static void increment() {
        lock.lock();
        try{for (int i = 1; i <= 10000; i++) {counter++;}}
        finally {
            lock.unlock();
        }

    }
}
