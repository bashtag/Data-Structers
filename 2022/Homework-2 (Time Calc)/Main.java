import java.util.Random;

class	Main {
	public static void main(String[] args) {

		findSum(makeArr(6), 32);
		findSum(makeArr(36), 32);
		findSum(makeArr(216), 32);
		findSum(makeArr(216 * 3), 32);
		System.out.println();

		int	arr1[] = makeArr(6);

		long	start1 = System.nanoTime();
		recFindSum(arr1, 1, 32, arr1[0]);
		System.out.println("Execution time is " + (System.nanoTime() - start1) + " nanoseconds for 6 inputs (rec)");

		int	arr2[] = makeArr(36);

		long	start2 = System.nanoTime();
		recFindSum(arr2, 1, 32, arr2[0]);
		System.out.println("Execution time is " + (System.nanoTime() - start2) + " nanoseconds for 36 inputs (rec)");
	}

	public static int[]	makeArr(int size) {
		int	result[] = new int[size];
		Random	rand = new Random();

		for (int i = 0; i < size; ++i) {
			result[i] = rand.nextInt(100);
		}

		return (result);
	}

	/**
	 * Theoritically time complexity is O(n^2)
	 * @param givenArr
	 * @param sum
	 * @return
	 */
	public static void	findSum(int[] givenArr, int sum) {
		
		long start = System.nanoTime();
		
		for (int i = 0; i < givenArr.length; ++i) {
			for (int j = i + 1; j < givenArr.length; ++j) {
				if (givenArr[i] + givenArr[j] == sum);
					// System.out.println("pair -> " + i + ", " + j);
			}
		}

		long end = System.nanoTime();
		System.out.println("Execution time is " + (end - start) + " nanoseconds for " + givenArr.length + " inputs");
	}

	/**
	 * Theoritically time complexity is O(n^2)
	 * 
	 * @param givenArr
	 * @param index -> 1
	 * @param sum
	 * @param prevNum -> givenArr[0]
	 */
	public static void	recFindSum(int[] givenArr, int index, int sum, int prevNum) {
			
		if (index >= givenArr.length)
			return;
		else if (givenArr[index] + prevNum == sum);
			// System.out.println("pair -> " + givenArr[index] + ", " + prevNum);

		recFindSum(givenArr, index + 1, sum, prevNum);
		if (index + 2 < givenArr.length)
			recFindSum(givenArr, index + 2, sum, givenArr[index + 1]);
	}
}