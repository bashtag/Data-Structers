public class corporate_customer extends customer {
	private String company_name;

	/**
	 * Constructor
	 * @param name
	 * @param surname
	 * @param address
	 * @param phone
	 * @param ID
	 * @param operator_ID
	 * @param company_name
	 */
	public corporate_customer(String name, String surname, String address, String phone, int ID, int operator_ID, String company_name) {
		super(name, surname, address, phone, ID, operator_ID);
		this.company_name = company_name;
	}

	// Getter and setter for company_name
	public String getCompanyName() {
		return (company_name);
	}

	/**
	 * Implementation of abstract method print_customer from Customer class
	 */
	@Override
	public void print_customer() {
		// Print personal information
		System.out.println("Name & Surname: " + this.getName() + " " + this.getSurname());
		System.out.println("Address: " + this.getAddress());
		System.out.println("Phone: " + this.getPhone());
		System.out.println("ID: " + this.getID());
		System.out.println("Operator ID: " + this.getOperator_ID());
		System.out.println("Company name: " + this.getCompanyName());

		// Print orders of the customer
		print_orders();
	}
}
