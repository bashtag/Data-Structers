public class Playground implements IBuilding{
	private String	playgroundName;
	private int	length;
	private int	position;
	private int	height;

	public Playground(String playgroundName, int length, int position) {
		this.playgroundName = playgroundName;
		this.length = length;
		this.position = position;
	}

	@Override
	public String getName() {
		return (this.playgroundName);
	}

	@Override
	public void setName(String name) {
		this.playgroundName = name;		
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
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int getPosition() {
		return (this.position);
	}

	@Override
	public IIdentity getOwner() {
		return null;
	}

	@Override
	public void setOwner(Owner owner) {
	}

	@Override
	public void focusing() {
		System.out.format("The Length of The Playground is %s.\n", this.length);
	}

	@Override
	public BType getBuildingType() {
		return (BType.PLAYGROUND);
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
