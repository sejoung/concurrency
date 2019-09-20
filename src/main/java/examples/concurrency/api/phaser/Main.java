package examples.concurrency.api.phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Phaser ph = new Phaser(1);

        // phase 0
        System.out.println("ph.getPhase() : " + ph.getPhase());

        executorService.submit(new LongRunningAction("thread-1", ph));
        executorService.submit(new LongRunningAction("thread-2", ph));
        executorService.submit(new LongRunningAction("thread-3", ph));

        ph.arriveAndAwaitAdvance();

        // phase 1
        System.out.println("ph.getPhase() : " + ph.getPhase());

        //and
        executorService.submit(new LongRunningAction("thread-4", ph));
        executorService.submit(new LongRunningAction("thread-5", ph));
        ph.arriveAndAwaitAdvance();

        // phase 2
        System.out.println("ph.getPhase() : " + ph.getPhase());


        ph.arriveAndDeregister();
    }
}
