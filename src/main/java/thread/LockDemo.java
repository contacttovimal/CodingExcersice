package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		final ReentrantLock rl = new ReentrantLock();

		class Worker implements Runnable {
			private String name;

			Worker(String name) {
				this.name = name;
			}

			public void run() {
				System.out.println("count : "+ rl.getHoldCount());
				rl.lock();
				try {
					if (rl.isHeldByCurrentThread())
						System.out
								.printf(
										"Thread %s has entered its critical section.%n",
										name);
					System.out.printf(
							"Thread %s is performing work for 2 seconds.%n",
							name);
					try {
						rl.lock();
						Thread.sleep(2000);
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
					System.out
							.printf("Thread %s has finished working.%n", name);
				} finally {
					rl.unlock();
					rl.unlock();
				}
			}
		}

		executor.execute(new Worker("A"));
		executor.execute(new Worker("B"));

		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		executor.shutdownNow();
	}
}