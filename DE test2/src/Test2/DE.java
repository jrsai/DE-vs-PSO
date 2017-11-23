package Test2;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.stat.descriptive.summary.*;

public class DE {
	static int dimension = 3;
	static int populationSize = 100; //population size
	static double crossOverRate = 0.9; //crossover probability
	static double mutationConstant = 0.8; //differential weight
	static int runs = 51; //number of runs
	static int maxFunctionCalls = 5*dimension; //number of function calls
	static double bestFitnessValue = 0;
	static double[] fitnessValues = null;
	static int function = 0;
	static double mean = 0;
	static List<Double> standardDeviations = new ArrayList<Double>();
	static org.apache.commons.math3.stat.descriptive.SummaryStatistics callFitnesses = new org.apache.commons.math3.stat.descriptive.SummaryStatistics();
	static org.apache.commons.math3.stat.descriptive.SummaryStatistics runFitnesses = new org.apache.commons.math3.stat.descriptive.SummaryStatistics();
	
	public static void generateAndTestLoop(int function){
		int currentFunctionCalls = 0;
		double bestFitnessPerCall = 0;
		double bestFitnessPerRun = 0;
		DE.function = function; 
		
		List<List<Double>> initialPopulation = new ArrayList<List<Double>>();
		switch (function){
		case 1:
			initialPopulation.addAll(generatePopulation(-10.0,10.0));
			break;
		case 2:
			initialPopulation.addAll(generatePopulation(-10.0,10.0));
			break;
		case 3:
			initialPopulation.addAll(generatePopulation(-10.0,10.0));
			break;
		case 4:
			initialPopulation.addAll(generatePopulation(-10.0,10.0));
			break;
		case 5:
			initialPopulation.addAll(generatePopulation(-10.0,10.0));
			break;
		case 6:
			initialPopulation.addAll(generatePopulation(-10.0,10.0));
			break;
		case 7:
			initialPopulation.addAll(generatePopulation(-10.0,10.0));
			break;
		case 8:
			initialPopulation.addAll(generatePopulation(-10.0,10.0));
			break;
		case 9:
			initialPopulation.addAll(generatePopulation(-10.0,10.0));
			break;
		}
		
		for (int i = 0; i < runs; i++){
			currentFunctionCalls = 0;
			while (currentFunctionCalls < maxFunctionCalls){
				for (int j = 0; j < populationSize; j++){
					List<Integer> randomizedIndex = randomParentSelector(populationSize, 4);
					List<Double> individualA = new ArrayList<Double>();
					List<Double> individualB = new ArrayList<Double>();
					List<Double> individualC = new ArrayList<Double>();
					
					if (randomizedIndex.get(0) == j){
						individualA = initialPopulation.get(randomizedIndex.get(3));
					}
					else {
						individualA = initialPopulation.get(randomizedIndex.get(0));
					}
					if (randomizedIndex.get(1) == i) {
						individualB = initialPopulation.get(randomizedIndex.get(3));
                    } 
					else {
                        individualB = initialPopulation.get(randomizedIndex.get(1));
                    }

                    if (randomizedIndex.get(2) == i) {
                        individualC = initialPopulation.get(randomizedIndex.get(3));
                    }
                    else {
                        individualC = initialPopulation.get(randomizedIndex.get(2));
                    }
                    
                    List<Double> trialVector = new ArrayList<Double>();
                    
                    List<Double> noiseVector = addIndividuals(individualA, multiplyIndividuals(subtractIndividuals(individualC, individualB), mutationConstant));
                    
                    for (int k = 0; k < dimension; k++) {
                        if (crossOverRate > Math.random() || k == (int) Math.random() * j) {
                            trialVector.add(k, noiseVector.get(k));
                        }
                        else {
                            trialVector.add(k, initialPopulation.get(j).get(k));
                        }
                    }
                    
                    List<Double> childIndividual = new ArrayList<Double>();
                    
                    double currentIndividualFitness = cost(function, initialPopulation.get(i));
                    double trialVectorFitness = cost(function, trialVector);
                    
                    if (trialVectorFitness <= currentIndividualFitness) {
                        childIndividual = trialVector;
                        bestFitnessPerCall = trialVectorFitness;
                    }
                    else {
                        childIndividual = initialPopulation.get(j);
                        bestFitnessPerCall = currentIndividualFitness;
                    }
                    initialPopulation.set(j, childIndividual);
				}
				
				callFitnesses.addValue(bestFitnessPerCall);
                currentFunctionCalls++;
			}
			bestFitnessPerRun = callFitnesses.getMin();
            runFitnesses.addValue(bestFitnessPerRun);
		}
		 standardDeviations.add(runFitnesses.getStandardDeviation());
	     mean = runFitnesses.getMean();
	}
	
	public static List<List<Double>> generatePopulation(double min, double max) {
        List<List<Double>> population = new ArrayList<List<Double>>();
        List<Double> individual;
        for (int i = 0; i < populationSize; i++) {
            individual = new ArrayList<Double>();
            for (int j = 0; j < dimension; j++) {
                individual.add(Math.random() * (2 * max) + min);
            }
            population.add(individual);
        }
        return population;
    }
	
	public static ArrayList<Integer> randomParentSelector(int maxValue, int size) {
        ArrayList<Integer> populationIndex = new ArrayList<Integer>();
        ArrayList<Integer> parents = new ArrayList<Integer>();

        for (int i = 0; i < maxValue; i++) {
            populationIndex.add(i);
        }

        Random rand = new Random();
        while (populationIndex.size() > populationSize - size) {
            int index = rand.nextInt(populationIndex.size());
            parents.add(populationIndex.remove(index));
        }
        return parents;
    }
	
	 public static double cost(int n, List<Double> individual) {
	        double cost = 0;
	        switch (n) {
	        case 1:
	            // 1st de jong
	            for (int i = 0; i < individual.size(); i++) {
	                cost += Math.pow((individual.get(i)), 2);
	            }
	            break;
	        case 2:
	            // axis parallel hyper ellipsoid
	            for (int i = 0; i < individual.size(); i++) {
	                cost += (i + 1) * Math.pow((individual.get(i)), 2);
	            }
	            break;
	        case 3:
	            // schwefel's problem
	            for (int i = 0; i < individual.size(); i++) {
	                double innerSum = 0;
	                for (int j = 0; j <= i; j++) {
	                    innerSum += individual.get(i);
	                }
	                cost += Math.pow(innerSum, 2);
	            }
	            break;
	        case 4:
	            // rosenbrock's valley
	            for (int i = 0; i < individual.size() - 1; i++) {
	                cost += 100 * Math.pow(((individual.get(i + 1)) - Math.pow(individual.get(i), 2)), 2)
	                        + Math.pow((1 - individual.get(i)), 2);
	            }
	            break;
	        case 5:
	            // rastrigrin's function
	            for (int i = 0; i < individual.size(); i++) {
	                cost += (Math.pow(individual.get(i), 2) - 10 * Math.cos(2 * Math.PI * individual.get(i)));
	            }
	            cost += 10 * dimension;
	            break;
	        }
	        return cost;
	    }
	 
	 public static List<Double> addIndividuals(List<Double> individualA, List<Double> individualB) {
	        List<Double> individualResult = new ArrayList<Double>();
	        for (int i = 0; i < dimension; i++) {
	            individualResult.add(individualA.get(i) + individualB.get(i));
	        }
	        return individualResult;
	    }
	 
	 public static List<Double> subtractIndividuals(List<Double> individualA, List<Double> individualB) {
	        List<Double> individualResult = new ArrayList<Double>();
	        for (int i = 0; i < dimension; i++) {
	            individualResult.add(individualA.get(i) - individualB.get(i));
	        }
	        return individualResult;
	    }

	 public static List<Double> multiplyIndividuals(List<Double> individual, double constant) {
	        List<Double> individualResult = new ArrayList<Double>();
	        for (int i = 0; i < dimension; i++) {
	            individualResult.add(constant * individual.get(i));
	        }
	        return individualResult;
	    }
	 public static List<Double> getStandardDeviation() {
	        return standardDeviations;
	    }

	 public static double getMean() {
	        return mean;
	    }
}
