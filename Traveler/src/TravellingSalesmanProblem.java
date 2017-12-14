
public class TravellingSalesmanProblem {

	public TravellingSalesmanProblem() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Generation generation = new Generation(100);
		generation.GenerateCities(10);
		for(City city : City.citiesArray) {
			System.out.println(city.toString());
		}
		
		System.out.println("\n");
		PathBetweenTwoCities.GenerateMatrice();
		
		for(PathBetweenTwoCities p : PathBetweenTwoCities.pathArray) {
			System.out.println(p.toString());
		}
		
		for(int i = 0; i < 10; i++) {
			PathTraveler.GenerateAPathBetweenAllCities();
		}
		
		for(int i = 0; i < PathTraveler.pathTravelerArray.size(); i++) {
			for(int j =0; j < City.citiesArray.size(); j++)
				System.out.println("IndexPath : " + PathTraveler.pathTravelerArray.indexOf(PathTraveler.pathTravelerArray.get(i))+ " CityIndex : " + PathTraveler.pathTravelerArray.get(i).get(j).getIndex());
		}
		
		for(int i = 0; i < PathTraveler.pathTravelerArray.size(); i++) {
			System.out.println(PathTraveler.getDistanceForAPath(i));
		}

	}
}
