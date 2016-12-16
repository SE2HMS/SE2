package login_ui;

import javafx.beans.property.SimpleStringProperty;

public class RoomTable {
	private final SimpleStringProperty roomtype;  
    private final SimpleStringProperty price;  
    private final SimpleStringProperty number;
    private final SimpleStringProperty ordernum;
    private final SimpleStringProperty subtotel;
    
    public RoomTable(String roomtype,String price,String number,String subtotel,String ordernum){
    	this.roomtype=new SimpleStringProperty(roomtype);
    	this.price=new SimpleStringProperty(price);
    	this.number=new SimpleStringProperty(number);
    	this.subtotel=new SimpleStringProperty(subtotel);
    	this.ordernum=new SimpleStringProperty(ordernum);
    }
    
    public void setNumber(String num){
    	this.number.set(num);
    }
    
    public void setOrderNum(String num){
    	this.ordernum.set(num);
    }
    
    public int getOrderNum(){
    	return Integer.valueOf(ordernum.get());
    }
    
    public void setSubtotel(String s){
    	this.subtotel.set(s);
    }
    
    public SimpleStringProperty roomtypeProperty(){
    	return roomtype;
    }
    
    public SimpleStringProperty priceProperty(){
    	return price;
    }
    
    public SimpleStringProperty numberProperty(){
    	return number;
    }
    
    public SimpleStringProperty subtotelProperty(){
    	return subtotel;
    }
    
    public SimpleStringProperty ordernumProperty(){
    	return ordernum;
    }
}
