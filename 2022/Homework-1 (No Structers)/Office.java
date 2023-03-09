public class Office implements IBuilding, IIdentity {
	private String	officeName;
	private JobType	jobType;
	private IIdentity	owner;
	private int	length;
	private int	doorNo;
	private int	position;
	private int	height;
	
	public Office(JobType jobType, int height, int length, int position) {
		this.jobType = jobType;
		this.length = length;
		this.position = position;
		this.height = height;
		this.owner = new Owner();
		this.setDoorNo();
	}

	public Office(JobType jobType, int height, int length, int position, Owner owner) {
		this.jobType = jobType;
		this.owner = owner;
		this.length = length;
		this.position = position;
		this.height = height;
		this.setDoorNo();
	}

	private void	setDoorNo()
	{
		this.doorNo = Statics.getBuildingNum();
		Statics.increaseBuildingNum();
	}

	@Override
	public int getId() {
		return (this.doorNo);
	}

	@Override
	public String getName() {
		return (this.officeName);
	}

	@Override
	public void setName(String name) {
		this.officeName = name;
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
		System.out.format("The Job Type of this office is %s.\n", jobType);
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public void setOwner(IIdentity owner) {
		this.owner = owner;
	}

	public int getLength() {
		return (length);
	}

	public void setLength(int length) {
		this.length = length;
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

		if (!(obj instanceof Office))
			return (false);

		Office	comp = (Office) obj;
		return (this.doorNo == comp.doorNo);
	}

	@Override
	public String	toString()
	{
		return (String.format("Office [ID (Door No): %d" + 
		"\n\tName = %s" +
		"\n\tJob Type = %s" +
		"\n\tLength = %d" +
		"\n\tPosition = %d" +
		"\n\tOwner = %s" +
		"]", this.doorNo, this.officeName, this.jobType, this.length, this.position, this.owner));
	}

	@Override
	public BType getBuildingType() {
		return (BType.OFFICE);
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
