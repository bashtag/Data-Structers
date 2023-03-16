import java.time.LocalTime;

public class Market implements IBuilding, IIdentity{
	private String	marketName;
	private IIdentity	owner;
	private LocalTime	openingTime;
	private LocalTime	closingTime;
	private int	length;
	private int doorNo;
	private int	position;
	private int	height;
	
	public Market(String marketName, IIdentity owner, LocalTime openingTime, LocalTime closingTime, int length,
			int position, int height) {
		this.marketName = marketName;
		this.owner = owner;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.length = length;
		this.position = position;
		this.height = height;
	}

	@Override
	public int getId() {
		return (this.doorNo);
	}

	@Override
	public String getName() {
		return (this.marketName);
	}

	@Override
	public void setName(String name) {
		this.marketName = name;
	}

	@Override
	public int getBuildingLength() {
		return (this.length);
	}

	@Override
	public void setBuildingLength(int length) {
		this.length = length;
	}

	@Override
	public IIdentity getOwner() {
		return (this.owner);
	}

	@Override
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public void focusing() {
		System.out.format("The Closing Time of The Market is %s.\n", this.closingTime);
	}

	public void setOwner(IIdentity owner) {
		this.owner = owner;
	}

	public LocalTime getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(LocalTime openingTime) {
		this.openingTime = openingTime;
	}

	public LocalTime getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(LocalTime closingTime) {
		this.closingTime = closingTime;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(int doorNo) {
		this.doorNo = doorNo;
	}

	@Override
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int getPosition() {
		return (this.position);
	}

	@Override
	public boolean	equals(Object obj)
	{
		if (this == obj)
			return (true);

		if (!(obj instanceof Market))
			return (false);

		Market	comp = (Market) obj;
		return (this.doorNo == comp.doorNo);
	}

	@Override
	public String	toString()
	{
		return (String.format("Market [ID (Door No): %d" + 
		"\n\tName = %s" +
		"\n\tOpening Time = %s" +
		"\n\tClosing Time = %s" +
		"\n\tLength = %d" +
		"\n\tPosition = %d" +
		"\n\tOwner = %s" +
		"]", this.doorNo, this.marketName, this.openingTime, this.closingTime, this.length, this.position, this.owner));
	}

	@Override
	public BType getBuildingType() {
		return (BType.MARKET);
	}

	@Override
	public int getHeight() {
		return (this.height);
	}

	@Override
	public void setHeight(int height) {
		this.height = height;		
	}

}
