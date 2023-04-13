/**
 * BinarySearchTree implementation. It must be takes a comparable class.
 * Each node holds a unique key.
 * Search: O(logn)
 * Insertion: O(logn)
 * Deletion: O(logn)
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

	protected Node<E>	root;

	/**
	 * Node class for a binary tree containing a parent node.
	 * Node is a comparable class cause of binary search.
	 */
	protected static class Node<E extends Comparable<? super E>> implements Comparable<E> {

		protected E	data;
		protected Node<E>	right = null;
		protected Node<E>	left = null;
		protected Node<E>	parent = null;

		/**
		 * Constructor using an element
		 * O(1)
		 * @param data
		 */
		public Node(E data) {
			this.data = data;
		}

		/**
		 * No parameter constructor
		 * O(1)
		 */
		public Node() {}

		/**
		 * Element comparision.
		 * Time-space complexity depends on element's compareTo function
		 */
		@Override
		public int compareTo(E o) {
			return (o.compareTo(data));
		}
	}

	/**
	 * Exception class for an addition mistake.
	 * If tree includes relative key, it raises.
	 */
	protected static class TreeInsertionException extends Exception {
		protected TreeInsertionException() {
			super("Key must be unique.");
		}
	}

	/**
	 * No parameter constructor initializes with a null root
	 */
	public BinarySearchTree() {
		this.root = null;
	}

	/**
	 * Constructor using an element
	 * @param element
	 */
	public BinarySearchTree(E element) {
		this.root = new Node<E>(element);
	}

	/**
	 * Constructor using an element, a left tree object and a right tree object
	 * @param element
	 * @param leftTree
	 * @param rightTree
	 */
	public BinarySearchTree(E element, BinarySearchTree<E> leftTree, BinarySearchTree<E> rightTree) {
		this.root = new Node<E>(element);

		if (leftTree.root != null)
			this.root.left = leftTree.root;

		if (rightTree.root != null)
			this.root.right = rightTree.root;
	}

	/**
	 * Constructor for subclasses like BinarySearchArray.
	 * It takes a root node.
	 * @param root
	 */
	protected BinarySearchTree(Node<E> root) {
		this.root = root;
	}

	/**
	 * O(1) -> there isn't any iteration or recursion
	 * @return left subtree object
	 */
	public BinarySearchTree<E>	getLeftSubtree() {
		if (this.root != null || this.root.left != null)
			return (new BinarySearchTree<E>(this.root.left));
		else
			return (null);
	}

	/**
	 * O(1) -> there isn't any iteration or recursion
	 * @return right subtree object
	 */
	public BinarySearchTree<E>	getRightSubtree() {
		if (this.root != null || this.root.right != null)
			return (new BinarySearchTree<E>(this.root.right));
		else
			return (null);
	}

	/**
	 * Inserting a node using a recursive function.
	 * O(logn)
	 * @param element
	 * @throws TreeInsertionException
	 */
	public void	insert(E element) throws TreeInsertionException {
		if (this.root != null)
			insertRec(this.root, element);
		else
			this.root = new Node<E>(element);
	}

	/**
	 * Inserting a node using recursion.
	 * O(logn)
	 * @param node
	 * @param val
	 * @throws TreeInsertionException
	 */
	protected void	insertRec(Node<E> node, E val) throws TreeInsertionException {
		if (val.compareTo(node.data) > 0)
		{
			if (node.right == null)
				node.right = new Node<E>(val);
			else
				insertRec(node.right, val);
		}
		else if (val.compareTo(node.data) < 0)
		{
			if (node.left == null)
				node.left = new Node<E>(val);
			else
				insertRec(node.left, val);
		}
		else
			throw new TreeInsertionException();
	}

	/**
	 * toString method for binary tree diagram.
	 * This method uses a recursive function.
	 * O(n) -> recursive function
	 */
	@Override
	public String toString() {
		StringBuilder	sb = new StringBuilder();
		diagramBuilder(this.root, sb, 0);
		return (sb.toString());
	}

	/**
	 * This recursive method makes a binary tree diagram using a StringBuilder object.
	 * O(n) -> it visits all of the elements
	 * @param node
	 * @param sb
	 * @param depth
	 */
	protected void	diagramBuilder(Node<E> node, StringBuilder sb, int depth) {
		for (int i = 0; i < depth; ++i)
			sb.append('\t');

		if (node != null)
		{
			sb.append("-> " + node.data + "\n");
			if (node.right != null || node.left != null) {
				diagramBuilder(node.left, sb, depth + 1);
				diagramBuilder(node.right, sb, depth + 1);
			}
		}
		else
			sb.append("-> null\n");
	}

}
