package datahelper;

public class SubOrder {
	private String orderid,roomtype;
	private int num;
	private double price,subtotel;
	
	public SubOrder(String orderid,String roomtype,int num,double price,double subtotel){
		this.orderid=orderid;
		this.roomtype=roomtype;
		this.num=num;
		this.price=price;
		this.subtotel=subtotel;
	}
	
	public String getOrderID(){
		return this.orderid;
	}
	
	public String getRoomType(){
		return this.roomtype;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public double getSubTotel(){
		return this.subtotel;
	}
}
