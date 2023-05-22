import java.util.Arrays;
import java.util.Random;

public class driver {
	public static void main(String[] args) {
		long	nanotime = System.nanoTime();

		testHashMap();

		testHashCoal();

		System.out.println("Compilation time: " + ((System.nanoTime() - nanotime) / 1000000.0) + " ms");

		Integer[] small, medium, large, sc, mc, lc, sn, mn, ln;
		small = generateArr(100);
		medium = generateArr(1000);
		large = generateArr(10000);
		sc = Arrays.copyOf(small, small.length);
		sn = Arrays.copyOf(small, small.length);
		mc = Arrays.copyOf(medium, medium.length);
		mn = Arrays.copyOf(medium, medium.length);
		lc = Arrays.copyOf(large, large.length);
		ln = Arrays.copyOf(large, large.length);
		Sorting.<Integer>mergeSort(Integer.class, small);

		testMergeSort(small, medium, large);
		testQuickSort(sc, mc, lc);
		testNewSort(sn, mn);

		System.out.println("Small (Merge Sort): " + isSorted(small));
		System.out.println("Small (Quick Sort): " + isSorted(sc));
		System.out.println("Small (Quick Sort): " + isSorted(sn));
		System.out.println("Medium (Merge Sort): " + isSorted(medium));
		System.out.println("Medium (Quick Sort): " + isSorted(mc));
		System.out.println("Medium (Quick Sort): " + isSorted(mn));
		System.out.println("Large (Merge Sort): " + isSorted(large));
		System.out.println("Large (Quick Sort): " + isSorted(lc));

	}

	public static boolean	isSorted(Integer[] arr) {
		for (int i = 0; i + 1 < arr.length; ++i)
			if (arr[i].compareTo(arr[i + 1]) > 0)
				return (false);
		return (true);
	}

	public static void	printArr(Integer[] arr) {
		System.out.print('[');
		for (int i = 0; i < arr.length; ++i)
			System.out.print(arr[i] + ", ");
		System.out.println(']');
	}

	public static Integer[]	generateArr(int size) {
		Integer[]	arr = new Integer[size];
		Random	rand = new Random();

		for (int i = 0; i < size; ++i)
			arr[i] = rand.nextInt(size);

		return (arr);
	}

	public static void	testMergeSort(Integer[] small, Integer[] medium, Integer[] large) {
		long	nanoTime = System.nanoTime();

		Sorting.<Integer>mergeSort(Integer.class, small);

		System.out.println("Merge Sort Compilation time (small): " + ((System.nanoTime() - nanoTime) / 1000000.0) + " ms");
		nanoTime = System.nanoTime();

		Sorting.<Integer>mergeSort(Integer.class, medium);

		System.out.println("Merge Sort Compilation time (medium): " + ((System.nanoTime() - nanoTime) / 1000000.0) + " ms");
		nanoTime = System.nanoTime();

		Sorting.<Integer>mergeSort(Integer.class, large);

		System.out.println("Merge Sort Compilation time (large): " + ((System.nanoTime() - nanoTime) / 1000000.0) + " ms");
	}

	public static void	testQuickSort(Integer[] small, Integer[] medium, Integer[] large) {
		long	nanoTime = System.nanoTime();

		Sorting.<Integer>quickSort(small);

		System.out.println("Quick Sort Compilation time (small): " + ((System.nanoTime() - nanoTime) / 1000000.0) + " ms");
		nanoTime = System.nanoTime();

		Sorting.<Integer>quickSort(medium);

		System.out.println("Quick Sort Compilation time (medium): " + ((System.nanoTime() - nanoTime) / 1000000.0) + " ms");
		nanoTime = System.nanoTime();

		Sorting.<Integer>quickSort(large);

		System.out.println("Quick Sort Compilation time (large): " + ((System.nanoTime() - nanoTime) / 1000000.0) + " ms");
	}

	public static void	testNewSort(Integer[] small, Integer[] medium) {
		long	nanoTime = System.nanoTime();

		Sorting.<Integer>newSort(small);

		System.out.println("New Sort Compilation time (small): " + ((System.nanoTime() - nanoTime) / 1000000.0) + " ms");
		nanoTime = System.nanoTime();

		Sorting.<Integer>newSort(medium);

		System.out.println("New Sort Compilation time (medium): " + ((System.nanoTime() - nanoTime) / 1000000.0) + " ms");
		nanoTime = System.nanoTime();
	}

	public static void	testHashCoal() {
		System.out.println("*************HASHCOAL TEST FUNCTION**************");

		HashCoal<Integer, Integer>	hashCoal = new HashCoal<>();

		hashCoal.put(3,5);
		hashCoal.put(12,5);
		hashCoal.put(13,8);
		hashCoal.put(25,19);
		hashCoal.put(23,31);
		hashCoal.put(51,29);
		hashCoal.put(51,98);

		System.out.println(hashCoal);
		System.out.println("remove-> " + hashCoal.remove(13));
		System.out.println(hashCoal.remove(3));

		System.out.println("value-> " + hashCoal.get(23));
		System.out.println("value-> " + hashCoal.get(1));
		System.out.println("value-> " + hashCoal.get(51));
		System.out.println();

		System.out.println(hashCoal);

		HashCoal<Integer, Integer>	hashTable = new HashCoal<>();
	
		hashTable.put(15, 23);
		hashTable.put(10, 3);
		hashTable.put(3, 23);
		// hashTable.put(-5, 8);
		hashTable.put(81, 9);
	
		System.out.println(hashTable);
	
		KWHashMap<Integer, String> map = new HashCoal<>();
	
		// Insert some key-value pairs
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");
	
		// Print the size and contents of the map
		System.out.println("Size: " + map.size());
		System.out.println("Contents: " + map);
	
		// Retrieve a value by its key
		String value = map.get(3);
		System.out.println("Value of key 3: " + value);
	
		// Remove a key-value pair
		String removed = map.remove(4);
		System.out.println("Removed: " + removed);
	
		// Print the size and contents of the map again
		System.out.println("Size: " + map.size());
		System.out.println("Contents: " + map);
		System.out.println("*************END**************");
	}
	
	/* to test HashMap class */
	public static void	testHashMap() {
		System.out.println("*************HASHTABLE TEST FUNCTION**************");
		HashTable<Integer, Integer>	hashTable = new HashTable<>();
	
		hashTable.put(15, 23);
		hashTable.put(10, 3);
		hashTable.put(3, 23);
		// hashTable.put(-5, 8);
		hashTable.put(81, 9);
	
		System.out.println(hashTable);
	
		KWHashMap<Integer, String> map = new HashTable<>();
	
		// Insert some key-value pairs
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");
	
		// Print the size and contents of the map
		System.out.println("Size: " + map.size());
		System.out.println("Contents: " + map);
	
		// Retrieve a value by its key
		String value = map.get(3);
		System.out.println("Value of key 3: " + value);
	
		// Remove a key-value pair
		String removed = map.remove(4);
		System.out.println("Removed: " + removed);
	
		// Print the size and contents of the map again
		System.out.println("Size: " + map.size());
		System.out.println("Contents: " + map);
		System.out.println("*************END**************");

	}
}
