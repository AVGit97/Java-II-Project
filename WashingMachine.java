/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class WashingMachine extends HomeAppliance{
	
	//Constructors
	public WashingMachine(){ super(); }
	
	public WashingMachine(String id, String model, int year, String constructor, double price, int q){
		super(id, model, year, constructor, price, q);
	}
	
	public WashingMachine(String id, String model, int year, String constructor, double price, int q, String ec){
		super(id, model, year, constructor, price, q, ec);
	}
	
	//Constructor with WashingMachine specs
	public WashingMachine(String id, String model, int year, String constructor, double price, int q, String ec, double cap, double bends){
		super(id, model, year, constructor, price, q, ec);
		capacity = cap;
		this.bends = bends;
	}
	
	//Set methods
	public void setCapacity(double cap){
		capacity = cap;
	}
	
	public void setBends(double bends){
		this.bends = bends;
	}
	
	//Get methods
	public double getCapacity(){
		return capacity;
	}
	
	public double getBends(){
		return bends;
	}
	
	//toString method
	public String toString(){
		return super.toString() + "\nCapacity: " + getCapacity() + " kg\nSpin speed: " + getBends() +
		" rpm\n______________";
	}
	
	private double capacity, bends;
	
}
