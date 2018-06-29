/*	ANGELOS VANAKARIS			p3150006
	KONSTANTINOS KOUMPANAKIS	p3150200	*/

import java.util.*;

public class TV extends PicAndSound{
	
	//Constructors
	public TV(){ super(); }
	
	public TV(String id, String model, int year, String constructor, double price, int q){
		super(id, model, year, constructor, price, q);
	}
	
	public TV(String id, String model, int year, String constructor, double price, int q, String type){
		super(id, model, year, constructor, price, q, type);
	}
	
	//Constructor with TV specs
	public TV(String id, String model, int year, String constructor, double price, int q, String type, String res, double dim, String ports){
		super(id, model, year, constructor, price, q, type);
		this.res = res;
		dimension = dim;
		this.ports = ports;
	}
	
	//Set methods
	public void setResolution(String res){
		this.res = res;
	}
	
	public void setDim(int dim){
		dimension = dim;
	}
	
	public void setPorts(String ports){
		this.ports = ports;
	}
	
	//Get methods
	public String getResolution(){
		return res;
	}
	
	public double getDimension(){
		return dimension;
	}
	
	public String getPorts(){
		return ports;
	}
	
	public String toString(){
		return super.toString() + "\nDimension: " + getDimension() + "\nResolution: " + getResolution() + "\nPorts: " + getPorts() +
		"\n______________";
	}
	
	private double dimension;
	private String ports, res;
	
}
