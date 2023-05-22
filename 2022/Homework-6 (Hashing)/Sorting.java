import java.lang.reflect.Array;

public class Sorting {

	/**
	 * Overload
	 * @param <T>
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void	newSort(T[] arr) {
		newSort(arr, 0, arr.length - 1);
	}

	/**
	 * A sorting algorithm written in homework pdf
	 * O(n^2)
	 * @param <T>
	 * @param arr
	 * @param head
	 * @param tail
	 */
	public static <T extends Comparable<? super T>> void	newSort(T[] arr, int head, int tail) {
		if (head > tail)
			return ;
		
		int[]	minMax = minMaxFinder(arr, head, tail);
		swap(arr, head, minMax[0], minMax);
		swap(arr, tail, minMax[1], minMax);
		newSort(arr, head + 1, tail - 1);
	}

	/**
	 * minimum and maximum finder
	 * @param <T>
	 * @param arr
	 * @param head
	 * @param tail
	 * @return
	 */
	private static <T extends Comparable<? super T>> int[]	minMaxFinder(T[] arr, int head, int tail) {
		int[]	minMax = new int[2];
		minMax[0] = head;
		minMax[1] = tail;

		return (recMinMaxFinder(arr, head, tail, minMax));
	}

	/**
	 * recursive minimum maximum finder
	 * O(n)
	 */
	private static <T extends Comparable<? super T>> int[]	recMinMaxFinder(T[] arr, int head, int tail, int[] minMax) {
		if (head > tail)
			return (minMax);

		if (arr[head].compareTo(arr[minMax[0]]) < 0) // min
			minMax[0] = head;
		if (arr[head].compareTo(arr[minMax[1]]) > 0) // max
			minMax[1] = head;

		return (recMinMaxFinder(arr, head + 1, tail, minMax));
	}

	/**
	 * swap for new sort
	 * @param <T>
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static <T extends Comparable<? super T>> void	swap(T[] arr, int i, int j, int[] minMax) {

		if (i == minMax[1])
			minMax[1] = j;
		
		T	buff = arr[i];
		arr[i] = arr[j];
		arr[j] = buff;
	}

	/**
	 * QuickSort algorithm. 
	 * Pivot is the last element everytime.
	 * worst case O(n^2)
	 * best and average case is O(nlogn)
	 * @param <T>
	 * @param arr
	 * @param beginIndex
	 * @param endIndex
	 */
	public static <T extends Comparable<? super T>> void	quickSort(T[] arr, int beginIndex, int endIndex) {
		if (beginIndex >= endIndex)
			return;
		
		/*
		 * pivot is partitioning index.
		 * final state of the pivot index.
		 */
		int	pivot = partition(arr, beginIndex, endIndex);
		quickSort(arr, beginIndex, pivot - 1);
		quickSort(arr, pivot + 1, endIndex);
	}

	/**
	 * Overload
	 * @param <T>
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void	quickSort(T[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 * partition part
	 * @param <T>
	 * @param arr
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	private static <T extends Comparable<? super T>> int	partition(T[] arr, int beginIndex, int endIndex) {
		int	i = beginIndex;
		T	pivot = arr[endIndex], buff;

		for (int j = beginIndex; j <= endIndex; ++j) {
			if (arr[j].compareTo(pivot) < 0 || j == endIndex) {
				buff = arr[j];
				arr[j] = arr[i];
				arr[i] = buff;
				++i;
			}
		}

		return (i - 1);
	}


	/*
	 * first subarray is [l..m]
	 * secons subarray is [m+1..r]
	 */
	public static <T extends Comparable<? super T>> void	mergeSort(Class<T> classe, T[] arr, int beginIndex, int endIndex) {
		int	mid;

		if (beginIndex >= endIndex ||beginIndex < 0 || beginIndex > arr.length ||
			endIndex < 0 || endIndex > arr.length)
			return;

		mid = (beginIndex + endIndex) / 2;

		/* logn time */
		mergeSort(classe, arr, beginIndex, mid);
		mergeSort(classe, arr, mid + 1, endIndex);
		merge(classe, arr, beginIndex, mid, endIndex);
	}

	/**
	 * O(nlogn)
	 * @param <T>
	 * @param classe
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void	mergeSort(Class<T> classe, T[] arr) {
		mergeSort(classe, arr, 0, arr.length - 1);
	}

	/**
	 * first subarray is [l..m]
	 * secons subarray is [m+1..r]
	 * @param <T>
	 * @param arr
	 * @param beginIndex l
	 * @param mid
	 * @param endIndex r
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void	merge(Class<T> classe, T[] arr, int beginIndex, int mid, int endIndex) {
		int	size1, size2, i, j, k;
		T[]	arr1, arr2;

		size1 = mid - beginIndex + 1;
		size2 = endIndex - mid;

		arr1 = (T[])Array.newInstance(classe, size1);
		arr2 = (T[])Array.newInstance(classe, size2);

		for (i = 0; i < size1; ++i)
			arr1[i] = arr[beginIndex + i];
		for (i = 0; i < size2; ++i)
			arr2[i] = arr[mid + 1 + i];

		i = 0;
		j = 0;
		k = beginIndex;

		/* merge 
		 * n time
		*/
		while (i < size1 && j < size2) {
			if (arr1[i].compareTo(arr2[j]) <= 0)
				arr[k++] = arr1[i++];
			else
				arr[k++] = arr2[j++];
		}

		while (i < size1)
			arr[k++] = arr1[i++];
		
		while (j < size2)
			arr[k++] = arr2[j++];
	}
}
