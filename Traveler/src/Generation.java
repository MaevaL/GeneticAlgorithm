import java.util.Random;

public class Generation {
	long seed;
	public static Random generator;
	
	public Generation(long seed) {
		this.seed = seed;
		generator = new Random(seed);
	}
	
	public void GenerateCities(int nbCities) {
		for(int i = 0; i < nbCities; i++) {
			City.citiesArray.add(new City());
		}		
	}
}
