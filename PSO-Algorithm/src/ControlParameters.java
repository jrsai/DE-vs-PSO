public interface ControlParameters {
	
	int SWARM_SIZE = 100; // Population		
	int PROBLEM_DIMENSION = 2;	//2, 5, 10
	int MAX_ITERATION = 3000*PROBLEM_DIMENSION; 
	
	int FUNCTION_NUM = 2; 
	/* 1 = High Conditioned Elliptic Function
	 * 2 = Bent Cigar Function
	 * 3 = Discus Function
	 * 4 = Rosenbrock's Functions
	 * 5 = Ackley's Function
	 * 6 = Weierstrass Function
	 * 7 = Griewank's Function
	 * 8 = Rastrigin's Function
	 * 9 = Katsuura Function
	 * */
	
	 // Given in Project Description
	double C1 = 2.05;
	double C2 = 2.05; 
	double W_UPPERBOUND = 0.9; 
	double W_LOWERBOUND = 0.4; 
	
}