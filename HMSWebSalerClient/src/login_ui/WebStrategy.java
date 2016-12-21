package login_ui;

import javafx.beans.property.SimpleStringProperty;

public class WebStrategy { 
	private final SimpleStringProperty type;
	private final SimpleStringProperty name;

	public WebStrategy(String type,String name){
		this.type=new SimpleStringProperty(type);
		this.name=new SimpleStringProperty(name);

	}
	
	public String getWebStrategyType(){
		return type.get();
	}
	public String getName(){
		return name.get();
	}
	public SimpleStringProperty typeProperty(){
		return type;
	}
	
	public SimpleStringProperty nameProperty(){
		return name;
	}
	

}
