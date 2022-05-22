package thread;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
	private final Semaphore roomOrganizer = new Semaphore(1, true); // true:

	// first
	// come,
	// first
	// serve

	public static void main(String[] args) {
		try {
			/*
			 * SemaphoreDemo test = new SemaphoreDemo(); test.mystart();
			 */

			runDanger();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mystart() {
		for (int i = 0; i < 20; i++) {
			People people = new People();
			people.start();
		}
	}

	public class People extends Thread {
		@Override
		public void run() {
			try {
				roomOrganizer.acquire();
			} catch (InterruptedException e) {
				System.out.println("received InterruptedException");
				return;
			}
			System.out.println("Thread " + this.getId()
					+ " starts to use the room");
			try {
				sleep(1000);
			} catch (InterruptedException e) {

			}
			System.out.println("Thread " + this.getId() + " leave the room\n");
			roomOrganizer.release();
		}
	}

	public static void runDanger() throws Exception {
		Semaphore s1 = new Semaphore(1);
		Semaphore s2 = new Semaphore(1);

		Thread t = new Thread(new DoubleResourceGrabber(s1, s2));
		// now reverse them ... here comes trouble!
		Thread t2 = new Thread(new DoubleResourceGrabber(s2, s1));

		t.start();
		t2.start();

		/*t.join();
		t2.join();*/
		Thread.currentThread().join();
		System.out.println("We got lucky!");
	}

	private static class DoubleResourceGrabber implements Runnable {
		private Semaphore first;
		private Semaphore second;

		public DoubleResourceGrabber(Semaphore s1, Semaphore s2) {
			first = s1;
			second = s2;
		}

		public void run() {
			try {
				Thread t = Thread.currentThread();

				if(!first.tryAcquire()){
					Thread.sleep(100); 
				}
				System.out.println(t + " acquired " + first);

				Thread.sleep(1000); // demonstrate deadlock

				if(!second.tryAcquire()){
					Thread.sleep(100); 
				}
				System.out.println(t + " acquired " + second);

				second.release();
				System.out.println(t + " released " + second);

				first.release();
				System.out.println(t + " released " + first);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
