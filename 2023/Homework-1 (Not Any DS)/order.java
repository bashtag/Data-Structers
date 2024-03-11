public class order {
	private String product_name;
	private int count;
	private int total_price;
	private int status;
	private int customer_ID;

	/**
	 * Constructor
	 * @param product_name
	 * @param count
	 * @param total_price
	 * @param status
	 * @param customer_ID
	 */
	public order(String product_name, int count, int total_price, int status, int customer_ID) {
		this.product_name = product_name;
		this.count = count;
		this.total_price = total_price;
		this.status = status;
		this.customer_ID = customer_ID;
	}

	// Getters and setters
	public int getCustomerID() {
		return (customer_ID);
	}

	/**
	 * Method to print order details
	 */
	public void print_order() {
		String statusString = convertStatusToString(this.status);
		System.out.println("Product name: " + this.product_name + 
						   " - Count: " + this.count + 
						   " - Total price: " + this.total_price + 
						   " - Status: " + statusString + ".");
	}

	/**
	 * Helper method to convert status code to a string
	 * @param status
	 * @return
	 */
	private String convertStatusToString(int status) {
		switch (status) {
			case 0:
				return ("Initialized");
			case 1:
				return ("Processing");
			case 2:
				return ("Completed");
			case 3:
				return ("Cancelled");
			default:
				return ("Unknown Status");
		}
	}
}
