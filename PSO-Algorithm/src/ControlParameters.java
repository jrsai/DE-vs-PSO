public interface ControlParameters {
	
	int SWARM_SIZE = 100; // Population		
	int PROBLEM_DIMENSION = 10;	//2, 5, 10
	int MAX_ITERATION = 3000*PROBLEM_DIMENSION; 
	
	int FUNCTION_NUM = 6; 
	/* 1 = High Conditioned Elliptic Function (Medium)
	 * 2 = Bent Cigar Function (Fast)
	 * 3 = Discus Function (Fast)
	 * 4 = Rosenbrock's Functions (Fast)
	 * 5 = Ackley's Function (Medium)
	 * 6 = Weierstrass Function (Slow)
	 * 7 = Griewank's Function (Medium)
	 * 8 = Rastrigin's Function (Medium)
	 * 9 = Katsuura Function (Slow)
	 * */
	
	 // Given in Project Description
	double C1 = 2.05;
	double C2 = 2.05; 
	double W_UPPERBOUND = 0.9; 
	double W_LOWERBOUND = 0.4; 
	
}