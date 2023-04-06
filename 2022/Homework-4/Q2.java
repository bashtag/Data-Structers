public class Q2 {

	public static int	recIntsNum(int[] array, int num1, int num2) {
		return (helper(array, 0, array.length - 1, num1, num2));
	}

	private static int	helper(int[] array, int min, int max, int num1, int num2) {
		
		if (min > max)
			return (0);

		int	mid = (min + max) / 2;

		if (array[mid] < num1)
			return (helper(array, mid + 1, max, num1, num2));

		if (array[mid] > num2)
			return (helper(array, min, mid - 1, num1, num2));

		// else

		/**
		 * add 1 and search another numbers in num1, num2 range
		 * 1- Search for right numbers
		 * 2- Search for left numbers
		 */
		return (1 + helper(array, mid + 1, max, num1, num2) +
			helper(array, min, mid - 1, num1, num2));
	}
}
