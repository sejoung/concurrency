package examples.concurrency.api.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Starter {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(50); //BlockingQueue
        queue = new LinkedBlockingQueue();
        Thread p = new Thread(new Producer(queue));
        Thread c = new Thread(new Consumer(queue));
        p.start();
        c.start();
    }
}

