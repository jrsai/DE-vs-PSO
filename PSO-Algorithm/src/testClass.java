import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class testClass implements ControlParameters {
	
	private static int NUMBER_OF_RUNS = 51;
	static ArrayList<ArrayList<Double>> listOLists = new ArrayList<ArrayList<Double>>();
	static ArrayList<Double> averageFitness = new ArrayList<Double>();
	static ArrayList<Double> bestList = new ArrayList<Double>();
	private static String CSV_FILENAME = "";

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		for (int i=0; i<NUMBER_OF_RUNS; i++) { // 51 Runs of PSO
			listOLists.add(new PSO().execute(i));
			findBest(i);
		}
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Runtime:" +  totalTime + "ms");
		
		fileNameGenerator();
		tallyData();
		
		calculateMean();
		calculateWorst();
		calculateBest();
		calculateSD();
			
	}
	
	public static void tallyData() {
		int current = 0;
		double sum = 0; double average = 0;
		int loopLimit = ((3000*PROBLEM_DIMENSION)/SWARM_SIZE);
		while (current < loopLimit) {
			for (int i = 0; i < listOLists.size(); i++) {
				sum += listOLists.get(i).get(current);
			}	
			average = sum/listOLists.size();
			sum = 0;
			tallyData(CSV_FILENAME, average);
			current++;
		}
	}
	
	public static void tallyData(String filename, double bestFitness) {
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(filename, true);
			fileWriter.append(String.valueOf(bestFitness));
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

	public static void fileNameGenerator() {
		switch(FUNCTION_NUM){
		case 1 : 
			CSV_FILENAME=PROBLEM_DIMENSION + "D_HighConditioned_PSO.csv";
			break;
		case 2 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_BentCigar_PSO.csv";
			break;
		case 3 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Discus_PSO.csv";
			break;
		case 4 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Rosenbrocks_PSO.csv";
			break;
		case 5 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Ackleys_PSO.csv";
			break;
		case 6 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Weierstrass_PSO.csv";
			break;
		case 7 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Griewanks_PSO.csv";
			break;
		case 8 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Rastrigins_PSO.csv";
			
			break;
		case 9 :
			CSV_FILENAME=PROBLEM_DIMENSION + "D_Katsuura_PSO.csv";
			break;
		}
	}
	
	public static void findBest(int runNum) {
		ArrayList<Double> temp = new ArrayList<Double>();
		temp = listOLists.get(runNum);
		Collections.sort(temp);
		bestList.add(temp.get(0));
	}
	
	public static void calculateSD() {
		double sum = 0;
		for (int i = 0; i < bestList.size(); i++) {
			sum += bestList.get(i);
		}
		double mean = sum/bestList.size();
		
		ArrayList<Double> difference = new ArrayList<Double>();
		for (int i = 0; i < bestList.size(); i++) {
			difference.add(Math.pow(bestList.get(i) - mean, 2));
		}
		
		double sumOfSquares = 0;
		for (int i = 0; i < difference.size(); i++) {
			sumOfSquares += difference.get(i);
		}
		
		double sd = Math.sqrt(sumOfSquares/(bestList.size()-1));
		System.out.println("Standard Deviation = " + sd);
	}
	
	public static void calculateBest() {
		double best = Double.MAX_VALUE;
		for (int i = 0; i < bestList.size(); i++) {
			if (bestList.get(i) < best) {
				best = bestList.get(i);
			}
		}
		System.out.println("Best = " + best);
	}
	
	public static void calculateWorst() {
		double worst = Double.MIN_VALUE;
		for (int i = 0; i < bestList.size(); i++) {
			if (bestList.get(i) > worst) {
				worst = bestList.get(i);
			}
		}
		System.out.println("Worst = " + worst);
	}
	
	public static void calculateMean() {
		double sum = 0;
		for (int i = 0; i < bestList.size(); i++) {
			sum += bestList.get(i);
		}
		System.out.println("Mean = " + sum/bestList.size());
	}
	
}
