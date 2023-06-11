import java.util.Iterator;
import java.util.Random;

/**
 * a Custom Skip List Class
 * Insertion: 
 * Deletion: 
 * Search: 
 */
public class	CustomSkipList<K extends Comparable<K>, V> implements Iterable<K> {
	private static final double	DEFAULT_PB = 0.5;
	private static final Random	random = new Random();

	private int	size = 0;
	private double	probability;
	private Node<K, V>	head;
	transient private boolean	addRes = true;

	/**
	 * static Node class for storing elements
	 */
	private static class	Node<K extends Comparable<K>, V> implements Comparable<K> {
		private K	key;
		private V	value;
		private Node<K, V>	next = null;
		private Node<K, V>	down = null;

		public	Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public	Node(K key, V value, Node<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		/**
		 * Compare method for comparable node class
		 */
		@Override
		public int	compareTo(K o) {
			return (this.key.compareTo(o));
		}
	}

	/**
	 * Constructor with a probability parameter
	 * @param probability to determine the height of the nodes
	 */
	public	CustomSkipList(double probability) {
		this.head = new Node<K, V>(null, null);
	}

	/**
	 * Default constructor
	 */
	public	CustomSkipList() {
		this(DEFAULT_PB);
	}

	public boolean	insert(K key, V value) {
		this.head = insertRec(this.head, key, value);
		boolean	res = this.addRes;
		this.addRes = true;
		return (res);
	}

	private Node<K, V>	insertRec(Node<K, V> node, K key, V value) {
		if (node == null)
			return (new Node<>(key, value));
		/* Node cannot be equal to key */
		else if (node.equals(key))
			this.addRes = false;
		/* to get down */
		else if (node.next != null && node.compareTo(key) < 0 && node.next.compareTo(key) > 0)
			node.down = insertRec(node.down, key, value);
		else if (node.next == null || )
			node.down = insertRec(node.down, key, value);

		return (node);
	}

	private Node<K, V>	search(K key) {
		return (recSearch(this.head, key));
	}

	private Node<K, V>	recSearch(Node<K, V> node, K key) {
		if (node == null)
			return (null);
		
		while (node.next != null && node.compareTo(key) < 0)
			node = node.next;
		
		if (node.equals(key))
			node = getBase(node);

		else if (node.down == null)
			return (node);

		return (recSearch(node.down, key));
	}

	private Node<K, V>	getBase(Node<K, V> node) {
		while (node.down != null)
			node = node.down;
		return (node);
	}

	@Override
	public Iterator<K>	iterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'iterator'");
	}
}