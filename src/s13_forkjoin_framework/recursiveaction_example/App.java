package s13_forkjoin_framework.recursiveaction_example;

import java.util.concurrent.RecursiveAction;

public class App {
    public static void main(String[] args) {
        SimpleRecursiveAction action = new SimpleRecursiveAction(120);
        action.invoke();
    }
}
/*
* fork() - asynchronously executes the given tasks in the pool
*           we can call it when using RecursiveTask<T> or RecursiveAction
* join() - returns the result of the computation when it is finished
* invoke() executes the given task + wait + wait + return the result upon completion
* RecursiveAction is non return type.
* */

class SimpleRecursiveAction extends RecursiveAction {
    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {
        //if the task is too large then we split it and execute the tasks in parallel
        if (simulatedWork > 100) {
            System.out.println("Paralel execution and split the tasks..."+ simulatedWork);
            SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork/2);
            action1.fork();
            action2.fork();
        } else {
            System.out.println("The task is rather small, so sequential execution is fine..");
            System.out.println("The size of the task "+simulatedWork );
        }
    }
}