package thread;

class NewThread implements Runnable {

	String name; // name of thread

	Thread t;

	NewThread(String threadname) {
		try {
			name = threadname;

			t = new Thread(this, name);

			System.out.println("New thread: " + t);

			// t.start(); // Start the thread

			// t.join();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This is the entry point for thread.

	public void run() {

		try {

			for (int i = 5; i > 0; i--) {

				System.out.println(name + ": " + i);

				Thread.sleep(500);

			}

		} catch (InterruptedException e) {

			System.out.println(name + " interrupted.");

		}

		System.out.println(name + " exiting.");

	}

}

public class DemoJoin {

	public static void main(String args[]) throws Exception {

		NewThread ob1 = new NewThread("One");

		System.out.println("2");
		NewThread ob2 = new NewThread("Two");
		System.out.println("3");

		NewThread ob3 = new NewThread("Three");

		/*System.out.println("Thread One is alive: "

		+ ob1.t.isAlive());

		System.out.println("Thread Two is alive: "

		+ ob2.t.isAlive());

		System.out.println("Thread Three is alive: "

		+ ob3.t.isAlive());
*/
		// wait for threads to finish

		try {
			System.out.println("Waiting for threads to finish.");
			ob1.t.start();
			ob1.t.join();
			ob2.t.start();
			ob2.t.join();
			ob3.t.start();
			ob3.t.join();
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		/*
		 * System.out.println("Thread One is alive: "
		 * 
		 * + ob1.t.isAlive());
		 * 
		 * System.out.println("Thread Two is alive: "
		 * 
		 * + ob2.t.isAlive());
		 * 
		 * System.out.println("Thread Three is alive: "
		 * 
		 * + ob3.t.isAlive());
		 */
//		Thread.currentThread().join();
		System.out.println("Main thread exiting.");

	}

}