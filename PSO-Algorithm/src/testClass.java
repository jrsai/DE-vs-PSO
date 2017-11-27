import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class testClass implements ControlParameters {
	
	private static int NUMBER_OF_RUNS = 51;
	static ArrayList<ArrayList<Double>> listOLists = new ArrayList<ArrayList<Double>>();
	static ArrayList<Double> averageFitness = new ArrayList<Double>();
	private static String CSV_FILENAME = "";

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		for (int i=0; i<NUMBER_OF_RUNS; i++) { // 51 Runs of PSO
			listOLists.add(new PSO().execute());
		}
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Runtime:" +  totalTime + "ms");
		
		fileNameGenerator();
		tallyData();
			
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
			CSV_FILENAME="High_Conditioned_Elliptic_Function_" + PROBLEM_DIMENSION + "_D.csv";
			break;
		case 2 :
			CSV_FILENAME="Bent_Cigar_Function_" + PROBLEM_DIMENSION + "_D.csv";
			break;
		case 3 :
			CSV_FILENAME="Discus_Function_" + PROBLEM_DIMENSION + "_D.csv";
			break;
		case 4 :
			CSV_FILENAME="Rosenbrocks_Function_" + PROBLEM_DIMENSION + "_D.csv";
			break;
		case 5 :
			CSV_FILENAME="Ackleys_Function_" + PROBLEM_DIMENSION + "_D.csv";
			break;
		case 6 :
			CSV_FILENAME="Weierstrass_Function_" + PROBLEM_DIMENSION + "_D.csv";
			break;
		case 7 :
			CSV_FILENAME="Griewanks_Function_" + PROBLEM_DIMENSION + "_D.csv";
			break;
		case 8 :
			CSV_FILENAME="Rastrigin's_Function_" + PROBLEM_DIMENSION + "_D.csv";
			break;
		case 9 :
			CSV_FILENAME="Katsuura_Function_" + PROBLEM_DIMENSION + "_D.csv";
			break;
		}
	}
	
}
