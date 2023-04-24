import java.lang.reflect.Array;

/**
 * BinarySearchArray implementation. It must be takes a comparable class.
 * Each element holds a unique key.
 * This class is serializable. And deserializable for Integer datas.
 * 
 * Search: O(logn)
 * Insertion: O(logn)
 * Deletion: O(logn)
 * Serialization: O(n)
 */
public class BinarySearchArray<E extends Comparable<? super E>> extends BinarySearchTree<E>{
	/* stored array */
	private E[]	arr;
	/* instant capacity */
	private int	capacity = 1;

	/**
	 * Constructor using capacity
	 * O(1)
	 * @param instance generic instance
	 * @param capacity
	 * @throws Exception: newInstance method
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchArray(Class<? extends E> instance, int capacity) throws Exception{
		if (capacity == 0)
			throw new Exception("Memory Exception");
		this.arr = (E[])Array.newInstance(instance, capacity);
		this.capacity = capacity;
	}

	/**
	 * Constructor using root's element and array's initial capacity
	 * O(1)
	 * @param instance generic instance
	 * @param element root's element
	 * @param capacity
	 * @throws Exception newInstance method
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchArray(Class<? extends E> instance, E element, int capacity) throws Exception{
		if (capacity == 0)
			throw new Exception("Memory Exception");
		this.arr = (E[])Array.newInstance(instance, capacity);
		this.capacity = capacity;
		this.arr[0] = element;
	}

	/**
	 * Constructor using an array
	 * O(n)
	 * @param instance generic instance
	 * @param element root's element
	 * @param capacity
	 * @throws Exception newInstance method
	 */
	@SuppressWarnings("unchecked")
	private BinarySearchArray(Class<? extends E> instance, E[] arr) throws Exception{
		if (arr.length == 0)
			throw new Exception("Memory Exception");
		this.arr = (E[])Array.newInstance(instance, arr.length);
		for (int i = 0; i < arr.length; ++i)
			this.arr[i] = arr[i];
		this.capacity = arr.length;
	}

	/**
	 * Inserting an element using a recursive function
	 * O(logn) -> recursive function
	 * @param element
	 * @throws TreeInsertionException
	 */
	public void	insert(E object) throws TreeInsertionException {
		insertRec(0, object);
	}

	/**
	 * Inserting an element using recursion.
	 * O(logn) -> searching this specified element
	 * @param index
	 * @param object
	 * @throws TreeInsertionException
	 */
	private void	insertRec(int index, E object) throws TreeInsertionException {
		if (this.arr[index] == null) {
			this.arr[index] = object;
			return ;
		}
		else if (object.compareTo(this.arr[index]) > 0) {
			if (2*index + 2 > capacity)
				realloc();
			insertRec(2*index + 2, object);
		}
		else if (object.compareTo(this.arr[index]) < 0) {
			if (2*index + 1 > capacity)
				realloc();
			insertRec(2*index + 1, object);
		}
		else
			throw new TreeInsertionException();
	}

	/**
	 * reallocate tree array.
	 * In the form of exponents of two
	 */
	@SuppressWarnings("unchecked")
	private void	realloc() {
		capacity *= 2;
		E[]	newArr = (E[])Array.newInstance(this.arr.getClass().getComponentType(), capacity);
		
		for (int i = 0; i < this.arr.length; ++i)
			newArr[i] = this.arr[i];

		this.arr = newArr;
	}

	/**
	 * Binary search array diagram.
	 * O(n) -> recursive method
	 */
	@Override
	public String toString() {
		StringBuilder	sb = new StringBuilder();
		diagramBuilder(0, sb, 0);
		return (sb.toString());
	}

	/**
	 * This recursive method makes a binary tree diagram using a StringBuilder.
	 * O(n) -> it visits akk of the elements
	 * @param index
	 * @param sb
	 * @param depth
	 */
	private void	diagramBuilder(int index, StringBuilder sb, int depth) {
		for (int i = 0; i < depth; ++i)
			sb.append('\t');
		
		if (this.arr[index] != null) {
			sb.append("-> " + this.arr[index] + "\n");
			if (2*index + 2 < capacity && (this.arr[2*index + 2] != null || this.arr[2*index + 1] != null)) {
				diagramBuilder(2*index + 1, sb, depth + 1);
				diagramBuilder(2*index + 2, sb, depth + 1);
			}
		}
		else
			sb.append("-> null\n");
	}

	/**
	 * It checks there is or not an element
	 * O(logn) -> recursive method
	 * @param element
	 * @return
	 */
	public boolean	contains(E element) {
		return (recContains(0, element));
	}

	/**
	 * This function checks the array includes given element or not.
	 * O(logn) -> search
	 */
	private boolean	recContains(int index, E element) {
		if (index > capacity)
			return (false);
			
		if (this.arr[index] == null)
			return (false);

		if (element.compareTo(this.arr[index]) > 0)
			return (recContains(2*index + 2, element));

		if (element.compareTo(this.arr[index]) < 0)
			return (recContains(2*index + 1, element));

		return (true);
	}

	/**
	 * Delete this element from the array.
	 * It delete likes binary search tree.
	 * O(logn) -> recursive function.
	 * @param element
	 */
	public void	delete(E element) {
		deleteRec(0, element);
	}

	/**
	 * Recursive deletion method
	 * O(logn) -> search for element
	 * @param index
	 * @param element
	 */
	private void	deleteRec(int index, E element) {
		if (this.arr[index] == null)
			return ;

		if (2*index + 2 > capacity)
			return ;

		if (element.compareTo(this.arr[index]) > 0)
			deleteRec(2*index + 2, element);
		else if (element.compareTo(this.arr[index]) < 0)
			deleteRec(2*index + 1, element);
		else {
			if (this.arr[2*index + 1] == null) {
				this.arr[index] = this.arr[2*index + 2];
				/* if the capacity is exceeded */
				if (2*(2*index + 2) + 2 > capacity)
					this.arr[2*index + 2] = null;
				else
					deleteRec(2*index + 2, this.arr[index]);
			}
			else if (this.arr[2*index + 2] == null) {
				this.arr[index] = this.arr[2*index + 1];
				deleteRec(2*index + 1, this.arr[index]);
			}
			else {
				this.arr[index] = minValue(2*index + 2);
				deleteRec(2*index + 2, this.arr[index]);
			}
		}
	}

	/**
	 * Get min value of the specified subtree determined by index
	 * O(logn) -> search algorithm
	 * @param index
	 * @return
	 */
	private E	minValue(int index) {
		if (this.arr[index] == null)
			return (null);
		else if (this.arr[2*index + 1] == null)
			return (this.arr[index]);
		return (minValue(2*index + 1));
	}

	/**
	 * Serialize bst with pre-order traversal.
	 * Null marker: #
	 * O(n) -> visit all nodes
	 * @return serialize string
	 */
	public String	serialize() {
		StringBuilder	sb = new StringBuilder();
		for (int i = 0; i < this.capacity; ++i) {
			if (this.arr[i] == null)
				sb.append("# ");
			else
				sb.append(this.arr[i] + " ");
		}
		return (sb.toString());
	}

	/**
	 * Deserialize given string to the Integer typed bsa.
	 * @param serialized
	 * @return
	 */
	public static BinarySearchArray<Integer>	deserialize(String serialized) {
		String[]	serArr = serialized.split(" ");
		int	capacity = serArr.length;
		Integer[]	arr = new Integer[capacity];
		
		for (int i = 0; i < capacity; ++i)
			if (serArr[i] != "#")
				arr[i] = Integer.parseInt(serArr[i]);

		try {
			return (new BinarySearchArray<Integer>(Integer.class, arr));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (null);
	}
}
