package websaler_main_ui;

import javafx.beans.property.SimpleStringProperty;

public class OrderTable { 
	private final SimpleStringProperty id;
	private final SimpleStringProperty hotelname;
	private final SimpleStringProperty intime;
	private final SimpleStringProperty outtime;
	private final SimpleStringProperty lasttime;
	private final SimpleStringProperty totel;
	private final SimpleStringProperty state;
	
	public OrderTable(String id,String hotelname,String outtime,String intime,String lasttime,String totel,String state){
		this.id=new SimpleStringProperty(id);
		this.hotelname=new SimpleStringProperty(hotelname);
		this.outtime=new SimpleStringProperty(outtime);
		this.intime=new SimpleStringProperty(intime);
		this.lasttime=new SimpleStringProperty(lasttime);
		this.totel=new SimpleStringProperty(totel);
		this.state=new SimpleStringProperty(state);
	}
	
	public String getOrderID(){
		return id.get();
	}
	
	public SimpleStringProperty idProperty(){
		return id;
	}
	
	public SimpleStringProperty hotelnameProperty(){
		return hotelname;
	}
	
	public SimpleStringProperty  inttimeProperty(){
		return intime;
	}
	
	public SimpleStringProperty  outimeProperty(){
		return outtime;
	}
	
	public SimpleStringProperty  lasttimeProperty(){
		return lasttime;
	}
	
	public SimpleStringProperty  totelProperty(){
		return totel;
	}
	
	public SimpleStringProperty  stateProperty(){
		return state;
	}
}
