package thread;

public class ThreadJoinExample {

	public static void main(String[] args) {
		MyRunnable t1 = new MyRunnable("T1");
		MyRunnable t2 = new MyRunnable("T2");
//		Thread t2 = new Thread(new MyRunnable(), "t2");
//		Thread t3 = new Thread(new MyRunnable(), "t3");

		/*t1.start();

		// start second thread after waiting for 2 seconds or if it's dead
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
*/		try {
			t1.start();
			t2.start();
			
			/*synchronized(t2){
				t2.wait();
			}*/
			t2.join1(0);
			t1.join();
			
			System.out.println("done");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

/*		t2.start();

		// start third thread only when first thread is dead
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t3.start();

		// let all threads finish execution before finishing main thread
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

*/		System.out.println("All threads are dead, exiting main thread");
	}

}

class MyRunnable extends Thread {
	public MyRunnable(String name){
		
	}
	
	@Override
	public synchronized void run() {
		System.out.println("Thread started:::"
				+ Thread.currentThread().getName());
		try {
			synchronized(this){
				this.wait(15000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out
				.println("Thread ended:::" + Thread.currentThread().getName());
	}
	public  synchronized void join1(long millis)
    throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (millis == 0) {
            while (isAlive()) {
                wait(0);
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }

}