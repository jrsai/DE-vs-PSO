public class Functions {
	public static final double LOC_X_LOW = 1;
	public static final double LOC_X_HIGH = 4;
	public static final double LOC_Y_LOW = -1;
	public static final double LOC_Y_HIGH = 1;
	public static final double VEL_LOW = -1;
	public static final double VEL_HIGH = 1;
	
	public static final double ERR_TOLERANCE = 1E-20; // the smaller the tolerance, the more accurate the result, 
	                                                  // but the number of iteration is increased
	
	public static double evaluate(Location location) {
		double result = 0;
		double x = location.getLoc()[0]; // the "x" part of the location
		double y = location.getLoc()[1]; // the "y" part of the location
		
		// Replace with switch statement and cases for each function
		return result;
	}
}