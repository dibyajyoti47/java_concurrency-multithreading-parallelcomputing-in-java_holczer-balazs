package s7_executor_framework.stoppingthreads;

import s7_executor_framework.singlethreadexecutor.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(int i=0; i<100; i++) {
            executor.execute(new Task(i));
        }
        //have to deal with shutting down
        //we prevent the executor to execute any further tasks
        executor.shutdown();

        //terminate actual running task
        try{
            if(!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
               executor.shutdownNow(); //if returned value is false then we force shutdown
            }
        }catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
