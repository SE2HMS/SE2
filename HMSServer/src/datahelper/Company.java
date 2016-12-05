package datahelper;

public class Company {
private String hotelname,companies;
	
	public Company(String hotelname,String companies){
		this.hotelname=hotelname;
		this.companies=companies;
	}
	
	public String getHotelName(){
		return this.hotelname;
	}
	
	public String getCompanies(){
		return this.companies;
	}
}
