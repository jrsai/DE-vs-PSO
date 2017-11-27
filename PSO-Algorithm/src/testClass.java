import java.util.ArrayList;

public class testClass {
	
	private static int NUMBER_OF_RUNS = 51;

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		for (int i=0; i<NUMBER_OF_RUNS; i++) {
			new PSO().execute();
		}
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println("Runtime:" +  totalTime + "ms");
		
	}

}
