import java.util.AbstractList;
import java.util.Deque;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * A linked list implementation of the List interface.
 * I didn't use List super interface because it isn't necessary.
 * 
 * @author 2023
 *
 * @param <T> The type of elements in this list.
 */
public class LDLinkedList<T> extends AbstractList<T> implements Deque<T> {

	private static class	Node<T> {
		private T	data;
		private Node<T>	next = null;
		private Node<T>	prev = null;

		private	Node(T data) {
			this.data = data;
		}

		private Node(T data, Node<T> ref) {
			this.data = data;
			this.next = ref;
		}
	}
	
	private Node<T>	head = null;
	private Node<T>	tail = null;
	private int	length = 0;

	@Override
	public void add(int index, T element) {

		if (index == 0)
			this.addFirst(element);
		else if (index == this.size() - 1)
			this.addLast(element);
		else {
			Node<T>	node = getNode(index - 1);
			Node<T>	newNode = new Node<T>(element, node.next);
	
			newNode.prev = node;
			node.next = newNode;
			length++;
		}
	}

	@Override
	public void	addFirst(T element) {
		head = new Node<T>(element, head);
		length++;

		if (length == 1)
			tail = head;
	}

	@Override
	public void	addLast(T element) {
		tail.next = new Node<T>(element);
		tail.next.prev = tail;
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

	@Override
	public T removeFirst() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
	}

	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeLast'");
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeFirstOccurrence'");
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'removeLastOccurrence'");
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

	@Override
	public Iterator<T> descendingIterator() {
		return (new DescendingIterator());
	}

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

	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return super.remove(index);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return super.remove(o);
	}

	@Override
	public T set(int index, T element) {
		// TODO Auto-generated method stub
		return super.set(index, element);
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return super.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return super.toArray(a);
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return super.indexOf(o);
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return super.contains(o);
	}
}
