public class Q3 {

	public static void	findSubarraySum(int[] nums, int given) {
		helper(nums, 0, 0, 0, given);
	}

	private static boolean	helper(int[] nums, int prevIndex, int index, int total, int given) {

		if (total == given)
		{
			System.out.println("Range -> " + prevIndex + " - " + index);
			return (true);
		}
		
		if (prevIndex == nums.length)
			return (false);
		else if (index == nums.length)
			return (helper(nums, prevIndex + 1, prevIndex + 1, 0, given));
		
		if (total < given)
			return (helper(nums, prevIndex, index + 1, total + nums[index], given));
		
		// if total > given
		return (helper(nums, prevIndex + 1, prevIndex + 1, 0, given));
	}
}
