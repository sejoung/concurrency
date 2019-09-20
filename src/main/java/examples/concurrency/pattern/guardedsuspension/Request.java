package examples.concurrency.pattern.guardedsuspension;

public class Request {
    private final String name;
    public Request(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return "[ examples.concurrency.pattern.workerthread.Request " + name + " ]";
    }
}
