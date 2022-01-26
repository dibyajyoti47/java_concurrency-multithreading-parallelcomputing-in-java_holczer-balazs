package s8_concurrent_collections.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*multiple threads can wait for each other.
* A cyclic barrier is used in situations where you want to create a group of
* tasks to perform work in a consurrent manner + wait until the are all finished before moving on th the next step.
* something like join()
* it can be reused over and over again.
* */
public class App {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("All tasks have been finished..");
            }
        });
        for(int i=1;i<=5;i++) {
            service.execute(new Worker(i,barrier));
        }
        service.shutdown();
    }
}

class Worker implements Runnable {
    private int id;
    private Random random;
    private CyclicBarrier barrier;

    public Worker(int id, CyclicBarrier barrier) {
        this.id = id;
        this.random = new Random();
        this.barrier = barrier;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with id : "+id+" starts the work");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            // all the threads call await()
            //this is when the barrier is broken
            barrier.await();
            System.out.println("after the await.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}