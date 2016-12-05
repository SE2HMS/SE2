package PO;
public class HotelStrategyPO {
	private String hotelname,strategyname,specialInfo;
	double discount;
	String type;
	
	public HotelStrategyPO(String hotelname,String strategyname,String specialInfo,double discount,
			String t){
		this.hotelname=hotelname;
		this.strategyname=strategyname;
		this.specialInfo=specialInfo;
		this.discount=discount;
		this.type=t;
	}
	
	public String getHotelName(){
		return this.hotelname;
	}
	
	public String getStrategyName(){
		return this.strategyname;
	}
	
	public String getSpecialInof(){
		return this.specialInfo;
	}
	
	public double getDiscount(){
		return this.discount;
	}
	
	public String getType(){
		return this.type;
	}
	
	public void setSpecialInfo(String s){
		this.specialInfo=s;
	}
	
	public void setDiscount(double d){
		this.discount=d;
	}
}
