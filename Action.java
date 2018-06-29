/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class Action{
	
	//Constructors
	public Action(){}
	
	public Action(Item device, String name, String phone){
		this.device = device;
		this.name = name;
		this.phone = phone;
	}
	
	//Get methods
	public Item getDevice(){
		return device;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPhone(){
		return phone;
	}
	
	//Set methods
	public void setDevice(Item device){
		this.device = device;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	private Item device;
	private String name, phone;
	
}
