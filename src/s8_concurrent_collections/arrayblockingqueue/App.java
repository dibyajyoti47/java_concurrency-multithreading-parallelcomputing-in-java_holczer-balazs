package s8_concurrent_collections.arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        new Thread(new FirstWorker(queue)).start();
        new Thread(new SecondWorker(queue)).start();
    }
}

class FirstWorker implements Runnable {
    private BlockingQueue<Integer> queue;

    public FirstWorker(BlockingQueue blockingQueue) {
        this.queue = blockingQueue;
    }

    public void run() {
        int counter = 0;
        while(true) {
            try {
                queue.put(counter);
                System.out.println("Putting value into the queue..."+counter);
                counter++;
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
}

class SecondWorker implements Runnable {
    private BlockingQueue<Integer> queue;

    public SecondWorker(BlockingQueue blockingQueue) {
        this.queue = blockingQueue;
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Taking value from the queue..."+queue.take());
                Thread.sleep(300);
            } catch (InterruptedException e) {}
        }
    }
}