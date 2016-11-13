package PO;

import java.util.ArrayList;

public class UserPO {
	private String id;
	private String password;
	private ArrayList<String> hotelList;
	
	public UserPO(String id,String password){
		super();
		this.id=id;
		this.password=password;
		hotelList=new ArrayList<String>();
	}
	
	public String getID(){
		return id;
	}
	
	public void setPassword(String p){
		this.password=p;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void addHote(String name){
		hotelList.add(name);
	}
}
