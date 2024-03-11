import java.io.File;
import java.util.Scanner;

/**
 * Class to manage file I/O operations
 */
public class file_io {
	private operator[] operators; // My main array
	private customer[] customers; // just getting the customers data from file (I am not using this array in my program, I am using the customers array in the operator class.)
	private order[] orders; // just getting the orders data from file (I am not using this array in my program, I am using the orders array in the customer class in the operator class.)
	private int operatorsCount, customersCount, ordersCount;

	/**
	 * Constructor
	 */
	public file_io() {
		this.operators = new operator[Main.MAX_RECORDS];
		this.customers = new customer[Main.MAX_RECORDS];
		this.orders = new order[Main.MAX_RECORDS];
		this.operatorsCount = 0;
		this.customersCount = 0;
		this.ordersCount = 0;
	}

	/**
	 * To start the program process
	 */
	public void startProcess() {
		// Get the data from content.txt and process it into the corresponding arrays
		try {
			Scanner fileScanner = new Scanner(new File("content.txt"));
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				processData(line);
			}
			fileScanner.close();
		} catch (Exception e) {
			System.out.println("The content file was not found.");
			return;
		}

		// Save the data into the operators array
		saveDataToOperators();

		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Please enter your ID...");
		if (!inputScanner.hasNextInt()) {
			System.out.println("Invalid ID format.");
			inputScanner.close();
			return;
		}
		int searchID = inputScanner.nextInt();
		boolean found = printDetails2(searchID);

		if (!found) {
			System.out.println("No operator/customer was found with ID " + searchID + ". Please try again.");
		}

		inputScanner.close();
	}

	/**
	 * To save the data into the operators array.
	 * Adding customers one by one to the corresponding operator
	 * Adding orders one by one to the corresponding customer
	 */
	private void saveDataToOperators() {
		for (int i = 0; i < operatorsCount; i++) {
			customer[] op_customers = operators[i].getCustomers();
			for (int j = 0; j < customersCount; j++) {

				// Hold this index value to add corresponding orders to the customer
				int op_customers_index = operators[i].getCustomersCount();

				if (operators[i].getID() == customers[j].getOperator_ID()) {
					op_customers[op_customers_index] = customers[j];
					operators[i].define_customers(op_customers);
					operators[i].increaseCustomersCount();

					// Adding orders to the corresponding new customer
					for (int k = 0; k < ordersCount; k++) {
						if (op_customers[op_customers_index] != null && op_customers[op_customers_index].getID() == orders[k].getCustomerID()) {
							order[] customer_orders = op_customers[op_customers_index].getOrders();
							customer_orders[op_customers[op_customers_index].getOrdersCount()] = orders[k];
							op_customers[op_customers_index].define_orders(customer_orders);
							op_customers[op_customers_index].increaseOrdersCount();
						}
					}
				}

			}
		}
	}

	/**
	 * Method to process the data from the file
	 * 
	 * @param line
	 */
	private void processData(String line) {
		String[] parts = line.split(";");
		int id, wage, operator_id, count, total_price, status, customer_id;
		try {
			switch (parts[0]) {
				case "operator":
					id = Integer.parseInt(parts[5]);
					wage = Integer.parseInt(parts[6]);
					if (parts.length == 7
							&& id > 0
							&& wage > 0
							&& isNew(id)) {
						operators[operatorsCount] = new operator(parts[1], parts[2], parts[3], parts[4],
								Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
						operatorsCount++;
					}
					break;
				case "retail_customer":
					id = Integer.parseInt(parts[5]);
					operator_id = Integer.parseInt(parts[6]);
					if (parts.length == 7
							&& id > 0
							&& operator_id > 0
							&& isNew(id)) {
						customers[customersCount] = new retail_customer(parts[1], parts[2], parts[3], parts[4],
								Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
						customersCount++;
					}
					break;
				case "corporate_customer":
					id = Integer.parseInt(parts[5]);
					operator_id = Integer.parseInt(parts[6]);
					if (parts.length == 8
							&& id > 0
							&& operator_id > 0
							&& isNew(id)) {
						customers[customersCount] = new corporate_customer(parts[1], parts[2], parts[3], parts[4],
								Integer.parseInt(parts[5]), Integer.parseInt(parts[6]), parts[7]);
						customersCount++;
					}
					break;
				case "order":
					count = Integer.parseInt(parts[2]);
					total_price = Integer.parseInt(parts[3]);
					status = Integer.parseInt(parts[4]);
					customer_id = Integer.parseInt(parts[5]);
					if (parts.length == 6
							&& count > 0
							&& total_price > 0
							&& (status >= 0 && status <= 3)
							&& customer_id > 0) {
						orders[ordersCount] = new order(parts[1], Integer.parseInt(parts[2]),
								Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
						ordersCount++;
					}
					break;
			}
		} catch (NumberFormatException e) {
			// Ignoring the line if there's a format issue
		}
	}

	/**
	 * To check if the given ID is new
	 * 
	 * @param id
	 * @return
	 */
	private boolean isNew(int id) {
		for (int i = 0; i < operatorsCount; i++) {
			if (operators[i].getID() == id)
				return false;
		}
		for (int i = 0; i < customersCount; i++) {
			if (customers[i].getID() == id)
				return false;
		}
		return true;
	}

	private boolean printDetails2(int searchID)
	{

		for (int i = 0; i < operatorsCount; i++) {

			if (operators[i] == null)
				return (false);

			customer[] op_customers = operators[i].getCustomers();

			// search for the operator with the given ID
			if (operators[i].getID() == searchID) {

				System.out.println("*** Operator Screen ***");

				operators[i].print_operator();
				operators[i].print_customers();
				
				System.out.println("----------------------------");

				return (true);
			}

			// search for the customer id
			for (int j = 0; j < op_customers.length; j++) {
				if (op_customers[j] != null && op_customers[j].getID() == searchID) {
					System.out.println("*** Customer Screen ***");
					op_customers[j].print_customer();
					return (true);
				}
			}
		}
		return (false);
	}
}
