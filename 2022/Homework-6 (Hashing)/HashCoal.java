public class HashCoal<K extends Comparable<? super K>, V> implements KWHashMap<K, V> {
	
	/**
	 * static class to store Node structer
	 */
	private static class	Node<K extends Comparable<? super K>, V> {
		private K	key;
		private V	value;
		/* for coalesced hashing */
		private int	nextIndex = -1;
		
		private Node() {
			this.key = null;
			this.value = null;
		}

		private Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

	}

	/**
	 * On java, we must use static keyword while creating macros.
	 * Because static things are stored before any object.
	 */
	private static final double	DEFAULT_THRESHOLD = 0.8;
	private static final int	DEFAULT_CAPACITY = 10;

	private Node<K,V>[]	table;
	private final double	THRESHOLD;
	private double	loadFactor = 0.0;
	private int	tableSize = 0;
	private int	capacity;
	private int	primeNumber;

	/**
	 * Default constructor
	 */
	public	HashCoal() {
		this(DEFAULT_CAPACITY, DEFAULT_THRESHOLD);
	}

	/**
	 * constructor using capacity
	 * @param capacity
	 */
	public	HashCoal(int capacity) {
		this(capacity, DEFAULT_THRESHOLD);
	}

	/**
	 * Constructor using capacity and a threshold value.
	 * All possible errors are checked.
	 * @param capacity
	 * @param threshold
	 */
	@SuppressWarnings("unchecked")
	public	HashCoal(int capacity, double threshold) {
		if (capacity <= 0 || threshold <= 0.0 || threshold >= 1.0 || Double.isNaN(threshold))
			throw	new IllegalArgumentException();

		this.capacity = capacity;
		this.THRESHOLD = threshold;
		this.table = new Node[this.capacity];
		this.primeNumber = this.largestPrime();
	}

	/**
	 * Hash function for hashing technique that is a combination of the double hashing
	 * O(1)
	 * @param key
	 * @return
	 */
	private int	hash(K key, int probe) {
		int	hash1, hash2;
		
		hash1 = key.hashCode() % this.capacity;
		hash2 = this.primeNumber - (key.hashCode() % this.primeNumber);

		return ((hash1 + (probe * hash2)) % this.capacity);
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
	 * Get the Value of the Key
	 * @return Value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		int	hashKey;
		K	kKey = (K) key;

		hashKey = this.hash(kKey, 0);
		for (int i = 0; i < this.capacity; ++i) {
			if (this.table[hashKey] == null)
				return (null);
			else if (this.table[hashKey].key == kKey)
				return (this.table[hashKey].value);
			else if (this.table[hashKey].nextIndex != -1)
				hashKey = this.table[hashKey].nextIndex;
			else
				return (null);
		}

		return (null);
	}

	/**
	 * @return true if the map is empty or false if not empty
	 */
	@Override
	public boolean isEmpty() {
		return (this.tableSize == 0);
	}

	/**
	 * Puts V value using K and hash
	 * @param key
	 * @param value
	 * @return V value
	 */
	@Override
	public V put(K key, V value) {
		int	hashKey;

		if (this.loadFactor >= this.THRESHOLD)
			this.reallocate();

		hashKey = this.hash(key, 0);
		for (int i = 0; i < this.capacity;) {
			if (this.table[hashKey] != null) {
				if (this.table[hashKey].key == key) {
					this.table[hashKey].value = value;
					break;
				}
				else if (this.table[hashKey].nextIndex == -1) {
					this.table[hashKey].nextIndex = this.hash(key, ++i);
					hashKey = this.table[hashKey].nextIndex;
				}
				else
					hashKey = this.table[hashKey].nextIndex;
			}
			else {
				this.table[hashKey] = new Node<K,V>(key, value);
				break;
			}
		}

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
		Node<K,V>[]	newTable = new Node[this.capacity * 2];

		for (int i = 0; i < this.capacity; ++i)
			newTable[i] = this.table[i];

		this.table = newTable;
		this.capacity *= 2;
		this.updateLoadFactor();
	}

	/**
	 * Remove function for hashmap
	 * Used a recursive function
	 * the worst case O(n^2) caused by shifting
	 * the best case O(1)
	 * @param key : key of the element to be deleted
	 * @return Value of removing element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {
		K	kKey = (K) key;
		return (recRemove(kKey, this.hash(kKey, 0), 0));
	}

	/**
	 * Recursive removing method
	 * @param key : key of the element to be deleted
	 * @param hashKey : result of the 0 probe hash method
	 * @return Value of removing element
	 */
	private V	recRemove(K key, int hashKey, int probe) {

		for (int i = 0; i < this.capacity; ++i) {
			if (this.table[hashKey] == null)
				return (null);
			else if (this.table[hashKey].key == key) {
				V	retV = this.table[hashKey].value;
				if (this.table[hashKey].nextIndex == -1) {
					this.table[hashKey] = null;
				}
				else {
					int nextHash = this.table[hashKey].nextIndex;
					this.table[hashKey] = this.table[nextHash];
					recRemove(this.table[nextHash].key, nextHash, probe + 1);
				}
				return (retV);
			}
			else if (this.table[hashKey].nextIndex == -1)
				return (null);
			else
				hashKey = this.table[hashKey].nextIndex;
		}

		return (null);
	}

	/**
	 * @return size of the map
	 */
	@Override
	public int size() {
		return (this.tableSize);
	}

	/**
	 * to print hashmap
	 * @return string converted from a StringBuilder
	 */
	@Override
	public String	toString() {
		StringBuilder	sb = new StringBuilder();
		
		sb.append("HashValue\tKey\tNext\n");
		for (int i = 0; i < this.capacity; ++i) {
			if (this.table[i] != null)
				sb.append(i + "\t" + this.table[i].key + "\t" + this.table[i].nextIndex + "\n");
			else
				sb.append(i + "\n");
		}
		return (sb.toString());
	}
}
