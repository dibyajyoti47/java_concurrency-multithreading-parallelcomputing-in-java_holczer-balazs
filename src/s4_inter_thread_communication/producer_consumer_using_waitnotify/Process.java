package s4_inter_thread_communication.producer_consumer_using_waitnotify;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private List<Integer> list = new ArrayList<>();
    private static final int UPPER_LIMIT = 5;
    private int value = 0;
    private Object lock = new Object();

    public void produce () throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_LIMIT) {
                    System.out.println("waiting items to be removed....");
                    lock.wait();
                } else {
                    System.out.println("producing items..");
                    list.add(value++);
                    lock.notify();
                }
                Thread.sleep(100);
            }
        }
    }

    public void consume () throws InterruptedException {
        synchronized (lock) {
            while (true) {
                synchronized (lock) {
                    if(list.size() == 0) {
                        System.out.println("Waiting for items to be added...");
                        lock.wait();
                    } else {
                        System.out.println("consumed value : "+list.remove(list.size()-1));
                        lock.notify();
                    }
                }
                Thread.sleep(2000);
            }
        }
    }
}
