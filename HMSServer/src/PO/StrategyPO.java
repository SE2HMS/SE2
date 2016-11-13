package PO;

public class StrategyPO {
	public enum type{
		hotel,vip;
	}
	private String name;
	private double totel,discount;
	private String date;
	private type t;
	
	public StrategyPO(String name,String date,double totel,double discount,type t){
		super();
		this.name=name;
		this.date=date;
		this.discount=discount;
		this.totel=totel;
		this.t=t;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public double getTotel(){
		return totel;
	}
	
	public void setTotel(double totel){
		this.totel=totel;
	}
	
	public double getDiscount(){
		return discount;
	}
	
	public void setDiscount(double discount){
		this.discount=discount;
	}
	
	public String getDate(){
		return date;
	}
	
	public void setDate(String date){
		this.date=date;
	}
	
	public type getType(){
		return t;
	}
}
