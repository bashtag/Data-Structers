public class Driver {
	
	public static void main(String[] args) {
		House	house = new House("hehe", 0, null, 15, 5, 0);
		House	house2 = new House("lolo", 0, null, 26, 26, 15);
		House	house3 = new House("lele", 0, null, 31, 13, 3);
		House	house4 = new House("bro", 0, null, 9, 7, 18);

		Street	street = new Street(50);


		street.addBuilding(house, true);
		street.addBuilding(house2, true);
		street.addBuilding(house3, false);
		street.addBuilding(house4, false);

		street.printSilhouette();
	}
}
