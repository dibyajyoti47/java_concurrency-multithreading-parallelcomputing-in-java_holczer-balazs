package s8_concurrent_collections.concurrentmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class App {
    public static void main(String[] args) {
        ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
        new Thread(new FirstWorker(map)).start();
        new Thread(new SecondWorker(map)).start();
    }
}

class FirstWorker implements Runnable {
    private ConcurrentMap<String, Integer> map;
    public FirstWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }
    public void run() {
        try {
            map.put("B", 12);
            Thread.sleep(1000);
            map.put("Z",5);
            map.put("A", 25);
            Thread.sleep(2000);
            map.put("D",19);
        } catch (InterruptedException e) {}
    }
}

class SecondWorker implements Runnable {
    private ConcurrentMap<String, Integer> map;
    public SecondWorker(ConcurrentMap<String, Integer> map) {
        this.map = map;
    }
    public void run() {
        try {
            System.out.println(map.get("B"));
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            System.out.println(map.get("Z"));
            Thread.sleep(2000);
            System.out.println(map.get("D"));
        } catch (InterruptedException e) {}
    }
}