package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Worker implements Runnable {
	private String name;
	ReentrantLock rl = new ReentrantLock();
	ReentrantLock r2 = new ReentrantLock();
	
	Worker(String name) {
		this.name = name;
	}

	public void run() {
		ReentrantLock l1;
		System.out.println("count : "+ rl.getHoldCount());
		if("A".equals(name) || "C".equals(name)){
			l1= this.rl;
			System.out.println(" l1 = r1");
		}else{
			l1= this.r2;
			System.out.println(" l1 = r2");
		}
		l1.lock();
		try {
			if (l1.isHeldByCurrentThread())
				System.out
						.printf(
								"Thread %s has entered its critical section.%n",
								name);
			try {
				System.out.printf(
						"Thread %s is performing work for 2 seconds.%n",
						name);
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		} finally {
			System.out
			.printf("Thread %s has finished working.%n", name);
			l1.unlock();
		}
	}
}

public class MultipleLockDemo {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new Worker("A"));
		executor.execute(new Worker("B"));
		executor.execute(new Worker("C"));
		executor.execute(new Worker("D"));

		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		executor.shutdownNow();
	}
}