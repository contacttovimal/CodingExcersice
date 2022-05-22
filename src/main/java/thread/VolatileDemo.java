package thread;

public class VolatileDemo implements Runnable {
   private static volatile int testValue1;
    private static volatile int testValue;
    private int newValue;
    public VolatileDemo(int value){
    	this.newValue = value;
    }
	public void run() {
		Thread t = Thread.currentThread();
		String name = t.getName();
		try {
			  for (int i = 0; i < 10; i++) {
				    System.out.println("************START*************************");
			        System.out.println(t.getName() + " testValue1 before update: " + testValue1);
			        System.out.println(t.getName() + " testValue before update: " + testValue);
			        testValue = i * newValue;
			        testValue1 = i * newValue;
			        System.out.println(t.getName() + " testValue1 after update: " + testValue1);
			        System.out.println(t.getName() + " testValue after update: " + testValue);
			        System.out.println("****************END*********************");
			        if("Thread2".equals(t.getName())){
			        	Thread.sleep(4);
			        }else{
			        	Thread.sleep(5);
			        }
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws InterruptedException {
		Runnable r = new VolatileDemo(10);
		Runnable r1 = new VolatileDemo(100);
		Thread t1 = new Thread(r);
		t1.setName("Thread1");
		t1.start();

//		Thread.sleep(1000);
		
		Thread t2 = new Thread(r1);
		t2.setName("Thread2");

		t2.start();
		
	}
}