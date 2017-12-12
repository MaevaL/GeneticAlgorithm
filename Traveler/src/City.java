import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class City {

	public float[][] distance;
	public ArrayList<Vec2> location;
	public int nbCities;
	public int[][] individual;
	public int nbIndividuals;
	
	public City(int nbCities, long seed) {
		this.nbCities = nbCities;
		location = new ArrayList<Vec2>(nbCities);
		distance = new float[nbCities][nbCities];
		
		GenerateCitiesLocation(seed);
		GenerateMatriceDistance();
		
	}
		
	public void GenerateCitiesLocation(long seed) {
		Random generator = new Random(seed);
		for(int i = 0; i < nbCities * 2 ; i++) {
			location.add(new Vec2(generator.nextFloat() * (2), generator.nextFloat() * (2)));
		}	
	}
	
	
	public void GenerateMatriceDistance() {
		for(int i = 0; i < nbCities; i++) {
			for(int j = 0; j < nbCities; j++) {
				if(i == j) {
					distance[i][j] = -1;
				}
				
				distance[i][j] = location.get(i).getDistance(location.get(j));
			}
		}
	}
	
	public void GenerateIndividuals(long seed, int nbIndividuals) {
		this.nbIndividuals = nbIndividuals;
		individual = new int[nbIndividuals][nbCities];
		
		Random generator = new Random();
		int randomNumber;
		for(int i = 0; i < nbIndividuals; i++) {
			ArrayList<Integer> cityList = GenerateCityList();
			for(int j = 0; j < nbCities; j++) {
				randomNumber = Math.abs(generator.nextInt()) % cityList.size();
				individual[i][j] = cityList.get(randomNumber);
				cityList.remove(randomNumber);
			}
		}	
	}
	
	public float[] calculDistance() {
		float[] f = new float[nbIndividuals];
		float sum = 0;
		for(int i = 0; i < nbIndividuals; i++) {
			for(int j =0; j < nbCities; j++) {
				if(j == (nbCities - 1)) {
					sum += distance[individual[i][0]][individual[i][j]];
					break;
				}
				sum += distance[individual[i][j+1]][individual[i][j]];
			}
			
			f[i] = sum; 
			sum = 0;
		}	
		return f;		
	}
	
	
	
	public ArrayList<Integer> GenerateCityList() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i = 0; i < nbCities; i++) {
			array.add(i);
		}
		
		return array;
	}
	
	
	// TEST
	public static void main(String[] args) {
		City city = new City(4, 100);

		// works
		for(int i = 0; i < 4 ; i++ ) {
			city.location.get(i).showVec();
		}
		
		// works
		for(int i = 0 ; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print("i : " + i + " j : " + j + " : ");
				System.out.println(city.distance[i][j]);
			}
		}
		
		city.GenerateIndividuals(100, 4);
		
		//works
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.println("individu : "+ i + " ville : " + j + " : " + city.individual[i][j]);
			}
		}
		
		//works
		float[] sum = city.calculDistance();
		
		for(int i = 0; i < sum.length; i++) {
			System.out.println(sum[i]);
		}
	}

}
