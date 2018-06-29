/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class Fridge extends HomeAppliance{
	
	//Constructors
	public Fridge(){ super(); }
	
	public Fridge(String id, String model, int year, String constructor, double price, int q){
		super(id, model, year, constructor, price, q);
	}
	
	public Fridge(String id, String model, int year, String constructor, double price, int q, String ec){
		super(id, model, year, constructor, price, q, ec);
	}
	
	//Constructor with TV specs
	public Fridge(String id, String model, int year, String constructor, double price, int q, String ec, String type, double fc, double rc){
		super(id, model, year, constructor, price, q, ec);
		this.type = type;
		FreezerCapacity = fc;
		RefrigeratorCapacity = rc;
	}
	
	//Set methods
	public void setType(String type){
		this.type = type;
	}
	
	public void setFreezerCap(double fc){
		FreezerCapacity = fc;
	}
	
	public void setRefrigeratorCap(double rc){
		RefrigeratorCapacity = rc;
	}
	
	//Get methods
	public String getType(){
		return type;
	}
	
	public double getFreezerCap(){
		return FreezerCapacity;
	}
	
	public double getRefrigeratorCap(){
		return RefrigeratorCapacity;
	}
	
	//toString method
	public String toString(){
		return super.toString() + "\nFridge Type: " + getType() + "\nFreezer Capacity: " + getFreezerCap() +
		"L\nRefrigerator Capacity: " + getRefrigeratorCap() + "L\n______________";
	}
	
	private double FreezerCapacity, RefrigeratorCapacity;
	private String type;
	
}
