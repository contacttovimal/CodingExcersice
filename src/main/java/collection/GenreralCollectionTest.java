package collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GenreralCollectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<ListObject> a = new ArrayList<ListObject>();
		HashSet<ListObject> s = new HashSet<ListObject>();
		ListObject listObject1 = new ListObject("AAA");
		a.add(listObject1);
		s.add(listObject1);
		System.out.println("List 1 : "  + a.contains(new ListObject("AAA")));
		System.out.println("List 2 : "  + a.contains(listObject1));
		System.out.println("SET  1 : "  + s.contains(new ListObject("AAA")));
		System.out.println("SET  2 : "  + s.contains(listObject1));

	}

}

class ListObject{
	String name;
	public ListObject(String name){
		this.name=name;
	}
@Override
	public boolean equals(Object obj) {
	boolean flag = false;
		if(obj != null && obj instanceof ListObject){
			if(((ListObject)obj).name.equals(this.name)){
				return true;
			}
		}
		return flag;
	}
   @Override
	public int hashCode() {
		return 1;
	}
};
