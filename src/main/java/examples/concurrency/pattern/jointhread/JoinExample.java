package examples.concurrency.pattern.jointhread;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

public class JoinExample {
    public static void main(String[] args) {
        int threadCount = 100;

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Latch latch = new Latch();
        Thread[] th = new Thread[threadCount];
        for(int i = 0; i < threadCount; i++){
            th[i] = new Thread(new MyClass(latch), "th" + i);
        }

        for(int i = 0; i < threadCount; i++){
            th[i].start();
        }

        try {
            Thread.sleep(1000); // 마지막 start()가 wait할때까지 잠깐 기다려야 한다.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.unlock();

        for(int i = 0; i < threadCount; i++){
            try {
                th[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All three threads have finished execution");
    }
}

class Latch{
    public synchronized void lock(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void unlock(){
        notifyAll();
    }
}

class MyClass implements Runnable{
    private Latch latch;

    public MyClass(Latch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        latch.lock();

        Thread t = Thread.currentThread();
        System.out.println("Thread started: "+t.getName());

        for(int i = 0; i < 1000; i++) {
            try {
                URL url = new URL("https://ko.wikipedia.org/wiki/%EB%82%98%EB%AC%B4%EC%9C%84%ED%82%A4");
                try (InputStream in = url.openStream(); FileOutputStream fos = new FileOutputStream("c:\\tmp\\" + UUID.randomUUID())) {
                    byte[] buffer = new byte[1024];
                    int readCount = 0;
                    while ((readCount = in.read(buffer)) != -1) {
                        // write
                        fos.write(buffer, 0, readCount);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        for(int k = 0; k <=10;k++) {
//            int sum = 0;
//            for (int i = 0; i <= 5000000; i++) {
//                sum += i;
//            }
//        }
        System.out.println("Thread ended: "+t.getName());

    }
}