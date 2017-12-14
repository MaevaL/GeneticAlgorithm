import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OLD {

	public float[][] distance;
	public ArrayList<Vec2> location;
	public int nbCities;
	public int[][] individual;
	public int nbIndividuals;
	public float[] distanceMax;
	
	public OLD(int nbCities, long seed) {
		this.nbCities = nbCities;
		location = new ArrayList<Vec2>(nbCities);
		distance = new float[nbCities][nbCities];
		
		
	}
	
	public float getDistanceForAnIndividual(int indexIndividual) {
		float dist = 0;
		for(int j = 0; j < nbCities; j++) {
			if(j == (nbCities - 1)) {
				dist += distance[individual[indexIndividual][0]][individual[indexIndividual][j]];
				break;
			}
			dist += distance[individual[indexIndividual][j+1]][individual[indexIndividual][j]];
		}
		
		return dist;
	}	
		

	public void bestDistance(int nbIndividualDistBest) {
		distanceMax = new float[nbIndividualDistBest];
		int indexIndividuMin = 0;
		float distMin = 0;
		float d1 = 0;
		float d2 = 0;
		for(int i = 0; i < nbIndividualDistBest; i++) {
			for(int j = 0; j < nbIndividuals - 1; j++) {
				d1 = getDistanceForAnIndividual(j);
				d2 = getDistanceForAnIndividual(j+1);
				if(d1 <= d2) {
					if(i == 0) {
						distMin = getDistanceForAnIndividual(j);
						indexIndividuMin = j;
					} else if (distanceMax[i-1] != j && distMin > d2 && distMin > d1) {
						distMin = getDistanceForAnIndividual(j);
						indexIndividuMin = j;
					} 
				}
			}
			distanceMax[i] =  indexIndividuMin;
			System.out.println("index : " + distanceMax[i] + " distanceBest : " + distMin);
		}
	}	
	
	public ArrayList<Integer> GenerateCityList() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i = 0; i < nbCities; i++) {
			array.add(i);
		}
		
		return array;
	}
	
	public ArrayList<Integer> GenerateInduvidualsList(){
		ArrayList<Integer> array = new ArrayList<>(nbIndividuals);
		for(int i = 0 ; i < nbIndividuals; i++) {
			array.add(i);
		}
		return array;
	}
	
	
	// TEST
	public static void main(String[] args) {
//		OLD city = new OLD(4, 100);
//
//		// works
//		for(int i = 0; i < 4 ; i++ ) {
//			city.location.get(i).showVec();
//		}
//		
//		// works
//		for(int i = 0 ; i < 4; i++) {
//			for(int j = 0; j < 4; j++) {
//				System.out.print("i : " + i + " j : " + j + " : ");
//				System.out.println(city.distance[i][j]);
//			}
//		}
//
//		//works
//		for(int i = 0; i < city.nbIndividuals; i++) {
//			System.out.println("Individual dist : " + city.getDistanceForAnIndividual(i));
//		}
//		
//		city.bestDistance(2);
//		
	}

}
