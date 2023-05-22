public class HashTable<K extends Comparable<? super K>, V> implements KWHashMap<K, V> {

	/**
	 * Entry class for each bst node
	 */
	private class	Entry<K extends Comparable<? super K>, V> {
		/* not final. Cause of removing method */
		private K	key;
		private V	value;
		private Entry<K, V>	left = null;
		private Entry<K, V>	right = null;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public Entry() {
			this.key = null;
			this.value = null;
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
	private double	loadFactor = 0;

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
	 * O(n)
	 * @param key
	 * @return
	 */
	private int	hash(K key) {
		int	hash1, hash2, probe;
		
		hash1 = key.hashCode() % this.capacity;
		hash2 = this.primeNumber - (key.hashCode() % this.primeNumber);

		for (int i = 0; i < this.capacity; ++i) {
			probe = (hash1 + (i * hash2)) % this.capacity;
			if (this.table[probe] == null ||
				this.table[probe].key.equals(key))
				return (probe);
		}

		throw	new RuntimeException("Table is full.");
	}

	/**
	 * Calculates largest prime number smallest than (threshold * tableSize)
	 * O(n)
	 * @return Largest Prime Number smallest than threshold number
	 */
	private int	largestPrime() {
		int	thresholdNum = (int)(this.THRESHOLD * this.capacity);

		for (int i = thresholdNum; i >= 2; --i)
			if (isPrime(i))
				return (i);

		return (2);
	}

	/**
	 * Is the given number prime or not
	 * O(n)
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
	 * O(1) -> hashing (most common)
	 * O(n) -> worst case caused by bst
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
	 * O(1) -> hashing (most common)
	 * O(n) -> worst case caused by binary search
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
		int	hashRes = this.hash(key);

		if (this.loadFactor >= this.THRESHOLD)
			this.reallocate();

		if (this.table[hashRes] == null)
			this.table[hashRes] = new Entry<K,V>(key, value);
		else
			this.recInsertBstNode(this.table[hashRes], key, value);
		
		++this.tableSize;
		this.updateLoadFactor();

		return (value);
	}

	/**
	 * Upgrade load factor
	 * O(1)
	 */
	private void	updateLoadFactor() {
		this.loadFactor = this.tableSize / (double)this.capacity;
	}

	/**
	 * Reallocate table.
	 * Doubles the capacity
	 * O(n) -> to move elements
	 */
	@SuppressWarnings("unchecked")
	private void	reallocate() {
		Entry<K,V>[]	newTable = new Entry[this.capacity * 2];

		for (int i = 0; i < this.capacity; ++i)
			newTable[i] = this.table[i];

		this.table = newTable;
		this.capacity *= 2;
		this.updateLoadFactor();
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
		else
			node.value = value;
	}

	/**
	 * Remove key object from structer.
	 * O(1) -> best and common case
	 * O(logn) -> binary search
	 * @param key
	 * @return value of removed element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {
		K	rKey = (K) key;
		int	keyHash = this.hash(rKey);
		Entry<K, V>	ret = new Entry<K,V>(rKey, null);

		this.table[keyHash] = this.recRemove(this.table[keyHash], rKey, ret);

		--this.tableSize;
		this.updateLoadFactor();
		return (ret.value);
	}

	/**
	 * Recursive bst removing algorithm
	 * O(logn) -> search
	 * @param node
	 * @param key
	 * @param retRef
	 * @return node
	 */
	private Entry<K, V>	recRemove(Entry<K, V> node, K key, Entry<K, V> retRef) {
		if (node == null)
			return (null);

		else if (key.compareTo(node.key) > 0)
			return (recRemove(node.right, key, retRef));
		else if (key.compareTo(node.key) < 0)
			return (recRemove(node.right, key, retRef));

		else {
			retRef.value = node.value;

			if (node.right == null)
				return (node.left);
			else if (node.left == null)
				return (node.right);
			else {
				Entry<K,V>	buff = this.minNode(node.right);

				node.key = buff.key;
				node.value = buff.value;

				node.right = this.recRemove(node.right, node.key, new Entry<K,V>());
			}
		}
		return (node);
	}

	/**
	 * @return node which includes minimum value of this subtree
	 */
	private Entry<K, V>	minNode(Entry<K, V> node) {
		while (node != null) {
			if (node.left == null)
				return (node);
			node = node.left;
		}
		return (null);
	}

	/**
	 * @return size of the table
	 */
	@Override
	public int size() {
		return (this.tableSize);
	}
	
	/**
	 * Make a diagram of bst hashMap
	 * O(n) -> to visit all nodes
	 */
	@Override
	public String	toString() {
		StringBuilder	sb = new StringBuilder();

		for (int i = 0; i < this.capacity; ++i) {
			sb.append("Index: " + i);
			diagramBuilder(this.table[i], sb, 0);
		}

		return (sb.toString());
	}

	/**
	 * Diagram builder for each bst.
	 * O(n) -> to visit all nodes
	 * @param node
	 * @param sb
	 */
	private void	diagramBuilder(Entry<K,V> node, StringBuilder sb, int depth) {
		for (int i = 0; i < depth; ++i)
			sb.append('\t');
		
		if (node != null) {
			sb.append("-> " + node.key + ", " + node.value + "\n");
			if (!(node.right == null && node.left == null)) {
				this.diagramBuilder(node.left, sb, depth + 1);
				this.diagramBuilder(node.right, sb, depth + 1);
			}
		}
		else
			sb.append("-> null\n");
	}
}
