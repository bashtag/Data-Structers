package Q2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/* Chauffeur-Driven Rental Company Class */
public class CDRC {
	private BST	tree = new BST();

	/* Default Constructor */
	public CDRC() {}

	/* Constructor to get a file name */
	public CDRC(String fileName)
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
		int	index, id;

		/* initializations */
		buff = "";
		name = "";
		id = 0;
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
				++index;
			}
		}

		/* operations */
		if (buff.equals("Add_Captain"))
			addCaptain(id, name);
		else if (buff.equals("Display_captain"))
			this.printCaptain(id);
		else if (buff.equals("Is_Available"))
		{
			/* temp to get node values */
			BST	copyTree = new BST(tree);

			System.out.print("IsAvailable: ");

			if (copyTree.search(copyTree, id))
			{
				if (copyTree.root.getAvailable())
				{
					System.out.format("Reserve a new Ride with captain %d\n\n", id);
					this.rentACar(id);
				}
				else
					System.out.format("The captain %s is not available. He is on another ride!\n\n", copyTree.root.getCaptainName());
			}
			else
				System.out.format("Couldn't find any captain with ID number %d\n\n", id);
			System.out.println("----------------------------------------------------------------");
		}
		else if (buff.equals("Finish"))
		{
			/* temp to get node values */
			BST copyTree = new BST(tree);

			System.out.print("Finish: ");

			if (copyTree.search(copyTree, id))
			{
				if (!copyTree.root.getAvailable())
				{
					System.out.format("Finish ride with captain %d\n\n", id);
					/* name variable is string. But ratingStar is an integer */
					this.finishRide(id, Integer.parseInt(name));
				}
				else
					System.out.format("The captain %s is not in a ride\n", copyTree.root.getCaptainName());
			}
			else
				System.out.format("Couldn't find any captain with ID number %d\n", id);
			System.out.println("----------------------------------------------------------------");
		}
		else if (buff.equals("Delete_captain"))
		{
			/* temp to get node values */
			BST	copyBst = new BST(tree);

			System.out.print("Delete Captain:");

			if (copyBst.search(copyBst, id))
			{
				System.out.println("The captain Can left CCR");
				this.deleteCaptain(id);
			}
			else
			System.out.format(" Couldn't find any captain with ID number %d\n", id);
			System.out.println("----------------------------------------------------------------");
		}
		else if (buff.equals("Display_all_captains"))
			this.printAllCaptains();
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
	 * Function to add a captain to the database (tree)
	 * @param id
	 * @param name
	 */
	public void	addCaptain(int id, String name)
	{
		System.out.format("Add Captain: Add a new captain record in the System\n\n" +
				"\t\t\tID: %d\n" +
				"\t\t\tName: %s\n" +
				"\t\t\tAvailable: True\n" +
				"\t\t\tRatingStar: 0\n" +
				"----------------------------------------------------------------\n", id, name);
		this.tree.insert(id, name);
	}

	/**
	 * Function to Delete a captain from the database
	 * @param id
	 */
	public void	deleteCaptain(int id)
	{
		this.tree.delete(id);
	}

	/**
	 * Function to print a captain
	 * @param id
	 */
	public void	printCaptain(int id)
	{
		BST	copyBST = new BST(tree);

		System.out.print("Display Captain: ");
		if (copyBST.search(copyBST, id))
			System.out.format("\n\t\t\tID: %d\n" +
				"\t\t\tName: %s\n" +
				"\t\t\tAvailable: True\n" +
				"\t\t\tRatingStar: 0\n\n", copyBST.root.getId(), copyBST.root.getCaptainName(), copyBST.root.getAvailable(), copyBST.root.getRatingStar());
		else
			System.out.format(" Couldn't find any captain with ID number %d\n\n", id);
		System.out.println("----------------------------------------------------------------");
	}

	/**
	 * Function to print all captains
	 */
	public void	printAllCaptains()
	{
		System.out.println("----------ALL CAPTAINS----------");
		this.recPrint(this.tree.root);
	}

	/**
	 * Function to print all captains recursively
	 * @param r
	 */
	private void recPrint(BSTNode r)
	{
		if (r == null)
			return ;
		else
		{
			String	isAvailable = "True";
			if (!r.getAvailable())
				isAvailable = "False";
			System.out.format("--CAPTAIN:\n\n" +
				"\t\t\tID: %d\n" +
				"\t\t\tName: %s\n" +
				"\t\t\tAvailable: %s\n" +
				"\t\t\tRatingStar: %d\n\n", r.getId(), r.getCaptainName(), isAvailable, r.getRatingStar());
			recPrint(r.getLeft());
			recPrint(r.getRight());
		}
	}

	/**
	 * Function to change Captain's availability true to false
	 * @param id
	 */
	public void	rentACar(int id)
	{
		this.tree.root = this.tree.updateNode(tree.root, id);
	}
	
	/**
	 * Function to change Captain's availability false to true and 
	 * increase or decrease the Captain's rating star
	 * @param id
	 * @param ratingStar
	 */
	public void	finishRide(int id, int ratingStar)
	{
		this.tree.root = this.tree.updateNode(tree.root, id, ratingStar);

		/* printing part */
		/* copy the tree to get node values */
		BST	copyBst = new BST(tree);

		copyBst.search(copyBst, id);
		String	isAvailable = "True";
		if (!copyBst.root.getAvailable())
			isAvailable = "False";
		System.out.format("\n\t\t\tID: %d\n" +
			"\t\t\tName: %s\n" +
			"\t\t\tAvailable: %s\n" +
			"\t\t\tRatingStar: %d\n\n", copyBst.root.getId(), copyBst.root.getCaptainName(), isAvailable, copyBst.root.getRatingStar());
	}

	/**
	 * Function to print quit writing
	 */
	public void	quit()
	{
		System.out.println("Thank you for using CDRC, Good Bye!");
	}
}