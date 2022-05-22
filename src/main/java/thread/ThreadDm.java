package thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadDm {
	public static void main(String[] args) {
		try {
			new Thread3("AAAA").start();
			ThreadDm tdm = new ThreadDm();
			Thread2 td2 = new Thread2(tdm);
			Thread t2 = new Thread(td2);
			List<Thread> threadList = new ArrayList<Thread>();
			for(int i = 0 ; i < 10 ; i++){
				Thread1 td1 = new Thread1(tdm);
				Thread t1 = new Thread(td1);
				t1.start();
				threadList.add(t1);
				System.out.println("thread : " + i + " started.");
			}
			for(int i=0;i<threadList.size();i++){
				Thread th2 = threadList.get(i);
				th2.join();
			}
			
			t2.start();
//			System.out.println("Finish");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Thread3 extends Thread {
	String name;
	public Thread3(String name) {
		this.name=name;
		super.setName(name);
	}
	@Override
	public synchronized void start() {
		System.out.println("check lcoal data");
		super.start();
	}
	@Override
	public void run() {
		try{
		for(int i=0;i<5;i++){
			System.out.println(" i  = "+ i);
			Thread.sleep(1000);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class Thread1 implements Runnable {
	ThreadDm tdm = null;

	public Thread1(ThreadDm tdm) {
		this.tdm = tdm;
	}

	public void run() {
		for (int k = 0; k < 10; k++) {
			try {
				synchronized (tdm) {
					if (k % 2 == 0) {
//						System.out.println(k + " - " + Thread.currentThread().getName());
					}
					tdm.notify();
					if(k==9){
						break;
					}
					tdm.wait(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

class Thread2 implements Runnable {
	ThreadDm tdm = null;

	public Thread2(ThreadDm tdm) {
		this.tdm = tdm;
	}

	public void run() {
		for (int k = 0; k < 10; k++) {
			try {
				synchronized (tdm) {
					if (k % 2 == 1) {
						System.out.println(k + " - " + Thread.currentThread().getName());
					}
					tdm.notify();
					if(k==9){
						break;
					}
					tdm.wait();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}