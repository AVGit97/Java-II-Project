/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

import java.util.*;

public class BR_DVD extends PicAndSound{
	
	//Constructors
	public BR_DVD(){ super(); }
	
	public BR_DVD(String id, String model, int year, String constructor, double price, int q){
		super(id, model, year, constructor, price, q);
	}
	
	public BR_DVD(String id, String model, int year, String constructor, double price, int q, String type){
		super(id, model, year, constructor, price, q, type);
	}
	
	//Constructor with BR_DVD specs
	public BR_DVD(String id, String model, int year, String constructor, double price, int q, String type, String res, String format){
		super(id, model, year, constructor, price, q, type);
		this.res = res;
		this.format = format;
	}
	
	//Set methods
	public void setResolution(String res){
		this.res = res;
	}
	
	public void setFormat(String format){
		this.format = format;
	}
	
	//Get methods
	public String getResolution(){
		return res;
	}
	
	public String getFormat(){
		return format;
	}
	
	//toString method
	public String toString(){
		return super.toString() + "\nResolution: " + getResolution() + "\nFormat: " + getFormat() +
		"\n______________";
	}
	
	private String format, res;
	
}
