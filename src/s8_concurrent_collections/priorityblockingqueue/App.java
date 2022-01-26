package s8_concurrent_collections.priorityblockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class App {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new PriorityBlockingQueue<>();
        FirstWorker first = new FirstWorker(queue);
        SecondWorker second = new SecondWorker(queue);
        new Thread(first).start();
        new Thread(second).start();
    }
}
/*
* It implements the blocking queue.
* it is the synchronized version of normal priorityqueue
* it stores element on the priority order
* it determines the priority based on the comaparable implementation.
* no null items not allowed
* */
class FirstWorker implements Runnable {
    private BlockingQueue<String> queue;
    public FirstWorker (BlockingQueue<String> queue){
        this.queue = queue;
    }
    public void run() {
        try {
            queue.put("B");
            queue.put("H");
            queue.put("F");
            Thread.sleep(2000);
            queue.put("A");
            Thread.sleep(1000);
            queue.put("Z");
        } catch (InterruptedException e) {}
    }
}

class SecondWorker implements Runnable {
    private BlockingQueue<String> queue;
    public SecondWorker(BlockingQueue<String> queue) {
        this.queue = queue;
    }
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(queue.take());
            Thread.sleep(1000);
            System.out.println(queue.take());
            Thread.sleep(2000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {}
    }
}