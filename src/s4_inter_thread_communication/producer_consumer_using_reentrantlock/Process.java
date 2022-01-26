package s4_inter_thread_communication.producer_consumer_using_reentrantlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Process {
    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private int value = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce () throws InterruptedException {
        lock.lock();
        while (true) {
            if (list.size() == UPPER_LIMIT) {
                System.out.println("waiting items to be removed....");
                condition.await();
            } else {
                System.out.println("producing items..");
                list.add(value++);
                condition.signal();
                lock.unlock();
            }
            Thread.sleep(250);
        }
    }

    public void consume () throws InterruptedException {
        Thread.sleep(2000);
        lock.lock();
        while (true) {
            if(list.size() == 0) {
                System.out.println("Waiting for items to be added...");
                condition.await();
            } else {
                System.out.println("consumed value : "+list.remove(list.size()-1));
                condition.signal();
                lock.unlock();
            }
            Thread.sleep(1000);
        }
    }
}
