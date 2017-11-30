package five;

//5 dimensional individual
public class Individual5D {
	//genes to help determine fitness value
	private double gene1;
	private double gene2;
	private double gene3;
	private double gene4;
	private double gene5;
	private double fitness_value;
	
	//constructor methods
	public Individual5D(double value1, double value2, double value3, double value4, double value5) {
		this.gene1 = value1;
		this.gene2 = value2;
		this.gene3 = value3;
		this.gene4 = value4;
		this.gene5 = value5;
	}
	
	//setting fitness value
	public void Set_Fitness(double fitness) {
		this.fitness_value = fitness;
	}
	
	//getting genes which are encapsulated
	public double get_gene1() {
		return gene1;
	}
	
	public double get_gene2() {
		return gene2;
	}
	
	public double get_gene3() {
		return gene3;
	}
	
	public double get_gene4() {
		return gene4;
	}
	
	public double get_gene5() {
		return gene5;
	}
	
	public double get_fitness() {
		return fitness_value;
	}
}