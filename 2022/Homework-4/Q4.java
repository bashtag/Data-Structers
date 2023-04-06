/**
 * not finished yet
 */

public class Q4 {
	public static int	foo(int int1, int int2) {

		if (int1 < 10 || int2 < 10)
			return (int1 * int2);

		int	n, sub0, sub1, sub2;
		int[]	splitNums1, splitNums2;

		n = Math.max(numOfDigits(int1), numOfDigits(int2));
		splitNums1 = splitInteger(int1, int1 / 2);
		splitNums2 = splitInteger(int2, int2 / 2);

		sub0 = foo(splitNums1[1], splitNums2[1]);
		sub1 = foo((splitNums1[1] + splitNums1[0]), (splitNums2[1] + splitNums2[0]));
		sub2 = foo(splitNums1[0], splitNums2[0]);

		return (1);
	}

	private static int	numOfDigits(int num) {
		return (1);
	}

	private static int[]	splitInteger(int num, int del) {

		return (new int[]{});
	}
}
