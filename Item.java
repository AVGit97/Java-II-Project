/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class Item{
	
	//Constructors
	public Item(){}
	
	public Item(String id, String model, int year, String cons, double price, int q){
		this.id = id;
		this.model = model;
		this.year = year;
		constructor = cons;
		this.price = price;
		quantity = q;
	}
	
	//Set methods
	public void setID(String id){
		this.id = id;
	}
	
	public void setModel(String model){
		this.model = model;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public void setConstructor(String cons){
		constructor = cons;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public void setQuantity(int q){
		quantity = q;
	}
	
	//Get methods
	public String getID(){
		return id;
	}
	
	public String getModel(){
		return model;
	}
	
	public int getYear(){
		return year;
	}
	
	public String getConstructor(){
		return constructor;
	}
	
	public double getPrice(){
		return price;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	//toString method
	public String toString(){
		return "\nID: " + getID() + "\nModel: " + getModel() +
		"\nYear: " + getYear() + "\nConstructor: " + getConstructor() +
		"\nPrice: " + getPrice() + "\nAvailable: " + getQuantity() + " pieces";
	}
	
	private String id, model, constructor;
	private int year;
	private double price;
	private int quantity;

}
