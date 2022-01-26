package s4_inter_thread_communication.producer_consumer_using_reentrantlock;

public class App {
    public static void main(String[] args) {
        Process process = new Process();
        Thread producerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {}
            }
        });
        Thread consumerThread = new Thread(new Runnable() {
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {}
            }
        });
        producerThread.start();
        consumerThread.start();
    }

}
