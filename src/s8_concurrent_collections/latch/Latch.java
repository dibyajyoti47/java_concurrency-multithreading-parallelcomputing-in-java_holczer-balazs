package s8_concurrent_collections.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* A single thread can wait for other threads.
* We can't reuse Countdown latch.
* */
public class Latch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++) {
            service.execute(new Worker(i, latch));
        }
        latch.await();
        System.out.println("All tasks have been finished...");
        service.shutdown();
    }
}


class Worker implements Runnable {
    private int id;
    private CountDownLatch latch;
    public Worker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }
    public void run() {
        doWork();
        latch.countDown();
    }
    private void doWork() {
        System.out.println("Thread with id: "+this.id+" started working..");
        try {Thread.sleep(2000);} catch (InterruptedException e) {}
    }
}