package s9_diningphiliosophersprobelm;

import java.util.Random;

public class Philosopher implements Runnable {
    private int id;
    private volatile boolean full;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        random = new Random();
    }

    @Override
    public void run() {
        try{
            //after eating a lot then we will terminate the given thread
            while (!full) {
                think();
                if(leftChopstick.pickUp(this, State.LEFT)) {
                    //philosopher is able to acquire the left chopstick, then try to acquire the right
                    if(rightChopstick.pickUp(this,State.RIGHT)){
                        eat();
                        rightChopstick.putDown(this, State.RIGHT);
                    }
                    leftChopstick.putDown(this,State.LEFT);
                }
            }
        }catch (Exception e){}
    }

    private void think() throws InterruptedException {
        System.out.println(this+ " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this+ " is eating...");
        eatingCounter++;
        Thread.sleep(random.nextInt(1000));
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public int getEatingCounter( ) {
        return eatingCounter;
    }

    @Override
    public String toString() {
        return "Philosopher-"+this.id;
    }
}

