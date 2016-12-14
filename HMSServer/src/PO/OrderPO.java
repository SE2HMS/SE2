package PO;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderPO implements Serializable{
	private String id,userid,username,usercontact,hotel,intime,outtime,lasttime;
	private ArrayList<String> room;
	private ArrayList<Integer> num;
	private ArrayList<Double> price,subtotel;
	private double totel;
	private String type;
	private boolean children;
	private int personNum;
	
	public OrderPO(String id,String userid,String hotel,String username,
			String usercontact,String t,String intime,String outtime,String lasttime,double totel,boolean children,int personNum){
		super();
		this.id=id;
		this.username=username;
		this.hotel=hotel;
		this.usercontact=usercontact;
		this.userid=userid;
		this.type=t;
		this.intime=intime;
		this.outtime=outtime;
		this.lasttime=lasttime;
		this.totel=totel;
		this.children = children;
		this.personNum = personNum;
	}
	
	public String getID(){
		return id;
	}
	
	public String getUserName(){
		return username;
	}
	
	public String getUserID(){
		return this.userid;
	}
	
	public String getUserContact(){
		return this.usercontact;
	}
	
	public ArrayList<Double> getPrice(){
		return this.price;
	}
	
	public ArrayList<Double> getSubtotel(){
		return this.subtotel;
	}
	
	public double getTotel(){
		return this.totel;
	}
	
	public String getHotel(){
		return hotel;
	}
	
	public ArrayList<String> getRoom(){
		return room;
	}
	
	public ArrayList<Integer> getNum(){
		return num;
	}
	
	public String getType(){
		return type;
	}
	
	public boolean getChildren() {
		return children;
	}

	public int getPersonNum() {
		return personNum;
	}

	public void setType(String t){
		this.type=t;
	}
	
	public void setInTime(String t){
		this.intime=t;
	}
	
	public String getInTime(){
		return this.intime;
	}
	
	public String getOutTime(){
		return this.outtime;
	}
	
	public void setOutTime(String t){
		this.outtime=t;
	}
	
	public void setLastTime(String t){
		this.lasttime=t;
	}
	
	public String getLastTime(){
		return this.lasttime ;
	}
	
	public void setRoom(ArrayList<String> room){
		this.room=room;
	}
	
	public void setNum(ArrayList<Integer> num){
		this.num=num;
	}
	
	public void setPrice(ArrayList<Double> price){
		this.price=price;
	}
	
	public void setSubtotel(ArrayList<Double> subtotel){
		this.subtotel=subtotel;
	}
}