public interface IBuilding {
	public BType	getBuildingType();
	public String	getName();
	public void	setName(String name);
	public int	getBuildingLength();
	public void	setBuildingLength(int length);
	public int	getHeight();
	public void	setHeight(int height);
	/**
	 * x-coordinate starting point
	 * @param position
	 */
	public void	setPosition(int position);
	public int	getPosition();
	public IIdentity	getOwner();
	public void	setOwner(Owner owner);
	public void	focusing();
	public boolean	equals(Object obj);
	public String	toString();
}
