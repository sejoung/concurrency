package examples.concurrency.api.threadfactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class NamingExecutorThreads {
    public static void main(String argc[]) {
        System.out.println("Main thread starts here...");

        ExecutorService execService = Executors.newFixedThreadPool(2, new NamedThreadsFactory());

        execService.execute(new MyThreadTask());
        execService.execute(new MyThreadTask());
        execService.execute(new MyThreadTask());
        execService.execute(new MyThreadTask());

        execService.shutdown();
        System.out.println("Main thread ends here...");
    }
}

class NamedThreadsFactory implements ThreadFactory {

    private static int count = 0;
    private static String Name = "MyThread-";

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, Name + ++count); //Mythread-x 형태로 쓰레드 이름 설정
    }
}

class MyThreadTask implements Runnable {
    private static int count = 0;
    private int id;
    @Override
    public void run(){
        String threadName = Thread.currentThread().getName();

        for(int i = 0; i<5; i++) {
            System.out.println("<" + threadName + ", " + id+ ">TICK TICK " + i);
            try {
                TimeUnit.MICROSECONDS.sleep((long)Math.random()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public MyThreadTask() {
        this.id = ++count;
    }
}

