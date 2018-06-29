/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class Purchased extends Action{
	
	//Constructors
	public Purchased(){ super(); }
	
	public Purchased(Item device, String name, String phone){
		super(device, name, phone);
	}
	
	public Purchased(Item device, String name, String phone, String date, int code){
		super(device, name, phone);
		this.code = code;
		this.date = date;
		device.setQuantity(device.getQuantity() - 1);
	}
	
	//Get methods
	public String getDate(){
		return date;
	}
	
	public int getPurchaseCode(){
		return code;
	}
	
	//Set methods
	public void setDate(String date){
		this.date = date;
	}
	
	public void setPurchaseCode(int code){
		this.code = code;
	}
	
	//toString method
	public String toString(){
		return getDevice() + "\nPurchase Code: " + getPurchaseCode() + "\nName: " + getName() + "\nPhone: " + getPhone() + " \nDate: " + getDate() + "\nFinal price: " + getDevice().getPrice() + "\n";
	}
	
	private String date;
	private int code;
}
