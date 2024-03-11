public abstract class customer extends person {
	protected int operator_ID;
	protected order[] orders;
	private int ordersCount;

	/**
	 * Constructor
	 * @param name
	 * @param surname
	 * @param address
	 * @param phone
	 * @param ID
	 * @param operator_ID
	 */
	public customer(String name, String surname, String address, String phone, int ID, int operator_ID) {
		super(name, surname, address, phone, ID);
		this.operator_ID = operator_ID;
		this.orders = new order[Main.MAX_RECORDS];
		this.ordersCount = 0;
	}

	// Getters and setters
	public int getOperator_ID() {
		return (operator_ID);
	}

	public order[] getOrders() {
		return (orders);
	}

	public int getOrdersCount() {
		return (ordersCount);
	}

	public void increaseOrdersCount() {
		this.ordersCount++;
	}

	/**
	 * This abstract method will be implemented in the retail_customer and corporate_customer classes
	 */
	public abstract void print_customer();

	/**
	 * Method to define orders for the customer
	 * @param orders
	 */
	public void define_orders(order[] orders) {
		this.orders = orders;
	}

	/**
	 * Helper method to print orders of the customer
	 */
	protected void print_orders() {
		if (this.orders != null) {
			for (int i = 0; i < this.orders.length; i++) {
				if (this.orders[i] != null) {
					System.out.print("Order #" + (i + 1) + " => ");
					this.orders[i].print_order();
				}
			}
		}
	}
}
