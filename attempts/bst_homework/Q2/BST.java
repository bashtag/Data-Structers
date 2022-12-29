package Q2;

/* Class BST */
public class BST
{
	BSTNode root;

	/* Constructor */
	public BST()
	{
		root = null;
	}
	/* Copy Constructor */
	public BST(BST other)
	{
		root = this.copy(root, other.root);
	}

	/**
	 * @brief Function to perform deep copy through all nodes of the tree
	 * @param root
	 * @param otherRoot
	 * @return root
	 */
	public BSTNode	copy(BSTNode root, BSTNode otherRoot)
	{
		if (otherRoot == null)
			return null;
		else
		{
			root = new BSTNode(otherRoot.getId(), otherRoot.getCaptainName(), otherRoot.getAvailable(), otherRoot.getRatingStar());
			root.left = copy(root.left, otherRoot.left);
			root.right = copy(root.right, otherRoot.right);
		}
		return root;
	}
	/* Function to check if tree is empty */
	public boolean isEmpty()
	{
		return root == null;
	}
	/* Functions to insert data */
	public void insert(int id, String name)
	{
		root = insert(root, id, name);
	}
	/* Function to insert data recursively */
	private BSTNode insert(BSTNode node, int id, String name)
	{
		if (node == null)
			node = new BSTNode(id, name);
		else
		{
			if (id <= node.getId())
				node.left = insert(node.left, id, name);
			else
				node.right = insert(node.right, id, name);
		}
		return node;
	}
	/* Functions to delete data */
	public void delete(int k)
	{
		if (isEmpty());
		else if (search(k) == false);
		else
			root = delete(root, k);
	}
	/**
	 * Functions to delete data recursively
	 * @param root
	 * @param k
	 * @return root
	 */
	private BSTNode delete(BSTNode root, int k)
	{
		BSTNode p, p2, n;

		/* Binary Seach Tree Searching Algorithm */
		if (root.getId() == k)
		{
			BSTNode lt, rt;
			lt = root.getLeft();
			rt = root.getRight();
			if (lt == null && rt == null)
				return null;
			else if (lt == null)
			{
				p = rt;
				return p;
			}
			else if (rt == null)
			{
				p = lt;
				return p;
			}
			else
			{
				p2 = rt;
				p = rt;
				while (p.getLeft() != null)
					p = p.getLeft();
				p.setLeft(lt);
				return p2;
			}
		}
		if (k < root.getId())
		{
			n = delete(root.getLeft(), k);
			root.setLeft(n);
		}
		else
		{
			n = delete(root.getRight(), k);
			root.setRight(n);             
		}
		return root;
	}
	/**
	 * Function to search for an element recursively.
	 * Binary Search Complexity. O(log n)
	 * @param r -> BST object. Because I want to keep found node.
	 * @param val
	 * @return found
	 */
	public boolean search(BST r, int val)
	{
		boolean found = false;
		while ((r.root != null) && !found)
		{
			int rval = r.root.getId();
			if (val < rval)
			r.root = r.root.getLeft();
			else if (val > rval)
			r.root = r.root.getRight();
			else
			{
				found = true;
				break;
			}
			found = search(r, val);
		}
		return found;
	}

	/**
	 * Function to change Captain's availability true to false recursively
	 * @param r
	 * @param id
	 * @return 
	 */
	public BSTNode updateNode(BSTNode r, int id)
	{
		if (r == null)
			return (null);

		/* Binary Search Tree Searching Algorithm */
		if (r.getId() == id)
		{
			r.setAvailable(false);
			return r;
		}
		else if (r.getId() > id && r.getLeft() != null)
			updateNode(r.getLeft(), id);
		else if (r.getId() < id && r.getRight() != null)
			updateNode(r.getRight(), id);
		
		return r;
	}

	/**
	 * Function to change Captain's availability false to true and 
	 * increase or decrease the Captain's rating star recursively
	 * @param r
	 * @param id
	 * @param ratingStar
	 * @return
	 */
	public BSTNode updateNode(BSTNode r, int id, int ratingStar)
	{
		if (r == null)
			return (null);

		/* Binary Search Tree Searching Algorithm */
		if (r.getId() == id)
		{
			r.setAvailable(true);
			if (ratingStar == 1 && r.getRatingStar() < 5)
				r.setRatingStar(r.getRatingStar() + 1);
			else if (ratingStar == 0 && r.getRatingStar() > 0)
				r.setRatingStar(r.getRatingStar() - 1);
			return r;
		}
		else if (r.getId() > id && r.getLeft() != null)
			updateNode(r.getLeft(), id, ratingStar);
		else if (r.getId() < id && r.getRight() != null)
			updateNode(r.getRight(), id, ratingStar);
		
		return r;
	}

	/* Functions to search for an element */
	public boolean search(int val)
	{
		BST	copyBst = new BST(this);
		return search(copyBst.root, val);
	}
	/**
	 * Functions to search for an element recursively
	 * @param r -> BSTNode
	 * @param val
	 * @return found
	 */
	private boolean search(BSTNode r, int val)
	{
		boolean found = false;
		while ((r != null) && !found)
		{
			int rval = r.getId();
			if (val < rval)
				r = r.getLeft();
			else if (val > rval)
				r = r.getRight();
			else
			{
				found = true;
				break;
			}
			found = search(r, val);
		}
		return found;
	}
}
