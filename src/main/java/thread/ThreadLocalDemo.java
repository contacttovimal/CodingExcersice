package thread;


public class ThreadLocalDemo extends Thread {
	public static void main(String args[]) throws Exception {
		MyThreadLocal.set(Thread.currentThread().getName());
		new BusinessService().businessMethod();
		MyThreadLocal.unset();
		ThreadLocalDemo threadOne = new ThreadLocalDemo();
		threadOne.start();
		Thread.currentThread().sleep(100);
		System.out.println("thlocal value : " + threadOne.getThLocal().get());
		Thread threadTwo = new ThreadLocalDemo();
		threadTwo.start();
	}
	public ThreadLocal<Integer> getThLocal(){
		return thLocalValue;
	}
	private ThreadLocal<Integer> thLocalValue= new ThreadLocal<Integer>();
	public void run() {
		thLocalValue.set(10);
		System.out.println("set");
		MyThreadLocal.set(getName());
		new BusinessService().businessMethod();
		MyThreadLocal.unset();
	}
}

class MyThreadLocal {

	public static final ThreadLocal userThreadLocal = new ThreadLocal();

	public static void set(String str) {
		userThreadLocal.set(str);
	}

	public static void unset() {
		userThreadLocal.remove();
	}

	public static String get() {
		return (String) userThreadLocal.get();
	}
}

class BusinessService {
	public void businessMethod() {
		System.out.println((String)MyThreadLocal.get());
	}
}