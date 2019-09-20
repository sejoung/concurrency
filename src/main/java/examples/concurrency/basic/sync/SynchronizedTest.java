package examples.concurrency.basic.sync;

public class SynchronizedTest {
    public static void main(String[] args){
        SharedObject shareObject = new SharedObject();
        PrintThread printThread1 = new PrintThread(shareObject, 1);
        PrintThread printThread2 = new PrintThread(shareObject, 2);
        PrintThread printThread3 = new PrintThread(shareObject, 3);

        printThread1.start();
        printThread2.start();
        printThread3.start();
    }
}

class PrintThread extends Thread{
    private SharedObject sharedObject;
    private int type;
    public PrintThread(SharedObject sharedObject, int type){
        this.sharedObject = sharedObject;
        this.type = type;
    }
    public void run(){
        if(type == 1){
            sharedObject.printA();
        }else if(type == 2){
            sharedObject.printB();
        }else if(type == 3){
            sharedObject.printC();
        }

    }
}

class SharedObject{
    public void printA(){
        for(int i = 0; i < 100; i++){
            System.out.print("A");
            try {
                Thread.sleep((int)(500 * Math.random()) + 300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB(){
        for(int i = 0; i < 100; i++){
            System.out.print("B");
            try {
                Thread.sleep((int)(500 * Math.random()) + 300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void printC(){
        for(int i = 0; i < 100; i++){
            System.out.print("C");
            try {
                Thread.sleep((int)(500 * Math.random()) + 300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}