package collection;

import java.util.HashSet;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class SubMapTest {
	public static void main(String[] args) {
		TreeSet<String> treeSet = new TreeSet<String>();
		System.out.println(treeSet.add("A"));
		System.out.println(treeSet.add("B"));
		System.out.println(treeSet.add("A"));
		HashSet<String> hashSet= new HashSet<String>();
		System.out.println(hashSet.add("A"));
		System.out.println(hashSet.add("B"));
		System.out.println(hashSet.add("A"));
		
		TreeMap<String, String> map = new TreeMap<String, String>();
	    map.put("a", "ant"); map.put("d", "dog"); map.put("h", "horse");
	    SortedMap<String, String> submap;
	    SortedMap<String,String>headmap= map.headMap("d",true);
	    submap = map.subMap("b", "g");              // #1 create a backed collection
	    System.out.println(map + " " + submap + " " + headmap);     // #2 show contents
	    map.put("b", "bat");                        // #3 add to original
	    submap.put("f", "fish");                    // #4 add to copy
	    map.put("r", "raccoon");                    // #5 add to original - out of range
	    headmap.put("c", "cat");
	    //submap.put("p", "pig");                  // #6 add to copy - out of range
	    System.out.println(map + " " + submap + " " + headmap);     // #7 show final contents
	}
}
