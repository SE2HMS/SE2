package login_ui;

import javafx.beans.property.SimpleStringProperty;

public class WebStrategyTable { 
	private final SimpleStringProperty type;
	private final SimpleStringProperty name;

	public WebStrategyTable(String type,String name){
		this.type=new SimpleStringProperty(type);
		this.name=new SimpleStringProperty(name);

	}
	
	public String getWebStrategyType(){
		return type.get();
	}
	
	public SimpleStringProperty typeProperty(){
		return type;
	}
	
	public SimpleStringProperty hotelnameProperty(){
		return name;
	}
	

}
