package	Q2;

/* Class BSTNode */
public class BSTNode
{
	BSTNode left, right;
	private int id, ratingStar;
	private Boolean	available;
	private String captainName;

	/* Constructor */
	public BSTNode()
	{
		left = null;
		right = null;
		id = 0;
	}
	/* Constructor */
	public BSTNode(int id, String captainName)
	{
		left = null;
		right = null;
		this.id = id;
		this.captainName = captainName;
		this.ratingStar = 0;
		this.available = true;
	}
	/* Constructor */
	public BSTNode(int id, String captainName, Boolean available, int ratingStar)
	{
		left = null;
		right = null;
		this.id = id;
		this.captainName = captainName;
		this.ratingStar = ratingStar;
		this.available = available;
	}

	/**
	 * Function to set captain name
	 * @param captainName
	 */
	public	void	setCaptainName(String captainName)
	{
		this.captainName = captainName;
	}
	/**
	 * Function to get captain name
	 * @return captainName
	 */
	public String	getCaptainName()
	{
		return (this.captainName);
	}
	/**
	 * Function to get rating star
	 * @return ratingStar
	 */
	public int getRatingStar()
	{
		return (this.ratingStar);
	}
	/**
	 * Function to set rating star
	 * @param ratingStar
	 */
	public void	setRatingStar(int ratingStar)
	{
		this.ratingStar = ratingStar;
	}
	/**
	 * Function to get availability
	 * @return
	 */
	public Boolean getAvailable()
	{
		return (this.available);
	}
	/**
	 * Function to set availability
	 * @param available
	 */
	public void	setAvailable(Boolean available)
	{
		this.available = available;
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
