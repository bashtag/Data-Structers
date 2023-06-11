public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

	/**
	 * Node class for avl nodes.
	 * Difference is that it contains a height member.
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
		this.bstToAVL(bst);
	}

	/**
	 * Default Constructor
	 */
	public	AVLTree() {}

	/**
	 * BST to AVL method.
	 * It converts all of the nodes bst node to the avl node
	 * and balances all the tree
	 * @param bst
	 */
	public void	bstToAVL(BinarySearchTree<T> bst) {
		this.head = null;
		this.head = this.nodeTransform(bst.head);
		this.head = this.balanceTheTree(this.head);
	}

	/**
	 * Balance by searching the tree.
	 * @param node
	 * @return
	 */
	private AVLNode<T>	balanceTheTree(AVLNode<T> node) {
		if (node == null)
			return (null);

		node.left = this.balanceTheTree(node.left);
		node.right = this.balanceTheTree(node.right);

		/* update the height */
		node.height = 1 + Math.max(this.getHeight(node.left), this.getHeight(node.right));

		/* calculate the balance factor */
		int	balance = this.getBalance(node);

		if (balance > LEFT_HEAVY && node.left != null) {
			/* LL Case */
			if (this.getBalance(node.left) == 1)
				return (rotateR(node));
			/* LR Case */
			else {
				node.left = rotateL(node.left);
				return (rotateR(node.right));
			}
		}
		else if (balance < RIGHT_HEAVY && node.right != null) {
			/* RR Case */
			if (this.getBalance(node.right) == -1)
				return (rotateL(node));
			else {
				node.right = rotateR(node.right);
				return (rotateL(node));
			}
		}

		return (node);
	}

	/**
	 * BST to AVL node transformation.
	 * It doesn't make an avl tree. It just makes a node conversation.
	 * @param bstNode
	 * @return
	 */
	private AVLNode<T>	nodeTransform(Node<T> bstNode) {
		/* if bst is an empty tree */
		if (bstNode == null)
			return (null);

		AVLNode<T>	newNode = new AVLNode<T>(bstNode.data);

		if (bstNode.left != null);
			newNode.left = this.nodeTransform(bstNode.left);
		if (bstNode.right != null);
			newNode.right = this.nodeTransform(bstNode.right);

		return (newNode);
	}

	/**
	 * Get the height of the relative node
	 * @param node
	 * @return node.height
	 */
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
		return (this.addRes);
	}

	/**
	 * Recursive add method with bst addition algorithm approach.
	 * It balances the tree when addition is finished.
	 * @param node
	 * @param item
	 * @return
	 */
	private AVLNode<T>	recAdd(AVLNode<T> node, T item) {
		if (node == null) {
			this.addRes = true;
			return (new AVLNode<>(item));
		}
		
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
		middle.height = 1 + Math.max(this.getHeight(middle.left), this.getHeight(middle.right));

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
		middle.height = 1 + Math.max(this.getHeight(middle.left), this.getHeight(middle.right));

		return (middle);
	}

	/**
	 * Deletion method for avl tree
	 * @return deleted item
	 */
	public T	delete(T item) {
		this.head = this.recDel(this.head, item);
		T	res = this.delRes;
		this.delRes = null;
		return (res);
	}

	/**
	 * Recursive deletion.
	 * BST deletion algorithm and balance the tree
	 * @param node
	 * @param item
	 * @return node
	 */
	private AVLNode<T>	recDel(AVLNode<T> node, T item) {
		if (node == null)
			return (null);

		int	compRes = item.compareTo(node.data);
		if (compRes > 0)
			node.right = recDel(node.right, item);
		else if (compRes < 0)
			node.left = recDel(node.left, item);
		else {
			/* return value for delete method */
			if (this.delRes == null)
				this.delRes = node.data;

			/* node with only one child or no child */
			if (node.right == null)
				node = node.left;
			else if (node.left == null)
				node = node.right;
			
			/* node with two children */
			else {
				node.data = this.minFinder(node.right);
				node.right = this.recDel(node.right, node.data);
			}
		}
		
		/* if node is a leaf */
		if (node == null)
			return (node);

		/* update the height */
		node.height = 1 + Math.max(this.getHeight(node.left), this.getHeight(node.right));

		int	balance = this.getBalance(node);

		/* LL Case */
		if (balance > LEFT_HEAVY && node.left != null && item.compareTo(node.left.data) < 0)
			return (rotateR(node));
		
		/* LR Case */
		else if (balance > LEFT_HEAVY && node.left != null && item.compareTo(node.left.data) > 0) {
			node.left = rotateL(node.left);
			return (rotateR(node));
		}

		/* RR Case */
		else if (balance < RIGHT_HEAVY && node.right != null && item.compareTo(node.right.data) > 0)
			return (rotateL(node));

		/* RL Case */
		else if (balance < RIGHT_HEAVY && node.right != null && item.compareTo(node.right.data) < 0) {
			node.right = rotateR(node.right);
			return (rotateL(node));
		}

		return (node);
	}

	/**
	 * Minimum value of given subtree
	 * @param node
	 * @return
	 */
	private T	minFinder(AVLNode<T> node) {
		while (node.left != null)
			node = node.left;
		return (node.data);
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
