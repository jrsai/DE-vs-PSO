public interface ControlParameters {
	
	int SWARM_SIZE = 100;		
	int PROBLEM_DIMENSION = 2;	//2, 5, 10
	int MAX_ITERATION = 5000*PROBLEM_DIMENSION;
	double C1 = 2.05;
	double C2 = 2.05;
	double W_UPPERBOUND = 0.9;
	double W_LOWERBOUND = 0.4;
}