package thread;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ThreadWaitWithoutJoin {
	public static void main(String[] args) {
		try {
			String sequenceName = "";
			ThreadWaitWithoutJoin tdm = new ThreadWaitWithoutJoin();
			ArrayList list = new ArrayList();
			Semaphore semaphore = new Semaphore(2,true);
			Thread11 t11 = new Thread11("T11",0,list,semaphore);
			Thread11 t22 = new Thread11("T22",1,list,semaphore);
			Thread11 t33 = new Thread11("T33",2,list,semaphore);
			Thread t1 = new Thread(t11);
			Thread t2 = new Thread(t22);
			Thread t3 = new Thread(t33);
			t1.start();
			t1.start();
			t2.start();
			t3.start();
			// System.out.println("Finish");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Thread11 implements Runnable {
	String name;
	int sequence;
	static Integer currentCounter = 0;
	ArrayList sharedList;
	Semaphore semaphore; //synchronization through semaphore

	public Thread11(String name,int sequence,ArrayList list,Semaphore semaphore) {
		this.name = name;
		this.sequence = sequence;
		this.sharedList = list;
		this.semaphore =semaphore;
	}

	public void run() {
		try {
			synchronized(sharedList){
				System.out.println("seq : " + sequence);
				while(currentCounter != sequence){
					sharedList.wait();
				}
			}
//			semaphore.acquire();
			System.out.println("START : " + this.name);
			for (int i = 0; i < 5; i++) {
				System.out.println(" i : " + i + " : " + this.name);
				Thread.sleep(500);
			}
			currentCounter = sequence +1;
			synchronized(sharedList){
				System.out.println("notify call from : " + sequence);
				sharedList.notifyAll();
			}
			//semaphore.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}