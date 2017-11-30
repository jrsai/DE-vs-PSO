package five;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DE5D {
	static ArrayList<ArrayList<Double>> listOLists = new ArrayList<ArrayList<Double>>();
	static ArrayList<Double> bestOfCurrentGen = new ArrayList<Double>();
	static ArrayList<Double> bestList = new ArrayList<Double>();
	
	public static void main(String[] args) {
		int count = 1;
		
		for (int k = 0; k < 51; k++) {
			//populating the array with individuals for the population 
			//using the method initialize_population
			Individual5D[] initial_parents = initialize_population(100);
			double[] temp = new double[100];
			int D = 5; //defining the dimensions
			int generations = 3000*D; //defining the number of runs till termination
			
			//terminates when criteria is met that is when generations is <= 0
			while(generations > 0) {
				
				//For loop to reach every individual in the population so they are 
				//either replaced if they are not as good as the child/mutated individual
				for (int i = 0; i <= (initial_parents.length - 1); i++) {
					
					//generating random numbers so we can randomly choose 3 
					//other candidates
					int random_candidates1 = (int) (Math.random() * 100);
					int random_candidates2 = (int) (Math.random() * 100);
					int random_candidates3 = (int) (Math.random() * 100);
					
					//random candidates other than current candidate/parent
					//being accessed are selected from the population, help out with mutation and crossover
					if (random_candidates1 == i) {
						if (random_candidates1 == 99) {
							random_candidates1--;
						} else {
							random_candidates1++;
						}
					}
					
					if (random_candidates2 == i) {
						if (random_candidates2 == 99) {
							random_candidates2--;
						} else {
							random_candidates2++;
						}
					}
					
					if (random_candidates3 == i) {
						if (random_candidates3 == 99) {
							random_candidates3--;
						} else {
							random_candidates3++;
						}
					}			
					
					Individual5D candidate1 = initial_parents[random_candidates1];
					Individual5D candidate2 = initial_parents[random_candidates2];
					Individual5D candidate3 = initial_parents[random_candidates3];
					
					//Mutation method either returns the parents or 
					//mutated value based on if parents are more fit than
					//mutation or not
					initial_parents[i] = Mutation(initial_parents[i], candidate1, candidate2, candidate3);
					temp[i] = initial_parents[i].get_fitness();
					
					generations--;
				}
				
				if (count <= ((3000*D)/100)) {
					sendGen(initial_parents);
					System.out.println("Send gen: " + generations);
					count++;
				}
				
				Arrays.sort(temp);
				bestOfCurrentGen.add(temp[0]);
			}
			
			ArrayList<Double> bestCopy = new ArrayList<>(bestOfCurrentGen);
			listOLists.add(bestCopy);
			bestOfCurrentGen.clear();

			Individual5D best = new Individual5D(0,0,0,0,0);
			Integer myInf = Integer.MAX_VALUE;
			best.Set_Fitness(myInf);
			
			for (int j = 0; j <= (initial_parents.length - 1); j++) {
				double best_fitness = best.get_fitness();
				double check_fitness = initial_parents[j].get_fitness();
				
				if (best_fitness > check_fitness) {
					best = initial_parents[j];
				}
			}
			bestList.add(best.get_fitness());
		}
		tallyData(); //trigger performance plot generation
		
		//calculate statistical values
		calculateMean();
		calculateWorst();
		calculateBest();
		calculateSD();
	}
	
	//method to calculate standard deviation
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
	
	//method to find best value
	public static void calculateBest() {
		double best = Double.MAX_VALUE;
		for (int i = 0; i < bestList.size(); i++) {
			if (bestList.get(i) < best) {
				best = bestList.get(i);
			}
		}
		System.out.println("Best = " + best);
	}
	
	//method to find worst value
	public static void calculateWorst() {
		double worst = Double.MIN_VALUE;
		for (int i = 0; i < bestList.size(); i++) {
			if (bestList.get(i) > worst) {
				worst = bestList.get(i);
			}
		}
		System.out.println("Worst = " + worst);
	}
	
	//method to calculate mean
	public static void calculateMean() {
		double sum = 0;
		for (int i = 0; i < bestList.size(); i++) {
			sum += bestList.get(i);
		}
		System.out.println("Mean = " + sum/bestList.size());
	}
	
	//method to export data to csv file
	public static void tallyData(String filename, double gene1, double gene2, double bestFitness) {
		FileWriter fileWriter = null;
			
		try {
			fileWriter = new FileWriter(filename, true);
			fileWriter.append(String.valueOf(gene1) + "," + String.valueOf(gene2) + "," + String.valueOf(bestFitness));
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
	
	//method to export data to csv file
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
	
	//Initialization method where we assign random genes to each 
	//individual/original parents
	public static Individual5D[] initialize_population(int size) {
		Individual5D[] population = new Individual5D[size];
		
		for (int size_of_population = 0; size_of_population <= (population.length-1); size_of_population++) {
			double random = ((Math.random() * 10) - 10);
			double gene1 = random;
			double random2 = ((Math.random() * 10) - 10);
			double gene2 = random2;
			double random3 = ((Math.random() * 10) - 10);
			double gene3 = random3;
			double random4 = ((Math.random() * 10) - 10);
			double gene4 = random4;	
			double random5 = ((Math.random() * 10) - 10);
			double gene5 = random5;
			population[size_of_population] = new Individual5D(gene1,gene2,gene3,gene4,gene5);
		}
		return population;
	}
	
	//mutation method
	public static Individual5D Mutation(Individual5D parent, Individual5D one, Individual5D two, Individual5D three) {
		double F = 0.8;
		
		//process of mutation where each gene from 2 candidates are subtracted and 
		//transformed with the constant as well as a with the third candidate
		double result1 = one.get_gene1() - two.get_gene1();
		double result2 = one.get_gene2() - two.get_gene2();
		double result3 = one.get_gene3() - two.get_gene3();
		double result4 = one.get_gene4() - two.get_gene4();
		double result5 = one.get_gene5() - two.get_gene5();
		double transform_result1 = (three.get_gene1() + F*(result1));
		double transform_result2 = (three.get_gene2() + F*(result2));
		double transform_result3 = (three.get_gene3() + F*(result3));
		double transform_result4 = (three.get_gene4() + F*(result4));
		double transform_result5 = (three.get_gene5() + F*(result5));
		Individual5D mutant = new Individual5D(transform_result1, transform_result2, transform_result3, transform_result4, transform_result5);
		
		double genes[] = new double[6];
		double parent_genes[] = new double[6];
		parent_genes[1] = parent.get_gene1();
		parent_genes[2] = parent.get_gene2();
		parent_genes[3] = parent.get_gene3();
		parent_genes[4] = parent.get_gene4();
		parent_genes[5] = parent.get_gene5();
		
		double mutant_genes[] = new double[6];
		mutant_genes[1] = mutant.get_gene1();
		mutant_genes[2] = mutant.get_gene2();
		mutant_genes[3] = mutant.get_gene3();
		mutant_genes[4] = mutant.get_gene4();
		mutant_genes[5] = mutant.get_gene5();
		
		//performing crossover...new child will be a combination of 
		//genes from parent and mutation
		double rand = Math.random();
		for(int i = 1; i <= 5; i++) {
			if(rand <= 0.9) {
				genes[i] = mutant_genes[i];
			}
			if(rand > 0.9) {
				genes[i] = parent_genes[i];
			}
		}
		
		//creating final child individual with genes from previous steps
		Individual5D child = new Individual5D(genes[1],genes[2],genes[3],genes[4],genes[5]);
		
		//AssignFitness_HighConditioned5D(child);
		//AssignFitness_BentCigar5D(child);
		//AssignFitness_DiscusFunc5D(child);
		//AssignFitness_Rosenbrock5D(child);
		//AssignFitness_Ackleys5D(child);
		//AssignFitness_Weierstrass5D(child);
		//AssignFitness_Greiwank5D(child);
		//AssignFitness_Rastrigin5D(child);
		AssignFitness_Katsuura5D(child);
		double child_fitness = child.get_fitness();
		
		//AssignFitness_HighConditioned5D(parent);
		//AssignFitness_BentCigar5D(parent);
		//AssignFitness_DiscusFunc5D(parent);
		//AssignFitness_Rosenbrock5D(parent);
		//AssignFitness_Ackleys5D(parent);
		//AssignFitness_Weierstrass5D(parent);
		//AssignFitness_Greiwank5D(parent);
		//AssignFitness_Rastrigin5D(parent);
		AssignFitness_Katsuura5D(parent);
		double parent_fitness = parent.get_fitness();
		
		//comparing who is more fit to move into the next generation and returning the individual
		if (child_fitness < parent_fitness) {
			return child;
		} else {
			return parent;
		}	
	}
	
	//method to find the average of the best of each generation
		public static void tallyData() {
			int current = 0;
			double sum = 0; double average = 0;
			while (current < 150) {
				for (int i = 0; i < listOLists.size(); i++) {
					sum += listOLists.get(i).get(current);
				}
				
				average = sum/listOLists.size();
				sum = 0;
				tallyData("D5_Katsuura5D.csv", average);
				current++;
			}
		}
	
	public static void sendGen(Individual5D[] people) {
		for (int i = 0; i < people.length; i++) {
			tallyData("D5_Katsuura5D_All.csv", people[i].get_gene1(), people[i].get_gene2(), people[i].get_fitness());
		}
	}
	
	//the rest of these functions below are benchmark functions...
	public static void AssignFitness_HighConditioned5D(Individual5D person) {
		int D = 5;
		double genes[] = new double[6];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 1; i <= D; i++) {
			double exp  = (i - 1)/(D - 1);
			sum = sum + (Math.pow(ten_6, exp) * Math.pow(genes[i], 2));	
		}
		
		double answer = sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_BentCigar5D(Individual5D person) {
		int D = 5;
		double answer;
		double genes[] = new double[6];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();

		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 2; i <= D; i++) {
			sum = sum + (Math.pow(genes[i], 2));	
		}
		
		answer = Math.pow(genes[1], 2) + ten_6*sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_DiscusFunc5D(Individual5D person) {
		int D = 5;
		double answer;
		double genes[] = new double[6];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 2; i <= D; i++) {
			sum = sum + (Math.pow(genes[i], 2));	
		}
		
		answer = ten_6*Math.pow(genes[1], 2) + sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Rosenbrock5D(Individual5D person) {
		int D = 5;
		double genes[] = new double[6];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		
		double sum = 0;
		double answer;
		for (int i = 1; i <= (D-1); i++) {
			double calculate = (Math.pow(genes[i], 2) - genes[i+1]);
			double calculate1 = (Math.pow( (genes[i] - 1) , 2 )); 
			sum = sum + ((100*Math.pow(calculate, 2))+(calculate1));
		}
		
		answer = sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Ackleys5D(Individual5D person) {
		double answer;
		int D = 5;
		double genes[] = new double[6];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		
		double sum1 = 0.0;
		double sum2 = 0.0;
		for (int i = 1 ; i <= D ; i++) {
		        sum1 += Math.pow(genes[i], 2);
		        sum2 += (Math.cos(2*Math.PI*genes[i]));
		}
		
		answer = ((-20.0)*(Math.exp(-0.2*Math.sqrt(sum1/D))) - Math.exp(sum2 / D) + 20  + Math.exp(1.0));
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Weierstrass5D(Individual5D person) {
		double answer;
		int Kmax = 20;
		double a = 0.5;
		double b = 3.0;
		double PIx2 =  Math.PI * 2.0;
		int D = 5;
		double genes[] = new double[6];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		
		double sum1 = 0.0;
	    for (int i = 1; i <= D; i++) {
	      for (int k = 0; k <= Kmax; k++) {
	        sum1 += Math.pow(a, k) * Math.cos(PIx2 * Math.pow(b, k) * (genes[i] + 0.5));
	      }
	    }
	    
	    double sum2 = 0.0;
	    for (int k = 0; k <= Kmax; k++) {
	      sum2 += Math.pow(a, k) * Math.cos(PIx2 * Math.pow(b, k) * (0.5));
	    }

	    answer = (sum1 - (sum2 * D));
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Greiwank5D(Individual5D person) {
		double answer;
		int D = 5;
		double genes[] = new double[6];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		
		double sum = 0;
		double multiply = 1;
		for (int i = 1; i <= D; i++) {
			sum += (Math.pow(genes[i], 2)/4000);
			multiply *= Math.cos(genes[i]/Math.sqrt(i));
		}
		
		answer = sum - multiply + 1;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Rastrigin5D(Individual5D person) {
		int D = 5;
		double answer;
		double PIx2 =  Math.PI * 2.0;
		double genes[] = new double[6];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		
		double sum = 0;
		for (int i = 1; i <= D; i++) {
			sum += (Math.pow(genes[i], 2) - (10 * Math.cos(PIx2*genes[i])) + 10);	
		}
		
		answer = sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Katsuura5D(Individual5D person) {
		int D = 5;
		double answer;
		double genes[] = new double[6];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		
        double product = 1;	  	
        for (int i = 0; i < D; i++) {	
        	double sum = 0;
        	for (int j = 1; j <= 32; j++) {	
        		double term = Math.pow(2, j) * genes[i];	 	             
        		sum += Math.abs(term - Math.round(term)) / Math.pow(2, j);
        	}
        	product *= Math.pow(1 + (i * sum), 10.0 / Math.pow(D, 1.2));
        }
        
        answer = (10/Math.pow(D, 2))*product - 10/Math.pow(D, 2);
		person.Set_Fitness(answer);
	}
}