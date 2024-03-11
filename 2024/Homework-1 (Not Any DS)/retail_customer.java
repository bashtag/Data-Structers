public class retail_customer extends customer {

	/**
	 * Constructor
	 * @param name
	 * @param surname
	 * @param address
	 * @param phone
	 * @param ID
	 * @param operator_ID
	 */
	public retail_customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
		super(name, surname, address, phone, ID, operator_ID);
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

		// Print orders of the customer
		print_orders();
	}
}
