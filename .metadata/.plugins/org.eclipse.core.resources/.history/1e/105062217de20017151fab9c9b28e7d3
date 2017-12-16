import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class PathTraveler {
	public static List<List<City>>pathTravelerArray = new ArrayList<List<City>>();
	public static int indexBest;
	
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
				dist += path.getDistance();	
				break;
			}
			PathBetweenTwoCities path = new PathBetweenTwoCities(pathTravelerArray.get(pathIndex).get(j), pathTravelerArray.get(pathIndex).get(j+1));
			dist += path.getDistance();	
		}
		return dist;
	}
	
	public static float getDistance(List<City> array) {
		float dist = 0;
		for(int j = 0; j < array.size(); j++) {
			if(j == array.size() - 1) {
				PathBetweenTwoCities path = new PathBetweenTwoCities(array.get(0), array.get(j));				
				dist += path.getDistance();	
				break;
			}
			PathBetweenTwoCities path = new PathBetweenTwoCities(array.get(j), array.get(j+1));
			dist += path.getDistance();	
		}
		return dist;
	}
	
	public static float getBestDistance() {
		float bestDist = Float.POSITIVE_INFINITY;
		float dist1 = 0;
		float dist2 = 0;
		int indexPath = -1;
		for(int i = 0; i < pathTravelerArray.size() - 1; i++) {
			dist1 = getDistanceForAPath(i);
			dist2 = getDistanceForAPath(i+1);
			if(dist1 < dist2 && dist1 < bestDist) {
				bestDist = dist1;
				indexPath = i;
			} else if (dist2 < dist1 && dist2 < bestDist) {	
				bestDist = dist2;
				indexPath = i+1;
			}
		}
		indexBest = indexPath;
		return bestDist;
	}
	
	public static List getNBest(int n) {
		List<List<City>> temporary = new ArrayList<List<City>>(pathTravelerArray);
		Collections.sort(temporary, new Comparator<List<City>>() {

			@Override
			public int compare(List<City> o1, List<City> o2) {
				float d1 = getDistance(o1);
				float d2 = getDistance(o2);
				
				if(d1 > d2) {
					return 1;
				} else if (d1 < d2) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		return temporary.subList(0, n);
	}
	
}
