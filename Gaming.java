/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class Gaming extends Item{
	
	//Constructors
	public Gaming(){ super(); }
	
	public Gaming(String id, String model, int year, String cons, double price, int q){
		super(id, model, year, cons, price, q);
		setPrice(0.9 * getPrice());
	}
	
	//Constructor with Gaming specs
	public Gaming(String id, String model, int year, String cons, double price, int q, String type, String cpu, String gpu, String sound, double hd){
		super(id, model, year, cons, price, q);
		setPrice(0.9 * getPrice());
		this.type = type;
		CPU = cpu;
		GPU = gpu;
		this.sound = sound;
		HD = hd;
	}
	
	//Set methods
	public void setType(String type){
		this.type = type;
	}
	
	public void setCPU(String cpu){
		CPU = cpu;
	}
	
	public void setGPU(String gpu){
		GPU = gpu;
	}
	
	public void setSound(String sound){
		this.sound = sound;
	}
	
	public void setHD(double hd){
		HD = hd;
	}
	
	//Get methods
	public String getType(){
		return type;
	}
	
	public String getCPU(){
		return CPU;
	}
	
	public String getGPU(){
		return GPU;
	}
	
	public String getSound(){
		return sound;
	}
	
	public double getHD(){
		return HD;
	}
	
	//toString method
	public String toString(){
		return super.toString() + "\nType: " + getType() + "\nCPU: " + getCPU() +
		"\nGPU: " + getGPU() + "\nSound: " + getSound() + "\nHard Disk: " + getHD() +
		"\n______________";	
	}
	
	private String type, CPU, GPU, sound;
	private double HD;

}
