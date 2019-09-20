package examples.concurrency;

public class TestRun {

	public static void main(String[] args) {

		new PrintThread("*").start();
		new PrintThread("+").start();

	}

}
