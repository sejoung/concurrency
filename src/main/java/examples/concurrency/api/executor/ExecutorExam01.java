package examples.concurrency.api.executor;

// https://12bme.tistory.com/359?category=682904
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExam01 {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        ExecutorService executorService = Executors.newSingleThreadExecutor(); // 단일 쓰레드.
//        ExecutorService executorService = Executors.newCachedThreadPool(); // 쓰레드가 계속 늘어날 수 있다.

        // Runnable을 파라미터로 전달한다. Runnable은 public abstract void run(); 메소드를 가진다.
        executorService.submit(() -> {
            for(int i = 0; i < 10; i++){
                System.out.println(i);
            }
        });

        executorService.shutdown();
    }
}
