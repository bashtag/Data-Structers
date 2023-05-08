public class driver {
	public static void main(String[] args) {
		long	nanotime = System.nanoTime();

		// testHashMap();

		testHashCoal();

		System.out.println("Compilation time: " + ((System.nanoTime() - nanotime) / 1000000.0) + " ms");
	}

	public static void	testHashCoal() {
		HashCoal<Integer, Integer>	hashCoal = new HashCoal<>();

		hashCoal.put(3,5);
		hashCoal.put(12,5);
		hashCoal.put(13,8);
		hashCoal.put(25,19);
		hashCoal.put(23,31);
		hashCoal.put(51,29);

		System.out.println("value-> " + hashCoal.get(25));
		System.out.println("value-> " + hashCoal.get(1));
	}
	
	/* to test HashMap class */
	public static void	testHashMap() {
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
	}
}
