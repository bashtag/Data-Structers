package	Q1;

/* Class BSTNode */
public class BSTNode
{
	BSTNode left, right;
	int id, piece;
	String name;

	/* Constructor */
	public BSTNode()
	{
		left = null;
		right = null;
		id = 0;
	}
	/* Constructor */
	public BSTNode(int id, String name, int piece)
	{
		left = null;
		right = null;
		this.id = id;
		this.name = name;
		this.piece = piece;
	}

	/* Function to get piece from node */
	public int getPiece()
	{
		return (this.piece);
	}
	/* Function to set left node */
	public void setLeft(BSTNode n)
	{
		left = n;
	}
	/* Function to set right node */ 
	public void setRight(BSTNode n)
	{
		right = n;
	}
	/* Function to get left node */
	public BSTNode getLeft()
	{
		return left;
	}
	/* Function to get right node */
	public BSTNode getRight()
	{
		return right;
	}
	/* Function to set id to node */
	public void setId(int d)
	{
		id = d;
	}
	/* Function to get id from node */
	public int getId()
	{
		return id;
	}     
}
