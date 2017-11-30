package ten;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DE10D {
	static ArrayList<ArrayList<Double>> listOLists = new ArrayList<ArrayList<Double>>();
	static ArrayList<Double> bestOfCurrentGen = new ArrayList<Double>();
	static ArrayList<Double> bestList = new ArrayList<Double>();
	
	public static void main(String[] args) {
		int count = 1;
		
		for (int k = 0; k < 51; k++) {
			Individual10D[] initial_parents = initialize_population(100);
			double[] temp = new double[100];
			int D = 10;
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
						
					Individual10D candidate1 = initial_parents[random_candidates1];
					Individual10D candidate2 = initial_parents[random_candidates2];
					Individual10D candidate3 = initial_parents[random_candidates3];
						
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
			
			Individual10D best = new Individual10D(0,0,0,0,0,0,0,0,0,0);
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
	
	public static Individual10D[] initialize_population(int size) {
		Individual10D[] population = new Individual10D[size];
		
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
			double random6 = ((Math.random() * 10) - 10);
			double gene6 = random6;
			double random7 = ((Math.random() * 10) - 10);
			double gene7 = random7;
			double random8 = ((Math.random() * 10) - 10);
			double gene8 = random8;
			double random9 = ((Math.random() * 10) - 10);
			double gene9 = random9;	
			double random10 = ((Math.random() * 10) - 10);
			double gene10 = random10;
			population[size_of_population] = new Individual10D(gene1,gene2,gene3,gene4,gene5,gene6,gene7,gene8,gene9,gene10);
		}
		return population;
	}
		
	public static Individual10D Mutation(Individual10D parent, Individual10D one, Individual10D two, Individual10D three) {
		double F = 0.8;
		double result1 = one.get_gene1() - two.get_gene1();
		double result2 = one.get_gene2() - two.get_gene2();
		double result3 = one.get_gene3() - two.get_gene3();
		double result4 = one.get_gene4() - two.get_gene4();
		double result5 = one.get_gene5() - two.get_gene5();
		double result6 = one.get_gene6() - two.get_gene6();
		double result7 = one.get_gene7() - two.get_gene7();
		double result8 = one.get_gene8() - two.get_gene8();
		double result9 = one.get_gene9() - two.get_gene9();
		double result10 = one.get_gene10() - two.get_gene10();
		double transform_result1 = (three.get_gene1() + F*(result1));
		double transform_result2 = (three.get_gene2() + F*(result2));
		double transform_result3 = (three.get_gene3() + F*(result3));
		double transform_result4 = (three.get_gene4() + F*(result4));
		double transform_result5 = (three.get_gene5() + F*(result5));
		double transform_result6 = (three.get_gene6() + F*(result6));
		double transform_result7 = (three.get_gene7() + F*(result7));
		double transform_result8 = (three.get_gene8() + F*(result8));
		double transform_result9 = (three.get_gene9() + F*(result9));
		double transform_result10 = (three.get_gene10() + F*(result10));
		Individual10D mutant = new Individual10D(transform_result1, transform_result2, transform_result3, transform_result4, transform_result5, transform_result6, transform_result7, transform_result8, transform_result9, transform_result10);
		
		double genes[] = new double[11];
		double parent_genes[] = new double[11];
		parent_genes[1] = parent.get_gene1();
		parent_genes[2] = parent.get_gene2();
		parent_genes[3] = parent.get_gene3();
		parent_genes[4] = parent.get_gene4();
		parent_genes[5] = parent.get_gene5();
		parent_genes[6] = parent.get_gene6();
		parent_genes[7] = parent.get_gene7();
		parent_genes[8] = parent.get_gene8();
		parent_genes[9] = parent.get_gene9();
		parent_genes[10] = parent.get_gene10();
		
		double mutant_genes[] = new double[11];
		mutant_genes[1] = mutant.get_gene1();
		mutant_genes[2] = mutant.get_gene2();
		mutant_genes[3] = mutant.get_gene3();
		mutant_genes[4] = mutant.get_gene4();
		mutant_genes[5] = mutant.get_gene5();
		mutant_genes[6] = mutant.get_gene6();
		mutant_genes[7] = mutant.get_gene7();
		mutant_genes[8] = mutant.get_gene8();
		mutant_genes[9] = mutant.get_gene9();
		mutant_genes[10] = mutant.get_gene10();
		
		double rand = Math.random();
		for (int i = 1; i <= 10; i++) {
			if (rand <= 0.9) {
				genes[i] = mutant_genes[i];
			}
			if (rand > 0.9) {
				genes[i] = parent_genes[i];
			}
		}
		
		Individual10D child = new Individual10D(genes[1],genes[2],genes[3],genes[4],genes[5],genes[6],genes[7],genes[8],genes[9],genes[10]);
		//AssignFitness_HighConditioned10D(child);
		//AssignFitness_BentCigar10D(child);
		//AssignFitness_DiscusFunc10D(child);
		//AssignFitness_Rosenbrock10D(child);
		//AssignFitness_Ackleys10D(child);
		//AssignFitness_Weierstrass10D(child);
		//AssignFitness_Greiwank10D(child);
		//AssignFitness_Rastrigin10D(child);
		AssignFitness_Katsuura10D(child);
		double child_fitness = child.get_fitness();
		
		//AssignFitness_HighConditioned10D(parent);
		//AssignFitness_BentCigar10D(parent);
		//AssignFitness_DiscusFunc10D(parent);
		//AssignFitness_Rosenbrock10D(parent);
		//AssignFitness_Ackleys10D(parent);
		//AssignFitness_Weierstrass10D(parent);
		//AssignFitness_Greiwank10D(parent);
		//AssignFitness_Rastrigin10D(parent);
		AssignFitness_Katsuura10D(parent);
		double parent_fitness = parent.get_fitness();
		
		if (child_fitness < parent_fitness) {
			return child;
		} else {
			return parent;
		}
	}
	public static void tallyData() {
		int current = 0;
		double sum = 0; double average = 0;
		while (current < 300) {
			for (int i = 0; i < listOLists.size(); i++) {
				sum += listOLists.get(i).get(current);
			}
			
			average = sum/listOLists.size();
			sum = 0;
			tallyData("D10_Katsuura10D.csv", average);
			current++;
		}
	}
	
	public static void sendGen(Individual10D[] people) {
		for (int i = 0; i < people.length; i++) {
			tallyData("D10_Katsuura10D_All.csv", people[i].get_gene1(), people[i].get_gene2(), people[i].get_fitness());
		}
	}
	
	public static void AssignFitness_HighConditioned10D(Individual10D person) {
		int D = 10;
		double genes[] = new double[11];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		genes[6] = person.get_gene6();
		genes[7] = person.get_gene7();
		genes[8] = person.get_gene8();
		genes[9] = person.get_gene9();
		genes[10] = person.get_gene10();
		
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 1; i <= D; i++) {
			double exp  = (i - 1)/(D - 1);
			sum = sum + (Math.pow(ten_6, exp) * Math.pow(genes[i], 2));	
		}
		
		double answer = sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_BentCigar10D(Individual10D person) {
		int D = 10;
		double answer;
		double genes[] = new double[11];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		genes[6] = person.get_gene6();
		genes[7] = person.get_gene7();
		genes[8] = person.get_gene8();
		genes[9] = person.get_gene9();
		genes[10] = person.get_gene10();
		
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 2; i <= D; i++) {
			sum = sum + (Math.pow(genes[i], 2));	
		}
		
		answer = Math.pow(genes[1], 2) + ten_6*sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_DiscusFunc10D(Individual10D person) {
		int D = 10;
		double answer;
		double genes[] = new double[11];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		genes[6] = person.get_gene6();
		genes[7] = person.get_gene7();
		genes[8] = person.get_gene8();
		genes[9] = person.get_gene9();
		genes[10] = person.get_gene10();
		
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 2; i <= D; i++) {
			sum = sum + (Math.pow(genes[i], 2));	
		}
		
		answer = ten_6*Math.pow(genes[1], 2) + sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Rosenbrock10D(Individual10D person) {
		int D = 10;
		double genes[] = new double[11];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		genes[6] = person.get_gene6();
		genes[7] = person.get_gene7();
		genes[8] = person.get_gene8();
		genes[9] = person.get_gene9();
		genes[10] = person.get_gene10();
		
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
	
	public static void AssignFitness_Ackleys10D(Individual10D person) {
		double answer;
		int D = 10;
		double genes[] = new double[11];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		genes[6] = person.get_gene6();
		genes[7] = person.get_gene7();
		genes[8] = person.get_gene8();
		genes[9] = person.get_gene9();
		genes[10] = person.get_gene10();
		
		double sum1 = 0.0;
		double sum2 = 0.0;
		for (int i = 1 ; i <= D ; i++) {
		        sum1 += Math.pow(genes[i], 2);
		        sum2 += (Math.cos(2*Math.PI*genes[i]));
		}
		
		answer = ((-20.0)*(Math.exp(-0.2*Math.sqrt(sum1/D))) - Math.exp(sum2 / D) + 20  + Math.exp(1.0));
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Weierstrass10D(Individual10D person) {
		double answer;
		int Kmax = 20;
		double a = 0.5;
		double b = 3.0;
		double PIx2 =  Math.PI * 2.0;
		int D = 10;
		double genes[] = new double[11];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		genes[6] = person.get_gene6();
		genes[7] = person.get_gene7();
		genes[8] = person.get_gene8();
		genes[9] = person.get_gene9();
		genes[10] = person.get_gene10();
		
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
	
	public static void AssignFitness_Greiwank10D(Individual10D person) {
		double answer;
		int D = 10;
		double genes[] = new double[11];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		genes[6] = person.get_gene6();
		genes[7] = person.get_gene7();
		genes[8] = person.get_gene8();
		genes[9] = person.get_gene9();
		genes[10] = person.get_gene10();
		
		double sum = 0;
		double multiply = 1;
		for (int i = 1; i <= D; i++) {
			sum += (Math.pow(genes[i], 2)/4000);
			multiply *= Math.cos(genes[i]/Math.sqrt(i));
		}
		
		answer = sum - multiply + 1;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Rastrigin10D(Individual10D person) {
		int D = 10;
		double answer;
		double PIx2 =  Math.PI * 2.0;
		double genes[] = new double[11];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		genes[6] = person.get_gene6();
		genes[7] = person.get_gene7();
		genes[8] = person.get_gene8();
		genes[9] = person.get_gene9();
		genes[10] = person.get_gene10();
		
		double sum = 0;
		for (int i = 1; i <= D; i++) {
			sum += (Math.pow(genes[i], 2) - (10 * Math.cos(PIx2*genes[i])) + 10);	
		}
		
		answer = sum;
		person.Set_Fitness(answer);
	}
	
	public static void AssignFitness_Katsuura10D(Individual10D person) {
		int D = 10;
		double answer;
		double genes[] = new double[11];
		
		genes[1] = person.get_gene1();
		genes[2] = person.get_gene2();
		genes[3] = person.get_gene3();
		genes[4] = person.get_gene4();
		genes[5] = person.get_gene5();
		genes[6] = person.get_gene6();
		genes[7] = person.get_gene7();
		genes[8] = person.get_gene8();
		genes[9] = person.get_gene9();
		genes[10] = person.get_gene10();
		
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