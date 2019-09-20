package examples.concurrency;

public class PrintThread extends Thread {

	private final String printData;

	public PrintThread(String printData) {
		this.printData = printData;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(this.printData);
		}

	}
}
