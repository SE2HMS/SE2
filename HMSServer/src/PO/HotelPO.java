package PO;

import java.util.ArrayList;

public class HotelPO {
	private String name,businesscircle,introduce,address;
	private double stars;
	private ArrayList<String> comment,companies;
	private ArrayList<RoomPO> rooms;
	private ArrayList<HotelStrategyPO> strategy;
	
	public HotelPO(String name,String bc,String intro,String address,double stars){
		super();
		this.name=name;
		this.businesscircle=bc;
		this.introduce=intro;
		this.address=address;
		this.stars=stars;
		//to be continue...
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getBC(){
		return businesscircle;
	}
	
	public void setBC(String bc){
		this.businesscircle=bc;
	}
	
	public String getINTRO(){
		return introduce;
	}
	
	public void setINTRO(String intro){
		this.introduce=intro;
	}
	
	public void setAddress(String s){
		this.address=s;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public void setStars(double d){
		this.stars=d;
	}
	
	public double getStars(){
		return this.stars;
	}
	
	public ArrayList<String> getComment(){
		return comment;
	}
	
	
	public ArrayList<String> getCompanies(){
		return this.companies;
	}
	
	public ArrayList<RoomPO> getRoom(){
		return this.rooms;
	}
	
	public ArrayList<HotelStrategyPO> getStrategy(){
		return this.strategy;
	}
	
	public void setComment(ArrayList<String> c){
		
	}
	
	public void setCompanies(ArrayList<String> c){
		
	}
	
	public void setRomm(ArrayList<RoomPO> r){
		
	}
	
	public void setStrategy(ArrayList<HotelStrategyPO> s){
		
	}
}