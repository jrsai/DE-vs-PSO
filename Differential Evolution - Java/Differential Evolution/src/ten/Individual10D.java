package ten;
public class Individual10D {
	private double gene1;
	private double gene2;
	private double gene3;
	private double gene4;
	private double gene5;
	private double gene6;
	private double gene7;
	private double gene8;
	private double gene9;
	private double gene10;
	private double fitness_value;
	
	public Individual10D(double value1, double value2, double value3, double value4, double value5, double value6, double value7, double value8, double value9, double value10) {
		this.gene1 = value1;
		this.gene2 = value2;
		this.gene3 = value3;
		this.gene4 = value4;
		this.gene5 = value5;
		this.gene6 = value6;
		this.gene7 = value7;
		this.gene8 = value8;
		this.gene9 = value9;
		this.gene10 = value10;
	}
	
	public void Set_Fitness(double fitness) {
		this.fitness_value = fitness;
	}
	
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
	
	public double get_gene6() {
		return gene6;
	}
	
	public double get_gene7() {
		return gene7;
	}
	
	public double get_gene8() {
		return gene8;
	}
	
	public double get_gene9() {
		return gene9;
	}
	
	public double get_gene10() {
		return gene10;
	}
	
	public double get_fitness() {
		return fitness_value;
	}
}