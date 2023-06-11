import java.io.Serializable;

/**
 * Serializable
 */
public class BinaryTree<T> implements Serializable{
	
	protected static class	Node<T> {
		protected T	data;
		protected Node<T>	right = null;
		protected Node<T>	left = null;

		/* Constructor */
		protected Node(T data) {
			this.data = data;
		}

		/**
		 * @return data's toString method
		 */
		public String	toString() {
			return (data.toString());
		}
	}

	/* head */
	protected Node<T>	head = null;

	/* Default Constructor */
	public BinaryTree() {
	}

	/* data parameter constructor */
	public BinaryTree(T data) {
		head = new Node<T>(data);
	}

	/* Constructor that has a node parameter */
	protected BinaryTree(Node<T> node) {
		head = node;
	}

	/**
	 * Constructor that constructs using left subtree, right subtree and head data
	 * @param data
	 * @param left
	 * @param right
	 */
	public BinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
		this.head = new Node<T>(data);

		if (left != null)
			this.head.left = left.head;

		if (right != null)
			this.head.right = right.head;
	}

	/**
	 * 
	 * @return left subtree of this structure
	 */
	public BinaryTree<T>	getLeftSubTree() {
		if (head == null)
			return (null);
		return (new BinaryTree<T>(head.left));
	}
	
	/**
	 * 
	 * @return right subtree of this structure
	 */
	public BinaryTree<T>	getRightSubTree() {
		if (head == null)
			return (null);
		return (new BinaryTree<T>(head.right));
	}

	/**
	 * getting the data of head
	 * @return data of relative node
	 */
	public T	getData() {
		return (head.data);
	}

	/**
	 * to change the data of the head
	 * @param data
	 */
	public void	changeData(T data) {
		this.head.data = data;
	}

	/**
	 * 
	 * @return this node is a leaf or not
	 */
	public boolean	isLeaf() {
		return (this.head.left == null || this.head.right == null);
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
	private void	preOrderToString(Node<T> node, StringBuilder sb, int depth) {
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
