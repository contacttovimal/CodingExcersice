package thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExample {

	public static void main(String args[]) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(5);
		final ReentrantLock reLock = new ReentrantLock();
		
		ExecutorCompletionService executorCompService = new ExecutorCompletionService(
				service);
		// List<Future<String>> futureList = new ArrayList<Future<String>>();
		for (int i = 0; i < 10; i++) {
			// futureList.add(service.submit(new Task(i)));
//			 executorCompService.submit(new Task(i,reLock));
			service.execute(new Task(i, reLock));
			// new Thread(new Task(i,reLock)).start();
			if (i == 15) {
				/*
				 * List<Runnable> taskList= service.shutdownNow(); for (Iterator
				 * iterator = taskList.iterator(); iterator.hasNext();) {
				 * Runnable runnable = (Runnable) iterator.next();
				 * System.out.println(runnable); }
				 */
			}
		}
		/*
		 * for(Future<String> future : futureList){ String result =
		 * future.get(); System.out.println(result); }
		 */
		
		for (int i = 0; i < 10; i++) {
			String result = (String) executorCompService.take().get();
			System.out.println(result);
		}
		service.shutdown();
	}

}

final class Task implements Runnable,Callable<String> {
	private int taskId;
	ReentrantLock reLock;

	public Task(int id, ReentrantLock reLock) {
		this.taskId = id;
		this.reLock = reLock;
	}

	public void run() {
		try {
			System.out.println("trylock : " + reLock.tryLock());
			System.out.println("trylock 1 : " + reLock.tryLock(10,TimeUnit.SECONDS));
//			System.out.println("count : " + reLock.getHoldCount());
			if (reLock.tryLock()) {
				try {
					Thread.sleep(1000);
					System.out.println("Task ID : " + this.taskId
							+ " performed by "
							+ Thread.currentThread().getName());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					// reLock.unlock();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String call() throws Exception {
		// TODO Auto-generated method stub

		run();
		return "Completed : " + this.taskId;
	}
}
