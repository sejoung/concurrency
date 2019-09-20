package examples.concurrency.pattern.future;

public class Restaurant {
    public Data request(final int count, final char c) {
        System.out.println(" request(" + count + ", " + c + ") BEGIN");
        final FutureData future = new FutureData();
        new Thread() {
            public void run() {
                RealData realdata = new RealData(count, c); // 시간이 오래 걸린다. 늦게 setRealData가 될 것이다.
                future.setRealData(realdata);
            }
        }.start();
        System.out.println(" request(" + count + ", " + c + ") END");
        return future;
    }
}
