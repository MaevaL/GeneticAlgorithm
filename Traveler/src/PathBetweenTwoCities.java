import java.util.ArrayList;
import java.util.Random;

public class PathBetweenTwoCities {
	
	private float distanceBetweenTwoCities;
	private City start;
	private City end;
	public static ArrayList<PathBetweenTwoCities> pathArray = new ArrayList<PathBetweenTwoCities>();

	public PathBetweenTwoCities(City start, City end) {
		this.start = start;
		this.end = end;
		distanceBetweenTwoCities = calculDistanceBetweenTwoCities();
	}
	
	private float calculDistanceBetweenTwoCities() {
		return start.getLocation().getDistance(end.getLocation());
	}
	
	public static void GenerateMatrice() {
		for(int i = 0; i < City.citiesArray.size(); i++) {
			for(int j = 0; j < City.citiesArray.size(); j++) {
				if(i == j) {
					continue;
				} else {
					pathArray.add(new PathBetweenTwoCities(City.citiesArray.get(i), City.citiesArray.get(j)));
				}
				
			}
		}
	}
	
	public float getDistance() {
		return distanceBetweenTwoCities;
	}
	
	public String toString() {
		return new String("Start : " + start.getIndex() + " End : " + end.getIndex() + " Dist : " + distanceBetweenTwoCities);
	}
}

