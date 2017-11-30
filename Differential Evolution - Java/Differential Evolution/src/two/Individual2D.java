package two;


public class Individual2D {
	private double gene1;
	private double gene2;
	private double fitness_value;
	
	public Individual2D(double value1, double value2){
		this.gene1 = value1;
		this.gene2 = value2;
	}
	
	public void Set_Fitness(double fitness)
	{
		this.fitness_value = fitness;
	}
	
	public double get_gene1()
	{
		return gene1;
	}
	
	public double get_gene2()
	{
		return gene2;
	}
	
	public double get_fitness()
	{
		return fitness_value;
	}
}

