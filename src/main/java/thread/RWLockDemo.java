package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockDemo {

	public static void main(String[] args) throws Exception{
		ExecutorService service = Executors.newFixedThreadPool(2);
//		ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		MyConcurrentHashMap<String, String> myConcurrentMap = new MyConcurrentHashMap<String, String>();
		
		for (int k = 0; k < 10; k++) {
/*			service.execute(new ReaderThread(myConcurrentMap, readWriteLock,
					"KEY-" + k));
			service.execute(new WriterThread(myConcurrentMap, readWriteLock,
					"KEY-" + k));
*/
			service.execute(new ReaderThread(myConcurrentMap, "KEY-" + k));
			service.execute(new WriterThread(myConcurrentMap, "KEY-" + k));
	
		}
//		service.execute(new SyncThread(myConcurrentMap, readWriteLock));
		Thread.currentThread().sleep(1000);
		
		myConcurrentMap.printValue("KEY-0");
		/*
		 * for(int k=0;k < 5;k++){ service.execute(new
		 * WriterThread(myConcurrentMap, readWriteLock, "KEY-"+k)); }
		 */
		service.shutdown();
	}
}

class SyncThread implements Runnable {

	MyConcurrentHashMap<String, String> syncMap = null;
	ReentrantReadWriteLock reenterantLock = null;

	public SyncThread(MyConcurrentHashMap<String, String> syncMap,
			ReentrantReadWriteLock reentrantlock) {
		this.syncMap = syncMap;
		this.reenterantLock = reentrantlock;
	}

	public void run() {

		try {
			// reenterantLock.writeLock().lock();
			System.out.println("Sync Thread Start");
			synchronized (syncMap) {
				for (int k = 0; k < 5; k++) {
					System.out.println("Thread Running :" + k);
					Thread.currentThread().sleep(500);
				}
				// reenterantLock.writeLock().unlock();
				
			}
			System.out.println("Sync Thread Over");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ReaderThread implements Runnable {
	MyConcurrentHashMap<String, String> readMap = null;
	String readKey = null;

	public ReaderThread(MyConcurrentHashMap<String, String> readMap,
			/*ReentrantReadWriteLock readLock,*/ String readKey) {
		this.readMap = readMap;
		this.readKey = readKey;
	}

	public void run() {
		try {
			this.readMap.doReadLock();
			System.out.println("START Read thread  : " + readKey
					+ "  Thread : " + Thread.currentThread().getName());
			// System.out.println("GetLock  : " + readKey);
			// System.out.println("Key : " + readKey + " Value : " +
			// readMap.get(readKey));
			// System.out.println("Release Lock  : " + readKey);
			Thread.sleep(1000);
			System.out.println("END read thread  : " + readKey + "  Thread : "
					+ Thread.currentThread().getName());
			this.readMap.doReadUnLock();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class WriterThread implements Runnable {
	MyConcurrentHashMap<String, String> writeMap = null;
//	Lock writeLock = null;
	String writeKey = null;

	public WriterThread(MyConcurrentHashMap<String, String> writeMap,
			/*ReentrantReadWriteLock writeLock,*/ String writeKey) {
		this.writeMap = writeMap;
//		this.writeLock = writeLock.writeLock();
		this.writeKey = writeKey;
	}

	public void run() {
		try {
			this.writeMap.doWriteLock();
			System.out.println("START Write thread  : " + writeKey
					+ "  Thread : " + Thread.currentThread().getName());
			// System.out.println("GetLock  : " + writeKey);
			// System.out.println("Key : " + writeKey + " Value : " + writeKey);
			writeMap.put(writeKey, writeKey);
			// System.out.println("Release Lock  : " + writeKey);
			Thread.sleep(1000);
			System.out.println("END write thread  : " + writeKey
					+ "  Thread : " + Thread.currentThread().getName());
			this.writeMap.doWriteUnLock();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class MyConcurrentHashMap<K, V> {

	// private Map<K,V> hashMap = new HashMap<>();
	private Map hashMap = new HashMap<K, V>();
	// non-fair read write lock
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock readLock = lock.readLock();
	private final Lock writeLock = lock.writeLock();

	public MyConcurrentHashMap() {

	}
	public void doReadLock(){
		readLock.lock();
	}
	public void doReadUnLock(){
		readLock.unlock();
	}
	public void doWriteLock(){
		writeLock.lock();
	}
	public void doWriteUnLock(){
		writeLock.unlock();
	}

	public synchronized void printValue(String key) {
		try {
			Thread.currentThread().sleep(1000);
			System.out.println("**********************");
			System.out.println(hashMap.get(key));
			System.out.println("**********************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void put(K key, V value) {
		writeLock.lock();
		try {
			hashMap.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}

	public V get(K key) {
		readLock.lock();
		try {
			return (V) hashMap.get(key);
		} finally {
			readLock.unlock();
		}
	}

	public V remove(K key) {
		writeLock.lock();
		try {
			return (V) hashMap.remove(key);
		} finally {
			writeLock.unlock();
		}
	}

	public boolean containsKey(K key) {
		readLock.lock();
		try {
			return hashMap.containsKey(key);
		} finally {
			readLock.unlock();
		}
	}

}