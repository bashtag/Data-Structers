public class operator extends person {
	private int wage;
	private customer[] customers;
	private int customersCount;

	/**
	 * Constructor
	 * @param name
	 * @param surname
	 * @param address
	 * @param phone
	 * @param ID
	 * @param wage
	 */
	public operator(String name, String surname, String address, String phone, int ID, int wage) {
		super(name, surname, address, phone, ID);
		this.wage = wage;
		this.customers = new customer[Main.MAX_RECORDS];
		this.customersCount = 0;
	}

	// Getters and setters
	public int getWage() {
		return (wage);
	}

	/**
	 * To reach the customers of the operator
	 * @return
	 */
	public customer[] getCustomers() {
		return (customers);
	}

	public int getCustomersCount() {
		return (customersCount);
	}

	public void increaseCustomersCount() {
		this.customersCount++;
	}

	/**
	 * Method to print operator details
	 */
	public void print_operator() {
		System.out.println("----------------------------");
		System.out.println("Name & Surname: " + this.getName() + " " + this.getSurname());
		System.out.println("Address: " + this.getAddress());
		System.out.println("Phone: " + this.getPhone());
		System.out.println("ID: " + this.getID());
		System.out.println("Wage: " + this.getWage());
	}

	/**
	 * Setter method to define customers of the operator
	 * @param customers
	 */
	public void define_customers(customer[] customers) {
		this.customers = customers;
	}

	/**
	 * Helper method to print customers of the operator
	 */
	public void print_customers() {
		if (customers != null) {

			boolean foundACustomer = false;
			for (int i = 0; i < customers.length; i++) {
				if (customers[i] != null) {
					foundACustomer = true;
					System.out.print("----------------------------\n" +
										"Customer #" + (i + 1) + " ");

					if (customers[i] instanceof corporate_customer) {
						System.out.println("(a corporate customer):");
					} else if (customers[i] instanceof retail_customer){
						System.out.println("(a retail customer):");
					}
					customers[i].print_customer();
				}
			}

			if (!foundACustomer) {
				System.out.println("----------------------------\n" +
						"This operator doesn't have any customer.");
			}
		}
	}
}