public class Functions implements ControlParameters {
	public static final double BOUND_LOW = -10;
	public static final double BOUND_HIGH = 10;
	public static final double ERR_TOLERANCE = 1E-20; // the smaller the tolerance, the more accurate the result, 
	                                                  // but the number of iteration is increased
	
	public static double evaluate(Location location) {
		double result = 0;
		
		switch(FUNCTION_NUM){
		case 1 : 
			result = AssignFitness_HighConditioned(location);
			break;
		case 2 :
			result = AssignFitness_BentCigar(location);
			break;
		case 3 :
			result = AssignFitness_DiscusFunc(location);
			break;
		case 4 :
			result = AssignFitness_Rosenbrock(location);
			break;
		case 5 :
			result = AssignFitness_Ackleys(location);
			break;
		case 6 :
			result = AssignFitness_Weierstrass(location);
			break;
		case 7 :
			result = AssignFitness_Greiwank(location);
			break;
		case 8 :
			result = AssignFitness_Rastrigin(location);
			break;
		case 9 :
			result = AssignFitness_Katsuura(location);
			break;
		}
		
		
		return result;
	}
	
	
	public static double AssignFitness_HighConditioned(Location location) {
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		
		for (int i = 1; i <= PROBLEM_DIMENSION; i++) {
			double exp  = (i - 1)/(PROBLEM_DIMENSION - 1);
			sum = sum + (Math.pow(ten_6, exp) * Math.pow(location.getLoc()[i-1], 2));	
		}
		
		double answer = sum;
		return answer;
	}
	
	public static double AssignFitness_BentCigar(Location location) {
		double answer;

		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 2; i <= PROBLEM_DIMENSION; i++) {
			sum = sum + (Math.pow(location.getLoc()[i-1], 2));	
		}
		
		answer = Math.pow(location.getLoc()[0], 2) + ten_6*sum;
		return answer;
	}
	
	public static double AssignFitness_DiscusFunc(Location location) {
		double answer;
		
		double sum = 0;
		double ten_6 = Math.pow(10, 6);
		for (int i = 2; i <= PROBLEM_DIMENSION; i++) {
			sum = sum + (Math.pow(location.getLoc()[i-1], 2));	
		}
		
		answer = ten_6*Math.pow(location.getLoc()[0], 2) + sum;
		return answer;
	}
	
	public static double AssignFitness_Rosenbrock(Location location) {	
		double sum = 0;
		double answer;
		for (int i = 1; i <= (PROBLEM_DIMENSION-1); i++) {
			double calculate = (Math.pow(location.getLoc()[i-1], 2) - location.getLoc()[i]);
			double calculate1 = (Math.pow( (location.getLoc()[i-1] - 1) , 2 )); 
			sum = sum + ((100*Math.pow(calculate, 2))+(calculate1));
		}
		
		answer = sum;
		return answer;
	}
	
	public static double AssignFitness_Ackleys(Location location) {
		double answer;
		
		double sum1 = 0.0;
		double sum2 = 0.0;
		for (int i = 1; i <= PROBLEM_DIMENSION ; i++) {
		        sum1 += Math.pow(location.getLoc()[i-1], 2);
		        sum2 += (Math.cos(2*Math.PI*location.getLoc()[i-1]));
		}
		
		answer = ((-20.0)*(Math.exp(-0.2*Math.sqrt(sum1/PROBLEM_DIMENSION))) - Math.exp(sum2 / PROBLEM_DIMENSION) + 20  + Math.exp(1.0));
		return answer;
	}
	
	public static double AssignFitness_Weierstrass(Location location) {
		double answer;
		int Kmax = 20;
		double a = 0.5;
		double b = 3.0;
		double PIx2 =  Math.PI * 2.0;
		
		double sum1 = 0.0;
	    for (int i = 1; i <= PROBLEM_DIMENSION; i++) {
	      for (int k = 0; k <= Kmax; k++) {
	        sum1 += Math.pow(a, k) * Math.cos(PIx2 * Math.pow(b, k) * (location.getLoc()[i-1] + 0.5));
	      }
	    }
	    
	    double sum2 = 0.0;
	    for (int k = 0; k <= Kmax; k++) {
	      sum2 += Math.pow(a, k) * Math.cos(PIx2 * Math.pow(b, k) * (0.5));
	    }

	    answer = (sum1 - (sum2 * PROBLEM_DIMENSION));
		return answer;
	}
	
	public static double AssignFitness_Greiwank(Location location) {
		double answer;

		double sum = 0;
		double multiply = 1;
		for (int i = 1; i <= PROBLEM_DIMENSION; i++) {
			sum += (Math.pow(location.getLoc()[i-1], 2)/4000);
			multiply *= Math.cos(location.getLoc()[i-1]/Math.sqrt(i));
		}
		
		answer = sum - multiply + 1;
		return answer;
	}
	
	public static double AssignFitness_Rastrigin(Location location) {
		double answer;
		double PIx2 =  Math.PI * 2.0;

		
		double sum = 0;
		for (int i = 1; i <= PROBLEM_DIMENSION; i++) {
			sum += (Math.pow(location.getLoc()[i-1], 2) - (10 * Math.cos(PIx2*location.getLoc()[i-1])) + 10);	
		}
		
		answer = sum;
		return answer;
	}
	
	public static double AssignFitness_Katsuura(Location location) {
		double answer;
	
        double product = 1;	  	
        for (int i = 0; i < PROBLEM_DIMENSION; i++) {	
        	double sum = 0;
        	for (int j = 1; j <= 32; j++)  {	
        		double term = Math.pow(2, j) * location.getLoc()[i];	 	             
        		sum += Math.abs(term - Math.round(term)) / Math.pow(2, j);
        	}	 	            
        	product *= Math.pow(1 + (i * sum), 10.0 / Math.pow(PROBLEM_DIMENSION, 1.2));
        }
        
        answer = (10/Math.pow(PROBLEM_DIMENSION, 2))*product - 10/Math.pow(PROBLEM_DIMENSION, 2);
		return answer;
	}
}
