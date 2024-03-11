public class person {
	protected String name;
	protected String surname;
	protected String address;
	protected String phone;
	protected int ID;

	/**
	 * Constructor
	 * @param name
	 * @param surname
	 * @param address
	 * @param phone
	 * @param ID
	 */
	public person(String name, String surname, String address, String phone, int ID) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.ID = ID;
	}

	// Getters and setters for each protected field
	public String getName() {
		return (name);
	}

	public String getSurname() {
		return (surname);
	}

	public String getAddress() {
		return (address);
	}

	public String getPhone() {
		return (phone);
	}

	public int getID() {
		return (ID);
	}
}
