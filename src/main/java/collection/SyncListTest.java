package collection;

import java.util.*;

public class SyncListTest {
	public static void main(String[] args) {
		
		List<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(new Integer(1));
		arrayList.add(new Integer(2));
		arrayList.add(new Integer(3));
		Set<Integer> set = new HashSet<Integer>();
		set.add(new Integer(1));
		set.add(new Integer(2));
		arrayList.retainAll(set);
		for (Integer integer : arrayList) {
			System.out.println(integer);
		}
		
		final NameList nl = new NameList();
		class NameDropper implements Runnable {
			public void run() {
				try {
					while (true) {
						if (Thread.currentThread().getName().equals("TH1")) {
							nl.add("testdata");
						} else {
//							nl.removeFirst();
							nl.add("testdata");
						}
						Thread.sleep(1000);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		NameDropper nd = new NameDropper();
		Thread t1 = new Thread(nd, "TH1");
		Thread t2 = new Thread(nd, "TH2");

		t1.start();
		 t2.start();
	}
}

class NameList {
	// private List names = Collections.synchronizedList(new LinkedList());
	private List names = new LinkedList();

	public synchronized void add(String name) {
		names.add(Thread.currentThread().getName() + "-" + names.size());
			try {
				System.out.println("start : " + Thread.currentThread().getName());
				for(int k=0;k<100;k++){
					int p=10;
					p=k*p;
					Thread.sleep(100);
				}
				System.out.println("end : " + Thread.currentThread().getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public synchronized void removeFirst() {
		if (names.size() > 0) {
			System.out.println("Remove : " + (String) names.remove(0));
		} else {
			System.out.println("Not Found");
		}
	}
}
