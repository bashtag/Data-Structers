import java.util.Arrays;

public class driver {
	
	public static void main(String[] args) throws Exception {
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
	}
}
