package careercup;

public class ReverseLinkedList {
	public static void main(String[] args) {
		
		ListNode node1 = new ListNode();
		node1.data="1";

		ListNode node2 = new ListNode();
		node2.data="2";

		ListNode node3 = new ListNode();
		node3.data="3";
		
		ListNode node4 = new ListNode();
		node4.data="4";
		
		node1.next=node2;
		node2.next=node3;
		node3.next = node4;
		node4.next = null;
		
		printNode(node1);
		ListNode revHeadNode = Reverse(node1);
		System.out.println("************");
		printNode(revHeadNode);
		
		getMethod(null);
		
		System.out.println(retVal());
		

	}
	public static void printNode(ListNode node){
		while(node.next != null){
			System.out.println(node.data  + " , ");
			node = node.next;
		}
		System.out.println(node.data  + " , ");
		getMethod(null);
	}
	public static ListNode Reverse(ListNode list)
	{
	    if (list == null) return null; // first question

	    if (list.next == null) return list; // second question

	    // third question - in Lisp this is easy, but we don't have cons
	    // so we grab the second element (which will be the last after we reverse it)

	    ListNode secondElem = list.next;

	    // bug fix - need to unlink list from the rest or you will get a cycle
	    list.next = null;

	    // then we reverse everything from the second element on
	    ListNode reverseRest = Reverse(secondElem);

	    // then we join the two lists
	    secondElem.next = list;

	    return reverseRest;
	}
	public static void getMethod(Object ob){
		System.out.println("obj");
	}
	public static void getMethod(String ob){
		System.out.println("string");
	}
	/*public static void getMethod(Integer ob){
		System.out.println("Integer");
	}*/
	
	public static int retVal(){
		int i =10;
		try{
			return i;
		}finally{
			i= 20;
		}
	}
}

class ListNode {
	public String data;
	public ListNode next;
}
