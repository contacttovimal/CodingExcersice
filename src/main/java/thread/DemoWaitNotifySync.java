package thread;

class NewThread1 implements Runnable {

	String name; // name of thread

	NewThread1(String threadname) {
		try {
			name = threadname;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This is the entry point for thread.
	public void run() {
		try {
			synchronized (this) {
				for (int i = 5; i > 0; i--) {
					System.out.println(Thread.currentThread().getName() + " : "
							+ name + ": " + i);
					/*this.notifyAll();
					this.wait();*/
					Thread.sleep(5000);
				}
			}

		} catch (InterruptedException e) {

			System.out.println(name + " interrupted.");

		}

		System.out.println(name + " exiting.");

	}

}

public class DemoWaitNotifySync {

	public static void main(String args[]) throws Exception {

		NewThread1 ob1 = new NewThread1("One");
		Thread t1 = new Thread(ob1, "T1");
		Thread t2 = new Thread(ob1, "T2");
		t1.start();
		Thread.sleep(1000);
		t2.start();
		System.out.println("Main thread exiting.");

	}

}