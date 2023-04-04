import java.util.*;

public class driver {
	public static void main(String[] args) {
		LDLinkedList<Integer>	ownList = new LDLinkedList<Integer>();
		ownList.add(15);
		ownList.add(32);
		ownList.add(11);
		ownList.add(156);
		ownList.add(32);
		ownList.add(134);
		ownList.set(5, 128);
		ownList.add(3, 42);

		Object[]	oArray = ownList.toArray();
		Integer[]	iArray = ownList.toArray(new Integer[0]);
		ownList.remove(Integer.valueOf(42));

		System.out.println("olabilir");
		System.out.println(ownList.get(0));
		System.out.println(ownList.get(1));
		System.out.println(ownList.get(2));
		System.out.println(ownList.get(3));
		System.out.println(ownList.get(4));
		System.out.println(ownList.get(5));
		System.out.println("----------------------");
		System.out.println(ownList.contains(31));
		System.out.println(ownList.indexOf(31));
		System.out.println(ownList.indexOf(32));
		System.out.println(ownList.lastIndexOf(32));

		System.out.println("-------------------------");

		ownList.removeFirst();
		// ownList.removeLast();
		ownList.add(69);

		Iterator<Integer>	iter = ownList.descendingIterator();

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}

		System.out.println("---------------------------------------");

		for (Object o : oArray) {
			System.out.print(o + ", ");
		}
		System.out.println();

		for (Integer he : iArray) {
			System.out.print(he + ", ");
		}
		System.out.println();
	}
}
