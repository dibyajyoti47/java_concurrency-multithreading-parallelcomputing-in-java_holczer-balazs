package s7_executor_framework.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Future<String>> list = new ArrayList();
        for(int i=0;i<5;++i) {
            Future<String> future = service.submit(new Processor(i+1));
            list.add(future);
        }
        for(Future<String> future : list) {
            System.out.println(future.get());
        }
    }
}

class Processor implements Callable<String> {
    private int id;
    public Processor(int id) {
        this.id = id;
    }
    public String call() throws Exception {
        Thread.sleep(2000);
        return "ID : "+id;
    }
}