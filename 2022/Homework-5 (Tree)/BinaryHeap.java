/**
 * This heap is regulated as minheap. Also this class is structurally provides a node-link structure.
 * Big difference with bst is heap is a complete tree, bst not.
 * Avl tree is a complete tree but heap is not provide binary search
 * Insertion: O(logn)
 * Merge: O((n^2)logn)
 */
public class BinaryHeap<E extends Comparable<? super E>> extends BinarySearchTree<E> {
	// max size of the heap
	private int	capacity;

	// current size
	private int	size = 0;

	// height of the heap
	private int	height = 0;

	// number of nodes at the last level of the heap
	private int	lastNodeNum = 0;

	/**
	 * Constructs a binary heap.
	 * @param capacity : maximum size
	 */
	public BinaryHeap(int capacity) {
		this.root = null;
		this.capacity = capacity;
	}

	/**
	 * Inserts an element to the heap.
	 * O(logn) -> T(n) = 2logn -> findEnd and shiftUp methods
	 * @param element
	 * @return if the capacity is equal to size, return true.
	 */
	public boolean	insertKey(E element) {
		if (size == capacity)
			return (false);

		if (this.root == null) {
			this.root = new Node<E>(element);
			this.root.parent = null;
			++this.size;
			++this.height;
			return (true);
		}

		Node<E>	endNode = findEnd();
		// System.out.println("debug: " + endNode.data);
		if (endNode.left == null) {
			endNode.left = new Node<E>(element);
			endNode.left.parent = endNode;
			shiftUp(endNode.left);
		}
		else {
			endNode.right = new Node<E>(element);
			endNode.right.parent = endNode;
			shiftUp(endNode.right);
		}

		++lastNodeNum;
		if (lastNodeNum == Math.pow(2, height)) {
			lastNodeNum = 0;
			++height;
		}

		++this.size;
		return (true);
	}

	/**
	 * Shift upwards if the parent is bigger than related child.
	 * (MinHeap feature).
	 * O(logn) -> it depends on height
	 * @param node
	 */
	private void	shiftUp(Node<E> node) {
		while (node.parent != null && node.parent.compareTo(node.data) > 0) {
			E	temp = node.parent.data;
			node.parent.data = node.data;
			node.data = temp;

			// loop
			node = node.parent;
		}
	}

	/**
	 * Find the last node of the heap
	 * O(logn) -> rec method
	 * @return
	 */
	private Node<E>	findEnd() {
		return (recFindEnd(this.root, 0));
	}

	/**
	 * Find the end using recursive algorithm
	 * O(logn) -> binary search
	 * @param node
	 * @param curLevel
	 * @return last node
	 */
	private Node<E>	recFindEnd(Node<E> node, int curLevel) {
		if (node == null)
			return (null);

		if ((node.left == null || node.right == null) && curLevel + 1 == this.height)
			return (node);

		Node<E>	res = recFindEnd(node.left, curLevel + 1);
		if (res == null)
			res = recFindEnd(node.right, curLevel + 1);

		return (res);
	}

	/**
	 * Merge two binary heap tree.
	 * O((n^2)logn) -> addCurrentLevel method and loop that iterates n times.
	 * @param heap
	 */
	public void	merge(BinaryHeap<E> heap) {
		for (int i = 1; i <= heap.height; ++i)
			addCurrentLevel(heap.root, i);
	}

	/**
	 * Add given level to the class.
	 * O(nlogn) -> n -> visit all of the nodes, logn -> insertKey function
	 * @param heap
	 * @param curLevel
	 */
	private void	addCurrentLevel(Node<E> node, int curLevel) {
		if (node == null)
			return ;

		if (curLevel == 1)
			insertKey(node.data);
		else if (curLevel > 1) {
				addCurrentLevel(node.left, curLevel - 1);
				addCurrentLevel(node.right, curLevel - 1);
		}
	}
}
