/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class Order extends Action{
	
	//Constructors
	public Order(){ super(); }
	
	public Order(Item device, String name, String phone){
		super(device, name, phone);
	}
	
	public Order(Item device, String name, String phone, String order_date, String arrive_date, int code){
		super(device, name, phone);
		order_code = code;
		this.order_date = order_date;
		this.arrive_date = arrive_date;
	}
	
	//Get methods
	public int getOrderCode(){
		return order_code;
	}
	
	public String getOrderDate(){
		return order_date;
	}
	
	public String getArriveDate(){
		return arrive_date;
	}
	
	public boolean hasArrived(){
		return arrived;
	}
	
	//Set methods
	public void setOrderCode(int order_code){
		this.order_code = order_code;
	}
	
	public void setOrderDate(String order_date){
		this.order_date = order_date;
	}
	
	public void setArriveDate(String arrive_date){
		this.arrive_date = arrive_date;
	}
	
	public void setArrived(boolean arrived){
		this.arrived = arrived;
	}
	
	//toString method
	public String toString(){
		return getDevice() + "\nOrder Code: " + getOrderCode() + "\nName: " + getName() + "\nPhone: " + getPhone() + "\nOrder date: " + getOrderDate() + "\nEstimated arrival date: " + getArriveDate() + "\nArrived: " + hasArrived() + "\nFinal price: " + getDevice().getPrice() + "\n";
	}
	
	private Item device;
	private double final_price;
	private int order_code;
	private String order_date, arrive_date;
	private boolean arrived = false;
	
}
