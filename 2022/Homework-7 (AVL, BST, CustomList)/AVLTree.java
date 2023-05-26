public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	/**
	 * Node class for avl nodes
	 */
	private static class	AVLNode<T> extends Node<T> {
		/* CONSTANTS */

		/* balance factor for each node */
		private int	height = 0;

		/* links */
		private AVLNode<T>	right = null;
		private AVLNode<T>	left = null;

		/**
		 * Explicit Constructor
		 * @param item
		 */
		public AVLNode(T item) {
			super(item);
		}
	}

	/* constants */
	private final int	LEFT_HEAVY = -1;
	private final int	BALANCED = 0;
	private final int	RIGHT_HEAVY = 1;

	private boolean	addRes = false;


	/**
	 * constructor that takes bst parameter
	 * @param bst
	 */
	public	AVLTree(BinarySearchTree<T> bst) {
		this.head = bst.head;
	}

	/**
	 * Default Constructor
	 */
	public	AVLTree() {}

	/**
	 * Insertion method for AVL Tree.
	 * Rotations are included
	 */
	public boolean	add(T item) {
		this.head = recAdd(this.head, item);
		return (this.addRes);
	}

	private AVLNode<T>	recAdd(AVLNode<T> node, T item) {
		if (node == null)
			return (null);
		
		if (item.compareTo(node.data) > 0)
			node.right = recAdd(node.right, item);
		else if (item.compareTo(node.data) < 0)
			node.left = recAdd(node.left, item);
		else {
			this.addRes = false;
			return (node);
		}

		node.height = 1 + Math.max(node.left.height, node.right.height);

		int	balance = this.getBalance(node);

		if (true);
	}

	/**
	 * Get the balance value of the relevant node
	 * @param node
	 * @return left height - right height
	 */
	private int	getBalance(AVLNode<T> node) {
		if (node == null)
			return (0);

		int	leftH = node.left != null ? node.left.height : 0;
		int	rightH = node.right != null ? node.right.height : 0;
		return (leftH - rightH);
	}

	/**
	 * Rotate nodes to the right like an AVL Tree
	 * @param node
	 * @return
	 */
	private AVLNode<T>	rotateR(AVLNode<T> node) {
		AVLNode<T>	middle = node.left;
		AVLNode<T>	middleRight = middle.right;

		middle.right = node;
		node.left = middleRight;

		node.height = 1 + Math.max(node.left.height, node.right.height);
		middle.height = 1 + Math.max(node.left.height, node.right.height);

		return (node);
	}

	/**
	 * Rotate nodes to the right like an AVL Tree
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<T>	rotateL(AVLNode<T> node) {
		AVLNode<T>	middle = node.right;
		AVLNode<T>	middleLeft = middle.left;

		middle.left = node;
		node.right = middleLeft;

		node.height = 1 + Math.max(node.left.height, node.right.height);
		middle.height = 1 + Math.max(node.left.height, node.right.height);

		return (node);
	}

	
	public T	delete(T item) {
		throw	new UnsupportedOperationException();
	}


}
