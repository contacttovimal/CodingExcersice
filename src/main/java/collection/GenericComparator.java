package collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


class Student{
	String name;
	int rollnum;
	public Student(String name,int rollno){
		this.name= name;
		this.rollnum = rollno;
	}
	public String getName(){
		return name;
	}
	public int getRollNum(){
		return rollnum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name.toString();
	}
}

public class GenericComparator<T> implements Comparator<T> {

	private String property;

	public GenericComparator(String property) {
		this.property = property;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int compare(T o1, T o2) {
		Comparable o1Comparable = getComparable(o1);
		Comparable o2Comparable = getComparable(o2);
		return o1Comparable.compareTo(o2Comparable);
	}

	@SuppressWarnings("rawtypes")
	private Comparable<T> getComparable(Object o) {
		try {
			Object invoke = o.getClass().getMethod(property, new Class[] {})
					.invoke(o, new Class[] {});
			return ((Comparable)invoke);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		Student s1 = new Student("david",3);

		Student s2 = new Student("adam",5);

		List<Student> list = new ArrayList<Student>();

		list.add(s1);

		list.add(s2);

		System.out.println(list);

		java.util.Collections.sort(list, new GenericComparator<Student>("getRollNum"));
		System.out.println("After sort :" + list);
	}

}