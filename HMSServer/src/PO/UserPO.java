package PO;

import java.io.Serializable;
import java.util.ArrayList;


public class UserPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3996305783368778968L;
	private String id,name,contactInof,specialInfo;
	private int creditTol,vipLev,isLogin;
	private String password;
	private ArrayList<CreditPO> credits;
	private String type;
	
	public UserPO(String id,String password,String contactInfo,String name,String specialInfo,
			int creditTol,int vipLev,int isLogin,String type){
		super();
		this.id=id;
		this.password=password;
		this.contactInof=contactInfo;
		this.name=name;
		this.creditTol=creditTol;
		this.vipLev=vipLev;
		this.type=type;
		this.specialInfo=specialInfo;
		this.isLogin=isLogin;
		//to be continue...
	}

	public void setSpecialInfo(String info) {
		this.specialInfo = info;
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
	
	public void setContactInfo(String s){
		this.contactInof=s;
	}
	
	public String getContactInfo(){
		return this.contactInof;
	}
	
	public void setName(String s){
		this.name=s;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setCreditTol(int i){
		this.creditTol=i;
	}
	
	public int getCreditTol(){
		return this.creditTol;
	}
	
	public void setVipLev(int i){
		this.vipLev=i;
	}
	
	public int getVipLev(){
		return this.vipLev;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String getSpecialInfo(){
		return this.specialInfo;
	}
	
	public ArrayList<CreditPO> getCredit(){
		return this.credits;
	}
	
	public void setIsLogin(int i){
		this.isLogin=i;
	}
	
	public int getIsLogin(){
		return this.isLogin;
	}
	
	public void setCredit(ArrayList<CreditPO> c){
		this.credits=c;
	}
}
