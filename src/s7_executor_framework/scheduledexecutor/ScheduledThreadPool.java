package s7_executor_framework.scheduledexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new StickMarketUpdater(), 1000, 500, TimeUnit.MILLISECONDS);
    }
}

class StickMarketUpdater implements Runnable {
    @Override
    public void run() {
        System.out.println("Updating and download stock related data on the web.");
    }
}