package s7_executor_framework.singlethreadexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        //it its a single thread that will execute the tasks sequentially
        //so one after another
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i=0;i<5;i++) {
            executorService.execute(new Task(i));
        }
        //we must shut down the executor
    }
}
