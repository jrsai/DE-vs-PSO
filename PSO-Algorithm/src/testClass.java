
public class testClass {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		for (int i=0; i<51; i++) {
			new PSO().execute();
		}
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Runtime:" +  totalTime + "ms");
	}

}
