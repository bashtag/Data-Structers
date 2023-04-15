import java.util.Arrays;

public class driver {
	
	public static void main(String[] args) throws Exception {
		testBst();
	}
	
	/**
	 * testing bst structure and question 1
	 * @throws Exception
	 */
	private static void	testBst() throws Exception{
		BinarySearchTree<Integer>	bst0 = new BinarySearchTree<Integer>();
	
		bst0.insert(5);
		bst0.insert(15);
		bst0.insert(29);
		bst0.insert(18);
		bst0.insert(3);
		bst0.insert(-5);
		bst0.insert(10);
		bst0.insert(9);
		bst0.insert(6);
		bst0.insert(13);
	
		System.out.println(bst0);
		System.out.println("---------------------------------");
		
		bst0.delete(10);
		System.out.println(bst0);
		System.out.println("---------------------------------");
		bst0.delete(100);
		System.out.println(bst0);
	
		String myst = bst0.serialize();
		String[] arr = myst.split(" ");
		System.out.println(Arrays.toString(arr));
		System.out.println("----------DES_PART----------------");
	
		BinarySearchTree<Integer>	deserialize = BinarySearchTree.deserialize(myst);
		System.out.println(deserialize);

		System.out.println("-------------Q-1-A----------------");
		BinarySearchTree<Integer>	q1a = new BinarySearchTree<>();

		q1a.insert(10);
		q1a.insert(5);
		q1a.insert(12);
		System.out.println(q1a);
		System.out.println("Total depth: " + q1a.totalDepth());

		System.out.println("-------------Q-1-B------------------");
		BinarySearchTree<Integer>	q1b = BinarySearchTree.createFullBST(8191);

		System.out.println("Average number of compatitions in a bst with 8192 elements: " + calcAverageCompNum(q1b, 8191));

		System.out.println("--------------Q-1-C-----------------");
		System.out.println("Yes there is a restriction on the number of nodes in a full binary tree.\n" +
						"Have to be a form of powers of two.");
		System.out.println("For a bst with 8192 elements:" +
						"\nNumber of internal nodes: " + q1b.calcInternals() +
						"\nNumber of leaf nodes: " + q1b.calcLeaves());

	}

	/**
	 * Calculate average comparision number of a complete bst.
	 * O(nlogn) -> contains function and for statement
	 * @param bst
	 * @param lastElement
	 * @return
	 */
	private static double	calcAverageCompNum(BinarySearchTree<Integer> bst, int lastElement) {
		int[]	comparisionNum = new int[]{0};
		int	res = 0;

		for (int i = 0; i < lastElement; ++i) {
			bst.contains(i, comparisionNum);
			res += comparisionNum[0];
			comparisionNum[0] = 0;
		}

		return (res / (double)(lastElement + 1));
	}
}
