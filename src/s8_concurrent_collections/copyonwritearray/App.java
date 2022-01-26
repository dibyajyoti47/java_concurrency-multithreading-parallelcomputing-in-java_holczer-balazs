package s8_concurrent_collections.copyonwritearray;


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class App {
    public static void main(String[] args) {
        ConcurrentArray concurrentArray = new ConcurrentArray();
        concurrentArray.simulate();
    }
}

class WriteTask implements  Runnable {

    private List<Integer> list;
    private Random random;

    public WriteTask(List<Integer> list) {
        this.list = list;
        this.random = new Random();
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {}
            list.set(random.nextInt(list.size()), random.nextInt(10));
        }
    }
}

class ReadTask implements Runnable {
    private List<Integer> list;

    public ReadTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {}
            System.out.println(list);
        }
    }
}

class ConcurrentArray {
    private List<Integer> list;
    public ConcurrentArray() {
        this.list = new CopyOnWriteArrayList<>();
        this.list.addAll(Arrays.asList(0,0,0,0,0,0,0,0,0,0,0));
    }
    public void simulate() {
        new Thread(new WriteTask(list)).start();
        new Thread(new WriteTask(list)).start();
        new Thread(new WriteTask(list)).start();
        new Thread(new ReadTask(list)).start();


    }
}