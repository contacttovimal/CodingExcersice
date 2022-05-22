package collection;

import java.util.*;

public class HashMapSortOnValues {
	public static void main(String[] args) {
		MyHashMap map1 = new MyHashMap();
		map1.put("A", 0);
		map1.put("B", 1);
		map1.put("C", 0);
		map1.put("D", 1);
		map1.put("E", 0);
		
		Iterator<java.util.Map.Entry<String,Integer>> itr= map1.entrySet().iterator();
		while(itr.hasNext()){
			java.util.Map.Entry<String,Integer> entry = itr.next();
			System.out.println(entry.getKey()  + " : " + entry.getValue());
		}
	}
}

class MyHashMap extends HashMap<String, Integer> {
	@Override
	public Set<Entry<String, Integer>> entrySet() {
		// TODO Auto-generated method stub

		List<Entry<String,Integer>> dataList = new ArrayList<Entry<String,Integer>>();
		Iterator<Entry<String,Integer>> it = super.entrySet().iterator();
		while(it.hasNext()){
			dataList.add(it.next());
		}
		SortComparator srt = new SortComparator();
		Collections.sort(dataList, srt);
		Set<Entry<String, Integer>> ownSet = new LinkedHashSet<Entry<String,Integer>>();
		Iterator<Entry<String,Integer>> it1 = dataList.iterator();
		while(it1.hasNext()){
			ownSet.add(it1.next());
		}

		return ownSet;
	}

	/*
	 * class SortComparator implements Comparator<java.util.Map.Entry<String,
	 * Integer>>{
	 *
	 * @Override public int compare(java.util.Map.Entry<String, Integer> o1,
	 * java.util.Map.Entry<String, Integer> o2) {
	 * if(((java.util.Map.Entry<String, Integer>)o1).getValue() >
	 * ((java.util.Map.Entry<String, Integer>)o2).getValue()){ return 1; }else{
	 * return 0; } } }
	 */
	class SortComparator<T> implements
			Comparator<Entry<String, Integer>> {
		@Override
		public int compare(Entry<String, Integer> o1,
                           Entry<String, Integer> o2) {
			if (((Entry<String, Integer>) o1).getValue() > ((Entry<String, Integer>) o2)
					.getValue()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
