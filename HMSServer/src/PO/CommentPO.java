package PO;

import java.io.Serializable;

public class CommentPO implements Serializable{
	private String hotelname,detials;
	private double commentLevel;

	public CommentPO(String hotelname,String detials,double commentLevel){
		this.hotelname=hotelname;
		this.detials=detials;
		this.commentLevel = commentLevel;
	}
	
	public String getHotelName(){
		return this.hotelname;
	}
	
	public String getDetials(){
		return this.detials;
	}

	public double getCommentLevel() {
		return commentLevel;
	}
}
