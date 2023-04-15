/**
 * BinarySearchTree implementation. It must be takes a comparable class.
 * Each node holds a unique key.
 * Also this class is serialize. And deserialize for Integer datas.
 * Search: O(logn)
 * Insertion: O(logn)
 * Deletion: O(logn)
 * Serialization: O(n)
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

	/* root's node */
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
	 * O(logn) -> recursive function
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
	 * O(logn) -> searching this specified element
	 * @param node
	 * @param val
	 * @throws TreeInsertionException
	 */
	protected void	insertRec(Node<E> node, E val) throws TreeInsertionException {
		if (val.compareTo(node.data) > 0)
		{
			if (node.right == null) {
				node.right = new Node<E>(val);
				node.right.parent = node;
			}
			else
				insertRec(node.right, val);
		}
		else if (val.compareTo(node.data) < 0)
		{
			if (node.left == null) {
				node.left = new Node<E>(val);
				node.left.parent = node;
			}
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

	/**
	 * This method mainly calls a recursive function.
	 * O(logn) -> recursive function
	 * @param element
	 */
	public void	delete(E element) {
		this.root = deleteRec(this.root, element);
	}

	/**
	 * Recursive deletion method
	 * O(logn) -> search for specified element
	 * @param node
	 * @param val
	 */
	public Node<E>	deleteRec(Node<E> node, E val) {
		
		/* base case */
		if (node == null)
			return (null);

		/* Search */
		if (val.compareTo(node.data) > 0)
			node.right = deleteRec(node.right, val);
		else if (val.compareTo(node.data) < 0)
			node.left = deleteRec(node.left, val);

		/* val == node.data */
		else {
			if (node.right == null)
				return (node.left);
			else if (node.left == null)
				return (node.right);

			/**
			 * Otherwise.
			 * Successor: minimum value of right subtree
			 */
			node.data = minValue(node.right);

			/* to delete successor in the right subtree. */
			node.right = deleteRec(node.right, node.data);
		}

		return (node);
	}

	/**
	 * Get minimum value of given search tree
	 * O(logn) -> bst search algorithm
	 * @param node
	 * @return
	 */
	public E	minValue(Node<E> node) {
		if (node.left == null)
			return (node.data);
		return (minValue(node.left));
	}

	/**
	 * Serialize bst with pre-order traversal.
	 * Null marker: #
	 * @return
	 */
	public String	serialize() {
		StringBuilder	stringBuilder = new StringBuilder();
		recSerialize(root, stringBuilder);
		return (stringBuilder.toString());
	}

	/**
	 * Helper method to serialize recursively.
	 * O(n) -> Visit all nodes
	 */
	protected void	recSerialize(Node<E> node, StringBuilder sb) {
		if (node == null)
			sb.append("# ");
		else {
			sb.append(node.data + " ");
			recSerialize(node.left, sb);
			recSerialize(node.right, sb);
		}
	}

	/**
	 * Deserialize given string to the Integer typed bst.
	 * @param serialized
	 */
	public static BinarySearchTree<Integer>	deserialize(String serialized) {
		String[]	preOrderArr = serialized.split(" ");

		return (new BinarySearchTree<Integer>(recDeserialize(preOrderArr, new int[]{0})));
	}

	/**
	 * Helper method to deserialize recursively.
	 * O(n) -> Visit all nodes.
	 * @param arr
	 * @param index array parameter because of reference. I have to use a global variable or reference parameter.
	 * I chose to use a reference parameter.
	 * @return root
	 */
	protected static Node<Integer>	recDeserialize(String[] arr, int[] index) {
		/* base case */
		if (arr[index[0]].equals("#"))
			return (null);

		Node<Integer>	newNode = new Node<Integer>(Integer.parseInt(arr[index[0]]));
		++index[0];
		newNode.left = recDeserialize(arr, index);
		++index[0];
		newNode.right = recDeserialize(arr, index);

		return (newNode);
	}

	/* QUESTIONS' PART */

	/**
	 * Question-a: Calculate total depth of the nodes.
	 * Root's depth is 1.
	 * O(n) -> to visit all of the nodes
	 * @return total depth of all nodes
	 */
	public int	totalDepth() {
		return (recTotalDepth(this.root, 1));
	}

	/**
	 * Recursively total depth solution.
	 * O(n) -> to visit all of the nodes
	 * @param node
	 * @return total depth
	 */
	protected int	recTotalDepth(Node<E> node, int depth) {
		/* base case */
		if (node == null)
			return (0);
		
		return (depth + recTotalDepth(node.left, depth + 1) + recTotalDepth(node.right, depth + 1));
	}

	/**
	 * Question-b: Calculate average comparision number
	 * O(logn) -> recursive function
	 * @param element
	 * @param comparisionNumber reference
	 * @return if bst contains this element or not
	 */
	public boolean	contains(E element, int[] comparisionNumber) {
		return (recContains(this.root, element, comparisionNumber));
	}

	/**
	 * this function checks bst includes given element or not.
	 * O(logn) -> bst searching
	 * @param node
	 * @param element
	 * @param comparisionNumber : reference
	 * @return
	 */
	protected boolean	recContains(Node<E> node, E element, int[] comparisionNumber) {
		++comparisionNumber[0];
		if (node == null)
			return (false);

		if (element.compareTo(node.data) > 0)
			return (recContains(node.right, element, comparisionNumber));

		if (element.compareTo(node.data) < 0)
			return (recContains(node.left, element, comparisionNumber));

		return (true);
	}

	/**
	 * it creates a full bst
	 * O(nlogn) -> recursive function
	 * @param end until this value
	 * @return
	 */
	public static	BinarySearchTree<Integer>	createFullBST(int end) {
		return (new BinarySearchTree<>(recCreateFullBST(0, end)));
	}

	/**
	 * Recursively Integer typed full bst create function.
	 * O(nlogn) ->
	 * 	1: Searching place -> logn.
	 *  2: All elements -> n.
	 * These operations are inner. So we must multiply them. O(nlogn)
	 * @param start
	 * @param end
	 * @return
	 */
	protected static Node<Integer>	recCreateFullBST(int start, int end) {
		if (start > end)
			return (null);

		int	mid = (start + end) / 2;
		Node<Integer>	node = new Node<Integer>(mid);

		node.left = recCreateFullBST(start, mid - 1);
		node.right = recCreateFullBST(mid + 1, end);

		return (node);
	}

	/**
	 * Calculate the number of the internal nodes
	 * O(n) -> recursive function
	 * @return number of the internal nodes
	 */
	public int	calcInternals() {
		return (recCalcInternals(this.root));
	}

	/**
	 * Recursively calculate the total number of the internal nodes
	 * O(n) -> to visit all of the nodes
	 * @param node
	 * @return
	 */
	protected int	recCalcInternals(Node<E> node) {
		/* base case */
		if (node == null)
			return (0);

		/* leaf */
		if (node.left == null && node.right == null)
			return (0);
		return (1 + recCalcInternals(node.left) + recCalcInternals(node.right));
	}
	
	/**
	 * Calculate the number of leaves
	 * O(n) -> recursive function
	 * @return nmber of internal nodes
	 */
	public int	calcLeaves() {
		return (recCalcLeaves(this.root));
	}
	
	/**
	 * Recursively calculate the number of leaves
	 * O(n) -> Visit all nodes
	 * @param node
	 * @return
	 */
	protected int	recCalcLeaves(Node<E> node) {
		/* base case */
		if (node == null)
			return (0);

		/* leaf */
		if (node.left == null && node.right == null)
			return (1);
		return (recCalcInternals(node.left) + recCalcInternals(node.right));
	}

}
