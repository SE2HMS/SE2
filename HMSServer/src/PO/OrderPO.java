package PO;

public class OrderPO {
	public enum Type{
		normal,abnormal,revoke;
	}
	private String id,user,hotel,room;
	private int number;
	private Type type;
	
	public OrderPO(String id,String user,String hotel,String room,int num,Type t){
		super();
		this.id=id;
		this.user=user;
		this.room=room;
		this.hotel=hotel;
		this.number=num;
		this.type=t;
	}
	
	public String getID(){
		return id;
	}
	
	public String getUser(){
		return user;
	}
	
	public String getHotel(){
		return hotel;
	}
	
	public String getRoom(){
		return room;
	}
	
	public int getNum(){
		return number;
	}
	
	public Type getType(){
		return type;
	}
	
	public void setType(Type t){
		this.type=t;
	}
}