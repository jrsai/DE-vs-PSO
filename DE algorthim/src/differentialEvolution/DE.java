package differentialEvolution;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;
import java.math.*;

@SuppressWarnings("unused")
public class DE {
	//variables
	static final int Np = 100; //population size
	static final double Cr = 0.9; //crossover probability
	static final double F = 0.8; //differential weight
	static final int numRuns = 51; //number of runs
	static int D = 2; //number of unknown variables
	static final int Max_NFC = 5000*D; //number of function calls
	static Random rand = new Random();
	static double minRange = -10.0;
	static double maxRange = 10.0;
	static double rangeofVar = minRange + (maxRange-minRange) * rand.nextDouble(); //range

	public double fitnessFunction(Individual in, int option){
		switch (option){
		
		case 1: 
		//return fitness
		break;
		
		case 2: 
			//return fitness
		break;
		
		case 3: 
			//return fitness
		break;
		
		case 4: 
			//return fitness
		break;
		
		case 5: 
			//return fitness
		break;
		
		case 6: 
			//return fitness
		break;
		
		case 7: 
			//return fitness
		break;
		
		case 8: 
			//return fitness
		break;
		
		case 9: 
			//return fitness
		break;
		}
		return option;
	}
	//main loop of evolution
	public Individual evaluate(int option, LinkedList<Individual> population){
		int selectedFunction = option;
		
		int i = 0;
		int j = 0;
		while (i != Max_NFC){
			i++;
			j = 0;
			//calculate new candidate solution
			while(j < Np){
				//pick random point from population
				double x = Math.floor(rangeofVar % (population.size()-1));
				double a,b,c;
				
				//pick 3 different random points from population
				do{
					a = Math.floor(rangeofVar % (population.size()-1));
				}while(a==x);
				do{
					b = Math.floor(rangeofVar % (population.size()-1));
				}while(b==x | b==a);
				do{
					c = Math.floor(rangeofVar % (population.size()-1));
				}while(c==x | c==a | c==b);
				
				//pick a random index [0-Dimensionality]
				int R = rand.nextInt() % D;
				
				//compute the agents new position
				Individual original = population.get((int) x);
				Individual candidate = original;
				
				Individual individual1 = population.get((int) a);
				Individual individual2 = population.get((int) b);
				Individual individual3 = population.get((int) c);
				
				//if(i == R | i < Cr)
					//candidate = a + F * (b-c);  
				//else
					//candidate = x;
				if (0 == R | rangeofVar % 1 < Cr){
					candidate.data1 = individual1.data1 + F * (individual2.data1 - individual3.data1);
				} // else isn't needed because we cloned original to candidate
				if (1 == R | rangeofVar % 1 < Cr){
					candidate.data2 = individual1.data2 + F * (individual2.data2 - individual3.data2);
				}
				if (2 == R | rangeofVar % 1 < Cr){
					candidate.data3 = individual1.data3 + F * (individual2.data3 - individual3.data3);
				}
				//see if is better than original, if so replace
				if (fitnessFunction(original, selectedFunction) < fitnessFunction(candidate, selectedFunction)){
					population.remove(original);
					population.add(candidate);
				}
				j++;
			}
		}
		//find best candidate solution
		Individual bestFitness = new Individual();
		for (int k = 0; i < Np; k++){
			Individual individual = population.get(i);
			if (fitnessFunction(bestFitness, selectedFunction) < fitnessFunction(individual, selectedFunction)){
				bestFitness = individual;
			}
		}
		return bestFitness;
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter a number to choose a function you would like to optimize: \n"
				+ "1: High Conditioned Elliptic Function\n"
				+ "2: Bent Cigar Function\n"
				+ "3: Discus Function\n"
				+ "4: Rosenbrock's Function\n"
				+ "5: Ackley's Function\n"
				+ "6: Weierstrass Function\n"
				+ "7: Griewank's Function\n"
				+ "8: Rastrigin's Function\n"
				+ "9: Katsuura Function\n");
		
		int selectedBenchmark = in.nextInt();
		in.close();
		//linked list holds population
		LinkedList<Individual> population = new LinkedList<Individual>();
		//initialize population with individuals 
		for (int i = 0; i < Np; i++)
		{
			Individual individual = new Individual();
			individual.data1 = rangeofVar;
			individual.data2 = rangeofVar;
			individual.data3 = rangeofVar;
			population.add(individual);
		}
		
		switch (selectedBenchmark){
		
			case 1: 
			//get inital population and calculate minimization;
			break;
			
			case 2: 
			//get inital population and calculate minimization;
			break;
			
			case 3: 
			//get inital population and calculate minimization;
			break;
			
			case 4: 
			//get inital population and calculate minimization;
			break;
			
			case 5: 
			//get inital population and calculate minimization;
			break;
			
			case 6: 
			//get inital population and calculate minimization;
			break;
			
			case 7: 
			//get inital population and calculate minimization;
			break;
			
			case 8: 
			//get inital population and calculate minimization;
			break;
			
			case 9: 
			//get inital population and calculate minimization;
			break;
		}
		
	}

}
