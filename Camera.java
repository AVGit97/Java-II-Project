/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

public class Camera extends PicAndSound{
	
	//Constructors
	public Camera(){ super(); }
	
	public Camera(String id, String model, int year, String constructor, double price, int q){
		super(id, model, year, constructor, price, q);
	}
	
	//Constructor with Camera specs
	public Camera(String id, String model, int year, String constructor, double price, int q, String type, double megapix, double opticalZ, double digitalZ, double size){
		super(id, model, year, constructor, price, q, type);
		megaPixel = megapix;
		opticalZoom = opticalZ;
		digitalZoom = digitalZ;
		screenSize = size;
	}
	
	//Set methods
	public void setMegapix(double megapix){
		megaPixel = megapix;
	}
	
	public void setOpticalZ(double opticalZoom){
		this.opticalZoom = opticalZoom;
	}
	
	public void setDigitalZ(double digitalZoom){
		this.digitalZoom = digitalZoom;
	}
	
	public void setScreenSize(double Size){
		screenSize = Size;
	}
	
	//Get methods
	public double getMegaPix(){
		return megaPixel;
	}
	
	public double getOpticalZoom(){
		return opticalZoom;
	}
	
	public double getDigitalZoom(){
		return digitalZoom;
	}
	
	public double getScreenSize(){
		return screenSize;
	}
	
	//toString method
	public String toString(){
		return super.toString() + "\nMegapixel: " + getMegaPix() + "\nOptical Zoom: " + getOpticalZoom() +
		"\nDigital Zoom: " + getDigitalZoom() + "\nScreen Size: " + getScreenSize() +
		"\n______________";
	}

	private double megaPixel, opticalZoom, digitalZoom, screenSize;
	
}
