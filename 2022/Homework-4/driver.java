public class driver {
	public static void main(String[] args) {
		
		// Test Question - 1
		test_q1();
		
		// Test Question - 2
		test_q2();

		// Test Question - 3
		test_q3();
	}

	public static void	test_q1() {
		long	prev;
		System.out.println("\n-------------------test_q1----------------");
		System.out.println("Note: time complexity should be O(n) (n = big.length())");
		prev = System.nanoTime();
		System.out.println(Q1.recStrStr("asdc","asdcasdcasdcasdcasdcasdc", 6));
		System.out.println("Time: " + (System.nanoTime() - prev));
		prev = System.nanoTime();
		System.out.println(Q1.recStrStr("asdce","asdceasdceasdceasdceasdceasdceasdceasdceasdceasdceasdceasdce", 12));
		System.out.println("Time: " + (System.nanoTime() - prev));
		prev = System.nanoTime();
		System.out.println(Q1.recStrStr("adscek","adscekadscekadscekadscekadscekadscekadscekadscekadscekadscekadscekadscekadscekadscekadscekadscekadscekadscek"
										, 18));
		System.out.println("Time: " + (System.nanoTime() - prev));
		prev = System.nanoTime();
		System.out.println(Q1.recStrStr("adsceki","adscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadscekiadsceki"
										, 24));
		System.out.println("Time: " + (System.nanoTime() - prev));
		prev = System.nanoTime();
		System.out.println(Q1.recStrStr("adcekips","adcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekipsadcekips"
										, 30));
		System.out.println("Time: " + (System.nanoTime() - prev));
		System.out.println("-------------------test_q1_finished----------------");
	}

	public static void	test_q2() {
		long	prev;
		int[]	testArr1, testArr2, testArr3, testArr4, testArr5;
		System.out.println("\n-------------------test_q2----------------");
		System.out.println("Note: time complexity should be O(logn) cause of binary search approach");

		testArr1 = sortedArr(100);
		testArr2 = sortedArr(200);
		testArr3 = sortedArr(300);
		testArr4 = sortedArr(400);
		testArr5 = sortedArr(500);

		prev = System.nanoTime();
		System.out.println(Q2.recIntsNum(testArr1, 75, 100));
		System.out.println("Time: " + (System.nanoTime() - prev));

		prev = System.nanoTime();
		System.out.println(Q2.recIntsNum(testArr2, 175, 200));
		System.out.println("Time: " + (System.nanoTime() - prev));

		prev = System.nanoTime();
		System.out.println(Q2.recIntsNum(testArr3, 275, 300));
		System.out.println("Time: " + (System.nanoTime() - prev));

		prev = System.nanoTime();
		System.out.println(Q2.recIntsNum(testArr4, 375, 400));
		System.out.println("Time: " + (System.nanoTime() - prev));

		prev = System.nanoTime();
		System.out.println(Q2.recIntsNum(testArr5, 475, 500));
		System.out.println("Time: " + (System.nanoTime() - prev));

		System.out.println("-------------------test_q2_finished----------------");
	}

	public static int[]	sortedArr(int size) {
		int[]	res = new int[size];

		for (int i = 0; i < size; i++)
			res[i] = i;

		return (res);
	}

	public static void	test_q3() {
		long	prev;
		int[]	testArr1, testArr2, testArr3;

		testArr1 = new int[]{10, 9, 8, 2, -5, 32, 7};
		testArr2 = new int[]{1, 0, 1, 1, 1, 1, 0, -2, 1, 1, 1, 1, 1};
		testArr3 = new int[]{3, 4, 2, 1, -5};
		System.out.println("\n-------------------test_q3----------------");
		System.out.println("Note: time complexity should be O(n)");
		prev = System.nanoTime();
		Q3.findSubarraySum(testArr1, 39);
		System.out.println("Time: " + (System.nanoTime() - prev));
		System.out.println("Note: time complexity should be O(n)");
		prev = System.nanoTime();
		Q3.findSubarraySum(testArr2, 8);
		System.out.println("Time: " + (System.nanoTime() - prev));
		System.out.println("Note: time complexity should be O(n)");
		prev = System.nanoTime();
		Q3.findSubarraySum(testArr3, 10);
		System.out.println("Time: " + (System.nanoTime() - prev));
		System.out.println("-------------------test_q3_finished----------------");

	}
}
