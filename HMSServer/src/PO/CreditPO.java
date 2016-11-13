package PO;

import java.util.ArrayList;

public class CreditPO {
	private String id;
	private ArrayList list;
	private double totel;
	
	public CreditPO(String id,ArrayList list,double totel){
		super();
		this.id=id;
		this.list=list;
		this.totel=totel;
	}
	
	public String getID(){
		return id;
	}
	
	public ArrayList getList(){
		return list;
	}
	
	public void setList(ArrayList list){
		this.list=list;
	}
	
	public double getTotel(){
		return totel;
	}
	
	public void setTotel(double totel){
		this.totel=totel;
	}
}