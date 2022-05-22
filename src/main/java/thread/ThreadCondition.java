package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyWorker extends Thread {
	final int input;
	int result;
	static Lock lck = new ReentrantLock();
	Condition c = lck.newCondition();	

	public MyWorker(final int input) {
		this.input = input;
	}

/*	@Override
	public void run() {
		try {
			lck.lock();
			c.await();
			System.out.println(" input : " + input);
			this.result = input; // Or some other computation.
			System.out.println(" result : " + this.result);
			lck.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	@Override
	public void run() {
		try {
			synchronized(lck){
				lck.wait();
				System.out.println(" input : " + input);
				this.result = input; // Or some other computation.
				System.out.println(" result : " + this.result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	int getResult() {
		return result;
	}
/*	public void invokeThread(){
		lck.lock();
		c.signal();
		lck.unlock();
	}
*/
	public void invokeThread(){
		synchronized(lck){
			lck.notify();
		}
	}
	
}

public class ThreadCondition {

	public static void main(String[] args) throws InterruptedException {
		try{
		MyWorker[] workers = new MyWorker[10];
		for (int i = 0; i < 10; i++) {
			workers[i] = new MyWorker(i);
			workers[i].start();
		}

		// Assume it may take a while to do the real computation in the threads.

		for (MyWorker worker : workers) {
			// This can throw InterruptedException, but we're just passing that.
			//worker.join();
			worker.invokeThread();
//			System.out.println("Thread ORDERING : "+worker.getResult());
			System.out.println("*******************************");
			Thread.sleep(500);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}