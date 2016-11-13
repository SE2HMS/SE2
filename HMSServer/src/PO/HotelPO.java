package PO;

import java.util.ArrayList;

public class HotelPO {
	private String name,businesscircle,introduce;
	private ArrayList comment;
	
	public HotelPO(String name,String bc,String intro,ArrayList comment){
		super();
		this.name=name;
		this.businesscircle=bc;
		this.introduce=intro;
		this.comment=comment;
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
	
	public ArrayList getComment(){
		return comment;
	}
	
	public void setComment(ArrayList c){
		this.comment=c;
	}
}