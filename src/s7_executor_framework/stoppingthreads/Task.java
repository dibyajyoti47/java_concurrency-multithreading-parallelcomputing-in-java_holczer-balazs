package s7_executor_framework.stoppingthreads;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private int id;

    public Task(int id) {
        this.id = id;
    }

    public  void run () {
        System.out.println("Task with "+this.id+" is in work.Thread ID: -"+Thread.currentThread().getId());
        long duration = (long) (Math.random()*5);
        try {
            TimeUnit.SECONDS.sleep(duration); // performs a Thread.sleep()
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
