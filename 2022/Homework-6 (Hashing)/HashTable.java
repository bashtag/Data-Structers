public class HashTable<K extends Comparable<? super K>, V> implements KWHashMap<K, V> {

	/**
	 * Entry class for each bst node
	 */
	private class	Entry<K extends Comparable<? super K>, V> {
		private final K	key;
		private V	value;
		private Entry<K, V>	left = null;
		private Entry<K, V>	right = null;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private static final int	DEFAULT_CAPACITY = 10;
	private static final double	DEFAULT_THRESHOLD = 0.8;

	/* Hash Array */
	private Entry<K, V>[]	table;
	private int	tableSize = 0;
	private int	capacity;

	/* Load Factor treshold */
	private final double	THRESHOLD;
	
	/* Load factor for ratio of element num */
	private int	loadFactor = 0;

	/* prime number smaller than (loadFactor * tableSize) */
	private int	primeNumber;

	/**
	 * Default constructor
	 */
	public HashTable() {
		this(DEFAULT_CAPACITY, DEFAULT_THRESHOLD);
	}

	/**
	 * Constructor with capacity value
	 * @param capacity
	 */
	public HashTable(int capacity) {
		this(capacity, DEFAULT_THRESHOLD);
	}

	/**
	 * Constructs object using capacity and threshold values with error checking.
	 * @param capacity initial capacity of the table
	 * @param threshold threshold value for the table
	 */
	@SuppressWarnings("unchecked")
	public HashTable(int capacity, double threshold) {
		if (capacity <= 0 || threshold <= 0.0 || threshold >= 1.0 || Double.isNaN(threshold))
			throw	new IllegalArgumentException();
		this.THRESHOLD = threshold;
		this.capacity = capacity;
		this.primeNumber = this.largestPrime();
		this.table = new Entry[capacity];
	}

	/**
	 * Hash function for hashing technique that is a combination of the double hashing
	 * @param key
	 * @return
	 */
	private int	hash(K key) {
		int	hash1, hash2, probe;
		
		hash1 = key.hashCode() % this.tableSize;
		hash2 = this.primeNumber - (key.hashCode() % this.primeNumber);

		for (int i = 0; i < this.tableSize; ++i) {
			probe = (hash1 + (i * hash2)) % this.tableSize;
			if (this.table[probe] == null ||
				this.table[probe].key.equals(key))
				return (probe);
		}

		throw	new RuntimeException("Table is full.");
	}

	/**
	 * Calculates largest prime number smallest than (threshold * tableSize)
	 * @return Largest Prime Number smallest than threshold number
	 */
	private int	largestPrime() {
		int	thresholdNum = (int)(this.THRESHOLD * this.tableSize);

		for (int i = thresholdNum; i >= 2; --i)
			if (isPrime(i))
				return (i);

		return (2);
	}

	/**
	 * Is the given number prime or not
	 * @param num
	 * @return true if this number is prime
	 */
	private boolean	isPrime(int num) {
		for (int i = 2; i < num; ++i) {
			if (num % i == 0)
				return (false);
		}
		return (true);
	}

	/**
	 * Get the value of this key on the map.
	 * 
	 * @return V. But if it isn't exist return null.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		K	sKey = (K) key;
		return (this.recSearch(this.table[this.hash(sKey)], sKey));
	}

	/**
	 * Recursive search function for bst.
	 * @return V or null
	 */
	private V	recSearch(Entry<K, V> node, K key) {
		if (node == null)
			return (null);
		else if (key.compareTo(node.key) > 0)
			return (recSearch(node.right, key));
		else if (key.compareTo(node.key) < 0)
			return (recSearch(node.left, key));
		return (node.value);
	}

	/**
	 * @return if this table is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return (this.tableSize == 0);
	}

	/**
	 * Puts V using K value
	 * @return V
	 */
	@Override
	public V put(K key, V value) {
		this.recInsertBstNode(this.table[this.hash(key)], key, value);

		return (value);
	}

	/**
	 * recursive bst insertion algorithm
	 * O(logn) -> to search appropriate position
	 * @param node
	 * @param key
	 * @param value
	 */
	private void	recInsertBstNode(Entry<K,V> node, K key, V value) {
		if (node == null)
			return ;
		else if (key.compareTo(node.key) > 0) {
			if (node.right == null)
				node.right = new Entry<K, V>(key, value);
			else
				recInsertBstNode(node.right, key, value);
			}
		else if (key.compareTo(node.key) < 0) {
			if (node.left == null)
				node.left = new Entry<K, V>(key, value);
			else
				recInsertBstNode(node.left, key, value);
		}
		
		throw	new RuntimeException("This element is already on the map");
	}


	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {
		K	rKey = (K) key;
		int	keyHash = this.hash(rKey);
		Entry<K, V>	ret = new Entry<K,V>(rKey, null);

		this.table[keyHash] = recRemove(this.table[keyHash], rKey, ret);

		return (ret.value);
	}

	
	private Entry<K, V>	recRemove(Entry<K, V> node, K key, Entry<K, V> retRef) {
		return (null);
	}

	/**
	 * @return size of the table
	 */
	@Override
	public int size() {
		return (this.tableSize);
	}
	
}
