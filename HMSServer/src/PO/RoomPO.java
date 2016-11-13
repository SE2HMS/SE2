package PO;

public class RoomPO {
	private String hotelname,type;
	private int number;
	
	public RoomPO(String hn,String t,int num){
		super();
		this.hotelname=hn;
		this.type=t;
		this.number=num;
	}
	
	public String getHn(){
		return hotelname;
	}
	
	public void setHn(String hn){
		this.hotelname=hn;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String t){
		this.type=t;
	}
	
	public int getNum(){
		return number;
	}
	
	public void setNum(int num){
		this.number=num;
	}
}