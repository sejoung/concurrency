package examples.concurrency.pattern.workerthread;

import java.util.Random;

public class Request {
    private final String name; // 의뢰자
    private final int number;  // 리퀘스트번호
    private static final Random random = new Random();
    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executes " + this);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
        }
    }
    public String toString() {
        return "[ examples.concurrency.pattern.workerthread.Request from " + name + " No." + number + " ]";
    }
}
