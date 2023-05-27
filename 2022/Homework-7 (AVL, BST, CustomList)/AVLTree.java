public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	/**
	 * Node class for avl nodes
	 */
	private static class	AVLNode<T> extends Node<T> {

		/* height of node */
		private int	height = 1;

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
	private final int	LEFT_HEAVY = 1;
	private final int	RIGHT_HEAVY = -1;

	private boolean	addRes = false;
	private AVLNode<T>	head = null;


	/**
	 * constructor that takes bst parameter
	 * @param bst
	 */
	public	AVLTree(BinarySearchTree<T> bst) {
		this.head = (AVLNode<T>)bst.head;
	}

	/**
	 * Default Constructor
	 */
	public	AVLTree() {}

	private int	getHeight(AVLNode<T> node) {
		if (node == null)
			return (0);
		return (node.height);
	}
	/**
	 * Insertion method for AVL Tree.
	 * Rotations are included
	 */
	public boolean	add(T item) {		
		this.head = this.recAdd(this.head, item);
		// System.out.println("head: " + this.head);
		return (true);
	}

	private AVLNode<T>	recAdd(AVLNode<T> node, T item) {
		if (node == null)
			return (new AVLNode<>(item));
		
		if (item.compareTo(node.data) > 0)
			node.right = recAdd(node.right, item);
		else if (item.compareTo(node.data) < 0)
			node.left = recAdd(node.left, item);
		else {
			this.addRes = false;
			return (node);
		}

		node.height = 1 + Math.max(this.getHeight(node.left), this.getHeight(node.right));

		int	balance = this.getBalance(node);
		// System.out.println(node + ", " + node.height + ", balance: " + balance + ", " + (item.compareTo(node.right.data) > 0));
		/**
		 * If balance factor is greater than 1, it means that left rotation should be used.
		 * Also if the item is on the left of the node.left than another left rotation should be used.
		 */
		/* LL Case */
		if (balance > LEFT_HEAVY && item.compareTo(node.left.data) < 0)
			return (rotateR(node));

		/* LR Case */
		else if (balance > LEFT_HEAVY && item.compareTo(node.left.data) > 0) {
			/* first L rotate should be used because if R rotate is desired, it needs to be done */
			node.left = rotateL(node.left);
			return (rotateR(node));
		}

		/* RL Case */
		else if (balance < RIGHT_HEAVY && item.compareTo(node.right.data) < 0) {
			node.right = rotateR(node.right);
			return (rotateL(node));
		}

		/* RR Case */
		else if (balance < RIGHT_HEAVY && item.compareTo(node.right.data) > 0)
			return (rotateL(node));

		/* return the unchanged node */
		return (node);
	}

	/**
	 * Get the balance value of the relevant node
	 * @param node
	 * @return left height - right height
	 */
	private int	getBalance(AVLNode<T> node) {
		if (node == null)
			return (0);

		return (this.getHeight(node.left) - this.getHeight(node.right));
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

		node.height = 1 + Math.max(this.getHeight(node.left), this.getHeight(node.right));
		middle.height = 1 + Math.max(this.getHeight(node.left), this.getHeight(node.right));

		return (middle);
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

		node.height = 1 + Math.max(this.getHeight(node.left), this.getHeight(node.right));
		middle.height = 1 + Math.max(this.getHeight(node.left), this.getHeight(node.right));

		return (middle);
	}

	
	public T	delete(T item) {
		throw	new UnsupportedOperationException();
	}

	
	/**
	 * toString method overload.
	 * It makes a diagram of the bs.
	 * @return a String that has been made using StringBuilder
	 */
	public String	toString() {
		StringBuilder	sb = new StringBuilder();
		this.preOrderToString(this.head, sb, 0);
		return (sb.toString());
	}

	/**
	 * Recursive method that creates a diagram of the bs
	 * @param node
	 * @param sb
	 * @param depth
	 */
	private void	preOrderToString(AVLNode<T> node, StringBuilder sb, int depth) {
		for (int i = 0; i < depth; ++i)
			sb.append('\t');

		if (node != null)
		{
			sb.append("-> " + node.data + "\n");
			if (node.right != null || node.left != null) {
				preOrderToString(node.left, sb, depth + 1);
				preOrderToString(node.right, sb, depth + 1);
			}
		}
		else
			sb.append("-> null\n");
	}
}
