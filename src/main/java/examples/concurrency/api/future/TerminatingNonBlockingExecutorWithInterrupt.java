package examples.concurrency.api.future;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TerminatingNonBlockingExecutorWithInterrupt {
    public static void main(String argc[]) throws InterruptedException {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "]" + " thread starts here...");

        System.out.println("[" + currentThreadName + "]" + " thread starts here...");

        ExecutorService execService = Executors.newSingleThreadExecutor(); // task1이 끝나고 나서 task2가 실행된다.
//        ExecutorService execService = Executors.newFixedThreadPool(2);

        LoopTask task1 = new LoopTask();
        LoopTask task2 = new LoopTask();

        Future<?> future1 = execService.submit(task1);
        Future<?> future2 = execService.submit(task2);

        execService.shutdown();

        TimeUnit.MILLISECONDS.sleep(200);
        future1.cancel(true);
        TimeUnit.MILLISECONDS.sleep(100);
        future2.cancel(true);

        System.out.println("[" + currentThreadName + "]" + " thread ends here...");
    }
}

class LoopTask implements Runnable {
    private static int count = 0;
    private int id;
    private String taskId;
    private final int DATA_SIZE = 100000;

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();
        System.out.println("#### <" + currentThreadName + "," + taskId + "> starting...####");
        while (true) {
            System.out.println("<" + currentThreadName + "," + taskId + "> TICK TICK");
            doSomeWork();
            // 해당 줄이 실행되야만 cancel()이 실행된 것을 알 수 있다.
            if (Thread.interrupted()) {
                System.out.println("<" + currentThreadName + "," + taskId + "> got an interrupt! ..canceling...");
                break;
            }
        }
        System.out.println("#### <" + currentThreadName + "," + taskId + "> done...####");
    }

    public LoopTask() {
        this.id = ++count;
        this.taskId = "Task-" + id;
    }

    private void doSomeWork() {
        for (int i = 0; i < 2; i++) {
            Collections.sort(generateDataSet());
        }
    }

    private List<Integer> generateDataSet() {
        List<Integer> intList = new ArrayList<>();
        Random randomGenerator = new Random();
        for (int i = 0; i < DATA_SIZE; i++) {
            intList.add(randomGenerator.nextInt(DATA_SIZE));
        }
        return intList;
    }
}