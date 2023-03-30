import java.util.*;

public class driver {
	public static void main(String[] args) {
		LDLinkedList<Integer>	ownList = new LDLinkedList<Integer>();
		LinkedList<Integer>	deneme = new LinkedList<Integer>();


		ownList.add(15);
		ownList.add(32);
		ownList.add(11);
		ownList.add(156);
		ownList.add(134);

		System.out.println("olabilir");
		System.out.println(ownList.get(0));
		System.out.println(ownList.get(1));
		System.out.println(ownList.get(2));
		System.out.println(ownList.get(3));
		System.out.println(ownList.get(4));
		System.out.println("----------------------");
		System.out.print(ownList.contains(15));

		System.out.println("-------------------------");

		Iterator<Integer>	iter = ownList.descendingIterator();

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
