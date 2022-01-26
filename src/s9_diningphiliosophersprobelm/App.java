package s9_diningphiliosophersprobelm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
* this is to avoid thread starvation
* also able to avoid deadlock because try lock is used.
* */
public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;
        Chopstick[] chopsticks = null;
        try{
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHERS];
            chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICKS];
            for(int i=0; i<Constants.NUMBER_OF_CHOPSTICKS; ++i) {
                chopsticks[i] = new Chopstick(i);
            }
            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHERS);
            for (int i=0; i<Constants.NUMBER_OF_PHILOSOPHERS;++i) {
                Chopstick leftChopStick = chopsticks[i];
                Chopstick rightChopStick = chopsticks[(i+1) % Constants.NUMBER_OF_PHILOSOPHERS];
                //why are we doing that modular operator ? because the last philosopher will get the zeroth chopstick as right chopstick
                philosophers[i] = new Philosopher(i, leftChopStick, rightChopStick);
                executorService.execute(philosophers[i]);
            }
            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
            for(Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }
        } finally {
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }
            for(Philosopher philosopher : philosophers) {
                System.out.println(philosopher+ " eat #"+ philosopher.getEatingCounter());
            }
        }
    }
}
/*
* 0,1
* 1,2
* 2,3
* 3,4
* 4,0
* */
