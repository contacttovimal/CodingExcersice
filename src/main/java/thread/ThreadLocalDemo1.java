package thread;


public class ThreadLocalDemo1 {
	public static void main(String[] args) throws Exception {

		PrintTask1 pt1 = new PrintTask1();

		pt1.counter.set(10L);
		Thread t1 = new Thread(pt1, "TH1");
		Thread t2 = new Thread(pt1, "TH2");
		
		t1.start();
		Thread.currentThread().sleep(500);
		t2.start();
		Thread.currentThread().sleep(1000);
		System.out.println("Main  : "+pt1.counter.get());
		pt1.counter.remove();

	}
}

class PrintTask1 implements Runnable {
	ThreadLocal<Long> counter = new ThreadLocal<Long>();
	
	public PrintTask1() {
	}

	public void run() {
		counter.set(System.currentTimeMillis());
		System.out.println(Thread.currentThread().getName()+ " :  Counter : " + counter.get());
	}
}
