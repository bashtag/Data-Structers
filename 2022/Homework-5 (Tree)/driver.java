import java.util.Arrays;

public class driver {
	
	public static void main(String[] args) throws Exception {
		testBsArr();
		// testBst();
		// testBinHeap();
	}

	private static void	testBsArr() throws Exception {
		BinarySearchArray<Integer>	bsa0 = new BinarySearchArray<Integer>(Integer.class, 10);
		bsa0.insert(5);
		bsa0.insert(10);
		bsa0.insert(20);
		bsa0.insert(3);
		bsa0.insert(2);
		bsa0.insert(13);
		bsa0.insert(14);
		bsa0.insert(31);
		bsa0.insert(9);
		// bsa0.insert(5);
		bsa0.insert(15);
		bsa0.insert(29);
		bsa0.insert(18);
		// bsa0.insert(3);
		bsa0.insert(-5);
		// bsa0.insert(10);
		// bsa0.insert(9);
		bsa0.insert(6);
		// bsa0.insert(13);
	
		System.out.println(bsa0);
		System.out.println("---------------------------------");
		
		bsa0.delete(10);
		System.out.println(bsa0);
		System.out.println("---------------------------------");
		bsa0.delete(100);
		System.out.println(bsa0);
		System.out.println(bsa0.contains(15));

		String myst = bsa0.serialize();
		String[] arr = myst.split(" ");
		System.out.println(Arrays.toString(arr));
		System.out.println("----------DES_PART----------------");
	
		BinarySearchTree<Integer>	deserialize = BinarySearchTree.deserialize(myst);
		System.out.println(deserialize);
	}

	private static void	testBinHeap() {
		BinaryHeap<Integer>	hehe = new BinaryHeap<>(12);
		hehe.insertKey(40);
		hehe.insertKey(10);
		hehe.insertKey(40);
		hehe.insertKey(15);
		hehe.insertKey(50);
		hehe.insertKey(100);
		hehe.insertKey(30);
		System.out.println(hehe);
		System.out.println("---------------------");
		BinaryHeap<Integer>	abo = new BinaryHeap<>(5);
		abo.insertKey(30);
		abo.insertKey(20);
		abo.insertKey(100);
		abo.insertKey(10);
		System.out.println(abo);
		System.out.println("---------merge_part--------");
		hehe.merge(abo);
		System.out.println(hehe);
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
