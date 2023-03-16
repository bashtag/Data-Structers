public class Statics {
	private static int	ownerNum = 0;
	private static int	buildingNum = 0;

	public static int getOwnerNum() {
		return ownerNum;
	}

	public static void increaseOwnerNum() {
		++Statics.ownerNum;
	}

	public static int getBuildingNum() {
		return buildingNum;
	}

	public static void increaseBuildingNum() {
		++Statics.buildingNum;
	}
}
