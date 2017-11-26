public class Functions implements ControlParameters {
	public static final double LOC_X_LOW = -10;
	public static final double LOC_X_HIGH = 10;
	public static final double LOC_Y_LOW = -10;
	public static final double LOC_Y_HIGH = 10;
	public static final double VEL_LOW = -10;
	public static final double VEL_HIGH = 10;
	
	public static final double ERR_TOLERANCE = 1E-20; // the smaller the tolerance, the more accurate the result, 
	                                                  // but the number of iteration is increased
	
	public static double evaluate(Location location) {
		double result = 0;
		double x = location.getLoc()[0]; // the "x" part of the location
		double y = location.getLoc()[1]; // the "y" part of the location
		
		// Replace with switch statement and cases for each function
		switch(FUNCTION_NUM){
		case 1 : 
			AssignFitness_HighConditioned2D();
			break;
		case 2 :
			AssignFitness_BentCigar2D
			break;
		case 3 :
			AssignFitness_DiscusFunc2D
			break;
		case 4 :
			AssignFitness_Rosenbrock2D
			break;
		case 5 :
			AssignFitness_Ackleys2D
			break;
		case 6 :
			AssignFitness_Weierstrass2D
			break;
		case 7 :
			AssignFitness_Greiwank2D
			break;
		case 8 :
			AssignFitness_Rastrigin2D
			break;
		case 9 :
			AssignFitness_Katsuura2D
			break;
		}
		
		
		return result;
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
