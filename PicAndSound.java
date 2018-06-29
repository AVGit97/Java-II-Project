/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class PicAndSound extends Item{
	
	//Constructors
	public PicAndSound(){ super(); }
	
	public PicAndSound(String id, String model, int year, String constructor, double price, int q){
		super(id, model, year, constructor, price, q);
		setPrice(0.75 * getPrice());
	}
	
	//Constructor with Pic & Sound specs
	public PicAndSound(String id, String model, int year, String constructor, double price, int q, String type){
		super(id, model, year, constructor, price, q);
		this.type = type;
		setPrice(0.75 * getPrice());
	}
	
	//Set methods
	public void setType(String type){
		this.type = type;
	}
	
	//Get methods
	public String getType(){
		return type;
	}
	
	//toString method
	public String toString(){
		return super.toString() + "\nType: " + getType();
	}
	
	private String type;

}
