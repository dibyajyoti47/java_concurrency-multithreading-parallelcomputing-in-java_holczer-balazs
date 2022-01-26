package s13_forkjoin_framework.recursivetask_example;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class App {
    public static void main(String[] args) {
//        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        SimpleRecursiveTask simpleRecursiveTask = new SimpleRecursiveTask(120);
        System.out.println(simpleRecursiveTask.invoke());

    }
}

class SimpleRecursiveTask extends RecursiveTask<Integer> {
    private int simulatedWork;

    public SimpleRecursiveTask(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected Integer compute() {
        if (simulatedWork > 100) {
            System.out.println("Parallel execution so split the task..."  + simulatedWork);
            SimpleRecursiveTask task1 = new SimpleRecursiveTask(simulatedWork/2);
            SimpleRecursiveTask task2 = new SimpleRecursiveTask(simulatedWork/2);
            task1.fork();
            task2.fork();
            int subSolution = 0;
            subSolution += task1.join();
            subSolution += task2.join();
            return subSolution;
        } else {
            System.out.println("The task is small... we can execute it sequentially: "+simulatedWork);
            return 2*simulatedWork;
        }
    }
}