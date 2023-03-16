public class Owner implements IIdentity{
	private int	ownerId;
	private String	ownerName;

	public Owner() {
		this.ownerName = "Owner Name";
		this.setId();
	}

	public Owner(String ownerName) {
		this.ownerName = ownerName;
		this.setId();
	}

	private void	setId()
	{
		this.ownerId = 1000 + Statics.getOwnerNum();
		Statics.increaseOwnerNum();
	}

	@Override
	public int getId() {
		return (this.ownerId);
	}

	@Override
	public String getName() {
		return (this.ownerName);
	}

	@Override
	public String toString() {
		return (String.format("Owner [Name: %s,\n\tID: %d]", this.ownerName, this.ownerId));
	}

	@Override
	public void setName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return (true);

		if (!(obj instanceof Owner))
			return (false);

		Owner	comp = (Owner)obj;
		return (this.ownerId == comp.ownerId);
	}
}
