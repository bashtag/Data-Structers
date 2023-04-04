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

	@Override
	public void	addFirst(T element) {
		head = createNewNode(element, head);
		length++;

		if (length == 1)
			tail = head;
	}

	@Override
	public void	addLast(T element) {
		tail.next = createNewNode(element);
		tail.next.prev = tail;
		tail = tail.next;
		length++;
	}

	@Override
	public int	size() {
		return (this.length);
	}

	@Override
	public T get(int index) {
		return (getNode(index).data);
	}

	public Node<T>	getNode(int index) {
		Node<T>	node = head;

		if (index >= this.length)
			throw new IndexOutOfBoundsException();

		for (int i = 0; i < index && node != null; ++i)
			node = node.next;

		return (node);
	}

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

	@Override
	public boolean offerFirst(T e) {
		this.addFirst(e);
		return (true);
	}

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

	@Override
	public T pollFirst() {
		return (this.removeFirst());
	}

	@Override
	public T pollLast() {
		return (this.removeLast());
	}

	@Override
	public T getFirst() {
		return (this.head.data);
	}

	@Override
	public T getLast() {
		return (this.tail.data);
	}

	@Override
	public T peekFirst() {
		return (this.getFirst());
	}

	@Override
	public T peekLast() {
		return (this.getLast());
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		int	index = indexOf(o);

		if (index != -1) {
			this.remove(index);
			return (true);
		}

		return (false);
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		int	index = lastIndexOf(o);

		if (index != -1) {
			this.remove(index);
			return (true);
		}

		return (false);
	}


	@Override
	public boolean offer(T e) {
		this.addLast(e);
		return (true);
	}

	@Override
	public T remove() {
		return (this.removeFirst());
	}

	@Override
	public T poll() {
		return (this.removeFirst());
	}

	@Override
	public T element() {
		return (this.getFirst());
	}

	@Override
	public T peek() {
		return (this.getFirst());
	}

	@Override
	public void push(T e) {
		this.addFirst(e);
	}

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

	@Override
	public boolean remove(Object o) {
		return (this.removeFirstOccurrence(o));
	}

	/**
	 * Replaces element to the specified position.
	 * Ofcourse the element of the specified position is going to dissappear.
	 * 
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

	@Override
	public boolean contains(Object o) {
		if (this.indexOf(o) == -1)
			return (false);
		return (true);
	}

	@Override
	public int lastIndexOf(Object o) {
		Node<T>	node = tail;
		int	index;

		for (index = this.length - 1; index >= 0 && !(Objects.equals(o, node.data)); index--)
			node = node.prev;

		return (index);
	}
}
