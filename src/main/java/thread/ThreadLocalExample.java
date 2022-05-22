package thread;

import java.util.concurrent.ConcurrentSkipListMap;

public class ThreadLocalExample {
	public static void main(String[] args) throws Exception {
		ConcurrentSkipListMap<String, Integer> conSkipMap = new ConcurrentSkipListMap<String, Integer>();
		conSkipMap.put("BBB", 1);
		conSkipMap.put("AAA", 2);
		for (String string : conSkipMap.keySet()) {
			System.out.println(string);
		}

		PrintTask pt1 = new PrintTask();
		PrintTask pt2 = new PrintTask();
		PrintTask pt3 = new PrintTask();

		Thread t1 = new Thread(pt1, "TH1");
		Thread t2 = new Thread(pt2, "TH2");
		Thread t3 = new Thread(pt3, "TH3");

		t1.start();
		t2.start();
		t3.start();
		System.out.println("pt1 : " + pt1.getThreadLocalCounter());
		System.out.println("pt2 : " + pt2.getThreadLocalCounter());
		System.out.println("pt3 : " + pt3.getThreadLocalCounter());
		Thread.currentThread().sleep(1000);
		System.out.println("pt11 : " + pt1.getThreadLocalCounter());
		System.out.println("pt22 : " + pt2.getThreadLocalCounter());
		System.out.println("pt33 : " + pt3.getThreadLocalCounter());

		// Thread.currentThread().join();

	}
}

class PrintTask implements Runnable {
	ThreadLocal<Integer> counter = new ThreadLocal<Integer>();
	static Integer threadCounter = 0;

	public PrintTask() {
	}

	public Integer getThreadLocalCounter() {
		return counter.get();
	}

	public void run() {
		synchronized (threadCounter) {
			counter.set(threadCounter);
			threadCounter++;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 1;
		int n = 10;
		try {
			synchronized(this){
				while ((i % threadCounter) == (counter.get())) {
					synchronized (this) {
						this.wait();
					}
				}
			}
			while (i <= n) {
				if ((i % threadCounter) == (counter.get())) {
					System.out.println(Thread.currentThread().getName() + ": "
							+ i);
				}
				i++;
			}
			synchronized (this) {
				this.notifyAll();
			}
			counter.remove();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
