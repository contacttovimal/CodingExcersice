package collection;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AdvanceProducerConsumer<T> {
	Condition isFullCondition;
	Condition isEmptyCondition;
	Lock lock;
	int limit;
	ArrayList<T> q;
	
	public AdvanceProducerConsumer() {
		this(Integer.MAX_VALUE);
		q = new ArrayList<T>(Integer.MAX_VALUE);
	}

	public AdvanceProducerConsumer(int limit) {
		this.limit = limit;
		lock = new ReentrantLock();
		q = new ArrayList<T>(limit);
		isFullCondition = lock.newCondition();
		isEmptyCondition = lock.newCondition();
	}

	public void put(T t) {
		lock.lock();
		try {
			while (isFull()) {
				try {
					isFullCondition.await();
				} catch (InterruptedException ex) {
				}
			}
			q.add(t);
			isEmptyCondition.signalAll();
		} finally {
			lock.unlock();
		}
	}
	private synchronized boolean isFull(){
		return (q.size()==limit)? true: false;
	}
	private synchronized boolean isEmpty(){
		return (q.size()==0)? true: false;
	}

	public T get() {
		T t = null;
		lock.lock();
		try {
			while (isEmpty()) {
				try {
					isEmptyCondition.await();
				} catch (InterruptedException ex) {
				}
			}
			t = q.remove(0);
			isFullCondition.signalAll();
		} finally {
			lock.unlock();
		}
		return t;
	}
}