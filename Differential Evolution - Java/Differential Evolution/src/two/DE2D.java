package two;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DE2D {
	static ArrayList<ArrayList<Double>> listOLists = new ArrayList<ArrayList<Double>>();
	static ArrayList<Double> bestOfCurrentGen = new ArrayList<Double>();
	static ArrayList<Double> bestList = new ArrayList<Double>();
	
	public static void main(String[] args) {
		int count = 1;
		
		for (int k = 0; k < 51; k++) {
			Individual2D[] initial_parents = initialize_population(100);
			double[] temp = new double[100];
			int D = 2;
			int generations = 3000*D;
			
			while (generations > 0) {
				for (int i = 0; i <= (initial_parents.length - 1); i++) {
					int random_candidates1 = (int) (Math.random() * 100);
					int random_candidates2 = (int) (Math.random() * 100);
					int random_candidates3 = (int) (Math.random() * 100);
					
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
					
					Individual2D candidate1 = initial_parents[random_candidates1];
					Individual2D candidate2 = initial_parents[random_candidates2];
					Individual2D candidate3 = initial_parents[random_candidates3];
					
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

			Individual2D best = new Individual2D(0,0);
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
		tallyData();
		
		calculateMean();
		calculateWorst();
		calculateBest();
		calculateSD();
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
	
	public static Individual2D[] initialize_population(int size) {
		Individual2D[] population = new Individual2D[size];
		
		for (int size_of_population = 0; size_of_population <= (population.length-1); size_of_population++) {
			double random = ((Math.random() * 10) - 10);
			double gene1 = random;
			double random2 = ((Math.random() * 10) - 10);
			double gene2 = random2;			
			population[size_of_population] = new Individual2D(gene1,gene2);
		}
		return population;
	}
	
	public static void tallyData() {
		int current = 0;
		double sum = 0; double average = 0;
		while (current < 60) {
			for (int i = 0; i < listOLists.size(); i++) {
				sum += listOLists.get(i).get(current);
			}
			
			average = sum/listOLists.size();
			sum = 0;
			tallyData("D2_Katsuura2D.csv", average);
			current++;
		}
	}
	
	public static void sendGen(Individual2D[] people) {
		for (int i = 0; i < people.length; i++) {
			tallyData("D2_Katsuura2D_All.csv", people[i].get_gene1(), people[i].get_gene2(), people[i].get_fitness());
		}
	}
		
	public static Individual2D Mutation(Individual2D parent, Individual2D one, Individual2D two, Individual2D three) {
		double F = 0.8;
		double result1 = one.get_gene1() - two.get_gene1();
		double result2 = one.get_gene2() - two.get_gene2();
		double transform_result1 = (three.get_gene1() + F*(result1));
		double transform_result2 = (three.get_gene2() + F*(result2));
		
		Individual2D mutant = new Individual2D(transform_result1,transform_result2);
		double genes[] = new double[3];
		double rand = Math.random();
		
		for (int i = 1; i <= 2; i++) {
			if (rand <= 0.9 && i == 1) {
				genes[1] = mutant.get_gene1();
			}
			if (rand > 0.9 && i == 1) {
				genes[1] = parent.get_gene1();
			}
			if (rand <= 0.9 && i == 2) {
				genes[2] = mutant.get_gene2();
			}
			if (rand > 0.9 && i == 2) {
				genes[2] = parent.get_gene2();
			}
		}
		
		Individual2D child = new Individual2D(genes[1],genes[2]);
		//AssignFitness_HighConditioned2D(child);
		//AssignFitness_BentCigar2D(child);
		//AssignFitness_DiscusFunc2D(child);
		//AssignFitness_Rosenbrock2D(child);
		//AssignFitness_Ackleys2D(child);
		//AssignFitness_Weierstrass2D(child);
		//AssignFitness_Greiwank2D(child);
		//AssignFitness_Rastrigin2D(child);
		AssignFitness_Katsuura2D(child);
		double child_fitness = child.get_fitness();
		
		//AssignFitness_HighConditioned2D(parent);
		//AssignFitness_BentCigar2D(parent);
		//AssignFitness_DiscusFunc2D(parent);
		//AssignFitness_Rosenbrock2D(parent);
		//AssignFitness_Ackleys2D(parent);
		//AssignFitness_Weierstrass2D(parent);
		//AssignFitness_Greiwank2D(parent);
		//AssignFitness_Rastrigin2D(parent);
		AssignFitness_Katsuura2D(parent);
		double parent_fitness = parent.get_fitness();
		
		if (child_fitness < parent_fitness) {
			return child;
		} else { 
			return parent;
		}
	}
	
	public static void AssignFitness_HighConditioned2D(Individual2D person) {
		int D = 2;
		double genes[] = new double[3];
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		
		for (int i = 1; i <= D; i++) {
			double exp  = (i - 1)/(D - 1);
			sum = sum + (Math.pow(ten_6, exp) * Math.pow(genes[i], 2));	
		}
		
		double answer = sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_BentCigar2D(Individual2D person) {
		int D = 2;
		double answer;
		double genes[] = new double[3];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 2; i <= D; i++) {
			sum = sum + (Math.pow(genes[i], 2));	
		}
		
		answer = Math.pow(genes[1], 2) + ten_6*sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_DiscusFunc2D(Individual2D person) {
		int D = 2;
		double answer;
		double genes[] = new double[3];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 2; i <= D; i++) {
			sum = sum + (Math.pow(genes[i], 2));	
		}
		
		answer = ten_6*Math.pow(genes[1], 2) + sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Rosenbrock2D(Individual2D person) {
		int D = 2;
		double genes[] = new double[3];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		
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
	
	public static void AssignFitness_Ackleys2D(Individual2D person) {
		double answer;
		int D = 2;
		double genes[] = new double[3];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		
		double sum1 = 0.0;
		double sum2 = 0.0;
		for (int i = 1 ; i <= D ; i++) {
		        sum1 += Math.pow(genes[i], 2);
		        sum2 += (Math.cos(2*Math.PI*genes[i]));
		}
		
		answer = ((-20.0)*(Math.exp(-0.2*Math.sqrt(sum1/D))) - Math.exp(sum2 / D) + 20  + Math.exp(1.0));
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Weierstrass2D(Individual2D person) {
		double answer;
		int Kmax = 20;
		double a = 0.5;
		double b = 3.0;
		double PIx2 =  Math.PI * 2.0;
		int D = 2;
		double genes[] = new double[3];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		
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
	
	public static void AssignFitness_Greiwank2D(Individual2D person) {
		double answer;
		int D = 2;
		double genes[] = new double[3];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		
		double sum = 0;
		double multiply = 1;
		for (int i = 1; i <= D; i++) {
			sum += (Math.pow(genes[i], 2)/4000);
			multiply *= Math.cos(genes[i]/Math.sqrt(i));
		}
		
		answer = sum - multiply + 1;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Rastrigin2D(Individual2D person) {
		int D = 2;
		double answer;
		double PIx2 =  Math.PI * 2.0;
		double genes[] = new double[3];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		
		double sum = 0;
		for (int i = 1; i <= D; i++) {
			sum += (Math.pow(genes[i], 2) - (10 * Math.cos(PIx2*genes[i])) + 10);	
		}
		
		answer = sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Katsuura2D(Individual2D person) {
		int D = 2;
		double answer;
		double genes[] = new double[3];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		
        double product = 1;	  	
        for (int i = 0; i < D; i++) {	
        	double sum = 0;
        	for (int j = 1; j <= 32; j++)  {	
        		double term = Math.pow(2, j) * genes[i];	 	             
        		sum += Math.abs(term - Math.round(term)) / Math.pow(2, j);
        	}	 	            
        	product *= Math.pow(1 + (i * sum), 10.0 / Math.pow(D, 1.2));
        }
        
        answer = (10/Math.pow(D, 2))*product - 10/Math.pow(D, 2);
		person.Set_Fitness(answer);
	}
}