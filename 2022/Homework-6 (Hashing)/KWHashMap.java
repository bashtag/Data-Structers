/**
 * V is comparable cause of binary search tree
 */
public interface	KWHashMap<K extends Comparable<? super K>, V> {

	/**
	 * 
	 * @param key
	 * @return the value associated with the specified key.
	 * Returns null if the key is not present
	 */
	V	get(Object key);

	/**
	 * @return true if this table contains no key‐value mappings
	 */
	boolean	isEmpty();

	/**
	 * Associates the specified value with the specified key.
	 * @param key
	 * @param value
	 * @return the previous value associated with the specified key, or null if there was no mapping for the key.
	 */
	V	put(K key, V value);

	/**
	 * Removes the mapping for this key from this table if it is present (optional operation).
	 * @param key
	 * @return the previous value associated with the specified key, or null if there was no mapping
	 */
	V	remove(Object key);

	/*
	 * Return size of the table
	 */
	int	size();

	public class Entry<K extends Comparable<? extends K>, V> {
		private final K	key;
		private V	value;

		/**
		 * Constructs an Entry with the given values
		 * @param key
		 * @param value
		 */
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * Retrieves Key
		 * @return Key
		 */
		public K	getKey() {
			return (this.key);
		}

		/**
		 * Retrieves Value
		 * @return Value
		 */
		public V	getValue() {
			return (this.value);
		}

		/**
		 * Sets the value
		 * @param val
		 */
		public void	setValue(V val) {
			this.value = val;
		}
	}
}
