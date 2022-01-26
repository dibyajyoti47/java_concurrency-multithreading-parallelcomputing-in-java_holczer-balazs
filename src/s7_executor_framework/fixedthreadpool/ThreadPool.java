package s7_executor_framework.fixedthreadpool;

import s7_executor_framework.singlethreadexecutor.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for(int i=0; i<10; i++) {
            executor.execute(new Task(i));
        }
    }
}
