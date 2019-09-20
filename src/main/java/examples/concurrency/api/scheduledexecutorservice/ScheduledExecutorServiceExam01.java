package examples.concurrency.api.scheduledexecutorservice;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExam01 {
    public static void main(String[] args){
        ScheduledExecutorService reloadScheduler = Executors.newSingleThreadScheduledExecutor();


        // scheduleAtFixedRate : 시작딜레이(initialDelay) 이후 첫번째 실행을 시작으로 지정한 시간(period)만큼 차이로 정확하게 반복 실행 한다. 예를 들어보겠다.
        // initialDelay가 0이면 바로 실행된다. 그리고 나서 2분마다 실행된다.  (period 간격으로 실행

        // - schedule() : 지정한 delay 후에 command를 1회 실행한다.

        // - scheduleWithFixedDelay() : 시작딜레이(initialDelay) 이후 첫번째 실행을 시작으로 해당 command의 동작이 종료된 이후
        //   다음 실행 시간까지 지정한 시간(period)만큼 딜레이를 가지면서 반복 실행된다. 예를 들어보겠다. (실행종료 후에 period만큼 지나서 실행)
        reloadScheduler.scheduleAtFixedRate(
                ()->{
                    System.out.println(new Date());
                    for(int i = 0; i < 10; i++){
                        System.out.println(i);
                    }
                }, 0, 2, TimeUnit.MINUTES);


        //reloadScheduler.shutdown(); // shutdown을 하게 되면 바로 프로그램은 종료되고 스케쥴 작업은 실행되지 않는다.
    }
}
