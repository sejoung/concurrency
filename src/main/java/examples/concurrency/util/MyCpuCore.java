package examples.concurrency.util;

public class MyCpuCore {
    public static void main(String[] args){
//          적정 쓰레드 수는?
//        - 바쁜 시스템  : cpu core개수 + 1
//        - 한가한 시스템 : cpu core개수 * 2
//        출처: https://doitnow-man.tistory.com/16 [즐거운인생 (이유있는 삽질)]
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println(coreCount);
    }
}
