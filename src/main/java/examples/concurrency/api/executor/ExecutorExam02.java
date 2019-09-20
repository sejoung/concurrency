package examples.concurrency.api.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorExam02 {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Callable 을 파라미터로 전달하였다. Callable은 V call() throws Exception; 를 가진다.
        Future<Integer> result = executorService.submit(() -> {
            int total = 0;
            for (int i = 1; i <= 10; i++) {
                total += i;
            }
            return total;
        });


        while(true) {
            if (result.isDone()) {
                try {
                    System.out.println(result.get().intValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        executorService.shutdown();
    }
}
