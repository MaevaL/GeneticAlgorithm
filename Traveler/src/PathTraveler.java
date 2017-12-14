import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PathTraveler {
	public static ArrayList<ArrayList<City>>pathTravelerArray = new ArrayList<ArrayList<City>>();
	public static ArrayList<Float> DistArrayByPath = new ArrayList<Float>();
	
	public PathTraveler() {
		
	}
	
	public static void GenerateAPathBetweenAllCities() {		
		Random generator = new Random();
		int randomNumber;
		ArrayList<City> c = new ArrayList<City>();
		ArrayList<City> cityList = new ArrayList<City>(City.citiesArray);
		for(int j = 0; j < City.citiesArray.size(); j++) {
			randomNumber = Math.abs(generator.nextInt()) % cityList.size();
			c.add(cityList.get(randomNumber));
			cityList.remove(randomNumber);
		}
		pathTravelerArray.add(c);
	}
	
	public static float getDistanceForAPath(int pathIndex) {
		float dist = 0;
		for(int j = 0; j < City.citiesArray.size(); j++) {
			if(j == City.citiesArray.size() - 1) {
				PathBetweenTwoCities path = new PathBetweenTwoCities(pathTravelerArray.get(pathIndex).get(0), pathTravelerArray.get(pathIndex).get(j));
				DistArrayByPath.add(pathIndex, path.getDistance());
				dist += path.getDistance();	
				break;
			}
			PathBetweenTwoCities path = new PathBetweenTwoCities(pathTravelerArray.get(pathIndex).get(j), pathTravelerArray.get(pathIndex).get(j+1));
			DistArrayByPath.add(pathIndex, path.getDistance());
			dist += path.getDistance();	
		}
		return dist;
	}
	
	
	
//	public float getDistanceForAnIndividual(int indexIndividual) {
//		float dist = 0;
//		for(int j = 0; j < nbCities; j++) {
//			if(j == (nbCities - 1)) {
//				dist += distance[individual[indexIndividual][0]][individual[indexIndividual][j]];
//				break;
//			}
//			dist += distance[individual[indexIndividual][j+1]][individual[indexIndividual][j]];
//		}
//		
//		return dist;
//	}	
		
}
