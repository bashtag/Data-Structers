import java.util.Scanner; /* Scanner class for console inputs */

/**
 * Driver class is checks the validity of inputs and makes a console interface for users.
 * Exceptions have handled.
 * 
 * I haven't finished yet.
 */
public class Driver {

	public static void main(String[] args) {
		Scanner	scanner = new Scanner(System.in);
		Street	street;
		int	input = -1;
		
		System.out.print("Welcome to the street silhouette program!\n");
		do {
			// input for length of the street
			System.out.print("Give me the length of the street: ");
			try {
				input = scanner.nextInt();
				if (input < 5)
					throw new IllegalArgumentException();
			} catch (Exception e) {
				System.out.println("Give me a valid input.");
				continue;
			}
			street = new Street(input);
			input = -1;

			// input to chose a mode
			System.out.println("\tEditing mode press 1\n" + 
								"\tViewing mode press 2\n" + 
								"\tFor quit press 0");
			
			try {
				input = scanner.nextInt();
				if (input != 1 && input != 2 && input != 0)
					throw new IllegalArgumentException();
			} catch (Exception e) {
				System.out.println("Give me a valid input.");
				continue;
			}

			// mode functions calls
			switch (input) {
				case 1:
					editingMode(scanner, street);
					break;

				case 2:
					viewingMode(scanner, street);
			
				default:
					break;
			}

			break;
		} while (input != 0);

		scanner.close();
	}

	public static void	editingMode(Scanner scanner, Street street) {
		int	input = -1;

		System.out.println("\n\tEditing Mode\n");

		do {
			System.out.println("-> To add a building press 1\n" +
							"-> To delete a building press 2");
			try {
				input = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Give me a valid input!");
				continue;
			}

			switch (input) {
				case 1:
					addBuilding(scanner, street);
					break;

				case 2:
					deleteBuilding(scanner, street);
					break;
			
				default:
					break;
			}

		} while (input == -1);
	}

	public static void	addBuilding(Scanner scanner, Street street) {
	}

	public static void	deleteBuilding(Scanner scanner, Street street) {
	}

	public static void	viewingMode(Scanner scanner, Street street) {
	}

}
