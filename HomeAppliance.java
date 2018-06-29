/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class HomeAppliance extends Item{
	
	//Constructors
	public HomeAppliance(){ super(); }
	
	public HomeAppliance(String id, String model, int year, String constructor, double price, int q){
		super(id, model, year, constructor, price, q);
		setPrice(0.8 * getPrice());
	}
	
	//Constructor with HomeAppliance specs
	public HomeAppliance(String id, String model, int year, String constructor, double price, int q, String ec){
		super(id, model, year, constructor, price, q);
		setPrice(0.8 * getPrice());
		energyClass = ec;
	}
	
	//Set methods
	public void setEC(String ec){
		energyClass = ec;
	}
	
	//Get methods
	public String getEC(){
		return energyClass;
	}
	
	//toString method
	public String toString(){
		return super.toString() + "\nEnergy Class: " + getEC();
	}
	
	private String energyClass;
	
}
