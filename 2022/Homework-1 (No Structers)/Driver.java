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
			System.out.print("\tEditing mode press 1\n" + 
								"\tViewing mode press 2\n" + 
								"\tFor quit press 0\n\n" +
								"Your selection: ");
			
			try {
				input = scanner.nextInt();
				if (input != 1 && input != 2 && input != 0)
					throw new IllegalArgumentException();
			} catch (Exception e) {
				System.out.println("Give me a valid input.");
				continue;
			}

			// mode functions' calls
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
			System.out.print("-> To add a building press 1\n" +
							"-> To delete a building press 2\n\n" +
							"Your Selection: ");
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
		int	side = 0, type = 0, position = 0, length = 0, height = 0, colorType = 0;
		String	ownerName;

		System.out.println("\n\tAdd Building Mode\n");
		
		while (true) {

			// input for side of the building
			System.out.print("Which side do you wanna build?\n" +
						"1. Left\n" +
						"2. Right\n\n" +
						"Your selection: ");

			try {
				side = scanner.nextInt();
				if (side != 1 && side != 2)
					throw new IllegalArgumentException();
			} catch (Exception e) {
				System.out.println("Give me a valid input!");
				continue;
			}

			// input for type of the building
			System.out.println("What type?\n" +
						"1. House\n" +
						"2. Office\n" +
						"3. Market\n" +
						"4. Playground\n\n" +
						"Your selection: ");
			try {
				type = scanner.nextInt();
				if (type != 1 && type != 2 && type != 3 && type != 4)
					throw new IllegalArgumentException();
			} catch (Exception e) {
				System.out.println("Give me a valid input!");
				continue;
			}

			// input for position of the building
			System.out.print("Give me the position of the building: ");
			try {
				position = scanner.nextInt();
				if (position < 0 || position > street.getLength())
					throw new IllegalArgumentException();
			} catch (Exception e) {
				System.out.println("Give me a valid input!");
				continue;
			}

			// input for length of the building
			System.out.print("Give me the length of the building: ");
			try {
				length = scanner.nextInt();
				if (length < 0 || length > street.getLength())
					throw new IllegalArgumentException();
			} catch (Exception e) {
				System.out.println("Give me a valid input!");
				continue;
			}

			// input for height of the building
			System.out.print("Give me the height of the building:");
			try {
				height = scanner.nextInt();
				if (height < 0 || height > street.getLength())
					throw new IllegalArgumentException();
			} catch (Exception e) {
				System.out.println("Give me a valid input!");
				continue;
			}

			// input for owner name of the building
			System.out.print("Give me the owner name of the building: ");
			try {
				ownerName = scanner.next();
			} catch (Exception e) {
				System.out.println("Give me a valid input!");
				continue;
			}

			// input for color of the house
			if (type == 1) {
				System.out.println("What color?\n" +
							"1. Red\n" +
							"2. Blue\n" +
							"3. Green\n" +
							"4. Yellow\n\n" +
							"Your selection: ");
				try {
					colorType = scanner.nextInt();
					if (colorType != 1 && colorType != 2 && colorType != 3 && colorType != 4)
						throw new IllegalArgumentException();
				} catch (Exception e) {
					System.out.println("Give me a valid input!");
					continue;
				}
			}

			// create a building
			switch (type) {
				case 1:
					switch (colorType) {
						case 1:
							break;
						

					
						default:
							break;
					}
					break;

				case 2:
					
					break;
				
				case 3:
					
					break;
				
				case 4:
					
					break;
			
				default:
					break;
			}

			break;
		}
	}

	public static void	deleteBuilding(Scanner scanner, Street street) {
	}

	public static void	viewingMode(Scanner scanner, Street street) {
	}

}
