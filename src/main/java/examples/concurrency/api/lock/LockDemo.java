package examples.concurrency.api.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    public static void main(String args[]) {

        final Counter myCounter = new Counter(new ReentrantLock());

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.printf("Count at thread %s is %d %n",
                        Thread.currentThread().getName(), myCounter.getCount());
            }
        };

        // Creating three threads
        Thread t1 = new Thread(r, "T1");
        Thread t2 = new Thread(r, "T2");
        Thread t3 = new Thread(r, "T3");

        //starting all threads
        t1.start();
        t2.start();
        t3.start();
    }
}
class Counter {
    private Lock lock; // Lock to protect our counter
    private int count; // Integer to hold count

    public Counter(Lock myLock) {
        this.lock = myLock;
    }

    public final int getCount() {
        lock.lock(); // 명시적 lock
        try {
            count++;
            return count;
        } finally {
            lock.unlock();
        }

    }
}
