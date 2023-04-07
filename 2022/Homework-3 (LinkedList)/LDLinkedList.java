import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Deque;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Objects;

/**
 * A lazily deleted linked list implementation of the List interface.
 * I didn't use List super interface because it isn't necessary.
 * 
 * @author bashtag (btstsiy@hotmail.com)
 *
 * @param <T> The type of elements in this list.
 */
public class LDLinkedList<T> extends AbstractList<T> implements Deque<T> {

	/**
	 * It represents a single node of the linkedlist.
	 * This class keeps previous node.
	 * 
	 * @param <T> The type of elements in the list
	 */
	private static class	Node<T> {
		private T	data;
		private Node<T>	next = null;
		private Node<T>	prev = null;

		/**
		 * Data parameter constructor
		 * @param data
		 */
		private	Node(T data) {
			this.data = data;
		}

		/**
		 * Construct using reference node
		 * @param data
		 * @param ref
		 */
		private Node(T data, Node<T> ref) {
			this.data = data;
			this.next = ref;
		}
	}
	
	private Node<T>	head = null;
	private Node<T>	tail = null;

	/**
	 * Trash is a list of lazily deleted elements.
	 */
	private Node<T>	trash = null;

	private int	length = 0;

	/**
	 * Overload
	 */
	private Node<T> createNewNode(T element) {
		return (this.createNewNode(element, null));
	}

	/**
	 * Create new node using lazily deleted elements
	 * @param element
	 * @param ref
	 * @return A New Node
	 */
	private Node<T>	createNewNode(T element, Node<T> ref) {
		if (trash != null) {
			Node<T>	newNode = trash;
			newNode.data = element;
			newNode.next = ref;
			newNode.prev = null;
			trash = trash.next;
			return (newNode);
		} else {
			return (new Node<T>(element, ref));
		}
	}

	/**
	 * Add an element at the index.
	 * @param index
	 * @param element
	 */
	@Override
	public void add(int index, T element) {
		
		if (index > this.size() || index < 0)
			throw new IndexOutOfBoundsException();

		if (index == 0)
			this.addFirst(element);
		else if (index == this.size())
			this.addLast(element);
		else {
			Node<T>	node = getNode(index - 1);
			Node<T>	newNode = createNewNode(element, node.next);
	
			newNode.prev = node;
			node.next = newNode;
			length++;
		}
	}

	/**
	 * Add an element at the beginning of the list.
	 * @param element
	 */
	@Override
	public void	addFirst(T element) {
		head = createNewNode(element, head);
		length++;

		if (length == 1)
			tail = head;
	}

	/**
	 * Add an element at the end of the list.
	 * @param element
	 */
	@Override
	public void	addLast(T element) {
		tail.next = createNewNode(element);
		tail.next.prev = tail;
		tail = tail.next;
		length++;
	}

	/**
	 * @return the size of the list
	 */
	@Override
	public int	size() {
		return (this.length);
	}

	/**
	 * @return elemnt at the index
	 */
	@Override
	public T get(int index) {
		return (getNode(index).data);
	}

	/**
	 * @return node at the index
	 * @throws IndexOutOfBoundsException
	 * @param index
	 */
	public Node<T>	getNode(int index) {
		Node<T>	node = head;

		if (index >= this.length)
			throw new IndexOutOfBoundsException();

		for (int i = 0; i < index && node != null; ++i)
			node = node.next;

		return (node);
	}

	/**
	 * @return shallow copy of the list
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		LDLinkedList<T>	copy = new LDLinkedList<T>();
		Node<T>	node = head;

		copy.head = node;
		copy.length = this.length;

		while (node != null && node.next != null)
			node = node.next;

		copy.tail = node;

		return (copy);
	}

	/**
	 * Add an element at the beginning of the list.
	 * @param element
	 * @return true
	 */
	@Override
	public boolean offerFirst(T e) {
		this.addFirst(e);
		return (true);
	}

	/**
	 * Add an element at the end of the list.
	 * @param element
	 * @return true
	 */
	@Override
	public boolean offerLast(T e) {
		this.addLast(e);
		return (true);
	}

	/**
	 * Removing the beginning of the list using lazy deletion.
	 * It means u can use these removing elements future.
	 * 
	 * @return removed element
	 */
	@Override
	public T removeFirst() {
		Node<T>	buff = this.head;
		this.head = this.head.next;
		this.head.prev = null;
		this.length--;

		// Save at the beggining of the trash for future use.
		buff.next = this.trash;
		
		if (this.trash != null)
			this.trash.prev = buff;
		
		this.trash = buff;

		return (this.trash.data);
	}

	/**
	 * Removing the end of the list using lazy deletion.
	 * It means u can use these removing elements future.
	 * 
	 * @return removed element
	 */
	@Override
	public T removeLast() {
		Node<T>	buff = this.tail;
		this.tail = this.tail.prev;
		this.tail.next = null;
		this.length--;

		// Save the removing element
		buff.prev = null;
		buff.next = this.trash;

		if (this.trash != null)
			this.trash.prev = buff;

		this.trash = buff;

		return (this.trash.data);
	}

	/**
	 * Removing the beginning of the list.
	 * @return removed element
	 */
	@Override
	public T pollFirst() {
		return (this.removeFirst());
	}

	/**
	 * Removing the end of the list.
	 * @return removed element
	 */
	@Override
	public T pollLast() {
		return (this.removeLast());
	}

	/**
	 * @return the first element of the list
	 */
	@Override
	public T getFirst() {
		return (this.head.data);
	}

	/**
	 * @return the last element of the list
	 */
	@Override
	public T getLast() {
		return (this.tail.data);
	}

	/**
	 * this function is same as getFirst()
	 * @return the first element of the list
	 */
	@Override
	public T peekFirst() {
		return (this.getFirst());
	}

	/**
	 * this function is same as getLast()
	 * @return the last element of the list
	 */
	@Override
	public T peekLast() {
		return (this.getLast());
	}

	/**
	 * Remove the first occurrence of the specified element from this list, if it is present.
	 * @param element
	 * @return true if the removing is successful
	 */
	@Override
	public boolean removeFirstOccurrence(Object o) {
		int	index = indexOf(o);

		if (index != -1) {
			this.remove(index);
			return (true);
		}

		return (false);
	}

	/**
	 * Remove the last occurrence of the specified element from this list, if it is present.
	 * @param element
	 * @return true if the removing is successful
	 */
	@Override
	public boolean removeLastOccurrence(Object o) {
		int	index = lastIndexOf(o);

		if (index != -1) {
			this.remove(index);
			return (true);
		}

		return (false);
	}


	/**
	 * Add an element at the end of the list.
	 * This function is same as addLast()
	 * @param element
	 * @return true
	 */
	@Override
	public boolean offer(T e) {
		this.addLast(e);
		return (true);
	}

	/**
	 * Removing the beginning of the list.
	 * This function is same as removeFirst()
	 * @return removed element
	 */
	@Override
	public T remove() {
		return (this.removeFirst());
	}

	/**
	 * Removing the beginning of the list.
	 * This function is same as removeFirst()
	 * @return removed element
	 */
	@Override
	public T poll() {
		return (this.removeFirst());
	}

	/**
	 * this function is same as getFirst()
	 * @return the first element of the list
	 */
	@Override
	public T element() {
		return (this.getFirst());
	}

	/**
	 * this function is same as getFirst()
	 * @return the first element of the list
	 */
	@Override
	public T peek() {
		return (this.getFirst());
	}

	/**
	 * Add an element at the beginning of the list.
	 * This function is same as addFirst()
	 * @param element
	 */
	@Override
	public void push(T e) {
		this.addFirst(e);
	}

	/**
	 * Removing the beginning of the list.
	 * This function is same as removeFirst()
	 * @return removed element
	 */
	@Override
	public T pop() {
		return (this.removeFirst());
	}

	/**
	 * @return a new Descending Iterator
	 */
	@Override
	public Iterator<T> descendingIterator() {
		return (new DescendingIterator());
	}

	/**
	 * Descending Iterator class.
	 * It begins end of the linkedlist and ends begin of the linkedlist.
	 * So next function is also previous function of the ListIterator class.
	 * And hasNext function is also hasPrevious function of the ListIterator class.
	 * @param <T>	: type of the element
	 */
	private class DescendingIterator implements Iterator<T> {
		private final ListIterator<T>	iter = LDLinkedList.this.listIterator(length);
		@Override
		public boolean hasNext() {
			return (iter.hasPrevious());
		}

		@Override
		public T next() {
			return (iter.previous());
		}

		public void	remove() {
			iter.remove();
		}
	}

	/**
	 * Removing using lazy deletion.
	 * It means u can use these removing elements future.
	 * 
	 * @param index
	 * @return removed element
	 */
	@Override
	public T remove(int index) {

		if (index >= this.size() || index < 0)
			throw new IndexOutOfBoundsException();

		if (index == 0)
			return (this.removeFirst());
		else if (index == this.size() - 1)
			return (this.removeLast());
		else {
			Node<T>	buff = getNode(index);
			buff.prev.next = buff.next;
			buff.next.prev = buff.prev;

			// Saving removed element
			buff.prev = null;
			buff.next = this.trash;
			
			if (this.trash != null)
				this.trash.prev = buff;
			
			this.trash = buff;

			this.length--;

			return (this.trash.data);
		}
	}

	/**
	 * Removing the first occurrence of the specified element from this list, if it is present.
	 * This function is same as removeFirstOccurrence()
	 * @param element
	 * @return true if the removing is successful
	 */
	@Override
	public boolean remove(Object o) {
		return (this.removeFirstOccurrence(o));
	}

	/**
	 * Replaces element to the specified position.
	 * Ofcourse the element of the specified position is going to dissappear.
	 * @param index
	 * @param element
	 * @return previous element at the specified position
	 */
	@Override
	public T set(int index, T element) {

		if (index < 0 || index >= this.length)
			throw new IndexOutOfBoundsException();

		Node<T>	node = this.getNode(index);
		T	retVal = node.data;
		node.data = element;
		return (retVal);
	}

	/**
	 * Convert the linkedList to a conventional java array
	 * 
	 * @return Object array
	 */
	@Override
	public Object[] toArray() {
		Object[]	newArray = new Object[this.length];
		Node<T>	node = head;

		for (int i = 0; node != null; i++) {
			newArray[i] = node.data;
			node = node.next;
		}

		return (newArray);
	}

	/**
	 * Convert this linkedlist to the array.
	 * But u must give an array because of the type safety.
	 * @param array to be converted
	 * @return generic array
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E> E[] toArray(E[] a) {

		if (a.length < this.length)
			//This cast is safe because a is guaranteed to be an array of type E[].
			a = (E[]) Array.newInstance(a.getClass().getComponentType(), this.length);

		Node<T>	node = head;
		
		for (int i = 0; node != null; i++) {
			a[i] = (E)node.data;
			node = node.next;
		}
		
		if (a.length > this.length)
			a[this.length] = null;

		return (a);
	}

	/**
	 * @param element : element to be searched
	 * @return the index of the first occurrence of the specified element in this list,
	 * or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(Object o) {
		Node<T>	node = head;
		int	index;

		for (index = 0; index < this.length && !(Objects.equals(o, node.data)); index++)
			node = node.next;

		if (index == this.length)
			return (-1);

		return (index);
	}

	/**
	 * @param element : element to be searched
	 * @return true if this list contains the specified element.
	 */
	@Override
	public boolean contains(Object o) {
		if (this.indexOf(o) == -1)
			return (false);
		return (true);
	}

	/**
	 * @param element : element to be searched
	 * @return the index of the last occurrence of the specified element in this list,
	 * or -1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(Object o) {
		Node<T>	node = tail;
		int	index;

		for (index = this.length - 1; index >= 0 && !(Objects.equals(o, node.data)); index--)
			node = node.prev;

		return (index);
	}
}
