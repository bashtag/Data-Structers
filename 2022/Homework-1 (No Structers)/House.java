public class House implements IBuilding, IIdentity{
	private String	houseName;
	private int	roomsNum;
	private int	doorNo;
	private ColorType	color;
	private IIdentity	owner;
	private int	length;
	private int	position;
	private int	height;

	public House(String houseName, int roomsNum, ColorType color, int height, int length, int position) {
		this.houseName = houseName;
		this.roomsNum = roomsNum;
		this.color = color;
		this.length = length;
		this.position = position;
		this.height = height;
		this.owner = new Owner();
		this.setDoorNo();
	}

	public House(String houseName, int roomsNum, ColorType color, Owner owner, int height, int length, int position) {
		this.houseName = houseName;
		this.roomsNum = roomsNum;
		this.color = color;
		this.owner = owner;
		this.length = length;
		this.position = position;
		this.height = height;
		this.setDoorNo();
	}

	public void	setColor(ColorType color)
	{
		this.color = color;
	}

	public ColorType	getColor()
	{
		return (this.color);
	}

	private void	setDoorNo()
	{
		this.doorNo = 2000 + Statics.getBuildingNum();
		Statics.increaseBuildingNum();
	}

	@Override
	public int getId() {
		return (this.doorNo);
	}

	@Override
	public String getName() {
		return (this.houseName);
	}

	@Override
	public void setName(String name) {
		this.houseName = name;
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
		System.out.format("The owner of this house is %s.\n", this.owner);
	}

	@Override
	public boolean	equals(Object obj) {
		if (obj == this)
		return (true);

		if (!(obj instanceof House))
			return (false);

		House	comp = (House)obj;
		return (this.doorNo == comp.doorNo);
	}

	@Override
	public String	toString() {
		return (String.format("House [ID (Door No): %d" + 
			"\n\tName = %s" +
			"\n\tNumber of rooms = %d" +
			"\n\tLength = %d" +
			"\n\tColor = %s" +
			"\n\tPosition = %s" +
			"\tOwner = %s" +
			"]", this.doorNo, this.houseName, this.roomsNum, this.length, this.color, this.position, this.owner));
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
	public BType getBuildingType() {
		return (BType.HOUSE);
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
