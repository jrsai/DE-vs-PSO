import java.util.ArrayList;

public class fitnessList extends ArrayList<Double> {

	private ArrayList<Double> list = new ArrayList<>();

	public fitnessList(ArrayList<Double> list) {
		super();
		this.list = list;
	}

	public ArrayList<Double> getfitnessList() {
		return list;
	}

	public void setfitnessLet(ArrayList<Double> list) {
		this.list = list;
	} 
	
}