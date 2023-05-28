public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T>{

	protected T	delRes = null;

	public	BinarySearchTree() {}

	@SuppressWarnings("unchecked")
	public	BinarySearchTree(BinaryTree tree) {
		this.head = tree.head;
	}

	/**
	 * bst add method
	 * @return true if this operation happens succesfully
	 */
	public boolean	add(T item) {
		if (this.contains(item))
			return (false);
			
		if (this.head == null)
			this.head = new Node<>(item);
		else
			this.recAdd(this.head, item);
		return (true);
	}

	/**
	 * recursive add method
	 * O(logn) search
	 * worst case O(n)
	 * @param node
	 * @param item
	 */
	private void	recAdd(Node<T> node, T item) {
		if (node == null)
			return;

		else if (item.compareTo(node.data) > 0) {
			if (node.right == null)
				node.right = new Node<T>(item);
			else
				recAdd(node.right, item);
		}
		else if (item.compareTo(node.data) < 0) {
			if (node.left == null)
				node.left = new Node<T>(item);
			else
				recAdd(node.left, item);
		}
		throw	new RuntimeException("node.data and item cannot be equal while insertion.");
	}

	/**
	 * @return true if target is existing on the bst
	 */
	public boolean	contains(T target) {
		return (this.find(target) != null);
	}

	/**
	 * @return target if the target is existing on the bst otherwise it returns null
	 */
	public T	find(T target) {
		return (recFinder(this.head, target));
	}

	/**
	 * Recursive find method for bst
	 * @param node
	 * @param target
	 * @return
	 */
	private T	recFinder(Node<T> node, T target) {
		if (node == null)
			return (null);
		else if (target.compareTo(node.data) > 0)
			return (recFinder(node.right, target));
		else if (target.compareTo(node.data) > 0)
			return (recFinder(node.right, target));
		else
			return (node.data);
	}

	/**
	 * delete algorithm for bst
	 * @return deleted element
	 */
	public T	delete(T target) {
		this.head = recDelete(this.head, target);
		/* reset the delRes value for other deletion operations */
		T	ret = delRes;
		this.delRes = null;
		return (ret);
	}

	/**
	 * Recursively deletion method
	 * @param node
	 * @param target
	 * @return node recursively
	 */
	private Node<T>	recDelete(Node<T> node, T target) {
		if (node == null)
			return (null);
		
		int	compRes = target.compareTo(node.data);
		if (compRes > 0)
			return (recDelete(node.right, target));
		else if (compRes < 0)
			return (recDelete(node.left, target));
		else {
			if (this.delRes == null)
				this.delRes = node.data;
			if (node.left == null)
				return (node.right);
			else if (node.right == null)
				return (node.left);

			/* else */

			/* get the inorder successor */
			node.data = this.minFinder(node.right).data;
			node.right = recDelete(node.right, node.data);
		}
		return node;
	}

	/**
	 * minfinder for delete purposes
	 * @param node
	 * @return
	 */
	private Node<T>	minFinder(Node<T> node) {
		while (node.left != null)
			node = node.left;
		return (node.left);
	}

	/**
	 * remove method overload from SearchTree interface
	 */
	public boolean	remove(T target) {
		return (this.delete(target) != null);	
	}
}
