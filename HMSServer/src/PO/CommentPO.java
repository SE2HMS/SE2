package PO;

public class CommentPO {
	private String hotelname,detials;
	
	public CommentPO(String hotelname,String detials){
		this.hotelname=hotelname;
		this.detials=detials;
	}
	
	public String getHotelName(){
		return this.hotelname;
	}
	
	public String getDetials(){
		return this.detials;
	}
}
