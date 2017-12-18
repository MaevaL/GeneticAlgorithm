import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Paint;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TravellingSalesmanProblem extends Frame{

	public TravellingSalesmanProblem() {
		setSize(800	, 800);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			};
		});
	}
	
	public static List<City> crossOver(List<City> p1, List<City> p2) {
		List<City> crossed = new ArrayList<City>();
		int cut = p1.size()/2;
		
		for(int i = 0; i < cut; i++) {
			crossed.add(p1.get(i));
		}
		
		for(int j = 0; j < p2.size(); j++) {
			if(!crossed.contains(p2.get(j))) {
				crossed.add(p2.get(j));
			}
		}
		
		return crossed;
	}
	
	public static List<City> mutation(List<City> cityList){
		int randomIndice1 = Math.abs(Generation.generator.nextInt() % cityList.size());
		int randomIndice2 = Math.abs(Generation.generator.nextInt() % cityList.size());
		
		while(randomIndice1 == randomIndice2) {
			randomIndice1 = Math.abs(Generation.generator.nextInt() % cityList.size());
			randomIndice2 = Math.abs(Generation.generator.nextInt() % cityList.size());
		}
		
		Collections.swap(cityList, randomIndice1, randomIndice2);
		return cityList;
		
	}

	public static void main(String[] args) {
/**
 * TEST
 */
		/*********************************************************************************************************/
//		Generation generation = new Generation(100);
//		generation.GenerateCities(10);
//		for(City city : City.citiesArray) {
//			System.out.println(city.toString());
//		}
//		
//		System.out.println("\n");
//		PathBetweenTwoCities.GenerateMatrice();
//		
//		for(PathBetweenTwoCities p : PathBetweenTwoCities.pathArray) {
//			System.out.println(p.toString());
//		}
//		
//		for(int i = 0; i < 10; i++) {
//			PathTraveler.GenerateAPathBetweenAllCities();
//		}
//		
//		for(int i = 0; i < PathTraveler.pathTravelerArray.size(); i++) {
//			for(int j =0; j < City.citiesArray.size(); j++)
//				System.out.println("IndexPath : " + PathTraveler.pathTravelerArray.indexOf(PathTraveler.pathTravelerArray.get(i))+ " CityIndex : " + PathTraveler.pathTravelerArray.get(i).get(j).getIndex());
//		}
//		
//		for(int i = 0; i < PathTraveler.pathTravelerArray.size(); i++) {
////			System.out.println(PathTraveler.getDistanceForAPath(i));
//			System.out.println(PathTraveler.getDistance(PathTraveler.pathTravelerArray.get(i)));
//		}		
//		
//		
//		System.out.println("Best distance : " + PathTraveler.getBestDistance());
//		
//		List<List<City>> sortingBest = PathTraveler.getNBest(5);
//		
//		for(int i = 0; i < sortingBest.size(); i++) {
//			System.out.println(PathTraveler.getDistance(sortingBest.get(i)));
//		}
//		
//		// works
//		List<City> p1 = new ArrayList<City>(PathTraveler.pathTravelerArray.get(0));
//		System.out.println(p1.toString());
//		List<City> p2 = new ArrayList<City>(PathTraveler.pathTravelerArray.get(1));
//		System.out.println(p2.toString());
//		
//		List<City> resultCrossed = TravellingSalesmanProblem.crossOver(p1, p2);
//		System.out.println(resultCrossed.toString());
		/*********************************************************************************************************/
		
		int seed = 100;
		int nbCity = 100;
		int nbGen = 1000;
		int nbBest = 30;
		int nbIndivual = 300;

		
		//Initial population
		/*************************************************************************/
		Generation generation = new Generation(seed);
		generation.GenerateCities(nbCity);
		for(City city : City.citiesArray) {
			System.out.println(city.toString());
		}
		PathBetweenTwoCities.GenerateMatrice(); //generate path between each cities and calculate the distance

		for(int i = 0; i < nbIndivual; i++) {
			PathTraveler.GenerateAPathBetweenAllCities(); //generate individuals
		}
		
		int randomIndice1 = 0;
		int randomIndice2 = 0;
		List<List<City>> newPathCities = new ArrayList<List<City>>();
		/*************************************************************************/
		
		// Generation 
		/*************************************************************************/
		for(int i = 0; i < nbGen; i++) {
			List<List<City>> nBest = PathTraveler.getNBest(nbBest);
			for(int j = 0; j < nbIndivual - nbBest; j++) {
				// Selection and Crossing
				randomIndice1 = Math.abs(Generation.generator.nextInt()%nbIndivual);
				randomIndice2 = Math.abs(Generation.generator.nextInt()%nbIndivual);
				while(randomIndice1 == randomIndice2) {
					randomIndice1 = Math.abs(Generation.generator.nextInt()%nbIndivual);
					randomIndice2 = Math.abs(Generation.generator.nextInt()%nbIndivual);
				}
				List<City> parent1 = new ArrayList<City>(PathTraveler.pathTravelerArray.get(randomIndice1));
				List<City> parent2 = new ArrayList<City>(PathTraveler.pathTravelerArray.get(randomIndice2));
				List<City> crossed = TravellingSalesmanProblem.crossOver(parent1, parent2);	
				newPathCities.add(crossed);
			}
			
			newPathCities.addAll(nBest);
			PathTraveler.pathTravelerArray = newPathCities;
			
			for(int k = 0; k < newPathCities.size(); k++) {
				// Mutation
				if(Generation.generator.nextFloat() < 0.02) {
					List<City> citylist = new ArrayList<City>(PathTraveler.pathTravelerArray.get(k));
					TravellingSalesmanProblem.mutation(citylist);
					PathTraveler.pathTravelerArray.set(k, citylist) ;
				}
			}
		}
		/*************************************************************************/
		System.out.println(PathTraveler.getBestDistance());
		
		new TravellingSalesmanProblem();
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		for(int i = 0; i < City.citiesArray.size(); i++) {
			g.drawRect((int)(200 + (200* City.citiesArray.get(i).getLocation().getX())),(int)( 200 + (200*City.citiesArray.get(i).getLocation().getY())), 5, 5);
		}
		PathTraveler.getBestDistance();
		for(int i = 0; i <= City.citiesArray.size()-1; i++ ) {
			if( i == City.citiesArray.size()-1) {
				g.drawLine((int)(200 + (200 * PathTraveler.pathTravelerArray.get((int)PathTraveler.indexBest).get(0).getLocation().getX())), 
						(int)(200 + (200 * PathTraveler.pathTravelerArray.get(PathTraveler.indexBest).get(0).getLocation().getY())), 
						(int)(200 + (200 * PathTraveler.pathTravelerArray.get(PathTraveler.indexBest).get(i).getLocation().getX())), 
						(int)(200 + (200 * PathTraveler.pathTravelerArray.get(PathTraveler.indexBest).get(i).getLocation().getY())));
				break;
			}
			g.drawLine((int)(200 + (200 * PathTraveler.pathTravelerArray.get((int)PathTraveler.indexBest).get(i).getLocation().getX())), 
					(int)(200 + (200 * PathTraveler.pathTravelerArray.get(PathTraveler.indexBest).get(i).getLocation().getY())), 
					(int)(200 + (200 * PathTraveler.pathTravelerArray.get(PathTraveler.indexBest).get(i+1).getLocation().getX())), 
					(int)(200 + (200 * PathTraveler.pathTravelerArray.get(PathTraveler.indexBest).get(i+1).getLocation().getY())));
		}
	}
}
