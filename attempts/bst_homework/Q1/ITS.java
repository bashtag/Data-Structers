package Q1;
import java.io.FileInputStream;
import	java.io.IOException;
import	java.util.*;

/* Inventory Tracking System */
public class	ITS{
	private BST	tree = new BST();

	/* Default Constructor */
	public ITS() {}
	
	/* Constructor to get a file name */
	public ITS(String fileName)
	{
		readFile(fileName);
	}
	
	/**
	 * Function to get a line and make some binary search tree operations
	 * @param line
	 */
	public void	lineToBST(String line)
	{
		/* buff for operation name */
		String	buff, name;
		/* lineArr for strings in the line */
		String	lineArr[];
		int	index, id, piece;

		/* initializations */
		buff = "";
		name = "";
		id = 0;
		piece = 0;
		index = 0;
		lineArr = line.split(" ");
		
		for (var element : lineArr)
		{
			if (element != "")
			{
				if (index == 0)
					buff = element;
				else if (index == 1)
					id = Integer.parseInt(element);
				else if (index == 2)
					name = element;
				else if (index == 3)
					piece = Integer.parseInt(element);
				++index;
			}
		}

		/* operations */
		if (buff.equals("Add_product"))
			add_product(id, name, piece);
		else if (buff.equals("Is_Available"))
		{
			/* temp to get piece value */
			BSTNode	temp = new BSTNode();
			if (is_available(id, temp))
			{
				System.out.format("There are %d products\n\n", temp.getPiece());
			}
			else
				System.out.println("The product is out of stock!!!\n");
		}
		else if (buff.equals("Quit"))
			quit();
	}
	
	/**
	 * Function to read each database commands from file
	 * @param fileName
	 */
	public void	readFile(String fileName)
	{
		FileInputStream	file;
		Scanner	scanner;
		
		System.out.println("--------------- WELCOME TO ITS ---------------");
		try {
			file = new FileInputStream(fileName);
			scanner = new Scanner(file);

			while (scanner.hasNextLine())
			{
				lineToBST(scanner.nextLine());
			}

			scanner.close();
			file.close();
		} catch (IOException e) {
			System.out.println("Something went wrong.");
			e.printStackTrace();
		}
	}

	/**
	 * Function to add a product to the database (tree)
	 * @param id
	 * @param name
	 * @param piece
	 */
	public void	add_product(int id, String name, int piece)
	{
		System.out.format("Create Product:\n" +
				"\t\t\tID: %d\n" +
				"\t\t\tName: %s\n" +
				"\t\t\tPiece: %d\n", id, name, piece);
		this.tree.insert(id, name, piece);
	}

	/**
	 * Function to return is this id in the database or not
	 * @param id
	 * @param res
	 * @return
	 */
	public Boolean	is_available(int id, BSTNode res)
	{
		return (tree.search(id, res));
	}

	public void	quit()
	{
		System.out.println("Thank you for using ITS, Good Bye!");
	}
}