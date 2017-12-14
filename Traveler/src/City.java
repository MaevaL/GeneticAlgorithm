import java.util.ArrayList;
import java.util.Random;

public class City {

	private int indexCity = 0;
	private static int index = -1;
	private Vec2 location;
	public static ArrayList<City> citiesArray = new ArrayList<City>();
	
	public City(long seed, Random generator) {
		index++;
		indexCity = index;
		if(index == 0) {	
			location = generateRandomLocation(seed, generator);
		} else {
			Vec2 temporaryLocation = generateRandomLocation(seed, generator);
			while(isThisLocationTaken(temporaryLocation)) {}
			location = temporaryLocation;
		}				
	}
	
	public City() {
		location = new Vec2(0,0);
	}
	
	public Vec2 getLocation() {
		return location;
	}
	
	public int getIndex() {
		return indexCity;
	}
	
	
	public Boolean isThisLocationTaken(Vec2 location) {
		for(City c : citiesArray){
			if(c.location == location) {
				return true;
			}
		}
		return false;
	}
	
	public Vec2 generateRandomLocation(long seed, Random generator) {
		 return new Vec2(generator.nextFloat() * (2), generator.nextFloat() * (2));
	}
	
	public String toString() {
		return new String("Index : " + indexCity + " location : " + location.toString());
	}

}
