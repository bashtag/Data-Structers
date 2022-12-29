package Q1;

/* Class BST */
public class BST
{
	BSTNode root;
	static final int	COUNT = 0;

	/* Constructor */
	public BST()
	{
		root = null;
	}
	/* Function to check if tree is empty */
	public boolean isEmpty()
	{
		return root == null;
	}
	/* Functions to insert data */
	public void insert(int id, String name, int piece)
	{
		root = insert(root, id, name, piece);
	}
	/* Function to insert data recursively */
	private BSTNode insert(BSTNode node, int id, String name, int piece)
	{
		if (node == null)
			node = new BSTNode(id, name, piece);
		else
		{
			if (id <= node.getId())
				node.left = insert(node.left, id, name, piece);
			else
				node.right = insert(node.right, id, name, piece);
		}
		return node;
	}
	/* Functions to delete data */
	public void delete(int k)
	{
		BSTNode	buff = new BSTNode();
		if (isEmpty())
			System.out.println("Tree Empty");
		else if (search(k, buff) == false)
			System.out.println("Sorry "+ k +" is not present");
		else
		{
			root = delete(root, k);
			System.out.println(k+ " deleted from the tree");
		}
	}
	private BSTNode delete(BSTNode root, int k)
	{
		BSTNode p, p2, n;
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
	/* Functions to count number of nodes */
	public int countNodes()
	{
		return countNodes(root);
	}
	/* Function to count number of nodes recursively */
	private int countNodes(BSTNode r)
	{
		if (r == null)
			return 0;
		else
		{
			int l = 1;
			l += countNodes(r.getLeft());
			l += countNodes(r.getRight());
			return l;
		}
	}
	/* Functions to search for an element */
	public boolean search(int val, BSTNode res)
	{
		return search(root, val, res);
	}
	/* Function to search for an element recursively */
	private boolean search(BSTNode r, int val, BSTNode res)
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
			found = search(r, val, res);
		}
		if (found)
			res.piece = r.piece;
		return found;
	}
	/* Function for inorder traversal */
	public void inorder()
	{
		inorder(root);
	}
	private void inorder(BSTNode r)
	{
		if (r != null)
		{
			inorder(r.getLeft());
			System.out.print(r.getId() +" ");
			inorder(r.getRight());
		}
	}
	/* Function for preorder traversal */
	public void preorder()
	{
		preorder(root);
	}
	private void preorder(BSTNode r)
	{
		if (r != null)
		{
			System.out.print(r.getId() +" ");
			preorder(r.getLeft());             
			preorder(r.getRight());
		}
	}
	/* Function for postorder traversal */
	public void postorder()
	{
		postorder(root);
	}
	private void postorder(BSTNode r)
	{
		if (r != null)
		{
			postorder(r.getLeft());             
			postorder(r.getRight());
			System.out.print(r.getId() +" ");
		}
	}
}
