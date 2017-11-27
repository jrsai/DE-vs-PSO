import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class PSO implements ControlParameters {
	private static Vector<Particle> swarm = new Vector<Particle>();
	private static double[] pBest = new double[SWARM_SIZE];
	private static Vector<Location> pBestLocation = new Vector<Location>();
	private static ArrayList<Double> generationList = new ArrayList<>();
	private static double gBest;
	private static String CSV_FILENAME = "";
	private static Location gBestLocation;
	private static double[] fitnessValueList = new double[SWARM_SIZE];
	
	Random generator = new Random();
	
	public ArrayList<Double> execute(int runNum) {
		fileNameGenerator();
		initializeSwarm();
		updateFitnessList();
		
		for(int i=0; i<SWARM_SIZE; i++) {
			pBest[i] = fitnessValueList[i];
			pBestLocation.add(swarm.get(i).getLocation());
		}
		
		int t = 0;
		double w;
		double err = 9999;
		
		while(t < MAX_ITERATION && err > Functions.ERR_TOLERANCE) {
			
			// Step 1 - Update Personal Best
			for(int i=0; i<SWARM_SIZE; i++) {
				if(fitnessValueList[i] < pBest[i]) {
					pBest[i] = fitnessValueList[i];
					pBestLocation.set(i, swarm.get(i).getLocation());
				}
			}
				
			// Step 2 - Update Global Best
			int bestParticleIndex = getMinPos(fitnessValueList);
			if(t == 0 || fitnessValueList[bestParticleIndex] < gBest) {
				gBest = fitnessValueList[bestParticleIndex];
				gBestLocation = swarm.get(bestParticleIndex).getLocation();
			}	
			
			w = W_UPPERBOUND - (((double) t) / MAX_ITERATION) * (W_UPPERBOUND - W_LOWERBOUND);
			
			for(int i=0; i<SWARM_SIZE; i++) {
				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();
				
				Particle p = swarm.get(i);
				
				// Step 3 - Update Velocity
				double[] newVel = new double[PROBLEM_DIMENSION];
				for (int j=0; j<PROBLEM_DIMENSION; j++) {
					newVel[j] = (w * p.getVelocity().getPos()[j]) + 
							(r1 * C1) * (pBestLocation.get(i).getLoc()[j] - p.getLocation().getLoc()[j]) +
							(r2 * C2) * (gBestLocation.getLoc()[j] - p.getLocation().getLoc()[j]);
				}
				Velocity vel = new Velocity(newVel);
				p.setVelocity(vel);
				
				// Step 4 - Update Location
				double[] newLoc = new double[PROBLEM_DIMENSION];
				for (int j=0; j<PROBLEM_DIMENSION; j++) {
					newLoc[j] = p.getLocation().getLoc()[j] + newVel[j];
				}
				Location loc = new Location(newLoc);
				p.setLocation(loc);
				
				err = Functions.evaluate(gBestLocation) - 0; // minimizing the functions means it's getting closer to 0
				t++;
				updateFitnessList();	
			}
			if (runNum == 0) {
				sendGen();
			}
			System.out.println(gBest); // Output of the Global Best for each generation (100 Particles)
			generationList.add(gBest);
		}
		System.out.print("Best Location: ("); // Output of the Final Best Location for 1 run of the algorithm
		for (int i=0; i<PROBLEM_DIMENSION; i++) {
			System.out.print(gBestLocation.getLoc()[i] + ", " );
		}
		System.out.print(")" + "\n" + "\n");
		return generationList;
	}
	
	public static void sendGen() {
		for (int i = 0; i < pBest.length; i++) {
			tallyData(CSV_FILENAME, pBestLocation.get(i).getLoc()[0], pBestLocation.get(i).getLoc()[1], pBest[i]);
		}
	}
	
	public static void fileNameGenerator() {
		switch(FUNCTION_NUM){
		case 1 : 
			CSV_FILENAME=PROBLEM_DIMENSION + "D_HighConditioned_PSO_ALL.csv";
			break;
		case 2 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_BentCigar_PSO_ALL.csv";
			break;
		case 3 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Discus_PSO_ALL.csv";
			break;
		case 4 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Rosenbrocks_PSO_ALL.csv";
			break;
		case 5 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Ackleys_PSO_ALL.csv";
			break;
		case 6 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Weierstrass_PSO_ALL.csv";
			break;
		case 7 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Griewanks_PSO_ALL.csv";
			break;
		case 8 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Rastrigins_PSO_ALL.csv";
			
			break;
		case 9 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Katsuura_PSO_ALL.csv";
			break;
		}
	}
	
	//method to export data to csv file
	public static void tallyData(String filename, double locX, double locY, double bestFitness) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filename, true);
			fileWriter.append(String.valueOf(locX) + "," + String.valueOf(locY) + "," + String.valueOf(bestFitness));
			fileWriter.append("\n");
		} catch (Exception e) {
			System.out.println("Error in CSVFileWriter!");
			e.printStackTrace();	
			} finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter!");
					e.printStackTrace();
				}
			}
		}
	
	public void initializeSwarm() {
		Particle p;
		for(int i=0; i<SWARM_SIZE; i++) {
			p = new Particle();
			
			// randomize location inside a space defined in Problem Set
			double[] loc = new double[PROBLEM_DIMENSION];
			for(int j=0; j<PROBLEM_DIMENSION; j++) {
				loc[j] = Functions.BOUND_LOW + generator.nextDouble() * (Functions.BOUND_HIGH - Functions.BOUND_LOW);
			}
			Location location = new Location(loc);
			
			// randomize velocity in the range defined in Problem Set
			double[] vel = new double[PROBLEM_DIMENSION];
			for(int j=0; j<PROBLEM_DIMENSION; j++) {
				vel[j] = Functions.BOUND_LOW + generator.nextDouble() * (Functions.BOUND_HIGH - Functions.BOUND_LOW);
			}
			Velocity velocity = new Velocity(vel);
			
			p.setLocation(location);
			p.setVelocity(velocity);
			swarm.add(p);
		}
	}
	
	public void updateFitnessList() {
		for(int i=0; i<SWARM_SIZE; i++) {
			fitnessValueList[i] = swarm.get(i).getFitnessValue();
		}
	}
		
	public static int getMinPos(double[] list) {
		int pos = 0;
		double minValue = list[0];
			
		for(int i=0; i<list.length; i++) {
			if(list[i] < minValue) {
				pos = i;
				minValue = list[i];
			}
		}
			
		return pos;
	}
	
}