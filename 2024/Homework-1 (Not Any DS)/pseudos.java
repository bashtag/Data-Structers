public class pseudos {
		/**
	 * To print the details of the given ID
	 * 
	 * @param searchID
	 * @return
	 */
	private boolean printDetails(int searchID) {

		for (int i = 0; i < operatorsCount; i++) {

			// search for the operator with the given ID
			if (operators[i].getID() == searchID) {

				boolean foundACustomer = false;
				operators[i].print_operator();

				// Printing associated customers and orders
				for (int j = 0; j < customersCount; j++) {
					if (customers[j].getOperator_ID() == searchID) {
						foundACustomer = true;
						customers[j].print_customer();
						for (int k = 0; k < ordersCount; k++) {
							if (orders[k].getCustomerID() == customers[j].getID()) {
								orders[k].print_order();
							}
						}
					}
				}
				if (!foundACustomer) {
					System.out.println("----------------------------\n" +
							"This operator doesn't have any customer.");
				}
				System.out.println("----------------------------");

				return true;
			}
		}

		for (int i = 0; i < customersCount; i++) {

			// search for the customer with the given ID
			if (customers[i].getID() == searchID) {

				customers[i].print_customer();

				// Printing associated orders
				for (int j = 0; j < ordersCount; j++) {
					if (orders[j].getCustomerID() == searchID) {
						orders[j].print_order();
					}
				}
				System.out.println("----------------------------");

				return true;
			}
		}

		return false;
	}
}
