import java.util.Arrays;
public class Driver {

	public static void main(String[] args) {
		// testArrayToBST();
		testAVLTree();
	}

	@SuppressWarnings("unchecked")
	public static void	testAVLTree() {
		AVLTree	avl = new AVLTree();
		
		avl.add(10);
		avl.add(20);
		avl.add(30);
		avl.add(40);
		avl.add(50);
		avl.add(25);
		
		System.out.println(avl);
	}

	/**
	 * Test for array to bst method
	 */
	@SuppressWarnings("unchecked")
	public static void	testArrayToBST() {
		BinaryTree tree = new BinaryTree();
		BinarySearchTree BST = new BinarySearchTree();
		
		tree.head = new BinaryTree.Node<>(3);;
		tree.head.left = new BinaryTree.Node<>(3);;
		tree.head.right = new BinaryTree.Node<>(3);;
		tree.head.right.right = new BinaryTree.Node<>(3);
		tree.head.right.right.right = new BinaryTree.Node<>(3);
		tree.head.right.right.right.left = new BinaryTree.Node<>(3);
		tree.head.right.right.left = new BinaryTree.Node<>(3);
		tree.head.left.left = new BinaryTree.Node<>(3);
		tree.head.left.right = new BinaryTree.Node<>(3);
		tree.head.left.right.left = new BinaryTree.Node<>(3);

		Comparable [] array = {1,2,3,4,5,6,7,8,9,10,11};
		System.out.printf("BINARY TREE:\n\n%s\n\n\n\n",tree.toString()) ;

		BST = arrayToBST(tree,array);

		System.out.printf("BST:\n\n%s",BST.toString());
		

		
		
		BinaryTree tree2 = new BinaryTree();
		BinarySearchTree BST2 = new BinarySearchTree();
		
		tree2.head = new BinaryTree.Node<>(3);
		tree2.head.left = new BinaryTree.Node<>(3);
		tree2.head.right = new BinaryTree.Node<>(3);
		tree2.head.right.right = new BinaryTree.Node<>(3);
		tree2.head.right.right.right = new BinaryTree.Node<>(3);
		tree2.head.right.right.right.left = new BinaryTree.Node<>(3);

		Comparable [] array2 = {1,2,3,4,5,6};

		BST2 = arrayToBST(tree2,array2);

		System.out.printf("\n\n\n\nEXAMPLE 2:\nBINARY TREE:\n\n%s\n\n\n\n",tree2.toString()) ;
		System.out.printf("BST:\n\n%s",BST2.toString());
		
		
		
		
		
		
		BinaryTree tree3 = new BinaryTree();
		BinarySearchTree BST3 = new BinarySearchTree();

		Comparable [] array3 = {1,2,3,4,5,6,7,8};
		
		tree3.head = new BinaryTree.Node<>(3);
		tree3.head.right = new BinaryTree.Node<>(3);
		tree3.head.left = new BinaryTree.Node<>(3);
		tree3.head.right.left = new BinaryTree.Node(3);
		tree3.head.right.left.left = new BinaryTree.Node<>(3);
		tree3.head.right.left.left.left = new BinaryTree.Node<>(3);
		tree3.head.left.left = new BinaryTree.Node<>(3);
		tree3.head.left.left.left = new BinaryTree.Node<>(3);

		BST3 = arrayToBST(tree3,array3);

		System.out.printf("\n\n\n\nEXAMPLE 3:\nBINARY TREE:\n\n%s\n\n\n\n",tree3.toString()) ;
		System.out.printf("BST:\n\n%s",BST3.toString());
		
		
		
		
		
		
		BinaryTree tree4 = new BinaryTree();
		BinarySearchTree BST4 = new BinarySearchTree();

		Comparable [] array4 = {1,2,3,4,5};
		
		tree4.head = new BinaryTree.Node<>(3);
		tree4.head.right = new BinaryTree.Node<>(3);
		tree4.head.left = new BinaryTree.Node<>(3);
		tree4.head.left.left = new BinaryTree.Node<>(3);
		tree4.head.left.left.left = new BinaryTree.Node<>(3);
		
		BST4 = arrayToBST(tree4,array4);

		System.out.printf("\n\n\n\nEXAMPLE 4:\nBINARY TREE:\n\n%s\n\n\n\n",tree4.toString()) ;
		System.out.printf("BST:\n\n%s",BST4.toString());
		
		BinaryTree tree5 = new BinaryTree();
		BinarySearchTree BST5 = new BinarySearchTree();

		Comparable [] array5 = {1,2,3,4};
		
		tree5.head = new BinaryTree.Node<>(3);
		tree5.head.left = new BinaryTree.Node(3);
		tree5.head.right = new BinaryTree.Node<>(3);
		tree5.head.right.right = new BinaryTree.Node<>(3);

		BST5 = arrayToBST(tree5,array5);

		System.out.printf("\n\n\n\nEXAMPLE 5:\nBINARY TREE:\n\n%s\n\n\n\n",tree5.toString()) ;
		System.out.printf("BST:\n\n%s",BST5.toString());
		
		BinaryTree tree6 = new BinaryTree();
		BinarySearchTree BST6 = new BinarySearchTree();

		Comparable [] array6 = {1,2,3,4};
		
		tree6.head = new BinaryTree.Node<>(3);
		tree6.head.left = new BinaryTree.Node<>(3);
		tree6.head.left.left = new BinaryTree.Node(3);
		tree6.head.left.left.right = new BinaryTree.Node<>(3);

		BST6 = arrayToBST(tree6,array6);

		System.out.printf("\n\n\n\nEXAMPLE 6:\nBINARY TREE:\n\n%s\n\n\n\n",tree6.toString()) ;
		System.out.printf("BST:\n\n%s",BST6.toString());
	}

	/**
	 * Array to BST using a BT structure
	 * @param bt structure
	 * @param arr elements to add the tree
	 * @return
	 */
	public static BinarySearchTree	arrayToBST(BinaryTree bt, Comparable[] arr) {
		Arrays.sort(arr);
		int	index[] = new int[]{0};
		recArrayToBST(bt, arr, index);

		return (new BinarySearchTree(bt));
	}

	/**
	 * Recursive solution approach.
	 * Inorder Traversal is used.
	 * @param bt
	 * @param arr
	 * @param indexRef
	 */
	@SuppressWarnings("unchecked")
	private static void	recArrayToBST(BinaryTree bt, Comparable[] arr, int[] indexRef) {
		if (bt.head == null)
			return ;

		recArrayToBST(bt.getLeftSubTree(), arr, indexRef);

		bt.changeData(arr[indexRef[0]]);

		++indexRef[0];
		recArrayToBST(bt.getRightSubTree(), arr, indexRef);
	}
}