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
	}
}
