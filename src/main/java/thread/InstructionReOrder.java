package thread;

public class InstructionReOrder {
	public static void main(String[] args) {
		RunThread r1= new RunThread();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r1);
		Thread t3 = new Thread(r1);
		Thread t4 = new Thread(r1);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class RunThread implements Runnable {
    boolean flag;

	@Override
	public void run() {
		try {
			BadlyOrdered b1 = new BadlyOrdered();
			while (!flag) {
				b1.threadOne();
				flag = b1.a;
				System.out.println(Thread.currentThread().getName() + " : "
						+ b1.threadTwo());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class BadlyOrdered {
	boolean a = false;
	boolean b = false;

	void threadOne() {
		a = true;
		b = true;
		System.out.println("set : " + a + ":" + b);
	}

	boolean threadTwo() {
		boolean r1 = b; // sees true
		boolean r2 = a; // sees false
		boolean r3 = a; // sees true
		return (r1 && !r2) && r3; // returns true
	}

}