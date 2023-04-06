public class	Q1 {
	public static int	recStrStr(String query, String big, int occurence) {
		return (helper(query, big, 0, 0, 0, occurence));
	}

	private static int	helper(String query, String big, int qIndex, int bIndex, int occurence, int targetOccurence) {
		if (occurence == targetOccurence)
			return (bIndex - query.length());
		else if (bIndex == big.length())
			return (-1);
		else if (query.charAt(qIndex) == big.charAt(bIndex))
		{
			if (qIndex == query.length() - 1)
				return (helper(query, big, 0, bIndex + 1, occurence + 1, targetOccurence));
			return (helper(query, big, qIndex + 1, bIndex + 1, occurence, targetOccurence));
		}
		return (helper(query, big, 0, bIndex + 1, occurence, targetOccurence));
	}
}